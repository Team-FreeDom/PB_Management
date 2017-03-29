package com.base.serviceImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.daoImpl.ApplyDeptDaoImpl;
import com.base.daoImpl.BaseInfoDaoImpl;
import com.base.daoImpl.CheckViewDaoImpl;
import com.base.daoImpl.LandApplyDaoImpl;
import com.base.daoImpl.LandApply_viewDaoImpl;
import com.base.daoImpl.LandInfoDaoImpl;
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
import com.base.po.Startplan;
import com.base.po.TemperateSave;
import com.base.po.TemperateSave_View;
import com.base.service.LandApplyService;
import com.base.utils.MessageUtils;

@Service("landApplyService")
public class LandApplyServiceImpl<E> implements LandApplyService {
	
	@Autowired
	private LandApplyDaoImpl landApplyDaoImpl;
	@Autowired
	private BaseInfoDaoImpl baseInfoDaoImpl;

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
    @Autowired
    private CheckViewDaoImpl checkViewDaoImpl;
	
	//获得基地土地表里的基地信息
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
	 * bname,startTime,endTime,statusΪɸѡ�������涨����洢��̵����Ͳ�����û�У���-1
	 * */
	public ApplyList getselfApply(String applicantId,String bname,String status,int page,int length)
	{
		int start=0;
		int end=0;
		int statusZ=0;
		
		if(bname!=null&&bname.equals("")){
			bname=null;			
		}
		
		System.out.println(status);
		if(status==null){
			statusZ=-1;
		}else{
			statusZ=Integer.valueOf(status);
		}
		
		
		ApplyList al=landApply_viewDaoImpl.getapplys(applicantId, bname,statusZ, page, length);
		
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
  
    
    public int myFameCancel1(int la_id,String info_str,int tag)
    {
    	int flag=0;
    	String str=null;
    	String landstr=null;
    	LandApply la=null;
    	//获取消息sql语句
  	    String insertStr=MessageUtils.getInsertStr(info_str,7);  	   
  	    if(tag==0){  	    	
  	    	 //获取编号为la_id的记录且状态为锁定或待审核
  	    	la=landApplyDaoImpl.getapply(la_id,2,4); 
  	    }else if(tag==1){  	    
  	    	 //获取编号为la_id的记录且状态为待交费
  	    	la=landApplyDaoImpl.getapply(la_id,1,1); 
  	    }
  	   
    	if(la!=null){
    		la.setStatus(11); 
    		landApplyDaoImpl.updateLandApply(la);
    		checkViewDaoImpl.insertMessage(insertStr);
    		if(tag==1){
    			 landstr=la.getLid();
    		     landstr='('+landstr+')';            	
        		checkViewDaoImpl.releaseInfo(landstr);
            }
    		flag=1;
    	}      
        
        return flag;
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
   
   public void  delLayout_info(int bid,String path)
   {
	  /* List<String> list=new ArrayList<String>();
	   list=landInfoDaoImpl.deletelandimg(bid);
	   for(int i=0;i<list.size();i++)
	   {
		   String relativePath=list.get(i);
		   System.out.println(path+relativePath.substring(2));
		   if(relativePath!=null)
		   {		   
		   relativePath=relativePath.substring(2);
			File file=new File(path+relativePath);			
			file.delete(); 
		   }
	   }*/
	   landInfoDaoImpl.delLayout_info(bid);
   }

   
   public void updateLayInfo(String landinfoStr,String layoutStr)
   {
	   landInfoDaoImpl.doLayout_info(landinfoStr, layoutStr);
   }
   
  
// ��������ʱ����ȡ���ز���+���ػ���Ϣ+�������������+����������ʷ
   public List<RentCollection> getRentCollection(int bid)
   {
	   List<RentCollection> list=landApplyDaoImpl.getRentCollection(bid);
	   
	   return list;
   }
  
   //�ύ��������
   public int submitLandApply(String userid,String lidList,String str,String info_str)
   {
	  	 		
	  //�ύ���� 
	   int flag=landApplyDaoImpl.submitApply(userid,lidList,str);
	   
	   if(flag==1){
		   
	   //��ò������Ϣ���
		   
		  String insertStr=MessageUtils.getInsertStr(info_str,1);	
		  
	 //����Ϣ���в�����Ϣ 
		  checkViewDaoImpl.insertMessage(insertStr);
	   }
	   
		return flag;
	   
   }

@Override
public void updateLandApplyDate(Startplan sp) {
	// TODO Auto-generated method stub
	landApplyDaoImpl.updateLandApplyDate(sp);
	return;
}

@Override
public List<Startplan> getLandApplyDate() {
	// TODO Auto-generated method stub
	return landApplyDaoImpl.getLandApplyDate();
}

@Override
public Startplan getStartPlan(String id){
	
	Startplan sp=landApplyDaoImpl.getStartPlan(id);
	
	return sp;
}

public void endStartPlan(){
	
	landApplyDaoImpl.endAllRent();	
	
}
   
}
