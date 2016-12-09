package com.demo.chat.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Participant repository.
 * 
 * @author mpeguero
 *
 */
public interface MessageRepository extends JpaRepository<Message, Long> {
	
	
}
