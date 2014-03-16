#!/bin/bash

mvn clean install
mkdir target/app
mkdir target/app/log
mkdir target/app/data
cp scripts/* target/app
cp -r target/lib target/app
