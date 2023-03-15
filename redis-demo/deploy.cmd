#!/bin/bash

HOST=root@master.mwang.online
scp Dockerfile %HOST%:build/app.jar
scp -r target\*.jar root@master.mwang.online:build/app.jar
ssh %HOST% build/build.sh demo-app2
