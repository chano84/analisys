#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
ASSET_DIR="$ROOT_DIR/media/email-demo"
OUTPUT_DIR="$ASSET_DIR/output"
TMP_DIR="$OUTPUT_DIR/tmp"
CHROME_BIN="${CHROME_BIN:-}"

if [[ -z "$CHROME_BIN" ]]; then
  if command -v google-chrome >/dev/null 2>&1; then
    CHROME_BIN="$(command -v google-chrome)"
  elif command -v chromium >/dev/null 2>&1; then
    CHROME_BIN="$(command -v chromium)"
  elif command -v chromium-browser >/dev/null 2>&1; then
    CHROME_BIN="$(command -v chromium-browser)"
  else
    echo "No se encontro Chrome/Chromium en el sistema." >&2
    exit 1
  fi
fi

if ! command -v ffmpeg >/dev/null 2>&1; then
  echo "No se encontro ffmpeg en el sistema." >&2
  exit 1
fi

mkdir -p "$TMP_DIR"
rm -f "$TMP_DIR"/*.png "$TMP_DIR"/*.mp4 "$OUTPUT_DIR"/email-demo.mp4

STATES=("desktop" "inbox" "open" "focus")
SIZE="1366,768"
BASE_URL="$(python3 - <<'PY'
from pathlib import Path
print(Path("/workspace/media/email-demo/storyboard.html").resolve().as_uri())
PY
)"

for state in "${STATES[@]}"; do
  "$CHROME_BIN" \
    --headless=new \
    --disable-gpu \
    --hide-scrollbars \
    --window-size="$SIZE" \
    --screenshot="$TMP_DIR/$state.png" \
    "${BASE_URL}?state=${state}" >/dev/null 2>&1
done

ffmpeg -y -loop 1 -i "$TMP_DIR/desktop.png" -t 1.4 -vf "fps=30,format=yuv420p" "$TMP_DIR/desktop.mp4" >/dev/null 2>&1
ffmpeg -y -loop 1 -i "$TMP_DIR/inbox.png" -t 1.8 -vf "fps=30,format=yuv420p" "$TMP_DIR/inbox.mp4" >/dev/null 2>&1
ffmpeg -y -loop 1 -i "$TMP_DIR/open.png" -t 1.8 -vf "fps=30,format=yuv420p" "$TMP_DIR/open.mp4" >/dev/null 2>&1
ffmpeg -y -loop 1 -i "$TMP_DIR/focus.png" -t 2.4 -vf "fps=30,format=yuv420p" "$TMP_DIR/focus.mp4" >/dev/null 2>&1

ffmpeg -y \
  -i "$TMP_DIR/desktop.mp4" \
  -i "$TMP_DIR/inbox.mp4" \
  -i "$TMP_DIR/open.mp4" \
  -i "$TMP_DIR/focus.mp4" \
  -filter_complex "\
[0:v][1:v]xfade=transition=fade:duration=0.35:offset=1.05[v1]; \
[v1][2:v]xfade=transition=fade:duration=0.35:offset=2.50[v2]; \
[v2][3:v]xfade=transition=fade:duration=0.35:offset=3.95, \
format=yuv420p[v]" \
  -map "[v]" \
  "$OUTPUT_DIR/email-demo.mp4" >/dev/null 2>&1

printf 'Video generado en %s\n' "$OUTPUT_DIR/email-demo.mp4"
