package com.base.dao;

import java.util.List;

import com.base.po.LandLayout;

//���ز��ֹ��������ݿ����Ӳ�ӿ�
public interface LandLayoutDao {

	/*
	   ����˵����layout,Ϊ���ز���LandLayout����
	   ����ֵ��   �޷���ֵ
	   �������ܣ������ز��ֹ�����в���һ����¼
	 */
	public void doLandLayout(LandLayout layout);
	
	/*
	   ����˵����layout,Ϊ���ز���LandLayout����
	   ����ֵ��   �޷���ֵ
	   �������ܣ��������ز��ֹ�����е�һ����¼
	 */
	public void updateLandLayout(LandLayout layout);
	
	/*
	   ����˵����id,���ͣ���ʾ���ز��ּ�¼�ı��
	   ����ֵ��   �޷���ֵ
	   �������ܣ�ɾ�����ز��ֹ�����е�һ����¼
	 */
	public void delLandLayout(int id);
}
