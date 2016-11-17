package com.base.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.daoImpl.CheckViewDaoImpl;
import com.base.daoImpl.LandApplyDaoImpl;
import com.base.po.CheckView;
import com.base.po.LandApply;
import com.base.service.checkService;

@Service("checkService")
public class CheckServiceImpl implements checkService {
	
	@Autowired
	private LandApplyDaoImpl landApplyDaoImpl;
	@Autowired
	private CheckViewDaoImpl checkViewDaoImpl;

	@Override
	public List<CheckView> getLandApplys() {
		
		return null;
	}

	//������6��������4����������2������Ա�ܾ�Ϊ8
	@Override
	public void agreeApply(int la_id, int lid) {
		LandApply la=landApplyDaoImpl.getapply(la_id);
		la.setStatus(1);
		landApplyDaoImpl.updateLandApply(la);//��Ψһ�������¼״̬��Ϊ������	
		
		/*ȱ�ٶ�ʱ�����ж��Ƿ񳬹���������*/
		
		
		/*����ض�����״̬Ϊ�����еļ�¼����*/
		List<LandApply> list=landApplyDaoImpl.getUserApplys(lid, 2);
		for(LandApply lay:list)
		{
			lay.setStatus(4);
			landApplyDaoImpl.updateLandApply(lay);//��������״̬�ļ�¼״̬��Ϊ����
		}

	}

	/*��״̬Ϊ�����е�������¼״̬��Ϊ8*/
	@Override
	public void refuseOthers(int la_id, int lid) {
		List<LandApply> list=landApplyDaoImpl.getUserApplys(lid, 2);
		for(LandApply la:list)
		{
			if(la.getLa_id()!=la_id){
				la.setStatus(8);
				landApplyDaoImpl.updateLandApply(la);
			}			
		}
	}

	@Override
	public List<CheckView> getLandApplys(int status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CheckView> getLandApplys(String bname,int lid,String college,int userCount,String planting,int status) {
		// TODO Auto-generated method stub
		return null;
	}

}
