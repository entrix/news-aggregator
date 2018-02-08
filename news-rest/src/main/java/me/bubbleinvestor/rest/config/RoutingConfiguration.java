package me.bubbleinvestor.rest.config;

import me.bubbleinvestor.rest.handler.EchoHandler;
import me.bubbleinvestor.rest.handler.NewsHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class RoutingConfiguration {
    @Bean
    public RouterFunction<ServerResponse> routes(EchoHandler echoHandler) {
        return route(GET("/test"), serverRequest -> ok().body(fromObject("helloworld")))
                .andRoute(POST("/echo"), echoHandler::echo);

    }

    @Bean
    public RouterFunction<ServerResponse> newsRoutes(NewsHandler handler) {
        return route(GET("/news/{id}").and(accept(APPLICATION_JSON)), handler::getNews)
                .andRoute(POST("/news/save").and(accept(APPLICATION_JSON)), handler::saveNews);
    }
}
