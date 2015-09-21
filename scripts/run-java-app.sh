#!/bin/bash

MEMORY_PARAMS="-Xmx128m"
java $MEMORY_PARAMS  -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/home/nik/app/log/ -XX:OnOutOfMemoryError="kill -9 %p" \
 -cp "lib/*"  com.github.dreambrother.hackernews.HackernewsVkApp >> log/process.log 2>&1 &
