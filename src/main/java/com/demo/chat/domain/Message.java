package com.demo.chat.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Message Entity.
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
@Table(name="MESSAGE")
@EntityListeners({AuditingEntityListener.class})
public class Message {
	@Id
	@SequenceGenerator(name="MESSAGE_SEQ_GENERATOR", sequenceName="MESSAGE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="MESSAGE_SEQ_GENERATOR")
	@Column(name="ID")
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="SESSION_ID")
	private Participant from;
	
	private String text;
	@CreatedDate
	private Date sentDate;
}
