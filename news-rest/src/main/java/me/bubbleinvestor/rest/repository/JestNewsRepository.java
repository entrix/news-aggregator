package me.bubbleinvestor.rest.repository;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import me.bubbleinvestor.model.NewsEntity;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

@Repository
public class JestNewsRepository implements NewsRepository {

    private JestClient jestClient;

    @Autowired
    public JestNewsRepository(JestClient jestClient) {
        this.jestClient = jestClient;
    }

    @Override
    public Mono<Void> save(NewsEntity news) {
        Index index = new Index.Builder(news).index("media").type("news").build();
        JestResult elasticResult = null;
        try {
            elasticResult = jestClient.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Mono<NewsEntity> result = null;
        if (elasticResult.isSucceeded()) {
            result = Mono.just(news);
        }
        return result.then();
    }

    @Override
    public Mono<NewsEntity> update(NewsEntity news) {
        return null;
    }

    @Override
    public Mono<NewsEntity> findOne(String id) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("id", id));

        Search search = new Search.Builder(searchSourceBuilder.toString())
                // multiple index or types can be added.
                .addIndex("media")
                .addType("news")
                .build();

        SearchResult result = null;
        try {
            result = jestClient.execute(search);
        } catch (IOException e) {
            e.printStackTrace();
        }
        NewsEntity searchResult = result.getSourceAsObject(NewsEntity.class, true);
        return Mono.just(searchResult);
    }

    @Override
    public Mono<Boolean> delete(String id) {
        return null;
    }

    @Override
    public Flux<NewsEntity> findByState(String state) throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("name", state));
        Search search = new Search.Builder(searchSourceBuilder.toString())
                // multiple index or types can be added.
                .addIndex("media")
                .addType("news")
                .build();

        SearchResult result = jestClient.execute(search);
        List<NewsEntity> news = result.getSourceAsObjectList(NewsEntity.class, true);
        return Flux.fromIterable(news);
    }
}
