#!/bin/bash

MEMORY_PARAMS="-Xmx128m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/home/nik/app/log/ -XX:OnOutOfMemoryError='kill -9 %p'"
java $MEMORY_PARAMS -cp "lib/*" com.github.dreambrother.hackernews.HackernewsVkApp >> log/process.log 2>&1 &
