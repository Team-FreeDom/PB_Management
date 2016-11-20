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

	//交费是6，锁定是4，申请中是2，管理员拒绝为8
	@Override
	public void agreeApply(int la_id, int lid) {
		/*LandApply la=landApplyDaoImpl.getapply(la_id);
		la.setStatus(1);
		landApplyDaoImpl.updateLandApply(la);//将唯一的申请记录状态变为交费中	
		
		缺少定时器，判定是否超过交费日期
		
		
		获得特定土地状态为申请中的记录集合
		//List<LandApply> list=landApplyDaoImpl.getUserApplys(lid, 2);
		for(LandApply lay:list)
		{
			lay.setStatus(4);
			landApplyDaoImpl.updateLandApply(lay);//将申请中状态的记录状态变为锁定
		}*/

	}

	/*将状态为申请中的其他记录状态变为8*/
	@Override
	public void refuseOthers(int la_id, int lid) {
		/*List<LandApply> list=landApplyDaoImpl.getUserApplys(lid, 2);
		for(LandApply la:list)
		{
			if(la.getLa_id()!=la_id){
				la.setStatus(8);
				landApplyDaoImpl.updateLandApply(la);
			}			
		}*/
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
