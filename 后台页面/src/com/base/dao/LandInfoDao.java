package com.base.dao;

import java.util.List;

import com.base.po.LandInfo;

//���ز��ֹ��������ݿ����Ӳ�ӿ�
public interface LandInfoDao {
	
	/*
	   ����˵����spareValue,����
	   ����ֵ��   �޷���ֵ
	   �������ܣ��������ؿ���ֵ
	 */
	public void updateSpareValue(int spareValue);
	
	/*
	   ����˵����lid,���ͣ���ʾ���ر��
	   ����ֵ��   LandInfo,Ϊ���ع���LandInfo����
	   �������ܣ��������ر�Ż�ȡ�����ز��ֹ�����Ϣ
	 */
    public LandInfo getLandInfo(int lid);
    
    /*
	   ����˵����bid,���ͣ���ʾ���ر��
	   ����ֵ��   List<LandInfo>,Ϊ���ع���LandInfo����ļ���
	   �������ܣ����ݻ��ر�Ż�øû����е��������ز��ֹ�����Ϣ
	 */
    public List<LandInfo> getLandInfos(int bid);
}