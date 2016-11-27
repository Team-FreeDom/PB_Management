package com.base.utils;

import java.util.Calendar;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MessageUtils {
	
	public static String getTitle(int i){
		
		String str="";
		if(i==1){   //�û������ύ
			
			str="�������������ύ";
			
		}else if(i==2){  //����Ա���"ͬ������"
			
			str="����������˳ɹ�";
			
		}else if(i==3){  //����Ա���"�ܾ�����"
			
			str="�����������ʧ��";
			
		}else if(i==4){  //���ѹ���
			
			str="�������޽ɷ��У�����ʧ��";
			
		}else if(i==5){//����Ա���"ȡ������"
			
			str="�������޽ɷ��У����˻�";
			
		}else if(i==6){  //����Ա���"���ѳɹ�"
			
			str="�ɷѳɹ�";
			
		}else if(i==7){  //�û��Լ�����
			str="���볷��";
		}	
		
		return str;
	}
	
	
	public static String getContent(int i,String bname){

		String content="";
		
         if(i==1){   //�û������ύ
			
			content=bname+"���������ύ�ɹ�";
			
		}else if(i==2){  //����Ա���"ͬ������"
			
			content=bname+"���ͨ��";
			
		}else if(i==3){  //����Ա���"�ܾ�����"
			
			content=bname+"����Ա�˻��������";
			
		}else if(i==4){  //���ѹ���
			
			content=bname+"�ɷѹ���";
			
		}else if(i==5){//����Ա���"ȡ������"
			
			content=bname+"������Ա�˻�";
			
		}else if(i==6){  //����Ա���"���ѳɹ�"
			
			content=bname+"�������޳ɹ�";
			
		}else if(i==7){
			content=bname+"�������������ѳ���";
			}	
         
         return content;
      }
	
	public static String getInsertStr(String infoStr,int tag){
		
	  String insertStr="";
      Calendar c = Calendar.getInstance();//���Զ�ÿ��ʱ���򵥶��޸�
		
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