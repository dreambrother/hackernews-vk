#!/bin/sh

echo "Build project"
./make.sh
echo "Copy app to server"
scp -r target/app hackernewsvk:
echo "Connect to remote server"
ssh hackernewsvk
cd app
./run.sh