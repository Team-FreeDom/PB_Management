package com.base.service;

//����ģ���ҵ���߼���ӿ�
public interface PayForService {

	/*
	  ����˵����la_id,���ͣ�Ϊ�����¼��ţ�lid,���ͣ�Ϊ���ر��	          
	 ����ֵ��    �޷���ֵ
	 �������ܣ� �ܾ�����
	  */
	public void refusePay(int la_id,int lid);
	
	/*
	  ����˵����la_id,���ͣ�Ϊ�����¼��ţ�lid,���ͣ�Ϊ���ر��	          
	 ����ֵ��    �޷���ֵ
	 �������ܣ� ���ѳɹ�
	  */
	public void paySuccess(int la_id,int lid);
}
