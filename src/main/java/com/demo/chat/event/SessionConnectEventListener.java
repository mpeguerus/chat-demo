package com.demo.chat.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;

/**
 * Session Connect Event listener.
 * 
 * @author mpeguero
 *
 */
@Component
public class SessionConnectEventListener implements ApplicationListener<SessionConnectEvent>{

	@Override
	public void onApplicationEvent(SessionConnectEvent event) {
		// Do nothing yet!!
	}

}
