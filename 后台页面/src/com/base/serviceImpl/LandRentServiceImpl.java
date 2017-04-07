package com.base.serviceImpl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.daoImpl.LandRentInfoDaoImpl;
import com.base.po.ApplyDept;
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
		
		if(dept!=null&&dept.equals("")){
			
			dept=null;
		 }
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
		try {
			landRentInfoDaoImpl.deleteRentInfo(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//�������޼�¼�޸ĸ���
	public void landManageUpdate(int dept,String planCareer,Double expense,String startTime,String endTime,int lr_id)
	{
		if(planCareer==null){
			planCareer="";
		}
		if(startTime!=null&&startTime.equals("")){
			startTime=null;
		}
		if(endTime!=null&&endTime.equals("")){
			endTime=null;
		}
		
		LandRentInfo lr=landRentInfoDaoImpl.getOne(lr_id);
		
		lr.setEndTime(endTime);
		lr.setPlanting(planCareer);
		lr.setRentMoney(expense);
		lr.setStartTime(startTime);
		lr.setApplyDept(dept);
		System.out.println("rentMoney:"+lr.getRentMoney());
		landRentInfoDaoImpl.updateOne(lr);
		
	}
	
 public void landManageAdd(LandRentInfo lr)
 {
	 landRentInfoDaoImpl.doLandRentInfo(lr);
 }
 
 //获取土地租赁历史表里存在的部门
 public List<ApplyDept> getExistRentInfo(){
	 
	 List<ApplyDept> list=landRentInfoDaoImpl.getExistDept();
	 
	 return list;
	 
 }

//获取土地租赁历史表里存在的种植内容
 public List<String> getExistPlant(){
	 
	 List<String> list=landRentInfoDaoImpl.getExistPlant();
	 return list;
 }
}
