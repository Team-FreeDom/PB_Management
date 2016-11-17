package com.base.dao;

import java.util.List;

import com.base.po.LandInfo;

//���ز��ֹ��������ݿ����Ӳ�ӿ�
public interface LandInfoDao {
	
	/*
	   ����˵����lid,���ͣ���ʾ���ر��;spareValue,����
	   ����ֵ��   �޷���ֵ
	   �������ܣ��������ؿ���ֵ
	 */
	public void updateSpareValue(int lid,int spareValue);
	
	/*
	   ����˵����lid,���ͣ���ʾ���ر��
	   ����ֵ��   List<LandInfo>,Ϊ���ع���LandInfo����ļ���
	   �������ܣ��������ر�Ż�ȡ�����ز��ֹ�����Ϣ
	 */
    public List<LandInfo> getLandInfo(int lid);
    
    /*
	   ����˵����bid,���ͣ���ʾ���ر��
	   ����ֵ��   List<LandInfo>,Ϊ���ع���LandInfo����ļ���
	   �������ܣ����ݻ��ر�Ż�øû����е��������ز��ֹ�����Ϣ
	 */
    public List<LandInfo> getLandInfos(int bid);
}