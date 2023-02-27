package com.mytests.spring.springSecurityWebflux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;


@Configuration
public class NestedRoutes {

    @Bean
    public RouterFunction<ServerResponse> routeNestedSimple() {
        return RouterFunctions.nest(path("/case_zero"),
                route(GET("/a_route"),
                        req -> ok().body(fromValue("aaa")))
                        .andRoute(GET("/b_route"),
                                req -> ok().body(fromValue("bbb")))
        );
    }


    @Bean
    public RouterFunction<ServerResponse> routeNested2() {
        return route(GET("/case_first"), req -> ok().body(fromValue("aaaa")))
                .andNest(path("/case_second"),
                        route(GET("/c_route"),
                                req -> ok().body(fromValue("cccc")))
                                .andRoute(GET("/d_route"),
                                        req -> ok().body(fromValue("dddd"))))
                ;
    }

    @Bean
    public RouterFunction<ServerResponse> nestedPath() {
        return route()
                .path("/case_third", () ->
                        route()
                                .GET("/nested_path",
                                        req -> ok().body(fromValue("nested .path(..) passed"))).build()).build()

                ;
    }

    @Bean
    public RouterFunction<ServerResponse> routeComposedPredicate() {
        return route(method(HttpMethod.GET).and(path("/composedPredicate")),
                this::processComposedPredicates);
    }

    private Mono<ServerResponse> processComposedPredicates(ServerRequest serverRequest) {
        return ServerResponse.ok().body(fromValue("composed predicate"));
    }


}
