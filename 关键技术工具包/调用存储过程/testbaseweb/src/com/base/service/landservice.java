package com.base.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.dao.land;
import com.base.po.CheckView;
import com.base.po.landprocedure;

@Service("landservice")
public class landservice
{
	@Autowired
	private land landdao;
	public List<landprocedure> getland()
	{
		List<landprocedure> list=null;
		try
		{
			list = landdao.query();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public List<CheckView> getall()
	{
		List<CheckView> list=null;
		try
		{
			list=landdao.getLandApplys();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
}
