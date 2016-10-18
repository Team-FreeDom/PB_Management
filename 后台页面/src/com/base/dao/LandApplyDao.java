package com.base.dao;

import java.util.List;

import com.base.po.LandApply;

//�������������ݿ����Ӳ�ӿ�
public interface LandApplyDao {
	
	/*
	   ����˵����la,Ϊ��������LandApply����
	   ����ֵ��   �޷���ֵ
	   �������ܣ�������������в���һ���¼�¼
	 */
	public void doLandApply(LandApply la);
		
	/*
	   ����˵����la_id,���ͣ�Ϊ���������¼��ţ�status�����ͣ�Ϊ����״ֵ̬
	   ����ֵ��   �޷���ֵ
	   �������ܣ��������������¼��Ÿı�ü�¼��״ֵ̬Ϊָ����status
	 */
	public void updateStatus(int la_id,int status);
	
	/*
	   ����˵����lid,���ͣ�Ϊ���ر�ţ�formerStatus,���ͣ�Ϊԭ��״ֵ̬��status�����ͣ�Ϊ������״ֵ̬
	   ����ֵ��   �޷���ֵ
	   �������ܣ������ر��Ϊlid��״ֵ̬ΪformerStatus�ļ�¼��״ֵ̬��Ϊstatus
	 */
	public void updateStatus(int lid,int formerStatus,int status);
	
	/*
	   ����˵����applicantId,�ַ����ͣ�Ϊ�����˵�Id
	   ����ֵ��   List<LandApply>,Ϊ��������LandApply����ļ���
	   �������ܣ�����applicantId(������Id)��ѯ���������¼
	 */
    public List<LandApply> getUserApplys(String applicantId);
    
    /*
	   ����˵�����޲�
	   ����ֵ��   List<LandApply>,Ϊ��������LandApply����ļ���
	   �������ܣ���ѯ�������������¼
	 */    
    public List<LandApply> getLandApplys();
    
    
	/*
	  ����˵����la_id,���ͣ�Ϊ�����¼��ţ�lid,���ͣ�Ϊ���ر��	          
	 ����ֵ��    �޷���ֵ
	 �������ܣ� ���������ر��Ϊlid����Ϊ��la_id�ļ�¼�ܾ�(������ʧ��)
	  */
	public void updateOthers(int la_id,int lid);

}
