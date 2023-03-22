#!/bin/bash

APP=$1
Version=$2
Registry=registry-vpc.cn-shanghai.aliyuncs.com

cd build

docker build -t $APP:$Version -f Dockerfile .

docker login --username=limingwang06 --password=Dknhfre1st $Registry

docker tag $APP:$Version $Registry/mwangli/$APP:$Version

docker push $Registry/mwangli/$APP:$Version

echo "psuh success"
kubectl set image sts/$APP $APP=$Registry/mwangli/$APP:$Version --record

echo "deploy start"