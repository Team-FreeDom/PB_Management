package com.base.serviceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import com.base.utils.MessageUtils;

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
	
	
    //拒绝申请
	@Override
	public void refuseapply(String recordStr,String infoStr)
	{
		//获得插入的消息语句	   
		String insertStr=MessageUtils.getInsertStr(infoStr,3);		
		System.out.println(insertStr);
		
		//将特定编号的土地记录状态改为同类竞争
		checkViewDaoImpl.updateStatus(recordStr, 5);
		
		//向消息表中插入信息 
		checkViewDaoImpl.insertMessage(insertStr);
	}
	
	
	@Override
	public int agreeApply(String landstr,String recordstr,String infostr) {
		System.out.println("agreeApply---start");
		//获得插入的消息语句
		String insertStr=MessageUtils.getInsertStr(infostr,2);		
		System.out.println(insertStr);
		
		//把审核中的改为待缴费		
		//checkViewDaoImpl.updateStatusP(recordstr, 1);	
		
		int tag=checkViewDaoImpl.agreeInfo(recordstr, 1);
		System.out.println(tag+"  hello");
		if(tag==1){
		//把相同土地的其他申请置为锁定
		 checkViewDaoImpl.lockInfo(landstr);
		//向消息表中插入信息 
		 checkViewDaoImpl.insertMessage(insertStr);
		}
		 
		 return tag;
	}
	
	public void cancelPayFor(String landstr, String recordstr, String infostr){
		
		//获得插入的消息语句
		String insertStr=MessageUtils.getInsertStr(infostr,5);	
		
		//把特定记录的状态改为未交费
		checkViewDaoImpl.updateStatus(recordstr, 10);
		
		//把相同土地的状态为锁定的土地状态变为审核中
		checkViewDaoImpl.releaseInfo(landstr);
		
		//向消息表中插入信息 
		 checkViewDaoImpl.insertMessage(insertStr);
		
	}
	
	//确认交费
		@Override
		public void confirmPayFor(String landstr, String recordstr, String infostr)
		{
			 
			//获得插入的消息语句
			String insertStr=MessageUtils.getInsertStr(infostr,6);	
			
			//把特定记录的状态改为申请成功，并将记录插入土地租赁历史表中
			checkViewDaoImpl.payForSuccess(recordstr, 6);
			
			//把相同土地的状态为锁定的土地状态变为同类竞争
			checkViewDaoImpl.confirmInfo(landstr);
			
			//向消息表中插入信息 
			 checkViewDaoImpl.insertMessage(insertStr);
			
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
			//List<UserInfo> list=checkViewDaoImpl.getDept();
			return null;
		}


		@Override
		public void getApplys(int flag, String la_id) throws SQLException {
			// TODO Auto-generated method stub
			
		}
		
		
		

}
