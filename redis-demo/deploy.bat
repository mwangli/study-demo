#!/bin/bash

set HOST=root@master.mwang.online
scp -r target\*.jar %HOST%:build/app.jar
ssh %HOST% build/build.sh demo-app2
