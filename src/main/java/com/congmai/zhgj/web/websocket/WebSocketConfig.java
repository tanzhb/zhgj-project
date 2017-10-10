package com.congmai.zhgj.web.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer{

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(systemWebSocketHandler(), "/webSocketServer").addInterceptors(new MyHandshakeInterceptor());
		registry.addHandler(systemWebSocketHandler(), "/sockjs/webSocketServer").addInterceptors(new MyHandshakeInterceptor()).withSockJS();
		
	}
	@Bean
	public WebSocketHandler systemWebSocketHandler(){
		return new SystemWebSocketHandler();
	}
}
