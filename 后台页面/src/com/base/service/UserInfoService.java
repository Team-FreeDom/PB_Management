package com.base.service;

import java.util.List;

import com.base.po.UserInfo;

public interface UserInfoService {
	
	 /*
	   ����˵����id,�ַ������飬��ʾ�û���ŵ�����
	   ����ֵ��  �޷���ֵ
	   �������ܣ�ɾ��һ�������û���Ϣ
	 */
     public void delUser(String[] delid);
  
     /*
	   ����˵����ui,�����û���¼����UserInfo
	   ����ֵ��  �޷���ֵ
	   �������ܣ�����һ���û���Ϣ
	 */
    public void doUser(UserInfo ui);

     /*
             ����˵����ui,�����û���¼����UserInfo
            ����ֵ��  �޷���ֵ
            �������ܣ������û���Ϣ
    */
     public void updateUser(UserInfo ui);

    /*
            ����˵�����޲�
           ����ֵ��  List<UserInfo>���û���Ϣ��¼UserInfo����ļ���
          �������ܣ���ʾ���е��û���Ϣ
      */
    public List<UserInfo> getUserInfos();
    
    /* ����˵����userright,���ͣ������û���Ȩ��ֵ
                sex,�ַ�����Ϊ�Ա�
                id�����ͣ�ΪԱ�����
               ����ֵ��  List<UserInfo>���û���Ϣ��¼UserInfo����ļ���
               �������ܣ�����������ʾ���е��û���Ϣ
                 ע��         3�����������ϲ�ѯ��������������Ҳ���ܴ���û��ֵ�����
	                     ����ڴ���Ҫ�����жϲ����Ƿ���ֵ�����ݽ������dao��ķ���
	                     �ڴ˹涨�����ϲ㴫����userright��idֵ��Ϊ0����������������ֵ
    */
    public List<UserInfo> getUserInfos(int userright,String sex,int id);
/**
     * �޸ĸ�����Ϣ
     * @param userinfo
     */
    public void update(String id,String name,String telephone,String password,String img);
    /**
     * ��ȡ������Ϣ
     * @param id �û�id
     * @return �û���Ϣ
     */
    public List<UserInfo> getInfoPerson(String id);
    
 

}
