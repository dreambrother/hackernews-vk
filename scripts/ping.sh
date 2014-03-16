#!/bin/bash

for i in $(seq 1 10); do
    echo "Attempt $i to ping running process"
    if curl localhost:10000 &> /dev/null; then
        echo "Application is running"		
        exit 0
    fi
    echo "Application still not started. Sleep for 1s and try again"
    sleep 1s
done

echo "Application was not started"
exit 1
