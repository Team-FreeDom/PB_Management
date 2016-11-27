package com.base.dao;

import java.util.List;

import com.base.po.Message;
import com.base.po.Notification;

public interface NotificationDao {

	public void setNotification(String insertSql);
		
	public Notification getNotificationInfo();
	
	public void addMessage(String title,String content,String department);
	 
	 public List<Message> getMessageInfos(String userid);
	 
	 public List<Message> getMessageTop5Infos(String userid);
	 
	 public int getNoreadMessageCount(String userid);
	 
	 public void setReadMessage(int id);
}
