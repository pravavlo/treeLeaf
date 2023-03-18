package com.company.tree.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@EnableAutoConfiguration
@EnableDiscoveryClient
public class ApiGatewayConfiguration {

    @Value("${microserviceAuth.url}")
    private String microserviceAuth;

    @Value("${microserviceBlog.url}")
    private String microserviceBlog;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/api/auth/**")
                        .uri(microserviceAuth))
                .route(r -> r.path("/blog-posts/**")
                        .uri(microserviceBlog))
                .build();
    }

}
