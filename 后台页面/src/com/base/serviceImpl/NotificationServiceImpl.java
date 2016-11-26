package com.base.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.base.daoImpl.NotificationDaoImpl;
import com.base.po.Message;
import com.base.po.Notification;
import com.base.service.NotificationService;

@Service("notificationService")
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private NotificationDaoImpl notificationDaoImpl;
	
	
	@Override
	public void setNotification(String insertSql) {
		// TODO Auto-generated method stub
		notificationDaoImpl.setNotification(insertSql);
		return;
	}


	@Override
	public Notification getNotificationInfo() {
		// TODO Auto-generated method stub
		
		return notificationDaoImpl.getNotificationInfo();
	}


	@Override
	public void addMessage(String insertSql) {
		// TODO Auto-generated method stub
		notificationDaoImpl.setNotification(insertSql);
		return;
	}


	@Override
	public List<Message> getMessageInfos(String userid) {
		// TODO Auto-generated method stub
		return notificationDaoImpl.getMessageInfos(userid);
	}

	

}
