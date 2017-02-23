package com.base.po;

import javax.persistence.Entity;
import javax.persistence.Id;


public class MessageShow {

	
	private int id;//消息表id
	private String title;
	private String content;
	private String time;
	private int isRead; //是否已经阅读消息，0为未阅读，1为已经阅读
	private String userid;
	private int sn;//用于界面显示的消息序号
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getIsRead() {
		return isRead;
	}
	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getSn() {
		return sn;
	}
	public void setSn(int sn) {
		this.sn = sn;
	}
	
}
