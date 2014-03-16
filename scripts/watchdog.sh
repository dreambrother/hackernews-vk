#!/bin/sh

trap 'echo "Exit"; exit 0' 2 3 15

while true; do
    echo "Ping process"
    if ! curl localhost:10000 &> /dev/null; then
        echo "Process not responding, send email and restart"
        ./restart.sh
    fi
    sleep 10s
done
