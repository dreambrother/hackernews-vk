#!/bin/bash

echo "Build project"
./scripts/make.sh
echo "Remove old app lib"
ssh hackernewsvk 'rm -rf app/lib'
echo "Copy app to server"
scp -r target/app hackernewsvk:
echo "Connect to remote server"
ssh hackernewsvk << 'endsession'
cd app
./restart.sh +wd
endsession
