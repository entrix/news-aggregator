package me.bubbleinvestor.rest.repository;

import me.bubbleinvestor.model.NewsEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

public interface NewsRepository {
    Mono<Void> save(NewsEntity news);
    Mono<NewsEntity> update(NewsEntity news);
    Mono<NewsEntity> findOne(String id);
    Mono<Boolean> delete(String id);
    Flux<NewsEntity> findByState(String state) throws IOException;
}
