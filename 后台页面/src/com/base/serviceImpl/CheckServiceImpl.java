package com.base.serviceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.daoImpl.CheckViewDaoImpl;
import com.base.daoImpl.LandApplyDaoImpl;
import com.base.po.BaseInfo;
import com.base.po.CheckList;
import com.base.po.CheckView;
import com.base.po.LandApply;
import com.base.po.UserInfo;
import com.base.service.checkService;

@Service("checkService")
public class CheckServiceImpl implements checkService {

	@Autowired
	private LandApplyDaoImpl landApplyDaoImpl;
	@Autowired
	private CheckViewDaoImpl checkViewDaoImpl;

	//查询status中记录为审核的函数（status=2）
	@Override
	public CheckList getLandApply(int id,int pageindex,int size) throws SQLException  {
		CheckList list=checkViewDaoImpl.getLandApply(id,pageindex,size,null,null,null);
		return list;
	}
    //拒绝申请、同意申请
	@Override
	public void refuseapply(int flag,String la_id) throws SQLException
	{
		 checkViewDaoImpl.getLandApplys(flag,la_id);
	}
	//取消交费、同意交费
		@Override
		public void getApplys(int flag,String la_id) throws SQLException
		{
			 checkViewDaoImpl.getApplys(flag,la_id);
		}
	//详情查看
	@Override
	public List<CheckView> Rentdetail(int la_id) throws SQLException
	{
		List<CheckView> list=checkViewDaoImpl.detail(la_id);
		return list;
	}	
	//详情查看2
	@Override
	public List<CheckView> Rentdetail2(int la_id) throws SQLException
	{
		List<CheckView> list=checkViewDaoImpl.detail(la_id);
		return list;
	}	
	//刷选
	public CheckList getInfo(int flag,int startIndex,int pageindex,String basename,String username,String usercollage) throws SQLException{	   
		   CheckList list=checkViewDaoImpl.getLandApply(flag,startIndex,pageindex,basename,username,usercollage);
		   return list;
	   }
	//基地查询
	@Override
	public List<BaseInfo> getBaseInfos() {
		List<BaseInfo> list=checkViewDaoImpl.getBaseInfos();
		return list;
	}
	//申请人查询
	@Override
	public List<UserInfo> getappliInfos() throws SQLException {
		List<UserInfo> list=checkViewDaoImpl.getappliInfos();
		return list;
	}
	//申请部门查询
		@Override
		public List<UserInfo> getDept() throws SQLException {
			List<UserInfo> list=checkViewDaoImpl.getDept();
			return list;
		}
		
		
		@Override
		public void agreeApply(String str) {
			
			//把审核中的改为待缴费
			/*
			 * 
			 * landApplyDaoImpl.updateStatus(str,1)
			 * 
			 * 
			 * */
			
			/*把相同土地的其他申请置为锁定
			 * 
			 * str,int1,int2
			 * */
			/*
			 * userid ,bname+bid,
			 * */
		}

}
