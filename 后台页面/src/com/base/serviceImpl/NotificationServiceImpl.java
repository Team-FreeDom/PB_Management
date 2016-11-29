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
	public List getNotificationInfo(String currentPage,String itemsPerPage) {
		// TODO Auto-generated method stub
		
		return notificationDaoImpl.getNotificationInfo(currentPage,itemsPerPage);
	}


	@Override
	public void addMessage(String title,String content,String department) {
		// TODO Auto-generated method stub
		notificationDaoImpl.addMessage(title,content,department);
		return;
	}


	@Override
	public List getMessageInfos(String userid,String currentPage,String itemsPerPage) {
		// TODO Auto-generated method stub
		return notificationDaoImpl.getMessageInfos(userid,currentPage,itemsPerPage);
	}


	@Override
	public List<Message> getMessageTop5Infos(String userid) {
		// TODO Auto-generated method stub
		return notificationDaoImpl.getMessageTop5Infos(userid);
	}


	@Override
	public int getNoreadMessageCount(String userid) {
		// TODO Auto-generated method stub
		return notificationDaoImpl.getNoreadMessageCount(userid);
	}


	@Override
	public void setReadMessage(int id) {
		// TODO Auto-generated method stub
		notificationDaoImpl.setReadMessage(id);
		return;
	}


	@Override
	public Notification getNotification(String id) {
		// TODO Auto-generated method stub
		return notificationDaoImpl.getNotification(id);
	}


	@Override
	public List<Notification> getNotificationTop5Infos() {
		// TODO Auto-generated method stub
		return notificationDaoImpl.getNotificationTop5Infos();
	}

	
	

}
