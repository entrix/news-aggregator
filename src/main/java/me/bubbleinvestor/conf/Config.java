package me.bubbleinvestor.conf;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.http.HttpHost;

@Configuration
public class Config {

    @Bean
    ObjectMapper objectMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return objectMapper;
    }

    @Bean
    RestHighLevelClient restHighLevelClient() {
        return new RestHighLevelClient(
                RestClient
                        .builder(new HttpHost("elasticsearch", 9200))
                        .setRequestConfigCallback(config -> config
                                .setConnectTimeout(5_000)
                                .setConnectionRequestTimeout(5_000)
                                .setSocketTimeout(5_000)
                        )
                        .setMaxRetryTimeoutMillis(5_000));
    }
}
