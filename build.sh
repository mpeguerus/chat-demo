#!/bin/sh


jarfile=chat-demo-1.0.0.jar
cp target/${jarfile} .
echo $jarfile
jarfileversion=`echo ${jarfile##*-} | sed 's/\.jar//g'`

echo "chat-demo:${jarfileversion}"

docker build -t mpeguerus/chat-demo:${jarfileversion} .
