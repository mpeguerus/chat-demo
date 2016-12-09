package com.demo.chat.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.chat.domain.Message;
import com.demo.chat.domain.Participant;
import com.demo.chat.model.IncomingMessage;
import com.demo.chat.service.MessageService;

import lombok.extern.log4j.Log4j;

/**
 * Message Controller
 * 
 * @author mpeguero
 *
 *
 */
@Log4j
@RestController
public class MessageController {
	private final SimpMessagingTemplate template;
	private final MessageService service;
	
	@Autowired
	public MessageController(SimpMessagingTemplate template, MessageService service) {
		this.template = template;
		this.service = service;
	}


	/**
	 * Message Listener.
	 * 
	 * @param message
	 * @throws Exception
	 */
	@MessageMapping("/chat")
	public void send(IncomingMessage message, SimpMessageHeaderAccessor headerAccessor) throws Exception {
		log.info("Incomming Message: "+message);
		Message m = this.service.addMessageBySessionId(headerAccessor.getSessionId(), message.getText());
		log.info("Sending Message: "+m);
		this.template.convertAndSend("/topic/messages", m);
	}
	
	
	
	@RequestMapping(value="/participants", method=RequestMethod.GET)
	public List<Participant> getParticipants() {
		return service.getParticipants();
	}
}
