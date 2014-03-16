#!/bin/bash

mvn clean install
mkdir target/app
mkdir target/app/log
cp scripts/* target/app
cp -r target/lib target/app
