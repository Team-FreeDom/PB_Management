package com.base.dao;

import java.util.List;

import com.base.po.BaseInfo;

//������Ϣ������ݿ����Ӳ�ӿ�
public interface BaseInfoDao {
	    
    /*
	   ����˵����baseType,���ͣ���ʾ��������
	   ����ֵ��   List<BaseInfo>,Ϊ������ϢBaseInfo����ļ���
	   �������ܣ����ݻ������ͻ����Ӧ���ص���Ϣ
	 */
    public List<BaseInfo> getBaseInfos(int baseType);

}
