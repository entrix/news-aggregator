# Version: 0.0.1
FROM docker.elastic.co/elasticsearch/elasticsearch:6.1.2
MAINTAINER entrix, mrzo0m
ENV discovery.type single-node

# Define mountable directories.
VOLUME ["/data"]

# Mount elasticsearch.yml config
ADD config/elasticsearch.yml /elasticsearch/config/elasticsearch.yml

# Define working directory.
WORKDIR /data

# Define user
USER elasticsearch

# Define default command.
CMD ["elasticsearch"]

# Expose ports.
#   - 9200: HTTP
#   - 9300: transport
EXPOSE 9200
EXPOSE 9300