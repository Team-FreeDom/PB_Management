package com.base.service;

import java.util.List;

import com.base.po.Message;
import com.base.po.Notification;

//发布公告和消息的逻辑层
public interface NotificationService {

	/*
	   参数说明：insertSql,字符串,为要插入的sql字符串
	   返回值：    无返回值
	   函数功能：发布公告
	 */	
	 public void setNotification(String insertSql);
	 
	/*
	   参数说明：title，为标题;content,为消息的内容;department,为标志值
	   返回值：    无返回值
	   函数功能：发布消息
	 */	
	 public void addMessage(String title,String content,String department);
	
	/*
	   参数说明：currentPage，当前页;itemsPerPage,为每页的条数
	   返回值：    List,为公告记录的集合
	   函数功能：获得当前页的公告记录
	 */	
	 public List getNotificationInfo(String currentPage,String itemsPerPage); 
	 
	/*
	   参数说明：userid,为用户编号;currentPage，当前页;itemsPerPage,为每页的条数
	   返回值：    List,为公告记录的集合
	   函数功能：获得某用户当前页的消息记录
	 */
	 public List getMessageInfos(String userid,String currentPage,String itemsPerPage);
	 
	/*
	   参数说明：userid,为用户编号;
	   返回值：    List,为消息记录的集合
	   函数功能：获得某用户前五条消息
	 */
	 public List<Message> getMessageTop5Infos(String userid);
	 
	/*
	   参数说明：无参数值
	   返回值：    List,为公告记录的集合
	   函数功能：获得前五条公告
	 */
	 public List<Notification> getNotificationTop5Infos();
	 
	/*
	   参数说明：userid,为用户编号;
	   返回值：    int型
	   函数功能：获得某用户未读消息的条数
	 */
	 public int getNoreadMessageCount(String userid);
	 
	/*
	   参数说明：id,为消息记录编号;
	   返回值：    无返回值
	   函数功能：将某条信息设置为已读
	 */
	 public void setReadMessage(int id);
	 
	/*
	   参数说明：id,为公告记录编号;
	   返回值：    Notification,为公告信息对象
	   函数功能：获得公告详情
	 */
	 public Notification getNotification(String id);
}