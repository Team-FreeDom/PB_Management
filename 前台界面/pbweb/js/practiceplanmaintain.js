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
	var index= table.row().data()[0].id;
	$("#division").val(obj[index].division);
	$("#classname").val(obj[index].className);
	$("#major").val(obj[index].major);
	$("#class").val(obj[index].className);
	$("#grade").val();
	//$("#Applychart").show();
});
//隐藏实习申请表
$(document).on("click","#closemodal",function(){
	
	$("#Applychart").hide();
	
});


	
	
} );

