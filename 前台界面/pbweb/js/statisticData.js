//下面是前台页面用测试的js
// JavaScript Document
var obj=[];
var teachername="";
var testername="";
$(document).ready(function() {
	
	 var table=$("#statistictable").DataTable({
		   "aLengthMenu" : [ 5, 10, 20, 30 ], // 动态指定分页后每页显示的记录数。
		   "lengthChange" : true, // 是否启用改变每页显示多少条数据的控件
		   "bSort" : true,
		   "serverSide" : true,
		   "bFilter": true,
		   "ordering":true,
		    "dom": 'tipr<"bottom"l>',
		    "iDisplayLength": 5,
					"ajax":{
						"url":'statisticData.do',
						"type":"POST",
					},
					"aoColumns" : [
					   			{
					   				"mData" : "semester",//学期学年
					   				"orderable" : false,
					   				"sDefaultContent" : "",
					   				//"sWidth" : "2%",
					   			},
					   			{
					   				"mData" : "cid",//课程代码
					   				"orderable" : false,
					   				"sDefaultContent" : "",
					   			},
					   			{
					   				"mData" : "coursename",//课程名称
					   				"orderable" : false,
					   				"sDefaultContent" : "",
					   			},
					   			{
					   				"mData" : "count",//人数
					   				"orderable" : true,
					   				"sDefaultContent" : ""
					   			},
					   			{
					   				"mData" : "selectedCount",//已选人数
					   				"orderable" : true,
					   				"sDefaultContent" : "",
					   			},
					   			{
					   				"mData" : "composition",//教学班组成
					   				"orderable" : false,
					   				"sDefaultContent" : "",
					   			},
					   			{
					   				"mData" : "college",//开课学院
					   				"orderable" : true,
					   				"sDefaultContent" : "",
					   			},
					   			
					   			{
					   				"mData" : "weekClassify",//周学时
					   				"orderable" : true,
					   				"sDefaultContent" : "",
					   			},
					   			{
					   				"mData" : "credit",//学分
					   				"orderable" : true,
					   				"sDefaultContent" : "",
					   			},
					   			{
					   				"mData" : "courseNature",//课程性质
					   				"orderable" : false,
					   				"sDefaultContent" : "",
					   			},
					   			{
					   				"mData" : "courseCategory",//课程类别
					   				"orderable" : false,
					   				"sDefaultContent" : "",
					   			},
					   			{
					   				"mData" : "tid",//教职工号
					   				"orderable" : true,
					   				"sDefaultContent" : "",
					   			},
					   			{
					   				"mData" : "tname",//教师姓名
					   				"orderable" : true,
					   				"sDefaultContent" : "",
					   			},
					   			{
					   				"mData" : "week",//起始周
					   				"orderable" : false,
					   				"sDefaultContent" : "",
					   			},
					   			{
					   				"mData" : "mid",//专业编号
					   				"orderable" : false,
					   				"visible":false,
					   				"sDefaultContent" : "",
					   			},
					   			{
					   				"mData" : "major_oriented",//面向专业
					   				"orderable" : false,
					   				"visible":false,
					   				"sDefaultContent" : "",
					   			},
					   			{
					   				"mData" : "checkMethod",//考核
					   				"orderable" : false,
					   				"sDefaultContent" : "",
					   			},
								{
									"mData" : "id",//考核
									"orderable" : false,
									"sDefaultContent" : "",
									"render" : function(data,type,row){
										obj.push(row);
										return '<span id='+(obj.length-1)+'></span>';
									}
								}
						
					],
			        "language": {
						"lengthMenu": "每页 _MENU_ 条记录",
			            "zeroRecords": "没有找到记录",
			            "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
			            "infoEmpty": "无记录",
			            "infoFiltered": "(从 _MAX_ 条记录过滤)",
						"sSearch": "模糊查询：",
						"oPaginate": {
							"sFirst": "首页",
							"sPrevious": " 上一页 ",
							"sNext": " 下一页 ",
							 "sLast": " 尾页 "
							}
			         }
				});

		//显示实习申请表
	var tbodyStyle='<tbody class="tbodyID"><tr>'
							+'<td>序号</td>'
							+'<td>周次</td>'
							+'<td>开始时间</td>'
							+'<td>结束时间</td>'
							+'<td>实习内容</td>'
							+'<td>实习基地来源</td>'
							+'<td>实习地点</td>'
							+'<td>实习类别</td>'
							+'<td>备注</td>'
							
						 +'</tr>'
						 +'<tr>'
						 +'<td rowspan="3"><sapn class="mark"></span></td>'
						 +'<td><input id="weekend" type="text" class="text-center inputWidth" readonly="readonly"></td>'
						 +'<td><input id="startweek" name="control_date" type="text" readonly="readonly"></td>'
						 +'<td><input id="endweek" name="control_date" type="text"  readonly="readonly"></td>'
						 +'<td><input id="content" type="text" class="inputWidth" readonly="readonly"></td>'
						 +'<td><input disabled id="baseFrom" class="flag"></td>'
						 +'<td><input id="outBase" type="text" class="inputWidth" readonly="readonly"></td>'
						 +'<td><select id="category" disabled><option value="">请选择</option><option value="生产实习">生产实习</option><option value="教学实习">教学实习</option><option value="毕业实习">毕业实习</option><option value="综合实习">综合实习</option></select></td>'
						 +'<td><input id="remark" type="text" readonly="readonly"></td>'
						 
						 +'</tr>'
						 +'<tr>'
						 +'<td>实习形式</td>'
						 +'<td>实习基地联系人/电话</td>'
						 +'<td>目的</td>'
						 +'<td>实习经费预算</td>'
						 +'<td colspan="2">指导老师/实验员</td>'
						 +'<td colspan="2">指导老师/实验员</td>'
						 +'</tr>'
						 +'<tr>'
						 +'<td><select name="" id="practiceClass" disabled><option value="">请选择</option><option value="集中">集中</option><option value="分散">分散</option></select></td>'
						 +'<td><input id="phone" type="text" readonly="readonly"></td>'
						 +'<td><input type="text" id="aim" readonly="readonly"></td>'
						 +'<td><input id="budget" type="text" class="inputWidth" readonly="readonly"></td>'
						 +'<td colspan="2"><input type="text" readonly id="Tea"></td>'
					     +'<td colspan="2"><input type="text" readonly id="tes"></td>'
						 +'</tr></tbody>';
	
$("#statistictable tbody").on("click","tr",function(){
	$("#Applychart").show();
	/*Oneindex= $(this).find("span").attr("id");
	$("#division").val(obj[Oneindex].college);
	$("#classname").val(obj[Oneindex].coursename);
	$("#major").val(obj[Oneindex].major_oriented);
	$("#class").val(obj[Oneindex].composition);
	$("#grade").val(obj[Oneindex].credit);
	$("#number").val(obj[Oneindex].count);
	$("#weeks").val(obj[Oneindex].weekClassify);
	$("#leaderTeacher").val(obj[Oneindex].tname);*/
	for(var i=0;i<3;i++){
				$("#table tbody:last-child").after(tbodyStyle);
				
				$("#table tbody:last-child").find(".mark").html(i+1);
				$("#table tbody:last-child").find("#weekend").val("data[i].week");
				$("#table tbody:last-child").find("#startweek").val("data[i].starttime");
				$("#table tbody:last-child").find("#endweek").val("data[i].endtime");
				$("#table tbody:last-child").find("#content").val("data[i].content");
				$("#table tbody:last-child").find("#baseFrom").val("校外基地");
				$("#table tbody:last-child").find("#outBase").val("data[i].site");
				$("#table tbody:last-child").find("#category").val("教学实习");
				$("#table tbody:last-child").find("#remark").val("data[i].remark");
				$("#table tbody:last-child").find("#practiceClass").val("分散");
				$("#table tbody:last-child").find("#phone").val("data[i].telephone");
				$("#table tbody:last-child").find("#aim").val("data[i].aim");
				$("#table tbody:last-child").find("#budget").val("data[i].expense");
				$("#table tbody:last-child").find("#Tea").val("data[i].guideTeacher");
				$("#table tbody:last-child").find("#tes").val("data[i].assistant");
				
				if(i!==data.length-1){
					teachername=teachername+data[i].guideTeacher+",";
					testername=testername+data[i].assistant+",";
				}else{
					teachername=teachername+data[i].guideTeacher;
					testername=testername+data[i].assistant;
				}
			}
	$.ajax({
		url:'getplandata.do',
		type:"POST",
		dataType:"json",
		/*data:{
			"mid":obj[Oneindex].id
		},*/
		success:function(data){	
			var teachername="";
			var testername="";
			for(var i=0;i<3;i++){
				$("#table tbody:last-child").after(tbodyStyle);
				
				$("#table tbody:last-child").find(".mark").html(i+1);
				$("#table tbody:last-child").find("#weekend").val("data[i].week");
				$("#table tbody:last-child").find("#startweek").val("data[i].starttime");
				$("#table tbody:last-child").find("#endweek").val("data[i].endtime");
				$("#table tbody:last-child").find("#content").val("data[i].content");
				$("#table tbody:last-child").find("#baseFrom").val("校内基地");
				$("#table tbody:last-child").find("#schoolBase").val("data[i].site");
				$("#table tbody:last-child").find("#category").val("data[i].category");
				$("#table tbody:last-child").find("#remark").val("data[i].remark");
				$("#table tbody:last-child").find("#practiceClass").val("data[i].form");
				$("#table tbody:last-child").find("#phone").val("data[i].telephone");
				$("#table tbody:last-child").find("#aim").val("data[i].aim");
				$("#table tbody:last-child").find("#budget").val("data[i].expense");
				$("#table tbody:last-child").find("#Tea").val("data[i].guideTeacher");
				$("#table tbody:last-child").find("#tes").val("data[i].assistant");
				
				if(i!==data.length-1){
					teachername=teachername+data[i].guideTeacher+",";
					testername=testername+data[i].assistant+",";
				}else{
					teachername=teachername+data[i].guideTeacher;
					testername=testername+data[i].assistant;
				}
			}
			$("#testername").val(testername);
			$("#adviser").val(teachername);
		}
	});
	
	$("#Applychart").show();
});
	
	
//获取两个筛选框里面基地类别
$.ajax({
	url:'',
	type:"POST",
	dataType : 'json',
	success : function(data){
		for(var i=0;i<data[0].length;i++){//获取第一个筛选的基地类别
			$("#baseCategoryID").after(
			"<option class='rest' value="+data[0][i].base+">"+ data[0][i].base + "</option>"
			);
		}
		for(var j=0;j<data[1].length;j++){//获取第二个筛选的基地类别
			$("#baseCategory2ID").after(
			"<option class='rest' value="+data[1][j].base+">"+ data[1][j].base + "</option>"
			);
		}
		for(var m=0;m<data[2].length;m++){//获取第一个筛选的学院
			$("#collegeID").after(
			"<option class='rest' value="+data[2][m].base+">"+ data[1][m].base + "</option>"
			);
		}
		for(var n=0;n<data[3].length;n++){//获取第二个筛选的学院
			$("#college2ID").after(
			"<option class='rest' value="+data[3][n].base+">"+ data[1][n].base + "</option>"
			);
		}
		for(var y=0;y<data[4].length;y++){//获取第二个筛选的学院
			$("#teacherNameID").after(
			"<option class='rest' value="+data[4][y].tname+">"+ data[4][y].tname + "</option>"
			);
		}
	}
	
});
//当基地类别改变时，获取基地名字（基地和名字的联动）
$(document).on("change","#baseCategory",function(){//第一个筛选的联动
			   $("#baseName option:gt(0)").remove();
				var baseCategory=$("#baseCategory").val();
			   $.ajax({
				url:'',
				type:"POST",
				dataType : 'json',
				data:{
				"baseCategory": baseCategory
			    },
				success : function(data){
					for(var i=0;i<data[0].length;i++){//获取基地名字
						$("#baseNameID").after(
						"<option class='rest' value="+data[0][i].bname+">"+ data[0][i].bname + "</option>"
						);
					}
				}

			});
});
//筛选框的弹出
$("#screen").on("click", function () {
	$('.hide_ul').toggle();
	$('.hide_ul select').val(0);
});	
$("#screen2").on("click", function () {
	$('.hide_ul2').toggle();
	$('.hide_ul2 select').val(0);
});
$(document).on("change","#baseCategory2",function(){//第二个筛选的联动
			   $("#baseName2 option:gt(0)").remove();
				var baseCategory2=$("#baseCategory2").val();
			   $.ajax({
				url:'',
				type:"POST",
				dataType : 'json',
				data:{
				"baseCategory2": baseCategory2
			    },
				success : function(data){
					for(var i=0;i<data[1].length;i++){//获取基地名字
						$("#baseName2ID").after(
						"<option class='rest' value="+data[1][i].bname+">"+ data[1][i].bname + "</option>"
						);
					}
				}

			});
});
$(document).on("change","#college",function(){
	$("#major option:gt(0)").remove();
				var college=$("#college").val();
			   $.ajax({
				url:'',
				type:"POST",
				dataType : 'json',
				data:{
				"college": college
			    },
				success : function(data){
					for(var i=0;i<data[0].length;i++){//获取基地名字
						$("#baseName2ID").after(
						"<option class='rest' value="+data[0][i].major+">"+ data[0][i].major + "</option>"
						);
					}
				}

			});
});
$(document).on("change","#college2",function(){
	$("#major2 option:gt(0)").remove();
				var college2=$("#college2").val();
			   $.ajax({
				url:'',
				type:"POST",
				dataType : 'json',
				data:{
				"college2": college2
			    },
				success : function(data){
					for(var i=0;i<data[1].length;i++){//获取基地名字
						$("#baseName2ID").after(
						"<option class='rest' value="+data[1][i].major+">"+ data[1][i].major + "</option>"
						);
					}
				}

			});
});
	
/*生成年级开始*/
var myDate=new Date();
var dateYear=myDate.getFullYear();
var dateMonth=myDate.getMonth();
if(dateMonth>7){
	for(var y=0;y<4;y++){
		$("#gradeClassId").after("<option value="+(dateYear-y-2000)+">"+(dateYear-y-2000)+"级</option>");
		$("#gradeClass2Id").after("<option value="+(dateYear-y-2000)+">"+(dateYear-y-2000)+"级</option>");
	}
}else{
	for(var y2=1;y2<=4;y2++){
		$("#gradeClassId").after("<option value="+(dateYear-y2-2000)+">"+(dateYear-y2-2000)+"级</option>");
		$("#gradeClass2Id").after("<option value="+(dateYear-y2-2000)+">"+(dateYear-y2-2000)+"级</option>");
	}
}
/*生成年级结束*/
	
for(var classNum=0;classNum<15;classNum++){//生成班级
	$("#classNameID").after("<option value="+(15-classNum)+">"+ (15-classNum) + "</option>");
	$("#className2ID").after("<option value="+(15-classNum)+">"+ (15-classNum) + "</option>");
}
/*限制必须选择年级才能选班级开始*/
$(document).on("change","#className",function(){
	alert($("#gradeClass").val());
	if($("#gradeClass").val()==="0"){
		bootbox.alert({
			message : "请先选择年级",
			size : 'small'
			});
	}
});
$(document).on("change","#className2",function(){
	if($("#gradeClass2").val()==="0"){
		$("#className2").val(0);
		bootbox.alert({
			message : "请先选择年级",
			size : 'small'
			});
	}
});
/*限制必须选择年级才能选班级结束*/
	
//隐藏实习申请表
$(document).on("click","#closemodal",function(){
	
	$("#Applychart").hide();
	$(".tbodyID").remove();
	
});

	
	
	var table2=$("#statistictable2").DataTable({
					 "aLengthMenu" : [ 5, 10, 20, 30 ], // 动态指定分页后每页显示的记录数。
					 "lengthChange" : true, // 是否启用改变每页显示多少条数据的控件
					 "bSort" : true,
					 "ordering":true,			
					 "bFilter": true,
					 "ordering":true,
					  "dom": 'rtip<"bottom"l>',
					 "iDisplayLength": 5,	
					"ajax":{
						"url":'',
						"type":"POST",
					},
					"aoColumns" : [
					   			{
					   				"mData" : "baseCategory",//基地类型
					   				"orderable" : false,
					   				"sDefaultContent" : "",
					   				//"sWidth" : "2%",
					   			},
					   			{
					   				"mData" : "basename",//基地名称
					   				"orderable" : false,
					   				"sDefaultContent" : "",
					   			},
					   			{
					   				"mData" : "college",//学院
					   				"orderable" : false,
					   				"sDefaultContent" : "",
					   			},
								{
					   				"mData" : "major",//专业
					   				"orderable" : false,
					   				"sDefaultContent" : "",
					   			},
					   			{
					   				"mData" : "teacher",//老师
					   				"orderable" : true,
					   				"sDefaultContent" : "",
					   			},
					   			{
					   				"mData" : "class",//班级
					   				"orderable" : false,
					   				"sDefaultContent" : "",
					   			},
					   			{
					   				"mData" : "cost",//开课学院
					   				"orderable" : true,
					   				"sDefaultContent" : "",
					   			},
					   			{
					   				"mData" : "count",//人数
					   				"orderable" : true,
					   				"sDefaultContent" : ""
					   			},
					   			
						
					],
			        "language": {
						"lengthMenu": "每页 _MENU_ 条记录",
			            "zeroRecords": "没有找到记录",
			            "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
			            "infoEmpty": "无记录",
			            "infoFiltered": "(从 _MAX_ 条记录过滤)",
						"sSearch": "模糊查询：",
						"oPaginate": {
							"sFirst": "首页",
							"sPrevious": " 上一页 ",
							"sNext": " 下一页 ",
							 "sLast": " 尾页 "
							}
			         }
				});
	
$("#finish").click(function(){//第一个筛选框点击完成触发的事件
	var baseCategory=$("#baseCategory").val();
	var baseName=$("#baseName").val();
	var gradeClass=$("#gradeClass").val();
	var college=$("#college").val();
	var major=$("#major").val();
	var className=$("#className").val();
	$("#statistictable").DataTable({
		   "aLengthMenu" : [ 5, 10, 20, 30 ], // 动态指定分页后每页显示的记录数。
		   "lengthChange" : true, // 是否启用改变每页显示多少条数据的控件
		   "bSort" : true,
		   "serverSide" : true,
		   "bFilter": true,
		   "bDestroy":true,
		   "processing":true,
		   "ordering":true,
		    "dom": 'tipr<"bottom"l>',
		    "iDisplayLength": 5,
					"ajax":{
						"url":'statisticData.do',
						"type":"POST",
						"data":{
							"baseCategory":baseCategory,
							"baseName":baseName,
							"gradeClass":gradeClass,
							"college":college,
							"major":major,
							"className":className
						}
					},
					"aoColumns" : [
					   			{
					   				"mData" : "semester",//学期学年
					   				"orderable" : false,
					   				"sDefaultContent" : "",
					   				//"sWidth" : "2%",
					   			},
					   			{
					   				"mData" : "cid",//课程代码
					   				"orderable" : false,
					   				"sDefaultContent" : "",
					   			},
					   			{
					   				"mData" : "coursename",//课程名称
					   				"orderable" : false,
					   				"sDefaultContent" : "",
					   			},
					   			{
					   				"mData" : "count",//人数
					   				"orderable" : true,
					   				"sDefaultContent" : ""
					   			},
					   			{
					   				"mData" : "selectedCount",//已选人数
					   				"orderable" : true,
					   				"sDefaultContent" : "",
					   			},
					   			{
					   				"mData" : "composition",//教学班组成
					   				"orderable" : false,
					   				"sDefaultContent" : "",
					   			},
					   			{
					   				"mData" : "college",//开课学院
					   				"orderable" : true,
					   				"sDefaultContent" : "",
					   			},
					   			
					   			{
					   				"mData" : "weekClassify",//周学时
					   				"orderable" : true,
					   				"sDefaultContent" : "",
					   			},
					   			{
					   				"mData" : "credit",//学分
					   				"orderable" : true,
					   				"sDefaultContent" : "",
					   			},
					   			{
					   				"mData" : "courseNature",//课程性质
					   				"orderable" : false,
					   				"sDefaultContent" : "",
					   			},
					   			{
					   				"mData" : "courseCategory",//课程类别
					   				"orderable" : false,
					   				"sDefaultContent" : "",
					   			},
					   			{
					   				"mData" : "tid",//教职工号
					   				"orderable" : true,
					   				"sDefaultContent" : "",
					   			},
					   			{
					   				"mData" : "tname",//教师姓名
					   				"orderable" : true,
					   				"sDefaultContent" : "",
					   			},
					   			{
					   				"mData" : "week",//起始周
					   				"orderable" : false,
					   				"sDefaultContent" : "",
					   			},
					   			{
					   				"mData" : "mid",//专业编号
					   				"orderable" : false,
					   				"visible":false,
					   				"sDefaultContent" : "",
					   			},
					   			{
					   				"mData" : "major_oriented",//面向专业
					   				"orderable" : false,
					   				"visible":false,
					   				"sDefaultContent" : "",
					   			},
					   			{
					   				"mData" : "checkMethod",//考核
					   				"orderable" : false,
					   				"sDefaultContent" : "",
					   			},
								{
									"mData" : "id",//考核
									"orderable" : false,
									"sDefaultContent" : "",
									"render" : function(data,type,row){
										obj.push(row);
										return '<span id='+(obj.length-1)+'></span>';
									}
								}
						
					],
			        "language": {
						"lengthMenu": "每页 _MENU_ 条记录",
			            "zeroRecords": "没有找到记录",
			            "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
			            "infoEmpty": "无记录",
			            "infoFiltered": "(从 _MAX_ 条记录过滤)",
						"sSearch": "模糊查询：",
						"oPaginate": {
							"sFirst": "首页",
							"sPrevious": " 上一页 ",
							"sNext": " 下一页 ",
							 "sLast": " 尾页 "
							}
			         }
				});
	$('.hide_ul').toggle();
});	
	
$("#finish").click(function(){
	var baseCategory2=$("#baseCategory2").val();
	var baseName2=$("#baseName2").val();
	var gradeClass2=$("#gradeClass2").val();
	var college2=$("#college2").val();
	var major2=$("#major2").val();
	var className2=$("#className2").val();
	var teacherName=$("#teacherName").val();
	$("#statistictable2").DataTable({
					 "aLengthMenu" : [ 5, 10, 20, 30 ], // 动态指定分页后每页显示的记录数。
					 "lengthChange" : true, // 是否启用改变每页显示多少条数据的控件
					 "bSort" : true,
					 "ordering":true,			
					 "bFilter": true,
					 "bDestroy":true,
		             "processing":true,
					 "ordering":true,
					  "dom": 'rtip<"bottom"l>',
					 "iDisplayLength": 5,	
					"ajax":{
						"url":'',
						"type":"POST",
						"data":{
							"baseCategory2":baseCategory2,
							"baseName2":baseName2,
							"gradeClass2":gradeClass2,
							"college2":college2,
							"major2":major2,
							"className2":className2,
							"teacherName":teacherName
							
						}
					},
					"aoColumns" : [
					   			{
					   				"mData" : "baseCategory",//基地类型
					   				"orderable" : false,
					   				"sDefaultContent" : "",
					   				//"sWidth" : "2%",
					   			},
					   			{
					   				"mData" : "basename",//基地名称
					   				"orderable" : false,
					   				"sDefaultContent" : "",
					   			},
					   			{
					   				"mData" : "college",//学院
					   				"orderable" : false,
					   				"sDefaultContent" : "",
					   			},
								{
					   				"mData" : "major",//专业
					   				"orderable" : false,
					   				"sDefaultContent" : "",
					   			},
					   			{
					   				"mData" : "teacher",//老师
					   				"orderable" : true,
					   				"sDefaultContent" : "",
					   			},
					   			{
					   				"mData" : "class",//班级
					   				"orderable" : false,
					   				"sDefaultContent" : "",
					   			},
					   			{
					   				"mData" : "cost",//开课学院
					   				"orderable" : true,
					   				"sDefaultContent" : "",
					   			},
					   			{
					   				"mData" : "count",//人数
					   				"orderable" : true,
					   				"sDefaultContent" : ""
					   			},
					   			
						
					],
			        "language": {
						"lengthMenu": "每页 _MENU_ 条记录",
			            "zeroRecords": "没有找到记录",
			            "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
			            "infoEmpty": "无记录",
			            "infoFiltered": "(从 _MAX_ 条记录过滤)",
						"sSearch": "模糊查询：",
						"oPaginate": {
							"sFirst": "首页",
							"sPrevious": " 上一页 ",
							"sNext": " 下一页 ",
							 "sLast": " 尾页 "
							}
			         }
				});
	$('.hide_ul2').toggle();
});	
	
	
} );


	
	


	
	

