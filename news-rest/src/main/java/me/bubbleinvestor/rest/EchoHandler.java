package me.bubbleinvestor.rest;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Created by SBT-Volkov-AV on 24.01.2018.
 */
@Component
class EchoHandler {

    public Mono<ServerResponse> echo(ServerRequest request) {
        return ServerResponse.ok().body(org.springframework.web.reactive.function.BodyInserters.fromObject("helloworld"));
    }
}