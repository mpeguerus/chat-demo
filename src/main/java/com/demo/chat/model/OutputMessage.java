package com.demo.chat.model;

/**
 * Outgoing Message Model
 * 
 * @author mpeguero
 *
 */
public class OutputMessage {
	private String from;
    private String text;
    private String time;
    
	public OutputMessage(String from, String text, String time) {
		this.from = from;
		this.text = text;
		this.time = time;
	}

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}
	
	
    
    
}
