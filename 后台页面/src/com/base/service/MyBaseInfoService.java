package com.base.service;

import com.base.po.MyBaseList;

//我的实习基地申请逻辑层
public interface MyBaseInfoService {
  
	/*
	  参数说明：year,整型，为当前年份;status,整型，为标志值;userid,字符串型，为用户编号;
	          pageindex,为当前页数;size,为每页的条数; columnName,排序列;
              orderDir,为排序的顺序;      
	 返回值：   MyBaseList,该对象包括实习基地申请数据记录+记录条数
	 函数功能：获取该用户当年的特定状态的实习基地申请数据
	  */
   public MyBaseList MybaseInfo(int pageindex,int size,String columnName,
		String orderDir,int year,int status,String userid);
  
   /*
	  参数说明：year,整型，为当前年份;status,整型，为标志值;userid,字符串型，为用户编号;
	          pageindex,为当前页数;size,为每页的条数; order,排序列;
              orderDir,为排序的顺序;      
	 返回值：   MyBaseList,该对象包括实习基地申请数据记录+记录条数
	 函数功能：获取该用户当年的所有的实习基地申请数据
	  */
   public MyBaseList MybaseInfo2(int pageindex,int  size,int order,
		String orderDir,int year,int status,String userid);
   
   /*
	  参数说明：id,为实习申请记录的编号
              infoStr,字符串型，为要发送的消息   
              tag,为标识，2代表待审核，18代表续期中
	 返回值：   无返回值
	 函数功能：撤回实习基地申请
	*/
   public int recall(String id,String infostr,int tag);  
   
   /*
	  参数说明：id,为实习申请记录的编号
              adddate，为续期的月数
	 返回值：   无返回值
	 函数功能：续期
	*/
   public void updateDate(int id,String adddate);

}
