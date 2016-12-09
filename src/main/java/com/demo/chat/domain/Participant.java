package com.demo.chat.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * Participant Entity.
 * 
 * @author mpeguero
 *
 */
@Data
@Builder
@ToString(includeFieldNames=true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="PARTICIPANT")
public class Participant {
	@Id
	@Column(name="SESSION_ID")
	private String sessionId;
	
	private String username;
	private Long timestamp;
}
