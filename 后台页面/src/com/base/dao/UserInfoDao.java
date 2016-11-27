package com.base.dao;

import java.util.List;

import com.base.po.BaseInfo;
import com.base.po.UserInfo;


import com.base.po.Admin;
import com.base.po.ApplyDept;

import com.base.po.Manger;
import com.base.po.MangerList;



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
    /**
	     * �û�����
	     * @param pageindex ��ǰҳ��
	     * @param size      ��ǰ��ʾ������¼
	     * @return
	     * @throws SQLException
	     */
  public MangerList manger(int pageindex, int size,String searchValue);
  /**
   * 
   * @param id �û�id
   * @return �û�������Ϣ
   * @throws SQLException
   */
  public List<Manger> Mangerdetail(String id) ;
  /**
   * ɾ����Ա������Ϣ
   * @param str Ϊ��Աid���ַ���
   * @throws SQLException
   */
  public void deleteInfo(String str) ;
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
  * @return 
   * @throws SQLException
   */
  public void upInfo(String id, String name, String sex, String birthdate,
			String category, String attritube,String dept, String telephone, String idcard,
			String password);
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
   * ������Ա
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
  public int addInfo(String id, String name, String sex,
				String birthdate, String category, String attritube,
				String dept, String telephone, String idcard, String password);
   /**
    * ������Ա��Ϣ
    * @param dept
    * @return
    */
    public List<Manger> exportPersonInfo(String dept);

     

}
