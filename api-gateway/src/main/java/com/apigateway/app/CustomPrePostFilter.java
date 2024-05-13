package com.apigateway.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Configuration
public class CustomPrePostFilter implements GlobalFilter {

	Logger log = LoggerFactory.getLogger(getClass());
	
	
	// This is to run only the pre filter. To do post filter we need to add .then() as shown in bellow filter method

//	@Override
//	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//		ServerHttpRequest req = exchange.getRequest();
//		log.info("Authorization = "+req.getHeaders().getFirst("Authorization"));
//		return chain.filter(exchange);
//	}
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest req = exchange.getRequest();
		log.info("Authorization = "+req.getHeaders().getFirst("Authorization"));
		return chain.filter(exchange).then(Mono.fromRunnable(() -> {
			ServerHttpResponse res = exchange.getResponse();
			log.info("Post Filter = "+res.getStatusCode());
		}));
	}
}
