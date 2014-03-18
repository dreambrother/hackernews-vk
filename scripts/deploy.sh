#!/bin/bash

echo "Build project"
./scripts/make.sh
echo "Copy app to server"
scp -r target/app hackernewsvk:
echo "Connect to remote server"
ssh hackernewsvk << 'endsession'
cd app
./run.sh
endsession