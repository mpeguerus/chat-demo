package com.demo.chat.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

/**
 * Session Subscribe Event Listener.
 * 
 * @author mpeguero
 *
 */
@Component
public class SessionSubscribeEventListener implements ApplicationListener<SessionSubscribeEvent> {

	@Override
	public void onApplicationEvent(SessionSubscribeEvent event) {
		// Do nothing yet.
	}

}
