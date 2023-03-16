#!/bin/bash

set HOST=root@47.103.8.172
scp -r Dockerfile %HOST%:build/Dockerfile
scp -r target\*.jar %HOST%:build/app.jar
ssh %HOST% build/build.sh demo-app
