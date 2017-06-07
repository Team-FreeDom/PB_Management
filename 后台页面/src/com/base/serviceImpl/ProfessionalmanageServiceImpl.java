package com.base.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.dao.ProfessionalmanageDao;
import com.base.daoImpl.ProfessionalmanageDaoImpl;
import com.base.po.Professionalmanage;
import com.base.po.ProfessionalmanageList;
import com.base.service.ProfessionalmanageService;

@Service("ProfessionalmanageService")
public class ProfessionalmanageServiceImpl implements ProfessionalmanageService {

    @Autowired
    private ProfessionalmanageDao Professionalmanagedao;

    @Override
    public int insertMajor(String mid, String mname, int aid) {
	int flag = Professionalmanagedao.insertMajor(mid, mname, aid);
	return flag;
    }

    @Override
    public String deleteMajor(String str) {
	String message = Professionalmanagedao.deleteMajor(str);
	return message;
    }

    @Override
    public ProfessionalmanageList query_majors(int size, int pageindex,
	    int order, String orderDir, String searchValue) {
	String columnName = "";
	if (order == 0) {
	    columnName = "aid";
	} else if (order == 2) {
	    columnName = "mid";
	} else if (order == 3) {
	    columnName = "mname";
	}
	ProfessionalmanageList list = Professionalmanagedao.query_majors(size,
		pageindex, columnName, orderDir, searchValue);
	return list;
    }

    @Override
    public void updatemajor(String mid, String mname) {
	// TODO Auto-generated method stub
	Professionalmanagedao.updatemajor(mid, mname);
    }

    @Override
    public int CheckmName(String name) {
	int flag = Professionalmanagedao.CheckmName(name);
	return flag;
    }

    @Override
    public int CheckmName1(String name) {
	int flag = Professionalmanagedao.CheckmName1(name);
	return flag;
    }
}
