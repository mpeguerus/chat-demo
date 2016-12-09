package com.demo.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.chat.domain.Message;
import com.demo.chat.domain.MessageRepository;
import com.demo.chat.domain.Participant;
import com.demo.chat.domain.ParticipantRepository;

@Service
public class MessageService {
	private final ParticipantRepository participantRepository;
	private final MessageRepository messageRepository;
	
	@Autowired
	public MessageService(ParticipantRepository participantRepository, MessageRepository messageRepository) {
		this.participantRepository = participantRepository;
		this.messageRepository = messageRepository;
	}
	
	/*----------------------*/
	/*     PARTICIPANTS     */
	/*----------------------*/
	public Participant addParticipant(String sessionId, String username, Long timestamp) {
		return this.participantRepository.save(
				Participant.builder()
				           .sessionId(sessionId)
				           .username(username)
				           .timestamp(timestamp)
				           .build()
		);
	}
	
	public void removeParticipant(String sessionId) {
		this.participantRepository.delete(sessionId);
	}
	
	public List<Participant> getParticipants() {
		return this.participantRepository.findAll();
	}
	
	/*----------------------*/
	/*       MESSAGES       */
	/*----------------------*/
	
	public Message addMessageBySessionId(String sessionId, String text) {
		Participant from = this.participantRepository.findOne(sessionId);
		
		return this.messageRepository.save(
				Message.builder()
				       .from(from)
				       .text(text)
				       .build()
				);
	}
	
	
	
}
