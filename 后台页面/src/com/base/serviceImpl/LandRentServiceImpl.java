package com.base.serviceImpl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.daoImpl.LandRentInfoDaoImpl;
import com.base.po.LandRentInfo;
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
	public List<RentMaintain> getLandRentInfos(String bname,String lid,String deptName ,String plantingContent,String lr_id) {
		
		if(bname!=null&&bname.equals(""))
		{
			bname=null;
		}
		if(deptName!=null&&deptName.equals(""))
		{
			deptName=null;
		}
		if(lid!=null&&lid.equals("")){
			lid=null;
		}
		if(plantingContent!=null&&plantingContent.equals("")){
			plantingContent=null;
		}
		if(lr_id!=null&&lr_id.equals(""))
		{
			lr_id=null;
		}
		
		List<RentMaintain> list=landRentInfoDaoImpl.getRentMaintain(bname,lid , deptName, plantingContent,lr_id);
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
	
	
	

}
