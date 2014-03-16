#!/bin/bash

./run-java-app.sh

if ./ping.sh; then
    echo "Start watchdog"
    ./watchdog.sh >> log/watchdog.log 2>&1 &
fi
