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
	
	//未交费为3，锁定是4，申请中是2
	@Override
	public void refusePay(int la_id, int lid) {
		/*LandApply la=landApplyDaoImpl.getapply(la_id);
		la.setStatus(3);
		landApplyDaoImpl.updateLandApply(la);//将此记录状态记为未交费
        
		List<LandApply> list=landApplyDaoImpl.getUserApplys(lid, 4);
		for(LandApply lay:list)
		{
			lay.setStatus(2);
			landApplyDaoImpl.updateLandApply(lay);//将锁定状态的记录状态变为申请中
		}*/
	}

	//5.竞争失败   6.申请成功   4.锁定
	@Override
	public void paySuccess(int la_id, int lid) {
		
		/*LandApply la=landApplyDaoImpl.getapply(la_id);
		la.setStatus(6);
		landApplyDaoImpl.updateLandApply(la);//将此记录状态记为申请成功
        
		List<LandApply> list=landApplyDaoImpl.getUserApplys(lid, 4);
		for(LandApply lay:list)
		{
			lay.setStatus(5);
			landApplyDaoImpl.updateLandApply(lay);//将锁定状态的记录状态变为申请中
		}*/
	}

}
