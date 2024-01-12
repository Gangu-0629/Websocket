package com.springBoot.Websocket.Controller;

import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.SimpleMessageConverter;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import com.springBoot.Websocket.Model.Message;
import com.springBoot.Websocket.Model.Notification;

@Controller
@CrossOrigin("*")
public class WebscoketController 
{

	@Autowired
	private SimpMessagingTemplate smc;
	@MessageMapping("/public")//Anyone sends to messae using /ws/app/public
	@SendTo("/chat/public")//Client has to subscribe to this "/chatroom/public"
	private Message publicchatting(@Payload Message message) {
		System.out.println("Receieved");
		return message;
	}
	
	@MessageMapping("/privatechat")
	private Message privateChatting(@Payload Message message) {
		smc.convertAndSendToUser(message.getReceiver(), "/private", message); //listened at "/user/{username}/private"
		return message;
	}
	
	@MessageMapping("/sendnotification")
	private Notification NofifyPlayer(@Payload Notification notify) {
		smc.convertAndSendToUser(notify.getReceiver(), "/notification", notify); //listened at "/user/{receiverusername}/notification
		return notify;
	}
	
	@MessageMapping("/sendAccepetance")
	private Notification Acceptance(@Payload Notification notify) {
		smc.convertAndSendToUser(notify.getReceiver(), "/accepted", notify);
		//listened at "/user/{receiverusername}/accepted
		System.out.println("user/"+notify.getReceiver()+"/accepted");
		return notify;
	}
}
