#cmd# cd /home/;mkdir data
#tar# /home/data/crawler.tar.gz#div#/home/data
#tar# /home/data/analyser.tar.gz#div#/home/data
#cmd# /home/data/crawler/tools/init.sh
#wait# 30000
#scp# /home/data/start.sh#div#/home/data
#scp# /home/data/mime.types#div#/etc
#cmd# cd /home/data/;chmod 777 ./start.sh;./start.sh