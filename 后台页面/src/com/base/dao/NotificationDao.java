package com.base.dao;

import java.util.List;

import com.base.po.Message;
import com.base.po.Notification;

public interface NotificationDao {

	public void setNotification(String insertSql);
		
	public List getNotificationInfo(String currentPage,String itemsPerPage);
	
	public void addMessage(String title,String content,String department);
	 
	 public List getMessageInfos(String userid,String currentPage,String itemsPerPage);
	 
	 public List<Message> getMessageTop5Infos(String userid);
	 
	 public List<Notification> getNotificationTop5Infos();
	 
	 public int getNoreadMessageCount(String userid);
	 
	 public void setReadMessage(int id);
	 
	 public Notification getNotification(String id);
}

