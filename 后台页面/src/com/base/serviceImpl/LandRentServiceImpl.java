package com.base.serviceImpl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.daoImpl.LandRentInfoDaoImpl;
import com.base.po.LandRentInfo;
import com.base.po.RentList;
import com.base.po.RentMaintain;
import com.base.service.LandRentService;

@Service("landRentService")
public class LandRentServiceImpl implements LandRentService {

	@Autowired
	private LandRentInfoDaoImpl landRentInfoDaoImpl;
	
	@Override
	public void addLandRent(LandRentInfo lr) {
		landRentInfoDaoImpl.doLandRentInfo(lr);
	}

	@Override
	public List<LandRentInfo> getUserRentInfos(String userId) {
		List<LandRentInfo> list=landRentInfoDaoImpl.getLandRentInfo(userId);
		return list;
	}

	@Override
	public RentList getLandRentInfos(String bname,String deptName ,String plantingContent,int page,int length) {
		
		System.out.println("service层");
		if(bname!=null&&bname.equals(""))
		{
			bname=null;
		}
		if(deptName!=null&&deptName.equals(""))
		{
			deptName=null;
		}
		
		if(plantingContent!=null&&plantingContent.equals("")){
			plantingContent=null;
		}		
		
		RentList rl=landRentInfoDaoImpl.getRentMaintain(bname,deptName, plantingContent,page,length);			
		return rl;
	}
	
	public List<RentMaintain> getSingleRentInfo(String lr_id,String dept)
	{
		System.out.println("哈哈。我来到service层了");
		List<RentMaintain> list=landRentInfoDaoImpl.getSingleRentInfo(lr_id,dept);
		return list;
		
	}
	
	public void deleteRentInfo(String[] check)
	{
		String str="";
		int i=0;
		for(String st:check)
		{
			if(i==check.length-1)
			{
			str+=st;
			}else{
				str+=st+",";
			}
			i++;
		}
		System.out.println(str);
		try {
			landRentInfoDaoImpl.deleteRentInfo(str);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//土地租赁记录修改更新
	public void landManageUpdate(int dept,String planCareer,int expense,String startTime,String endTime,int lr_id)
	{
		LandRentInfo lr=landRentInfoDaoImpl.getOne(lr_id);
		lr.setEndTime(endTime);
		lr.setPlanting(planCareer);
		lr.setRentMoney(expense);
		lr.setStartTime(startTime);
		lr.setApplyDept(dept);
		
		landRentInfoDaoImpl.updateOne(lr);
		
	}
	
 public void landManageAdd(LandRentInfo lr)
 {
	 landRentInfoDaoImpl.doLandRentInfo(lr);
 }
 
}
