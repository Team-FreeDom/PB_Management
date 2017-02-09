// JavaScript Document
var obj=[];
var Oneindex;
$(document).ready(function() {
	
	 var table=$("#practiceplanmaintain").dataTable({
		"processing" : true,
		"serverSide" : true,
		"bSort": false,
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
				"mData" : "termYear",//学期学年
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
				/*"render" : function(data,type,row){
					obj.push(row);
				}*/
			}
			
		],
		"columnDefs" : [ {
			"orderable" : false,
			"targets" : [ 0 ],
			"data" : "id",
			"render" : function(data, type, row) {
				obj.push(row);
				return '<input type="checkbox" name="allcheckbox" value=' + data + 'id='+(obj.length-1)+'>';
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
var tbodyStyle='<tbody><tr>'
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
						 +'<td><input id="weekend" type="text" class="inputWidth flag"></td>'
						 +'<td><input id="startweek" type="text" class="inputWidth flag"></td>'
						 +'<td><input id="endweek" type="text" class="inputWidth flag"></td>'
						 +'<td><input id="content" type="text" class="inputWidth flag"></td>'
						 +'<td><select name="" id="baseFrom" class="flag"><option value="">请选择</option><option value="校内基地">校内基地</option><option value="校外基地">校外基地</option></select></td>'
						 +'<td id="practicePlace"><select id="schoolBase" class="flag" hidden><option id="schoolBaseID" value="">请选择</option></select><input id="outBase" type="text" class="inputWidth flag" hidden></td>'
						 +'<td><select name="" class="flag"><option value="">请选择</option><option value="生产实习">生产实习</option><option value="教学实习">教学实习</option><option value="毕业实习">毕业实习</option><option value="综合实习">综合实习</option></select></td>'
						 +'<td><input id="remark" type="text" class="flag"></td>'
						 +'</tr>'
						 +'<tr>'
						 +'<td>实习形式</td>'
						 +'<td colspan="2">实习基地联系人/电话</td>'
						 +'<td>目的</td>'
						 +'<td>实习经费预算</td>'
						 +'<td>指导老师</td>'
						 +'<td>实验员</td>'
						 +'<td>操作</td>'
						 +'</tr>'
						 +'<tr>'
						 +'<td><select name="" id="practiceClass" class="flag"><option value="">请选择</option><option value="集中">集中</option><option value="分散">分散</option></select></td>'
						 +'<td colspan="2"><input id="phone" type="text" class="flag"></td>'
						 +'<td><select id="aim" class="flag"><option id="aimID" value="">请选择</option></select></td>'
						 +'<td><input id="budget" type="text" class="inputWidth flag"></td>'
						 +'<td><input type="text" class="adviser2 inputWidth flag"></td>'
						 +'<td><a class="btn btn-primary choice">选择</a></td>'
						 +'<td><span class="deleteID" id="">删除</span></td>'
						 +'</tr></tbody>';
	
$("#practiceplanmaintain tbody tr").on("click","td:gt(0)",function(){
	/*Oneindex= $(this).closest("tr").children(":first").find("input").attr("id");
	$("#division").val(obj[Oneindex].division);
	$("#classname").val(obj[Oneindex].className);
	$("#major").val(obj[Oneindex].major);
	$("#class").val(obj[Oneindex].className);
	$("#grade").val(obj[Oneindex].grade);
	$("#number").val(obj[Oneindex].number);
	$("#weeks").val(obj[Oneindex].Prweeks);
	$("#leaderTeacher").val(obj[Oneindex].leaderTeacher);*/
	$.ajax({
		url:"",
		type:"POST",
		dataType:"json",
		success:function(data){
			
			for(var i=1;i<data.length;i++){
				var p=0;
				$("#table tbody:last-child").after(tbodyStyle);
				$("#table tbody:last-child").find(".mark").html(i);
				$("#table tbody:last-child").find(".flag").each(function(){
					$(this).val(data[i][p].value);
					p++;
				});
				if($("#table tbody:last-child").find("#baseFrom").val()==="校内基地"){
					$("#table tbody:last-child").find("#schoolBase").show();
				}
				if($("#table tbody:last-child").find("#baseFrom").val()==="校外基地"){
					$("#table tbody:last-child").find("#outBase").show();
				}
				$("#table tbody:last-child").find(".deleteID").attr("id",data[i][p]);
			}
		}
	});
	$("#Applychart").show();
});
	
	
/*$("#testexmple").click(function(){
	$("#table tbody:last-child").find(".flag").each(function(){
					$(this).val("yes");
					//p++;
				});
});	*/
	
	
	
	
	
	
/*实习申请表里面的操作*/
	//实习基地来源改变，对应的实习基地改变
$(document).on("change","#baseFrom",function(e){
	//$("#practicePlace").empty();
	if(e.target.value==='校内基地'){
		$(this).parent().next().children(":first").show();
		//$("#schoolBase").show();
		$(this).parent().next().children(":last").hide();
	}
	if(e.target.value==='校外基地'){
		$(this).parent().next().children(":last").show();
		$(this).parent().next().children(":first").hide();
	}
	if(e.target.value===''){
		$(this).parent().next().children(":last").hide();
		$(this).parent().next().children(":first").hide();
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
			"<option class='rest' value="+data[0][i].basename+">"+ data[0][i].basename + "</option>"
			);
		}
		for(i=0;i<data[1].length;i++){//获取实习目的下拉框
			$("#aimID").after(
			"<option class='rest' value="+data[1][i].aim+">"+ data[1][i].aim + "</option>"
			);
		}
		for(i=0;i<data[2].length;i++){//获取学院下拉框
			$("#collegeID").after(
			"<option class='rest' value="+data[2][i].collegename+">"+ data[2][i].collegename + "</option>"
			);
		}
	}
});
var name="";
$(document).on("change",".adviser2",function(e){//填写指导老师姓名
	name+=e.target.value+" ";
	$("#adviser").val(name);
});
	
$(document).on("click",".choice",function(){//点击选择弹出 框并且清空框里的数据
	$("#Selectname").modal('show');
	$("#selectTname").val("");
	$("#tester").val("");
	$("#selectCollege").val("");
});
	
//选择学院并且上传学院的名称，放回改学院老师的数据（包含老师名称和老师员工编号）
var obj2;
$(document).on("change","#selectCollege",function(){
	var college=$("#selectCollege").val;
	$.ajax({
	url:" ",
	type:"POST",
	dataType : 'json',
	data:{
		"college":college,
	},
	success : function(data){
		obj2=data;//用于下面函数里面的判断
		for(var i=0;i<data.length;i++){//获取校内基地的实习地点下拉框
			$("#schoolBaseID").after(
			"<option class='rest' value="+data[i].teacherName+">"+ data[i].teacherName + "</option>"
			);
		}
	}
});
});
//将实验员姓名显示在界面中，并且在选择的同时根据实验员的职工编号判断有没有选择同一人
var value=[];
var value2="";
var value3=[];
$(document).on("change","#selectTname",function(e){
	
	$.each(obj2,function(index,item){
		if(item.teacherName===e.target.value){
			if($.inArray(item.teacherID,value3)===-1){
				value.push(e.target.value);
			    value3.push(item.teacherID);
				value2=value.join(" ");
				$("#tester").val(value2);
			}else{
				alert("此人已经存在，请重新选择！");
			}
		}
	});
	
});
	
$(document).on("click","#finished",function(){//点击确定之后讲实验员姓名在表格中显示出来
	$("#testername").val(value2);
	value2="";
});
	
//实习表中添加一条记录

	
//隐藏实习申请表
$(document).on("click","#closemodal",function(){
	
	$("#Applychart").hide();
	
});

$(document).on("click","#add",function(){//增加一条实习记录弹出框的弹出
	$("#addPraItem").find("input").val("");
	$("#addPraItem").find("select").val("");
	
	$("#addPraItem").modal('show');
});
$(document).on("click","#saveadd",function(){//保存一条增加的实习记录
	bootbox.confirm({
			message: "确定保存？",
			size: 'small',
			buttons: {
				confirm: {
					label: '确定',
					className: 'btn-success'
				},
				cancel: {
					label: '取消',
					className: 'btn-danger'
				},
			},
			callback: function (result) {
				if(result){
					$("#addForm").submit();
				}
			}
	});
	$("#addPraItem").modal('hide');
});

$(document).on("click","#addTbody",function(){//添加一条空表的记录
	$("#table tbody:last-child").after(tbodyStyle);
});
	
$(document).on("click",".deleteID",function(){//弹出框里面的记录删除
	var judget=$(this).attr("id");
	if(judget!==""){
		$.ajax({
			url:"",
			type:"POST",
			dataType:"json",
			data:{
				"deleteid":judget
			},
			success : function(msg){
				bootbox.alert({
					message : msg.str,
					size : 'small'
				});
				$(this).closest("tbody").remove();
			}
		});
	}else{
		$(this).closest("tbody").remove();
	}

});	
	
	
$("#save").click(function(){//弹出框的保存
	//$("#PraForm").submit();
	bootbox.confirm({
			message: "确定保存？",
			size: 'small',
			buttons: {
				confirm: {
					label: '确定',
					className: 'btn-success'
				},
				cancel: {
					label: '取消',
					className: 'btn-danger'
				},
			},
			callback: function (result) {
				if(result){
					var str="(";
					var y=0;
					$("#table").find("tbody").each(function(){
						var x=0;
						if(y!==0){
							str=str+',';
						}
						$(this).find(".flag").each(function(){
							if(x!==0){
								str=str+','+'"'+$(this).val()+'"';
							}else{
								str=str+'"'+$(this).val()+'"';
								x++;
							}
							
						});
						str=str+')';
						y++;
					});
					
					$.ajax({
						url:"",
						type:"POST",
						dataType:"json",
						data:{
							"str":str,
							"courseID":obj[Oneindex].courseID
						},
						success : function(msg) {
							bootbox.alert({
								message : msg.str,
								size : 'small'
							});
							//怎样刷新啊？
						}
					});
					
				}
			}
	});
});
//删除表格的中记录	
$("#delete").click(function(){
	var flag=0;
	$('input[name="allcheckbox"]:checked').each(function(){
		flag++;
	});
	if(flag===0){
		bootbox.alert({
			message : "您还没有选择任何内容",
			size : 'small'
		});
	}else{
		bootbox.confirm({
			message: "确定删除？",
			size: 'small',
			buttons: {
				confirm: {
					label: '确定',
					className: 'btn-success'
				},
				cancel: {
					label: '取消',
					className: 'btn-danger'
				},
			},
			callback: function (result) {
				if(result){
					var deletstr = '(';
					var i=0;
					$("input[type='checkbox'][name='allcheckbox']:checked").each(function() {
						if(i!==0){
							deletstr = deletstr+ ','+ $(this).val();
						}else{
							deletstr = deletstr+ $(this).val();
						}
						i++;
					});
					deletstr = deletstr + ')';
					$.ajax({
						url : '',
						type : 'post',
						dataType : 'json',
						data : {
							"deletstr" : deletstr
						},
						success : function(msg) {
							bootbox.alert({
								message : msg.str,
								size : 'small'
							});
							table.draw(false);
						}
					});
				}
			}
	});
	}
});


} );

