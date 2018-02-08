package me.bubbleinvestor.rest.service;

import me.bubbleinvestor.model.NewsEntity;
import me.bubbleinvestor.model.Result;
import reactor.core.publisher.Mono;

public interface NewsService {
    Mono<Result> save(Mono<NewsEntity> newsMono);

    Mono<NewsEntity> update(NewsEntity news);

    Mono<NewsEntity> findOne(String id);

    Mono<Boolean> delete(String id);
}
