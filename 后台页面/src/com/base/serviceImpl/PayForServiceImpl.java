package com.base.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.daoImpl.LandApplyDaoImpl;
import com.base.po.LandApply;
import com.base.service.PayForService;

@Service("payForService")
public class PayForServiceImpl implements PayForService {

	@Autowired
	private LandApplyDaoImpl landApplyDaoImpl;
	
	//δ����Ϊ3��������4����������2
	@Override
	public void refusePay(int la_id, int lid) {
		LandApply la=landApplyDaoImpl.getapply(la_id);
		la.setStatus(3);
		landApplyDaoImpl.updateLandApply(la);//���˼�¼״̬��Ϊδ����
        
		List<LandApply> list=landApplyDaoImpl.getUserApplys(lid, 4);
		for(LandApply lay:list)
		{
			lay.setStatus(2);
			landApplyDaoImpl.updateLandApply(lay);//������״̬�ļ�¼״̬��Ϊ������
		}
	}

	//5.����ʧ��   6.����ɹ�   4.����
	@Override
	public void paySuccess(int la_id, int lid) {
		
		LandApply la=landApplyDaoImpl.getapply(la_id);
		la.setStatus(6);
		landApplyDaoImpl.updateLandApply(la);//���˼�¼״̬��Ϊ����ɹ�
        
		List<LandApply> list=landApplyDaoImpl.getUserApplys(lid, 4);
		for(LandApply lay:list)
		{
			lay.setStatus(5);
			landApplyDaoImpl.updateLandApply(lay);//������״̬�ļ�¼״̬��Ϊ������
		}
	}

}
