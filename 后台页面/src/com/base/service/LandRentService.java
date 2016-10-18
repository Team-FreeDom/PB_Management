package com.base.service;

import java.util.List;

import com.base.po.LandRentInfo;

//����ʹ����Ϣ���ҵ���߼���ӿ�
public interface LandRentService {
	
	/*
	   ����˵����lr��Ϊ����ʹ��LandRentInfo����ʵ��
	   ����ֵ��    �޷���ֵ
	   �������ܣ�1.������ʹ�ñ��в���һ���¼�¼
	           2.�����ر��Ϊ lr.getLid()��״̬Ϊ���������������¼��״ֵ̬��Ϊ����ʧ��               
	                          ��������Ϊlr.getLa_id()�����������¼��״ֵ̬��Ϊ����ɹ�
	 */	
	public void addLandRent(LandRentInfo lr);
	
	/*
	   ����˵����userId,�ַ����ͣ�Ϊ���ص�ʹ���˱��
	   ����ֵ��   List<LandRentInfo>��Ϊ����ʹ�ö���LandRentInfo�ļ���
	   �������ܣ���ѯ�û����˵�����ʹ�����
	 */
	public List<LandRentInfo> getUserRentInfos(String userId);
	
	/*
	   ����˵�����޲�
	   ����ֵ��   List<LandRentInfo>��Ϊ����ʹ�ö���LandRentInfo�ļ���
	   �������ܣ���ѯ��������ʹ�����
	 */
	public List<LandRentInfo> getLandRentInfos();
}
