package com.base.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("planMaintainController")
@RequestMapping("/jsp")
public class PlanMaintainController {

	// 显示实习计划数据
		@RequestMapping("/displayPlanInfo.do")
		public String displayPlanInfo(HttpServletRequest request,
				HttpServletResponse response) {

			return null;
		}

		// 导出实习计划安排表
		@RequestMapping("/exportPlanInfo.do")
		public String exportPlanInfo(HttpServletRequest request,
				HttpServletResponse response) {

			return null;
		}

		// 增加单条实习计划数据
		@RequestMapping("/addOnePlanInfo.do")
		public String addOnePlanInfo(HttpServletRequest request,
				HttpServletResponse response) {

			return null;
		}

		// 导入实习计划数据
		@RequestMapping("/importPlanInfo.do")
		public String importPlanInfo(HttpServletRequest request,
				HttpServletResponse response) {

			return null;
		}

		// 检测数据完整性
		@RequestMapping("/checkIsSave.do")
		public String checkIsSave(HttpServletRequest request,
				HttpServletResponse response) {

			return null;
		}

		// 删除实习计划数据
		@RequestMapping("/deletePlanInfo.do")
		public String deletePlanInfo(HttpServletRequest request,
				HttpServletResponse response) {

			return null;
		}
		
		// 提醒教师完善实习计划
		@RequestMapping("/callAttention.do")
		public String callAttention(HttpServletRequest request,
				HttpServletResponse response) {

					return null;
				}
	

}
