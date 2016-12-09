package com.demo.chat.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Incomming Message Model Definition.
 * 
 * @author mpeguero
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(includeFieldNames=true)
public class IncomingMessage {
	private String from;
	private String text;
}
