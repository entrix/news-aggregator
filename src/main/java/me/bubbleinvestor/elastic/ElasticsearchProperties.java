package me.bubbleinvestor.elastic;

import org.apache.http.HttpHost;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "elasticsearch")
public class ElasticsearchProperties {

    private List<String> hosts;
    private int connectTimeout;
    private int connectionRequestTimeout;
    private int socketTimeout;
    private int maxRetryTimeoutMillis;

    HttpHost[] hosts() {
        return hosts
                .stream()
                .map(HttpHost::create)
                .toArray(HttpHost[]::new);
    }

    public List<String> getHosts() {
        return hosts;
    }

    public void setHosts(List<String> hosts) {
        this.hosts = hosts;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getConnectionRequestTimeout() {
        return connectionRequestTimeout;
    }

    public void setConnectionRequestTimeout(int connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public int getMaxRetryTimeoutMillis() {
        return maxRetryTimeoutMillis;
    }

    public void setMaxRetryTimeoutMillis(int maxRetryTimeoutMillis) {
        this.maxRetryTimeoutMillis = maxRetryTimeoutMillis;
    }
}
