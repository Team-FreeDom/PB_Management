package com.base.po;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Notification {

	@Id
	private int id;//通知表id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	private String message;//通知内容
	
	
}
