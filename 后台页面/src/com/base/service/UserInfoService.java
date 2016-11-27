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
    /**
     * 
     * @param pageindex ��ǰҳ��
     * @param size      ��ǰ��ʾ������¼
     * @return
     * @throws SQLException
     */
    public MangerList manger(int pageindex,int size,String searchValue);
    /**
     * 
     * @param id �û�id
     * @return �û�������Ϣ
     * @throws SQLException
     */
    public List<Manger> Mangerdetail(String id);
    /**
     * ɾ����Ա������Ϣ
     * @param str Ϊ��Աid���ַ���
     * @throws SQLException
     */
    public void deleteInfo(String str);
    /**
     * �޸��û���Ϣ
     * @param id
     * @param name
     * @param sex
     * @param birthdate
     * @param category
     * @param attritube
     * @param telephone
     * @param idcard
     * @param password
     * @throws SQLException
     */
    public void upInfo(String id,String name,String sex,String birthdate,String category,String attritube,String dept,String telephone,String idcard,String password);
  /**
   * ���ż���
   * @return
  */
    public List<ApplyDept> getDepts();
    /**
     * Admin����Ա���Լ���
     * @return
     */
    public List<Admin> getAttritube();
    /**
     * �����û�
     * @param id
     * @param name
     * @param sex
     * @param birthdate
     * @param category
     * @param attritube
     * @param dept
     * @param telephone
     * @param idcard
     * @param password
     */
    public int addInfo(String id,String name,String sex,String birthdate,String category,String attritube,String dept,String telephone,String idcard,String password);
     /**
      * ������Ա��Ϣ
      * @return
      */
     public List<Manger> exportPersonInfo(String dept);
 

}
