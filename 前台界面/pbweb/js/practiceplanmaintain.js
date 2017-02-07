// JavaScript Document
var obj=[];
$(document).ready(function() {
	
	 var table=$("#practiceplanmaintain").dataTable({
		"processing" : true,
		"serverSide" : true,
		"bSort": false,
		"bFilter": false,
		"aLengthMenu":[5,7,9,12], //动态指定分页后每页显示的记录数。
		"lengthChange":true, //是否启用改变每页显示多少条数据的控件
		"iDisplayLength" : 5,  //默认每页显示多少条记录
		"dom":'ftipr<"bottom"l>',
		"ajax":{
			"URL":"xxx",
			"type":"POST"
		},
		"aoColumns" : [
			{
				"mData" : "id",
				"orderable" : false,
				"sDefaultContent" : "",
				//"sWidth" : "4%"
			},
			{
				"mData" : "number",//人数
				"orderable" : false,
				"sDefaultContent" : ""
			},
			{
				"mData" : "CheckedNum",//已选人数
				"orderable" : false,
				"sDefaultContent" : "",
			},
			{
				"mData" : "className",//教学班组成
				"orderable" : false,
				"sDefaultContent" : "",
			},
			{
				"mData" : "institute",//开课学院
				"orderable" : false,
				"sDefaultContent" : "",
			},
			{
				"mData" : "courseID",//课程代码
				"orderable" : false,
				"sDefaultContent" : "",
			},
			{
				"mData" : "courseName",//课程名称
				"orderable" : false,
				"sDefaultContent" : "",
			},
			{
				"mData" : "Wperiod",//周学时
				"orderable" : false,
				"sDefaultContent" : "",
			},
			{
				"mData" : "grade",//学分
				"orderable" : false,
				"sDefaultContent" : "",
			},
			{
				"mData" : "courceProperty",//课程性质
				"orderable" : false,
				"sDefaultContent" : "",
			},
			{
				"mData" : "courseClass",//课程类别
				"orderable" : false,
				"sDefaultContent" : "",
			},
			{
				"mData" : "teacherID",//教职工号
				"orderable" : false,
				"sDefaultContent" : "",
			},
			{
				"mData" : "teacherName",//教师姓名
				"orderable" : false,
				"sDefaultContent" : "",
			},
			{
				"mData" : "selectID",//选课课号
				"orderable" : false,
				"sDefaultContent" : "",
			},
			{
				"mData" : "sWeeke",//起始周
				"orderable" : false,
				"sDefaultContent" : "",
			},
			{
				"mData" : "division",//单位
				"orderable" : false,
				"sDefaultContent" : "",
			},
			{
				"mData" : "majoy",//面向专业
				"orderable" : false,
				"sDefaultContent" : "",
			},
			{
				"mData" : "Prweeks",//实习周数
				"orderable" : false,
				"sDefaultContent" : "",
			},
			{
				"mData" : "leadTeacher",//带队老师
				"orderable" : false,
				"sDefaultContent" : "",
			},
			{
				"mData" : "examine",//考核
				"orderable" : false,
				"sDefaultContent" : "",
				"render" : function(data,type,row){
					obj.push(row);
				}
			}
			
		],
		"columnDefs" : [ {
			"orderable" : false,
			"targets" : [ 0 ],
			"data" : "id",
			"render" : function(data, type, row) {
				return '<input type="checkbox" name="allcheckbox" value="'
					+data
					+'">';
			}
		} ],
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
	
//全选
$("#ck1").on("click", function () {
	// $("#Applychart").hide();
	if ($(this).prop("checked") === true) {
		$("#practiceplanmaintain input[name='allcheckbox']").prop("checked", true);
	} else {
		$("#practiceplanmaintain input[name='allcheckbox']").prop("checked", false);
	}
});
//显示实习申请表
$("#practiceplanmaintain tbody tr").on("click","td:gt(0)",function(){
	/*var index= table.row().data()[0].id;
	$("#division").val(obj[index].division);
	$("#classname").val(obj[index].className);
	$("#major").val(obj[index].major);
	$("#class").val(obj[index].className);
	$("#grade").val(obj[index].grade);
	$("#number").val(obj[index].number);
	$("#weeks").val(obj[index].Prweeks);
	$("#leaderTeacher").val(obj[index].leaderTeacher);*/
	$("#Applychart").show();
});
	
/*实习申请表里面的操作*/
	//实习基地来源改变，对应的实习基地改变
$("#baseFrom").change(function(e){
	$("#practicePlace").empty();
	if(e.target.value==='校内基地'){
		$("#practicePlace").append('<select id="schoolBase"><option id="schoolBaseID" value="">请选择</option></select>');
	}
	if(e.target.value==='校外基地'){
		$("#practicePlace").append('<input id="outBase" type="text" class="inputWidth">');	
	}	
});	
	//获取选择的内容
$.ajax({
	URL:"",
	type:"POST",
	dataType : 'json',
	success : function(data){
		for(var i=0;i<data[0].length;i++){//获取校内基地的实习地点下拉框
			$("#schoolBaseID").after(
			"<option class='rest' value="+data[0][i]+">"
									+ data[0][i] + "</option>"
			);
		}
		for(i=0;i<data[1].length;i++){//获取实习目的下拉框
			$("#aimID").after(
			"<option class='rest' value="+data[1][i]+">"
									+ data[1][i] + "</option>"
			);
		}
		for(i=0;i<data[2].length;i++){//获取学院下拉框
			$("#collegeID").after(
			"<option class='rest' value="+data[2][i]+">"
									+ data[2][i] + "</option>"
			);
		}
	}
});
$("#adviser2").change(function(e){//填写指导老师姓名
	$("#adviser").val(e.target.value);
});
$("#choice").click(function(){//点击选择弹出 框并且清空框里的数据
	$("#Selectname").modal('show');
	$("#selectTname").val("");
	$("#tester").val("");
	$("#selectCollege").val("");
});
	
//选择学院并且上传学院的名称，放回改学院老师的数据（包含老师名称和老师员工编号）
var obj2=[];
$("#selectCollege").change(function(){
	var college=$("#selectCollege").val;
	$.ajax({
	url:" ",
	type:"POST",
	dataType : 'json',
	data:{
		"college":college,
	},
	success : function(data){
		obj2=data;
		for(var i=0;i<data.length;i++){//获取校内基地的实习地点下拉框
			$("#schoolBaseID").after(
			"<option class='rest' value="+data[i].collegeName+">"
									+ data[i].collegeName + "</option>"
			);
		}
	}
});
});

var value=[];
var value2="";
var value3=[];
$("#selectTname").change(function(e){
	value.push(e.target.value);
	value2=value.join(" ");
	$("#tester").val(value2);
});
$("#finished").click(function(){//点击确定之后讲实验员姓名在表格中显示出来
	$("#testername").val(value2);
	value2="";
});
//隐藏实习申请表
$(document).on("click","#closemodal",function(){
	
	$("#Applychart").hide();
	
});


	
	
} );

