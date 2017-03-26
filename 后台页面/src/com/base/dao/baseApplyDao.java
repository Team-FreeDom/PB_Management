package com.base.dao;

import java.util.List;

import com.base.po.ApplyDept;
import com.base.po.Major;
import com.base.po.basetype;

//实习基地申请的数据交互层
public interface baseApplyDao {

	/*
	  参数说明：type,字符串型，为部门类型	          
	 返回值：    List<ApplyDept>，为ApplyDept对象的集合
	 函数功能： 根据部门类型获取部门
	  */
    public List<ApplyDept> getDept(int type);
    
    /*
	  参数说明：无参数值          
	 返回值：    List<basetype>，为basetype对象的集合
	 函数功能：获取基地类型
	  */
    public  List<basetype> getBasetype();
    
    /*
	  参数说明：aid，整型，为学院编号      
	 返回值：    List<Major>，为Major对象的集合
	 函数功能：根据学院编号获取专业
	  */
    public List<Major> getMajor(int aid);
    
    /*
	  参数说明：str1, 字符串型，为('基地编号','专业编号')的封装;str2,字符串型，为实习基地申请的信息;
	          info,字符串型，为要发送的消息 
	 返回值：    无返回值
	 函数功能：插入实习基地申请
	 */
    public void getRequestBaseInfo(String str1,String str2);
    
    /*
	  参数说明：name,为实习基地的名称   
	 返回值：   int型，标志是否存在该基地名字
	 函数功能：判断是否存在该实习基地名称
	 */
    public int CheckName(String name);
    
    /*
	  参数说明：sql,为消息的封装  
	 返回值：   无返回值
	 函数功能：插入消息
	 */
    public void insertMessage(String sql);
}
