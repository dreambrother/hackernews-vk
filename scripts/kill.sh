#!/bin/bash

getAppPid() {
    appPid=$(jps | grep HackernewsVkApp | awk '{print $1}')    
}

getWdPid() {
    wdPid=$(pgrep watchdog.sh)
}

killSafely() {
    pid=$1
    if [ -n "$pid" ]; then
        echo "Process is running, pid=$pid, try to terminate"
        kill $pid
        dead=false
    else
        echo "Process is dead"
        dead=true
    fi
}

if [ -n "$1" ]; then 
    echo "Kill watchdog too"
    for i in $(seq 1 5); do
        echo "Attempt $i to terminate watchdog"
        getWdPid
        killSafely $wdPid
        if $dead; then
            break
        fi
        echo "Sleep for 5s"
        sleep 5s
    done

    if ! $dead; then
        echo "Watchdog cannot be terminated safely, kill it"
        kill -9 $wdPid
    fi
fi

for i in $(seq 1 5); do
    echo "Attemt $i to terminate app"
    getAppPid
    killSafely $appPid
    if $dead; then
        break
    fi
    echo "Sleep for 5s"
    sleep 5s
done

if ! $dead; then
    echo "App cannot be terminated safely, kill it"
    kill -9 $appPid
fi

