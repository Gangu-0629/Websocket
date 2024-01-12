package com.springBoot.Websocket.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer 
{
	/*First register the endpoint
	 * 1.addEndpoint("__") this is connect to websocket when request "http://localhost:8000/(this_endpoint)
	 * 2. SetallowedOrigin like the cors we can allow specific origin or all by "*"
	 * 3.withSockjs()
	*/
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) 
	{
	
		System.out.println("Connected");
		registry.addEndpoint("ws").setAllowedOrigins("http://localhost:3000").withSockJS();
		System.out.println("Connected-finished");
	}
	
	
	/* Second Configure the messeage borker
	 * 1. setApplicationDestinationPrefixs with client should have this prefix to send any thing to server like 
	 *    if it "/app" then client can send to "/app/____"
	 * 2. enableSimpleBroker this is enable the topics 
	 * 3. setUserDesination this is for the specific users subscribes
	 * 
	 * */

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.setApplicationDestinationPrefixes("/app");
		registry.enableSimpleBroker("/chat","/user");
		registry.setUserDestinationPrefix("/user");
	}

	
	

}

