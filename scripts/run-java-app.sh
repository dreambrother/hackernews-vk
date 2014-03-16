#!/bin/sh

MEMORY_PARAMS="-Xmx128m"
java $MEMORY_PARAMS -cp "lib/*" com.github.dreambrother.hackernews.HackernewsVkApp >> log/process.log 2>&1 &
