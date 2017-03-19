//下面是前台页面用测试的js
// JavaScript Document
var obj=[];

$(document).ready(function() {
	
	 var table=$("#statistictable").DataTable({
					 "aLengthMenu" : [ 5, 10, 20, 30 ], // 动态指定分页后每页显示的记录数。
					 "lengthChange" : true, // 是否启用改变每页显示多少条数据的控件
					 "bSort" : true,
					 "ordering":true,
					 "serverSide" : true,
					 "bFilter": true,
					 "ordering":true,
					 "bDestroy":true,
					  "dom": 'frtip<"bottom"l>',
					 "iDisplayLength": 5,	
					"ajax":{
						"url":"displayThisCollegePlan.do",
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
						 +'<td><input readonly id="weekend" type="text" class="text-center inputWidth flag"></td>'
						 +'<td><input readonly id="startweek" name="control_date" class="flag"></td>'
						 +'<td><input readonly id="endweek" name="control_date" type="text"  class="flag"></td>'
						 +'<td><input readonly id="content" type="text" class="inputWidth flag"></td>'
						 +'<td><select readonly name="" id="baseFrom" class="flag"><option value="">请选择</option><option value="校内基地">校内基地</option><option value="校外基地">校外基地</option></select></td>'
						 +'<td readonly id="practicePlace"><select id="schoolBase" hidden><option id="schoolBaseID" value="">请选择</option></select><input id="outBase" type="text" class="inputWidth" hidden></td>'
						 +'<td><select readonly id="category" class="flag"><option value="">请选择</option><option value="生产实习">生产实习</option><option value="教学实习">教学实习</option><option value="毕业实习">毕业实习</option><option value="综合实习">综合实习</option></select></td>'
						 +'<td><input readonly id="remark" type="text" class="flag"></td>'
						 
						 +'</tr>'
						 +'<tr>'
						 +'<td>实习形式</td>'
						 +'<td>实习基地联系人/电话</td>'
						 +'<td>目的</td>'
						 +'<td>实习经费预算</td>'
						 +'<td colspan="2">指导老师</td>'
						 +'<td colspan="2">实验员</td>'
						 +'</tr>'
						 +'<tr>'
						 +'<td><select readonly name="" id="practiceClass" class="flag"><option value="">请选择</option><option value="集中">集中</option><option value="分散">分散</option></select></td>'
						 +'<td><input readonly id="phone" type="text" class="flag"></td>'
						 +'<td><select id="aim" class="flag" readonly><option id="aimID" value="">请选择</option></select></td>'
						 +'<td><input readonly id="budget" type="text" class="inputWidth flag"></td>'
						+'<td colspan="2"><input type="text" readonly  id="Tea" placeholder="指导老师"></td>'
						+'<td colspan="2"><input type="text" readonly  id="tes" placeholder="实验员"></td>'	
						 +'</tr></tbody>';
	
$("#statistictable tbody").on("click","tr",function(){
	
	Oneindex= $(this).find("span").attr("id");
	$("#division").val(obj[Oneindex].college);
	$("#classname").val(obj[Oneindex].coursename);
	$("#major").val(obj[Oneindex].major_oriented);
	$("#class").val(obj[Oneindex].composition);
	$("#grade").val(obj[Oneindex].credit);
	$("#number").val(obj[Oneindex].count);
	$("#weeks").val(obj[Oneindex].weekClassify);
	$("#leaderTeacher").val(obj[Oneindex].tname);
	
	$.ajax({
		url:'getplandata.do',
		type:"POST",
		dataType:"json",
		data:{
			"mid":obj[Oneindex].id
		},
		success:function(data){	
			var teachername="";
			var testername="";
			for(var i=0;i<data.length;i++){
				$("#table tbody:last-child").after(tbodyStyle);
				$.ajax({
					type : 'POST',
					dataType : 'json',		
					url : 'getPlanAim.do',  
					async : false,
					cache : false,
					data:{
						"mid":obj[Oneindex].mid
					},
					error : function(request) {
						bootbox.alert({
							message : "请求异常",
							size : 'small'
						});
					},
					success : function(data){
						
						for(var j=0;j<data[0].length;j++){//获取校内基地的实习地点下拉框
							$("#table tbody:last-child").find("#schoolBaseID").after(
							"<option class='rest' value="+data[0][j]+">"+ data[0][j] + "</option>"
							);
						}
						for(var j=0;j<data[2].length;j++){//获取校外基地的实习地点下拉框
							$("#table tbody:last-child").find("#outBaseId").after(
							"<option class='rest' value="+data[2][j]+">"+ data[2][j]+ "</option>"
							);
						}
						for(j=0;j<data[1].length;j++){//获取实习目的下拉框
							$("#table tbody:last-child").find("#aimID").after(
									"<option class='rest' id="+data[1][i].id+" value="+data[1][i].aim+" data-placement='top' data-toggle='tooltip' title='"+data[1][i].aim+"'>"+ (data[1][i].aim.length>20?data[1][i].aim.substring(0,20)+"...":data[1][i].aim )+ "</option>"

							);
						}
					}
				});
				$("#table tbody:last-child").find(".mark").html(i+1);
				$("#table tbody:last-child").find("#weekend").val(data[i].week);
				$("#table tbody:last-child").find("#startweek").val(data[i].starttime);
				$("#table tbody:last-child").find("#endweek").val(data[i].endtime);
				$("#table tbody:last-child").find("#content").val(data[i].content);
				$("#table tbody:last-child").find("#baseFrom").val(data[i].source);
				$("#table tbody:last-child").find("#category").val(data[i].category);
				$("#table tbody:last-child").find("#remark").val(data[i].remark);
				$("#table tbody:last-child").find("#practiceClass").val(data[i].form);
				
				$("#table tbody:last-child").find("#phone").val(data[i].telephone);
				$("#table tbody:last-child").find("#aim").val(data[i].aim);
				$("#table tbody:last-child").find("#budget").val(data[i].expense);
				//$("#table tbody:last-child").find("#guideTeacher").val(data[i].guideTeacher);
				$("#table tbody:last-child").find("#Tea").val("老师:"+data[i].guideTeacher);
				$("#table tbody:last-child").find("#tes").val("实验员:"+data[i].assistant);
				if($("#table tbody:last-child").find("#baseFrom").val()==="校内基地"){
					$("#table tbody:last-child").find("#schoolBase").show();
					$("#table tbody:last-child").find("#schoolBase").addClass("flag");
					$("#table tbody:last-child").find("#schoolBase").val(data[i].site);
					

				}
				if($("#table tbody:last-child").find("#baseFrom").val()==="校外基地"){
					$("#table tbody:last-child").find("#outBase").show();
					$("#table tbody:last-child").find("#outBase").addClass("flag");
					$("#table tbody:last-child").find("#outBase").val(data[i].site);
				}
				$("#table tbody:last-child").find(".deleteID").attr("id",data[i].id);
				if(i!==data.length-1){
					teachername=teachername+data[i].guideTeacher+",";
					testername=testername+data[i].assistant+",";
				}else{
					teachername=teachername+data[i].guideTeacher;
					testername=testername+data[i].assistant;
				}
				value[i]=data[i].assistant;
				teacherString[i]=data[i].guideTeacher;
			}
			$("#testername").val(testername);
			$("#adviser").val(teachername);
		}
	});
	
	$("#Applychart").show();
});
	
	
	//获取两个筛选框里面基地类别
$.ajax({
	URL:"",
	type:"POST",
	dataType : 'json',
	success : function(data){
		for(var i=0;i<data[0].length;i++){//获取基地类别
			$("#baseCategoryID").after(
			"<option class='rest' value="+data[0][i].base+">"+ data[0][i].base + "</option>"
			);
		}
		for(var j=0;j<data[1].length;j++){//获取基地类别
			$("#baseCategoryID").after(
			"<option class='rest' value="+data[1][j].base+">"+ data[1][j].base + "</option>"
			);
		}
	}
	
});
//当基地类别改变时，获取基地名字（基地和名字的联动）
$(document).on("change","#baseCategory",function(){//第一个筛选的联动
			   $("#baseName option:gt(0)").remove();
			   $.ajax({
				URL:"",
				type:"POST",
				dataType : 'json',
				success : function(data){
					for(var i=0;i<data[0].length;i++){//获取基地名字
						$("#baseNameID").after(
						"<option class='rest' value="+data[0][i].bname+">"+ data[0][i].bname + "</option>"
						);
					}
				}

			});
});
$(document).on("change","#baseCategory2",function(){//第二个筛选的联动
			   $("#baseName2 option:gt(0)").remove();
			   $.ajax({
				URL:"",
				type:"POST",
				dataType : 'json',
				success : function(data){
					for(var i=0;i<data[1].length;i++){//获取基地名字
						$("#baseName2ID").after(
						"<option class='rest' value="+data[1][i].bname+">"+ data[1][i].bname + "</option>"
						);
					}
				}

			});
});
$("#finish").click(function(){//第一个筛选框点击完成触发的事件
	
});	
//隐藏实习申请表
$(document).on("click","#closemodal",function(){
	
	$("#Applychart").hide();
	$(".tbodyID").remove();
	
});
//筛选框的弹出
$("#screen").on("click", function () {
	$('.hide_ul').toggle();
});	
$("#screen2").on("click", function () {
	$('.hide_ul2').toggle();
});
	
	
	var table2=$("#statistictable2").DataTable({
					 "aLengthMenu" : [ 5, 10, 20, 30 ], // 动态指定分页后每页显示的记录数。
					 "lengthChange" : true, // 是否启用改变每页显示多少条数据的控件
					 "bSort" : true,
					 "ordering":true,
					 "serverSide" : true,
					 "bFilter": true,
					 "ordering":true,
					 "bDestroy":true,
					  "dom": 'frtip<"bottom"l>',
					 "iDisplayLength": 5,	
					"ajax":{
						"url":"displayThisCollegePlan.do",
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
					   				"mData" : "college",//专业
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
	
	
	
	
} );


	
	


	
	

