package com.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.dao.land;
import com.base.po.song;

@Service("songservice")
public class songservice
{
	@Autowired
	private land la;
/*	public song update()
	{
		
		song so=la.update();
		System.out.println(so.getMoney()+"services");
		return so;
	}*/
	public void update()
	{
		la.update();
	}
}
