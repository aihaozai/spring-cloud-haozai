# spring-cloud-haozai
微服务  腾讯DevOps部署
#docker配置参数
export SKYWALKING_IP_PORT=host:port  
docker-compose -f xxx.yml config
#elasticsearch
docker run -p 9200:9200 -p 9300:9300 --name elasticsearch \
-e "discovery.type=single-node" \
-e "ES_JAVA_OPTS=-Xms1024m -Xmx1024m" \
-e "TAKE_FILE_OWNERSHIP=true" \
-v /data/es/logs:/usr/share/elasticsearch/logs \
-v /data/es/data:/usr/share/elasticsearch/data \
-d elasticsearch:7.14.2