package com.base.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("personalManageController")
@RequestMapping("/jsp")
public class PersonalManageController {
	
	//ɾ����Ա��Ϣ
	@RequestMapping("/deleteInfo.do")
	public String deleteInfo(HttpServletRequest request,ModelMap map){
		
		return null;
	}
	
	//������Ա��Ϣ
		@RequestMapping("/increaseInfo.do")
		public String increaseInfo(HttpServletRequest request,ModelMap map){
			
			return null;
		}
		
		//�޸���Ա��Ϣ
		@RequestMapping("/updateInfo.do")
		public String updateInfo(HttpServletRequest request,ModelMap map){
			
			return null;
		}

}
