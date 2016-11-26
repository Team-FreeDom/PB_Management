package com.base.serviceImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.daoImpl.ApplyDeptDaoImpl;
import com.base.daoImpl.BaseInfoDaoImpl;
import com.base.daoImpl.LandApplyDaoImpl;
import com.base.daoImpl.LandApply_viewDaoImpl;
import com.base.daoImpl.LandInfoDaoImpl;
import com.base.daoImpl.LandLayoutDaoImpl;
import com.base.daoImpl.LandLayout_infoDaoImpl;
import com.base.daoImpl.Land_PlantingDaoImpl;
import com.base.daoImpl.TemperateSaveDaoImpl;
import com.base.po.ApplyDept;
import com.base.po.ApplyList;
import com.base.po.BaseInfo;
import com.base.po.LandApply;
import com.base.po.LandApply_view;
import com.base.po.LandInfo;
import com.base.po.LandLayout;
import com.base.po.Land_Planting;
import com.base.po.Land_base;
import com.base.po.Layout_InfoView;
import com.base.po.RentCollection;
import com.base.po.TemperateSave;
import com.base.po.TemperateSave_View;
import com.base.service.LandApplyService;

@Service("landApplyService")
public class LandApplyServiceImpl<E> implements LandApplyService {
	
	@Autowired
	private LandApplyDaoImpl landApplyDaoImpl;
	@Autowired
	private BaseInfoDaoImpl baseInfoDaoImpl;
	@Autowired
	private LandLayoutDaoImpl landLayoutDaoImpl;
	@Autowired
	private LandInfoDaoImpl landInfoDaoImpl;
	@Autowired
	private Land_PlantingDaoImpl land_PlantingDaoImpl;
	@Autowired
	private LandApply_viewDaoImpl landApply_viewDaoImpl;
	@Autowired
	private TemperateSaveDaoImpl temperateSaveDaoImpl;
	@Autowired
	private ApplyDeptDaoImpl applyDeptDaoImpl;
	@Autowired
	private LandLayout_infoDaoImpl landLayout_infoDaoImpl;

	//1.��������  2.����У��  3.����У��    
	@Override
	public List<BaseInfo> getBaseInfos() {
		List<BaseInfo> list=baseInfoDaoImpl.getBaseInfos();
		return list;
	}
	
	public List<String> getLandLayout(int bid,String planting)
	{
		List<Land_Planting> list=land_PlantingDaoImpl.getPlanting(bid, planting);		
		List<String> str=new ArrayList<String>();	
		for(Land_Planting lp:list)
		{
			str.add(String.valueOf(lp.getLid()));
		}
		return str;
	}
	
	public List<Land_Planting> getPlanting(int bid)
	{
		List<Land_Planting> list=land_PlantingDaoImpl.getPlanting(bid);
		return list;
	}

	@Override
	public List<LandLayout> getLandLayout(int bid) {
		List<LandLayout> list=landLayoutDaoImpl.getLayout(bid);
		return list;
	}
	
	

    public List<Land_base> getLand_baseView(String lid) {
		
		return landInfoDaoImpl.getView(lid);
	}
    
    public List<Land_base> getLand_base(int bid)
    {
    	return landInfoDaoImpl.getlandbase(bid);
    }

	@Override
	public List<LandInfo> getLandInfo(String lid) {
		
		return landInfoDaoImpl.getLandInfo(lid);
	}

	@Override
	public void addLandApply(LandApply la) {
		landApplyDaoImpl.doLandApply(la);
         
	}

	//��ѯ�û��������е������¼
	@Override
	public List<LandApply> getUserApplys(String applicantId) {
		List<LandApply> list=landApplyDaoImpl.getUserApplys(applicantId);
		return list;
	}

	@Override
	public void updateUserApply(LandApply la) {
		landApplyDaoImpl.updateLandApply(la);

	}

	@Override
	public int getSpareValue(String lid) {
		// ����
		return 0;
	}

	//7.ʧЧ(�û�����ȡ��)
	@Override
	public void cancelApply(int la_id) {
		LandApply la=landApplyDaoImpl.getapply(la_id);
		la.setStatus(7);
		landApplyDaoImpl.updateLandApply(la);

	}
	
	//����û���������ʷ
	/*
	 * bname,startTime,endTime,statusΪɸѡ�������涨����洢���̵����Ͳ�������û�У���-1
	 * */
	public ApplyList getselfApply(String applicantId,String bname,String startTime,String endTime,String status,int page,int length)
	{
		int start=0;
		int end=0;
		int statusZ=0;
		
		if(bname!=null&&bname.equals("")){
			bname=null;			
		}
		if(startTime!=null&&startTime.equals(""))
		{
			startTime=null;
		}
		if(endTime!=null&&endTime.equals(""))
		{
			endTime=null;
		}
		System.out.println(status);
		if(status==null){
			statusZ=-1;
		}else{
			statusZ=Integer.valueOf(status);
		}
		
		
		ApplyList al=landApply_viewDaoImpl.getapplys(applicantId, bname, startTime, endTime, statusZ, page, length);
		
		return al;
	}
	
	
	//��ȡ�û���ͬ״̬�������¼
	public List<LandApply_view> getselfApply(String applicantId,int status)
	{
		List<LandApply_view> list=landApply_viewDaoImpl.getapplys(applicantId, status);
		return list;
	}
	
	public List<LandApply_view> myRentdetail(int la_id)
	{
		List<LandApply_view> list=landApply_viewDaoImpl.getapplys(la_id);
		return list;
	}	
  
    
    public ApplyList myRentFont1(String applicantId,int page,int length)
    {   	
    	
    	ApplyList al=landApply_viewDaoImpl.getapply(applicantId, page, length);    	
    	
    	return al;
    }   
  
    
    public void myFameCancel1(int la_id)
    {
    	LandApply la=landApplyDaoImpl.getapply(la_id);    	
    	
    	TemperateSave ts=new TemperateSave();
    	ts.setApplicantId(la.getApplicantId());
    	ts.setEndTime(la.getEndTime());
    	ts.setLid(la.getLid());
    	ts.setPlanting(la.getPlanting());
    	ts.setStartTime(la.getStartTime());
    	ts.setStatus(9);
    	ts.setResource(la.getResource());
    	ts.setApplyDept(la.getApplyDept());
    	
    	temperateSaveDaoImpl.doTemperate(ts);
    	
    	landApplyDaoImpl.delLandApply(la);   	
    	
    	
    }
    
    public void myFrameSubmit(int la_id){
    	
    	TemperateSave ts=temperateSaveDaoImpl.getTemperate(la_id);
    	
    	LandApply la=new LandApply();
    	la.setApplicantId(ts.getApplicantId());
    	la.setEndTime(ts.getEndTime());
    	la.setLid(ts.getLid());
    	la.setPlanting(ts.getPlanting());
    	la.setStartTime(ts.getStartTime());
    	la.setStatus(2);
    	la.setResource(ts.getResource());
    	la.setApplyDept(ts.getApplyDept());
    	landApplyDaoImpl.doLandApply(la);
    	
    	temperateSaveDaoImpl.delTemperate(la_id);
    	
    }
    
    public void myFrameDel1(int la_id)
    {
    	temperateSaveDaoImpl.delTemperate(la_id);
    }
    
    public List<TemperateSave_View> getTs(int la_id)
    {
    	List<TemperateSave_View> tsv=temperateSaveDaoImpl.getTemperates(la_id);
    	return tsv;
    }
    
   public List<LandApply_view> getUnionInfo(String applicantId,String bname,String startTime,String endTime,String lid,String desc ){
	   
	   LandApply_view lav=new LandApply_view();
	   lav.setApplicantId(applicantId);
	   lav.setBname(bname);
	   lav.setStartTime(startTime);
	   lav.setEndTime(endTime);
	   lav.setLid(lid);
	   lav.setDescp(desc);
	   
	   List<LandApply_view> list=landApply_viewDaoImpl.getAllStudents(lav);
	   return list;
   }
    
   public List<ApplyDept> getDepts()
   {
	   List<ApplyDept> list=applyDeptDaoImpl.getDepts();
	   return list;
   }
   
   public void updateContent(int la_id,String lid,int dept,String planting,String filename,String path)
   {
	   TemperateSave ts=temperateSaveDaoImpl.getTemperate(la_id);
	   ts.setLid(lid);
	   ts.setPlanting(planting);
	   ts.setApplyDept(dept);
	   if(filename!=null)
	   {
		   String relativePath=ts.getResource();
		   if(relativePath!=null)
		   {		   
		   relativePath=relativePath.substring(2);
			File file=new File(path+relativePath);			
			file.delete(); 
		   }
		   ts.setResource(filename);
	   }
	   temperateSaveDaoImpl.updateTemperate(ts);
	   
   }
   
   public List<Layout_InfoView>  getLayout()
   {
	   List<Layout_InfoView> list=landLayout_infoDaoImpl.getlayout_info();
	   return list;
   }
   
   public List<Layout_InfoView>  getDifferLayout(int bid)
   {
	   List<Layout_InfoView> list=landLayout_infoDaoImpl.getlayout_info(bid);
	   return list;
   }
   
   public void  delLayout_info(int bid)
   {
	   landInfoDaoImpl.delLayout_info(bid);
	 
   }
   
   public void updateLayInfo(String landinfoStr,String layoutStr)
   {
	   landInfoDaoImpl.doLayout_info(landinfoStr, layoutStr);
   }
   
  
// ��������ʱ����ȡ���ز���+���ػ�����Ϣ+�������������+����������ʷ
   public List<RentCollection> getRentCollection(int bid)
   {
	   List<RentCollection> list=landApplyDaoImpl.getRentCollection(bid);
	   
	   return list;
   }
   
   public void submitLandApply(String str)
   {
	   String userid=str.substring(0,str.indexOf('('));
	   String apply=str.substring(str.indexOf('('));
	  /* System.out.println(userid);
	   System.out.println(apply);*/
	   
	   landApplyDaoImpl.submitApply(userid,apply);
	   
	   
   }
   
}
