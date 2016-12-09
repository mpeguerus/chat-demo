package com.demo.chat.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.demo.chat.service.MessageService;

import lombok.extern.log4j.Log4j;

/**
 * Session Disconnect Event Listener
 * 
 * @author mpeguero
 *
 */
@Log4j
@Component
public class SessionDisconnectEventListener implements ApplicationListener<SessionDisconnectEvent> {
private final MessageService service;
	
	public SessionDisconnectEventListener(MessageService service) {
		this.service = service;
	}

	@Override
	public void onApplicationEvent(SessionDisconnectEvent event) {
		log.info("Session Disconnect Event. SessionId="+event.getSessionId());
		// Remove participant for repository.
		service.removeParticipant(event.getSessionId());
	}

}
