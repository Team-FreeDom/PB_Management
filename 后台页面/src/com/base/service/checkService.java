package com.base.service;

import java.util.List;

import com.base.po.LandApply;

//���ģ��ҵ���߼���ӿ�
public interface checkService {

	/*
	   ����˵�����޲�
	   ����ֵ��   List<LandApply>,Ϊ��������LandApply����ļ���
	   �������ܣ���ѯ�������������¼
	 */
	public List<LandApply> getLandApplys();
	
	/*
	  ����˵����la_id,���ͣ�Ϊ�����¼��ţ�lid,���ͣ�Ϊ���ر��	          
	 ����ֵ��    �޷���ֵ
	 �������ܣ� ͬ������
	  */
	public void agreeApply(int la_id,int lid);
	
	/*
	  ����˵����la_id,���ͣ�Ϊ�����¼��ţ�lid,���ͣ�Ϊ���ر��	          
	 ����ֵ��    �޷���ֵ
	 �������ܣ� �ܾ�����
	  */
	public void refuseOthers(int la_id,int lid);	


}
