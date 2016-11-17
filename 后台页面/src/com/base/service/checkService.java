package com.base.service;

import java.util.List;

import com.base.po.CheckView;
import com.base.po.LandApply;

//���ģ��ҵ���߼���ӿ�
public interface checkService {
	
	/*
	   ����˵����status,���ͣ�Ϊ�����¼��״ֵ̬
	   ����ֵ��   List<CheckView>,Ϊ����������ͼCheckView����ļ���
	   �������ܣ���ѯ��������������ͼ��¼(��õļ�¼������ʱ�䣬Ĭ��Ϊ����)
	 */
	public List<CheckView> getLandApplys(int status);
	

	/*
	   ����˵�����޲�
	   ����ֵ��   List<CheckView>,Ϊ����������ͼCheckView����ļ���
	   �������ܣ���ѯ�������������¼(��õļ�¼������ʱ�䣬Ĭ��Ϊ����)
	 */
	public List<CheckView> getLandApplys();
	
	/*
	  ����˵����la_id,���ͣ�Ϊ�����¼��ţ�lid,���ͣ�Ϊ���ر��	          
	 ����ֵ��    �޷���ֵ
	 �������ܣ� ͬ������(�������¼���Ϊla_id�ļ�¼״̬��Ϊ"������"��1��ͬʱ�����ر��
	                     Ϊlid��״̬Ϊ�����еļ�¼״̬��Ϊ"����"��4)
	          ��״̬��Ϊ�����У��������ݿⶨʱ��������Ƿ񳬹��̶����ޣ��������ޣ���la_id��¼��Ϊδ����
	  */
	public void agreeApply(int la_id,int lid);
	
	/*
	  ����˵����la_id,���ͣ�Ϊ�����¼��ţ�lid,���ͣ�Ϊ���ر��	          
	 ����ֵ��    �޷���ֵ
	 �������ܣ� �ܾ�����(�����ر��Ϊlid(����la_id��������¼)�ļ�¼״̬Ϊ�����м�2�ı�Ϊ"����Ա�ܾ�"��8)
	  */
	public void refuseOthers(int la_id,int lid);	
	
	/*
	   ����˵����bname,�ַ����ͣ�Ϊ��������
	           lid,���ͣ�Ϊ���ر��
	           college���ַ����ͣ�ΪѧԺ����
	           userCount,���ͣ�Ϊ���޸���
	   ����ֵ��  List<CheckView>,Ϊ����������ͼCheckView����ļ��� 
	   �������ܣ�����������ѯ��ȡ����������ͼ��Ϣ
	   ע��           6�����������ϲ�ѯ����ǰ�������Ҳ���ܴ���û��ֵ�����
	                     ����ڴ���Ҫ�����жϲ����Ƿ���ֵ�����ݽ������dao��ķ���
	                     �ڴ˹涨�����ϲ㴫����lid��usercountֵ��Ϊ0����������������ֵ
	 */
	public List<CheckView> getLandApplys(String bname,int lid,String college,int userCount,String planting,int status);
	


}
