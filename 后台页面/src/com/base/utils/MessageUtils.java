package com.base.utils;

import java.util.Calendar;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MessageUtils {
	
	public static String getTitle(int i){
		
		String str="";
		if(i==1){   //用户租赁提交
			
			str="土地租赁申请提交";
			
		}else if(i==2){  //管理员点击"同意申请"
			
			str="土地租赁审核成功";
			
		}else if(i==3){  //管理员点击"拒绝申请"
			
			str="土地租赁审核失败";
			
		}else if(i==4){  //交费过期
			
			str="土地租赁缴费中，租赁失败";
			
		}else if(i==5){//管理员点击"取消交费"
			
			str="土地租赁缴费中，被退回";
			
		}else if(i==6){  //管理员点击"交费成功"
			
			str="缴费成功";
			
		}else if(i==7){  //用户自己撤回
			str="申请撤回";
		}	
		
		return str;
	}
	
	
	public static String getContent(int i,String bname){

		String content="";
		
         if(i==1){   //用户租赁提交
			
			content=bname+"租赁申请提交成功";
			
		}else if(i==2){  //管理员点击"同意申请"
			
			content=bname+"审核通过";
			
		}else if(i==3){  //管理员点击"拒绝申请"
			
			content=bname+"管理员退回你的申请";
			
		}else if(i==4){  //交费过期
			
			content=bname+"缴费过期";
			
		}else if(i==5){//管理员点击"取消交费"
			
			content=bname+"被管理员退回";
			
		}else if(i==6){  //管理员点击"交费成功"
			
			content=bname+"土地租赁成功";
			
		}else if(i==7){
			content=bname+"土地租赁申请已撤回";
			}	
         
         return content;
      }
	
	public static String getInsertStr(String infoStr,int tag){
		
	  String insertStr="";
      Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
		
    	int year = c.get(Calendar.YEAR);  
    	int month=c.get(Calendar.MONTH);
        int date=c.get(Calendar.DATE);
    	String dateStr=""+year+"-"+month+"-"+date;
    	String msg="";
    	
		JSONArray obj = JSONArray.fromObject(infoStr);
		for (int i = 0; i < obj.size(); i++) {
			JSONObject temp = obj.getJSONObject(i);
			msg=temp.getString("msg");			
			insertStr=insertStr+"('"+MessageUtils.getTitle(tag)+"','"+MessageUtils.getContent(tag,msg)+"','"+dateStr+"',"+0+",'"+temp.getString("userid")+"'";
			if(i==obj.size()-1){
				insertStr+=")";
			}else{
				insertStr+="),";
			}
			
		}
		insertStr="insert into baseweb.message(title,content,time,isRead,userid) values"+insertStr;
		System.out.println(insertStr);
		return insertStr;
	}
	
}