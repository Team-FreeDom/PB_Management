package com.base.service;

import java.util.List;

import com.base.po.Message;
import com.base.po.Notification;


public interface NotificationService {

	
	 public void setNotification(String insertSql);
	 
	 public void addMessage(String title,String content,String department);
	
	 public List getNotificationInfo(String currentPage,String itemsPerPage); 
	 
	 public List getMessageInfos(String userid,String currentPage,String itemsPerPage);
	 
	 public List<Message> getMessageTop5Infos(String userid);
	 
	 public int getNoreadMessageCount(String userid);
	 
	 public void setReadMessage(int id);
}