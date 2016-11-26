package com.base.dao;

import java.util.List;

import com.base.po.BaseInfo;
import com.base.po.UserInfo;


public interface UserInfoDao {
	
	 /*
	   ����˵����id,���ͣ���ʾ�û����
	   ����ֵ��  �޷���ֵ
	   �������ܣ����û���Ϣ����ɾ��һ����Ϣ
	 */
     public void delUser(int id);
     
     /*
	   ����˵����ui,�����û���¼����UserInfo
	   ����ֵ��  �޷���ֵ
	   �������ܣ����û���Ϣ��������һ����Ϣ
	 */
   public void doUser(UserInfo ui);
   
     /*
           ����˵����ui,�����û���¼����UserInfo
          ����ֵ��  �޷���ֵ
          �������ܣ������û���Ϣ���е�һ����Ϣ
     */
     public void updateUser(UserInfo ui);
   
     /*
          ����˵�����޲�
          ����ֵ��  List<UserInfo>���û���Ϣ��¼�ļ���
          �������ܣ���ȡ���е��û���Ϣ
     */
     public List<UserInfo> getUserInfos();
     
     /*
             ����˵����userright,���ͣ������û���Ȩ��ֵ
            ����ֵ��  List<UserInfo>���û���Ϣ��¼�ļ���
            �������ܣ���ȡ�����û�Ȩ��ֵΪuserright���û���Ϣ
     */
     public List<UserInfo> getUserInfos(int userright);
     
     /*
                 ����˵����sex,�ַ�����Ϊ�Ա�
                 ����ֵ��  List<UserInfo>���û���Ϣ��¼�ļ���
                  �������ܣ�����������ȡ�û���Ϣ
        */
      public List<UserInfo> getUserInfos(String sex);
      
      /*
              ����˵����id�����ͣ�ΪԱ�����
             ����ֵ��  List<UserInfo>���û���Ϣ��¼�ļ���
             �������ܣ�����������ȡ�û���Ϣ
      */
      public List<UserInfo> getUserInfo(int id);
      
      /*
                 ����˵����sex,�ַ�����Ϊ�Ա�
                  userright,���ͣ������û���Ȩ��ֵ
                ����ֵ��  List<UserInfo>���û���Ϣ��¼�ļ���
                �������ܣ�����������ȡ�û���Ϣ
      */
     public List<UserInfo> getUserInfo(int userright,String sex);

       /*
                ����˵����userright,���ͣ������û���Ȩ��ֵ
                  id�����ͣ�ΪԱ�����
               ����ֵ��  List<UserInfo>���û���Ϣ��¼�ļ���
               �������ܣ�����������ȡ�û���Ϣ
       */
     public List<UserInfo> getUserInfo(int userright,int id);
     
       /*
                ����˵����sex,�ַ�����Ϊ�Ա�
                  id�����ͣ�ΪԱ�����
                ����ֵ��  List<UserInfo>���û���Ϣ��¼�ļ���
                �������ܣ�����������ȡ�û���Ϣ
       */
     public List<UserInfo> getUserInfos(String sex,int id);
     
       /*
                ����˵����sex,�ַ�����Ϊ�Ա�
                 id�����ͣ�ΪԱ�����
                 userright,���ͣ������û���Ȩ��ֵ
                ����ֵ��  List<UserInfo>���û���Ϣ��¼�ļ���
                �������ܣ�����������ȡ�û���Ϣ
       */
     public List<UserInfo> getUserInfo(int userright,String sex,int id);
      
 
     public boolean login1(String id,String pwd);
     
     
     public long login(String id,String pwd);
     
     /**
      * �޸ĸ�����Ϣ
      * @param userinfo
      */
     public void updateuser(String id,String name,String telephone,String password,String img);
     /**
      * ��ȡ������Ϣ
      * @param id �û�id
      * @return �û���Ϣ
      */
     public List<UserInfo> getInfoPerson(String id);
   
     

}
