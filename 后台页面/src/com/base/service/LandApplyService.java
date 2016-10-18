package com.base.service;

import java.util.List;

import com.base.po.BaseInfo;
import com.base.po.LandApply;
import com.base.po.LandInfo;
import com.base.po.LandLayout;

//����������ҵ���߼���ӿ�
public interface LandApplyService {
	
	/*
	   ����˵����baseType,���ͣ���ʾ�������ͣ���ȡ����ֵ��1(����)��2(У��)��3(У��)
	   ����ֵ��   List<BaseInfo>,Ϊ������ϢBaseInfo����ļ���
	   �������ܣ���ѯ������ص���Ϣ
	 */
	public List<BaseInfo> getBaseInfos(int baseType);

	/*
	   ����˵����bid,���ͣ���ʾ���ر��
	   ����ֵ��   List<LandInfo>,Ϊ���ع���LandInfo����ļ���
	   �������ܣ���ѯ���ز�����Ϣ
	 */
	public List<LandLayout> getLandLayout(int bid); 
	
	/*
	   ����˵����lid,���ͣ���ʾ���ر��
	   ����ֵ��   LandInfo,Ϊ���ع���LandInfo����
	   �������ܣ���ѯ������Ϣ
	 */
	public LandInfo getLandInfo(int lid);
	
	/*
	   ����˵����la,Ϊ��������LandApply����
	   ����ֵ��   �޷���ֵ
	   �������ܣ��ύ����������Ϣ
	 */
	public void addLandApply(LandApply la);
	
	/*
	   ����˵����applicantId,�ַ����ͣ�Ϊ�����˵�Id
	   ����ֵ��   List<LandApply>,Ϊ��������LandApply����ļ���
	   �������ܣ���ѯ�û����˵����������¼
	 */
	public List<LandApply> getUserApplys(String applicantId);
	
	/*
	   ����˵����applicantId,�ַ����ͣ�Ϊ�����˵�Id
	   ����ֵ��   �޷���ֵ
	   �������ܣ��޸�����������Ϣ
	 */
	public void updateUserApply(LandApply la);
	
	
	/* 
	   ����˵����lid,���ͣ���ʾ���ر��
	   ����ֵ��    ���ͣ����ص������ؿ���ֵ
	   �������ܣ��������ر�Ų�ѯ�����ص����ؿ���ֵ���Դ���ǰ̨�ж��·��Ƿ���Ч
	   ע��           �����û�ѡ����·ݿ����ǲ������ģ���˴�ʱ��β��׻�ȡ��Ч
	                     ���е�ʱ���·ݣ��˴��������ؿ���ֵ�ķ���.   	
	                     ����ԭ��1.���ؿ���ֵ�ĳ�ʼĬ��ֵΪ2^1+2^2+2^3+...+2^12(����12���¾���Ч)
	                    2.���û�������3��4��5�����·ݵ�ĳһ����ʱ�������ص�ֵΪ
	                      2^1+2^2+...+2^12-2^3-2^4-2^5
	                    3.�ж�ĳһ�·��Ƿ���У�spareValue��2^(�·�ֵ)���룬ֵΪ2^(�·�ֵ),��
	                                            ���·ݿ���
 */
	public int getSpareValue(int lid);
	
	/*
	   ����˵����la_id,���ͣ�Ϊ�����¼���
	   ����ֵ��    �޷���ֵ
	   �������ܣ�ȡ������
	 */
	public void cancelApply(int la_id);

}
 