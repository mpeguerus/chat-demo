package com.demo.chat.event;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.messaging.support.NativeMessageHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import com.demo.chat.domain.Participant;
import com.demo.chat.service.MessageService;

import lombok.extern.log4j.Log4j;

/**
 * Session Connected Event Listener.
 * 
 * @author mpeguero
 *
 */
@Log4j
@Component
public class SessionConnectedEventListener implements ApplicationListener<SessionConnectedEvent> {
	private final MessageService messageService;
	
	@Autowired
	public SessionConnectedEventListener(MessageService messageService) {
		this.messageService = messageService;
	}

	@Override
	public void onApplicationEvent(SessionConnectedEvent event) {
		// Get session id and connection timestamp
		StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
		String sessionId = sha.getSessionId();
		Long timestamp = sha.getTimestamp();
		
		// getting username from native header.
		MessageHeaderAccessor accessor = NativeMessageHeaderAccessor.getAccessor(event.getMessage(), SimpMessageHeaderAccessor.class);
		accessor.getMessageHeaders();
		GenericMessage<?> generic = (GenericMessage<?>) accessor.getHeader("simpConnectMessage");
		@SuppressWarnings("unchecked")
		Map<String, List<String>> nativeheaders = (Map<String, List<String>>) generic.getHeaders().get("nativeHeaders");
		String username = nativeheaders.get("username").get(0);
		
		// Save connected user
		Participant participant = this.messageService.addParticipant(sessionId, username, timestamp);
		
		log.info("Session Connected Event. " + participant);
	}

}
