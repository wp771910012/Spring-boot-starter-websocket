package com.wisely.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.wisely.domain.WiselyMessage;
import com.wisely.domain.WiselyResponse;

@Controller
public class WsController {
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@MessageMapping("/welcome")
	@SendTo("/topic/getResponse")
	public WiselyResponse say(WiselyMessage message) throws InterruptedException {
		Thread.sleep(3000);
		return new WiselyResponse("Welcome,"+message.getName());
	}
	
	@MessageMapping("/chat")
	public void handleChat(Principal principal,String msg){
		if(principal.getName().equals("wyf")){
			messagingTemplate.convertAndSendToUser("wisely", "/queue/notifications", principal.getName()+"-send:"+msg);
		}else{
			messagingTemplate.convertAndSendToUser("wyf", "/queue/notifications", principal.getName()+"-send:"+msg);
		}
	}
}
