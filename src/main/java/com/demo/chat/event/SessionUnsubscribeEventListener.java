package com.demo.chat.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

/**
 * Session Unsubscribe Event Listener.
 * 
 * @author mpeguero
 *
 */
@Component
public class SessionUnsubscribeEventListener implements ApplicationListener<SessionUnsubscribeEvent> {

	@Override
	public void onApplicationEvent(SessionUnsubscribeEvent event) {
		// Do nothing yet.
	}

}
