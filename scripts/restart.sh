#!/bin/bash

getPid() {
    pid=$(jps | grep HackernewsVkApp | awk '{print $1}')	
}

startApp() {
    ./run-java-app.sh
    ./ping.sh
}

killApp() {
    getPid
    if [ -n "$pid" ]; then
        echo "Process is running, pid=$pid, try to terminate"
        kill -15 $pid
        dead=false
    else
        echo "Process is dead"
        dead=true
    fi
}

for i in $(seq 1 15); do
    echo "Attemt $i to terminate app"
    killApp
    if $dead; then
        startApp
        exit 0
    fi
    echo "Sleep for 5s"
    sleep 5s
done

echo "Process cannot be terminated safely, kill it"
getPid
kill -9 $pid
startApp
