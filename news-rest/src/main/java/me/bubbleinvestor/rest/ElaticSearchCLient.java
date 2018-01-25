package me.bubbleinvestor.rest;

import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Component;

@Component
public class ElaticSearchCLient {

    private ElasticsearchTemplate template;

    @Autowired
    public ElaticSearchCLient(ElasticsearchTemplate template) {
        this.template = template;
    }

    public String getStatus() {
        return template.getClient().admin().cluster().health(new ClusterHealthRequest()).actionGet().getStatus().name();
    }
}
