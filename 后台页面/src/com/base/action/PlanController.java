package com.base.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("planController")
@RequestMapping("/jsp")
public class PlanController {

	
		//显示用户所属学院的所有实习计划
		@RequestMapping("/displayThisCollegePlan.do")
		public String displayThisCollegePlan(HttpServletRequest request,
				HttpServletResponse response) {

			return null;
		}
		
		//保存对实习计划的修改
		@RequestMapping("/savePlanModify.do")
		public String savePlanModify(HttpServletRequest request,
				HttpServletResponse response) {

			return null;
		}
	
		// 获取专业所对应的培训目的
		@RequestMapping("/getPlanAim.do")
		public String getPlanAim(HttpServletRequest request,
				HttpServletResponse response) {

			return null;
		}

		// 获取学院所对应的教师
		@RequestMapping("/getCollege_Teacher.do")
		public String getCollege_Teacher(HttpServletRequest request,
				HttpServletResponse response) {

			return null;
		}
		
		// 删除班级安排记录(单条)
		@RequestMapping("/deleteClassRecord.do")
		public String deleteClassRecord(HttpServletRequest request,
				HttpServletResponse response) {

					return null;
				}
		

}
