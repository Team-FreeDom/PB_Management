package com.base.dao;

import java.util.List;

import com.base.po.LandRentInfo;

//����ʹ�ñ�����ݿ����Ӳ�ӿ�
public interface LandRentInfoDao {
	
	/*
	   ����˵����lr��Ϊ����ʹ��LandRentInfo����ʵ��
	   ����ֵ��    �޷���ֵ
	   �������ܣ�������ʹ�ñ��в���һ���¼�¼	           
	 */	
    public void doLandRentInfo(LandRentInfo lr);
    
    /*
	   ����˵����userId,�ַ����ͣ�Ϊ���ص�ʹ���˱��
	   ����ֵ��   List<LandRentInfo>��Ϊ����ʹ�ö���LandRentInfo�ļ���
	   �������ܣ���������ʹ���˱�Ų�ѯ����ʹ�ü�¼
	 */
	public List<LandRentInfo> getLandRentInfo(String userId);
    
	/*
	   ����˵�����޲�
	   ����ֵ��   List<LandRentInfo>��Ϊ����ʹ�ö���LandRentInfo�ļ���
	   �������ܣ���ȡ��������ʹ�����
	 */
    public List<LandRentInfo> getLandRentInfos(); 
}
