package me.bubbleinvestor.rest.handler;

import me.bubbleinvestor.model.NewsEntity;
import me.bubbleinvestor.rest.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Component
public class NewsHandler {
    private final NewsService newsService;

    @Autowired
    public NewsHandler(NewsService newsService) {
        this.newsService = newsService;
    }

    public Mono<ServerResponse> getNews(ServerRequest request) {
        String newsId = request.pathVariable("id");
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Mono<NewsEntity> newsMono = null;
        newsMono = this.newsService.findOne(newsId);
        return newsMono
                .flatMap(news -> ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(news)))
                .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> saveNews(ServerRequest request) {
        Mono<NewsEntity> newsMono = request.bodyToMono(NewsEntity.class);
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(this.newsService.save(newsMono)));
    }
}
