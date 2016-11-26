package com.base.service;

import java.util.List;

import com.base.po.Message;
import com.base.po.Notification;


public interface NotificationService {

	
	 public void setNotification(String insertSql);
	 
	 public void addMessage(String insertSql);
	
	 public Notification getNotificationInfo(); 
	 
	 public List<Message> getMessageInfos(String userid);
	 
	 public List<Message> getMessageTop5Infos(String userid);
	 
	 public int getNoreadMessageCount(String userid);
	 
	 public void setReadMessage(int id);
}