package com.mytests.spring.springSecurityWebflux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * *
 * <p>Created by irina on 2/6/2023.</p>
 * <p>Project: spring-security-webflux</p>
 * *
 */
@Configuration
public class RouterForSecretsAndBars {

    @Bean
    public RouterFunction<ServerResponse> secretPath1() {
        return route(GET("/secret/path1"),
                req -> ok().body(fromValue("secret path1 ")));
    }
    @Bean
    public RouterFunction<ServerResponse> barWithPathVar() {
        return route(GET("/bar/{pathvar}"),
                req -> ok().body(fromValue("bar with " + req.pathVariable("pathvar"))));
    }
}
