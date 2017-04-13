package com.base.dao;

import java.util.List;

import com.base.po.ApplyDept;
import com.base.po.ExportBase;
import com.base.po.Prabaseinfo;
import com.base.po.MaintenanceList;

//实习基地管理的数据交互层
public interface MaintenanceDao {

	/*
	  参数说明：pageindex,为当前页数;size,为每页的条数; order,排序列;
            orderDir,为排序的顺序;      
	 返回值：   MaintenanceList,该对象包括实习基地数据记录+记录条数
	 函数功能：获取该用户实习基地数据
	 */
    public MaintenanceList maintenance(int pageindex, int size,String order,String orderDir,String searchValue);
    
    /*
	  参数说明：str,为实习基地记录的编号   
	 返回值： 无返回值
	 函数功能：删除实习基地信息
	  */
    public void delInfo(String str);
    
    /*
	  参数说明：无参数
	 返回值：   List<ApplyDept>，为ApplyDept对象的集合
	 函数功能：获取部门集合
	  */
    public List<ApplyDept> getExistDept();
    
    /*
	  参数说明：basetype，为实习基地类型;dept,为部门;star,为星级;
	          pageindex,为当前页数;size,为每页的条数; order,排序列;
            orderDir,为排序的顺序;      
	 返回值：   MaintenanceList,该对象包括实习基地数据记录+记录条数
	 函数功能：根据条件刷选获取实习基地数据
	  */
    public MaintenanceList getshaiBaseInfo(int basetype,int dept,int star,int pageindex, int size,String order,String orderDir,String searchValue);
    
    /*
	  参数说明：baseid，为实习基地编号;star,为星级;adddate,为续期月数 	         
	 返回值：   无返回值
	 函数功能：修改实习基地截止日期
	 */
    public void updateBaseInfo(String baseid, int star, String adddate);

    /*
	  参数说明：basetype，为实习基地类型;dept,为部门;star,为星级;        
	 返回值：   List<ExportBase>，为ExportBase对象的集合
	 函数功能：获得导出的实习基地数据
	 */
	public List<ExportBase> getInfo(int basetype, int dept, int star);

	/*
	  参数说明：str1, 字符串型，为('基地编号','专业编号')的封装;str2,字符串型，为实习基地申请的信息; 	         
	 返回值：   无返回值
	 函数功能：增加实习基地
	 */
	public void increaseBaseInfo(String str1, String str2);
	
	/*
	  参数说明：userid,为用户编号; recordStr1,为基地名称集合;recordStr2,为基地记录集合;recordStr3,,为基地-专业集合   
	 返回值：   int型，值为0,代表基地名称已存在;值为1,代表基地名称重复;值为2,代表插入成功
	 函数功能：判断是否存在基地名称，若不存在，则插入基地信息你，否则不插入
	 */
	public int judge_insert_base(String userid, String recordStr1,String recordStr2,String recordStr3,int count,String resultStr4);
	
}
