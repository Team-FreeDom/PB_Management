package com.base.service;

import java.util.List;

import com.base.po.LandLayout;

//���ز��ֹ�����ҵ���߼���ӿ�
public interface LandLayoutService {

	/*
	   ����˵����list,LandLayout����ļ��ϣ���ʾ���е����ز��ֶ���
	   ����ֵ��   �޷���ֵ
	   �������ܣ��洢���ز���
	 */
	public void addLandLayout(List<LandLayout> list);
	
	/*
	   ����˵����list,LandLayout����ļ��ϣ���ʾ���е����ز��ֶ���
	   ����ֵ��   �޷���ֵ
	   �������ܣ��޸����ز���
	 */
	public void updateLandLayout(List<LandLayout> list);
	
	/*
	   ����˵����id,���ͣ���ʾ���ز��ּ�¼�ı��
	   ����ֵ��   �޷���ֵ
	   �������ܣ�ɾ������
	 */
	public void deleteLandLayout(int id);
	
	
	
	
}
