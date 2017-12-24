#cmd# cd /home/;mkdir data;cd data;mkdir lts_analyzer
#jdk# http://120.52.72.24/download.oracle.com/c3pr90ntc0td/otn-pub/java/jdk/7u79-b15/jdk-7u79-linux-x64.tar.gz#div#/home/data#div#jdk7u79.tar.gz#div#jdk1.7.0_79
#wait# 10000
#cmd# cd /home/data/lts_analyzer;mkdir bin;mkdir lib;
#scp# /home/data/lts_analyzer/analyzer-tasktracker-0.0.1-SNAPSHOT.jar#div#/home/data/lts_analyzer/lib
#scp# /home/data/lts_analyzer/tasktrack.sh#div#/home/data/lts_analyzer/bin
#cmd# cd /home/data/lts_analyzer/bin;chmod 777 ./tasktrack.sh;./tasktrack.sh start