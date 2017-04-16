// JavaScript Document
var obj=[];
var Oneindex;
var value=[];
var table;
var str = null;
var writeName="";
var showName="";
var teacherString=[];
var majorString=[];


$(document).ready(function() {	
	
	$('[data-toggle="tooltip"]').tooltip();
	
	 table=$("#practiceapplytable").DataTable({
		 "aLengthMenu" : [ 5, 10, 15, 20 ], // 动态指定分页后每页显示的记录数。
		 "lengthChange" : true, // 是否启用改变每页显示多少条数据的控件
		 "bSort" : true,
		 "ordering":true,
		 "bFilter": true,
		 "ordering":true,
		  "dom": 'frtip<"bottom"l>',
		 "iDisplayLength": 10,	
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
	 $.ajax({
			url : 'Checkinfo.do',
			type : 'post',
			async: false,
			dataType : 'json',
		success : function(msg){
			if(msg.msg==0){				
				table=$("#practiceapplytable").DataTable({
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
					   				"render" : function(data,type,row){
										obj.push(row);
										return '<span class="spanstly" id='+(obj.length-1)+'>'+data+'</span>';
									}
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
				$("#daoru_daochu").show();
			}else{
				$("#daoru_daochu").hide();
				bootbox.alert({
				message : msg.msg,
				size : 'small'
			});
			}	
		}
		});
	 
	 
	$("#exportConfirm").click(function(){
		$("#export").modal('hide');
	});	
	 
	
//显示实习申请表
	var tbodyStyle='<tbody class="tbodyID"><tr style="background:#3071a9; color:#FFF">'
		+'<td>序号</td>'
		+'<td>周次<span class="starColor">*</span></td>'
		+'<td>班级<span class="starColor">*</span></td>'
		+'<td>开始时间<span class="starColor">*</span></td>'
		+'<td>结束时间<span class="starColor">*</span></td>'							
		+'<td>实习基地来源<span class="starColor">*</span></td>'
		+'<td>实习地点<span class="starColor">*</span></td>'
		+'<td>实习类别<span class="starColor">*</span></td>'
		+'<td>实习形式<span class="starColor">*</span></td>'
		+'<td>备注</td>'
		+'<td>操作</td>'
	 +'</tr>'
	 +'<tr>'
	 +'<td rowspan="5"><sapn class="mark"></span></td>'
	 +'<td style="padding-top:5px"><select id="weekend" type="text" class="text-center inputWidth flag"><option value="" id="weekend_option">请选择</select></td>'
	 +'<td style="padding-top:5px"><select id="class_one" type="text" class="text-center inputWidth flag"><option value="" id="class_one_option">请选择</select></td>'
	 +'<td style="padding-top:5px"><input id="startweek" type="text"  readonly="readonly" class="flag startweek"></td>'
	 +'<td style="padding-top:5px"><input id="endweek" type="text" readonly="readonly" class="flag endweek"></td>'
	 
	 +'<td style="padding-top:5px"><select name="" id="baseFrom" class="flag"><option id="baseForm" value="">请选择</option></select></td>'
	 +'<td style="padding-top:5px" id="practicePlace"><select id="schoolBase" class="flag" style="display:none;"><option id="schoolBaseID" value="">请选择</option></select><a class="btn btn-primary" href="baseApply.jsp" style="display:none;">添加基地</a></td>'
	 +'<td style="padding-top:5px"><select id="category" class="flag"><option value="">请选择</option><option value="生产实习">生产实习</option><option value="教学实习">教学实习</option><option value="毕业实习">毕业实习</option><option value="综合实习">综合实习</option></select></td>'
	 +'<td style="padding-top:5px"><select name="" id="practiceClass" class="flag"><option value="">请选择</option><option value="集中">集中</option><option value="分散">分散</option></select></td>'
	 +'<td style="padding-top:5px"><input id="remark" type="text" class="flag"></td>'
	 +'<td rowspan="5"><span class="deleteID" id="">删除</span></td>'
	 +'</tr>'
	 +'<tr>'
	 +'<td>实习基地联系人/电话<span class="starColor">*</span></td>'
	 +'<td>目的<span class="starColor">*</span></td>'
	 +'<td>实习经费预算</td>'
	 +'<td colspan="6">实习内容<span class="starColor">*</span></td>'
	 
	 +'</tr>'
	 +'<tr>'
	 
	 +'<td><input id="phone" type="text" class="flag"></td>'
	 +'<td><select id="aim" class="flag" style="width:150px;"><option id="aimID" value="">请选择</option></select></td>'
	 +'<td><input id="budget" type="text" class="inputWidth flag">万</td>'
	 +'<td colspan="6"><input id="content" type="text" class="flag" style="width:100%"></td>'						 
	 +'</tr>'
	 +'<tr>'
	 +'<td colspan="3">面向专业<span class="starColor">*</span></td>'
	 +'<td colspan="3">实验员<span class="starColor">*</span></td>'
	 +'<td colspan="3">指导老师<span class="starColor">*</span></td>'
	 +'</tr>'
	 +'<tr>'
	 +'<td style="padding-bottom:15px" colspan="3"><form class="form-inline"><div class="Iwidth"><div class="input-group Iwidth"><input readonly type="text" class="form-control" id="facemajoy" placeholder="面向专业"><div class="input-group-addon choice3">选择</div></div></div></form></td>'
	 +'<td style="padding-bottom:15px" colspan="3"><form class="form-inline"><div class="Iwidth"><div class="input-group Iwidth"><input readonly type="text" class="form-control" id="tes" placeholder="实验员"><div class="input-group-addon choice">选择</div></div></div></form></td>'
	 +'<td style="padding-bottom:15px" colspan="3"><form class="form-inline"><div class="Iwidth"><div class="input-group Iwidth"><input readonly type="text" class="form-control" id="Tea" placeholder="指导老师"><div class="input-group-addon choice2">选择</div></div></div></form></td>'						 
	 +'</tr></tbody>';
	
$("#practiceapplytable tbody").on("click","tr",function(){
	$("#modalbody").removeClass("modalbody");	
	$("#modalbody").removeClass("modalbody2");
	$("#modalbody").removeClass("modalbody3");
	Oneindex= $(this).find("span").attr("id");
	$("#weekend option:gt(0)").remove();//移除周次select的原来的option
	$("#division").val(obj[Oneindex].college);
	$("#classname").val(obj[Oneindex].coursename);
	$("#major").val(obj[Oneindex].major_oriented);
	$("#fromweek").val(obj[Oneindex].week);
	$("#class").val(obj[Oneindex].composition);
	$("#grade").val(obj[Oneindex].credit);
	$("#number").val(obj[Oneindex].count);
	$("#weeks").val(obj[Oneindex].weekClassify);
	$("#leaderTeacher").val(obj[Oneindex].tname);	
	
	majorString=[];//将面向专业的全局变量清空
	teacherString=[];//将指导老师的全局变量清空
	value=[];//将实验员的全局变量清空
	
	//获得周次的数组
	var fromweek=obj[Oneindex].week;
	var data_week_0=fromweek.split(',');
	var data_week=[];
	var data_week_1;				
	var data_week_3;
	for(var i in data_week_0){
		data_week_1=data_week_0[i].split('-');					
		var k=0;
		data_week_3=data_week_1[0];
		if(data_week_1[0]==data_week_1[data_week_1.length-1]){
			data_week.push(data_week_1[0]);
		}else{
		   while(data_week_3<data_week_1[data_week_1.length-1]){						
			data_week_3=Number(data_week_1[0])+(k++);
			data_week.push(data_week_3);
		}
		}
	}
	$.unique(data_week.sort(sortNumber));
	
	//获得班级的数组
	var composition=obj[Oneindex].composition;
	var data_composition=composition.split(',');
	$.unique(data_composition.sort(sortNumber));
	
	
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
			var majorname="";
			for(var i=0;i<data.length;i++){
				$("#table tbody:last-child").after(tbodyStyle);
				for(var week in data_week){
					$("#table tbody:last-child").find("#weekend_option").after(
							"<option value="+data_week[week]+">"+ data_week[week] + "</option>"
							);
				}	
				for(var composition_0 in data_composition){
					$("#table tbody:last-child").find("#class_one_option").after(
							"<option value="+data_composition[composition_0]+">"+ data_composition[composition_0] + "</option>"
							);
				}	
				$.ajax({
					type : 'POST',
					dataType : 'json',		
					url : 'getBasenameOfType.do',  
					async : false,
					cache : false,
					data:{
						"mid":obj[Oneindex].tid,
						"typename":data[i].source
					},
					error : function(request) {
						bootbox.alert({
							message : "请求异常",
							size : 'small'
						});
					},
					success : function(date){
						
						for(var i=0;i<date[0].length;i++){//获取基地类型
							$("#table tbody:last-child").find("#baseForm").after(
							"<option class='rest' value="+date[0][i].name+">"+ date[0][i].name+ "</option>"
							);
						}
						for(var j=0;j<date[1].length;j++){//获取基地名字
							$("#table tbody:last-child").find("#schoolBaseID").after(
							"<option class='rest' value="+date[1][j]+">"+ date[1][j] + "</option>"
							);
						}
						
						for(var t=0;t<date[2].length;t++){//获取实习目的下拉框
							$("#table tbody:last-child").find("#aimID").after(
									"<option class='rest' id="+date[2][t].id+" value="+date[2][t].aim+" data-placement='top' data-toggle='tooltip' title='"+date[2][t].aim+"'>"+ (date[2][t].aim.length>20?date[2][t].aim.substring(0,20)+"...":date[2][t].aim )+ "</option>"

							);
						}
						
						
					}
				});
				$("#table tbody:last-child").find(".mark").html(i+1);
				$("#table tbody:last-child").find("#weekend").val(data[i].week);
				$("#table tbody:last-child").find("#class_one").val(data[i].grade);//班级
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
				$("#table tbody:last-child").find("#Tea").val("老师:"+data[i].guideTeacher);
				$("#table tbody:last-child").find("#tes").val("实验员:"+data[i].assistant);
				$("#table tbody:last-child").find("#facemajoy").val("面向专业:"+data[i].major_oriented);
				
				$("#table tbody:last-child").find("#schoolBase").show();
				$("#table tbody:last-child").find("#schoolBase").addClass("flag");
				$("#table tbody:last-child").find("#schoolBase").val(data[i].site);	
				
				
				$("#table tbody:last-child").find(".deleteID").attr("id",data[i].id);
				if(i!==data.length-1){
					teachername=teachername+data[i].guideTeacher+",";
					testername=testername+data[i].assistant+",";
					majorname=majorname+data[i].major_oriented+',';
				}else{
					teachername=teachername+data[i].guideTeacher;
					testername=testername+data[i].assistant;
					majorname=majorname+data[i].major_oriented;
				}
				value[i]=data[i].assistant;
				teacherString[i]=data[i].guideTeacher;
				majorString[i]=data[i].major_oriented;
			}
			$("#testername").val(testername);
			$("#adviser").val(teachername);
			$("#major").val(majorname);
		}
	});
	
//获取选择的内容
$.ajax({
	type : 'POST',
	dataType : 'json',		
	url : 'getCollege.do',  
	async : false,
	cache : false,
	error : function(request) {
		bootbox.alert({
			message : "请求异常",
			size : 'small'
		});
	},
	success : function(data){
		for(var i=0;i<data.length;i++){//获取学院下拉框
			$("#collegeID").after(
			"<option class='rest' value="+data[i].dept+">"+ data[i].dept + "</option>"
			);
			$("#collegeID2").after(
					"<option class='rest' value="+data[i].dept+">"+ data[i].dept + "</option>"
					);
			$("#majorcollegeID").after(
					"<option class='rest' value="+data[i].dept+">"+ data[i].dept + "</option>"
					);
		}
	}
});
var tbodylength=$("#table tbody").size();

if(screen.width<=1525&&tbodylength>2){
	$("#modalbody").addClass("modalbody");
}
if(screen.width<=1708&&screen.width>1525&&tbodylength>3){
	$("#modalbody").addClass("modalbody2");
}
if(screen.width>1708&&tbodylength>3){
	$("#modalbody").addClass("modalbody2");
}
	$("#Applychart").show();
});

	
/*实习申请表里面的操作*/
	//实习基地来源改变，对应的实习基地改变
$(document).on("change","#baseFrom",function(e){
	var type=e.target.value;
	var selectObj=$(this).parent().next().children("select");
	var aObj=$(this).parent().next().children("a");
	selectObj.hide();
	selectObj.val("");
	selectObj.find("option:gt(0)").remove();
	aObj.css("display","none");
	
	if(type!=''){	
		$.ajax({
			type : 'POST',
			dataType : 'json',	
			data:{"typename":type},
			url : 'getBasenameOneOfType.do',  
			async : false,
			cache : false,
			error : function(request) {
				bootbox.alert({
					message : "请求异常",
					size : 'small'
				});
			},
			success : function(data){
				if(data.length==0){
					aObj.css("display","block");
					return;
				}else{
					
				for(var j=0;j<data.length;j++){//获取基地名字
					selectObj.find("#schoolBaseID").after(
					"<option class='rest' value="+data[j]+">"+ data[j] + "</option>"
					);
				}
				selectObj.show();
				
				}
			}
		});
	}
});	



//选择学院并且上传学院的名称，放回改学院老师的数据（包含老师名称和老师员工编号）
var obj2;
$(document).on("change","#selectCollege",function(){
	var college=$("#selectCollege").val();
	$("#selectTname option:gt(0)").remove();
	$.ajax({
		url : 'getCollege_Teacher.do',
		type : 'post',
		dataType : 'json',
		data : {
			"college" : college,								
		},
	success : function(data){
		obj2=data;//用于下面函数里面的判断
		for(var i=0;i<data.length;i++){//获取老师名字下拉框
			$("#teacherNmaeID").after(
			"<option class='rest' value="+data[i].name+">"+ data[i].name + "</option>"
			);
		}
	}
});
});

$(document).on("change","#majorCollege",function(){
	var college=$("#majorCollege").val();
	$("#majorName option:gt(0)").remove();
	$.ajax({
		url : 'getCollege_Major.do',
		type : 'post',
		dataType : 'json',
		data : {
			"college" : college,								
		},
	success : function(data){	
		for(var i=0;i<data.length;i++){//获取老师名字下拉框
			$("#majorNmaeID").after(
			"<option class='rest' value="+data[i].major+">"+ data[i].major + "</option>"
			);
		}
	}
});
});

$(document).on("change","#selectCollege2",function(){
	var college=$("#selectCollege2").val();
	$("#selectTname2 option:gt(0)").remove();
	$.ajax({
		url : 'getCollege_Teacher.do',
		type : 'post',
		dataType : 'json',
		data : {
			"college" : college,								
		},
	success : function(data){
		
		for(var i=0;i<data.length;i++){//获取老师名字下拉框
			$("#teacherNmaeID2").after(
			"<option class='rest' value="+data[i].name+">"+ data[i].name + "</option>"
			);
		}
	}
});
});

var selectNum;
$(document).on("click",".choice2",function(){//点击选择弹出 
	selectNum=$(this).closest("tbody").find(".mark").html()-1;
	$("#Selectteacher").modal('show');
	$("#selectCollege2").val("");
	$("#leadteachername").val(teacherString[selectNum]);
	$("#selectTname2").val("");
});

$(document).on("change","#selectTname2",function(e){//将指导老师姓名显示在界面中
	var teststring=$("#leadteachername").val();
	var testvalue=teststring.split(" ");
	testvalue.push(e.target.value);	
	teststring=testvalue.join(" ");
	$("#leadteachername").val(teststring);
	
});

$(document).on("click","#finished2",function(){//点击确定之后讲指导老师姓名在表格中显示出来
	var tester=$("#leadteachername").val();
	if(tester===""){
		bootbox.alert({
			message : "指导老师不能为空",
			size : 'small'
		});
		return;
	}else{
		teacherString[selectNum]=tester;
	}
	$(".tbodyID").each(function(){
		var tea=$(this).find('.mark').html()-1;
		if(tea===selectNum){
			$(this).find('#Tea').val("老师："+tester);
		}
	});
	var str=teacherString.join(',');
	$("#adviser").val(str);
});
var value3=[];
$(document).on("click",".choice",function(){//点击选择弹出 
	
	selectNum=$(this).closest("tbody").find(".mark").html()-1;
	$("#Selectname").modal('show');
	$("#selectTname").val("");
	$("#tester").val(value[selectNum]);
	$("#selectCollege").val("");
});
	
$(document).on("change","#selectTname",function(e){//将实验员姓名显示在界面中，并且在选择的同时根据实验员的职工编号判断有没有选择同一人
	var teststring=$("#tester").val();
	var testvalue=teststring.split(" ");
	testvalue.push(e.target.value);
	teststring=testvalue.join(" ");
	$("#tester").val(teststring);
	
	
});
	
$(document).on("click","#finished",function(){//点击确定之后讲实验员姓名在表格中显示出来
	var tester=$("#tester").val();
	if(tester===""){
		bootbox.alert({
			message : "实验员不能为空",
			size : 'small'
		});
		return;
	}else{
		value[selectNum]=tester;
	}
	$(".tbodyID").each(function(){
		var tea=$(this).find('.mark').html()-1;
		if(tea===selectNum){
			$(this).find('#tes').val("实验员："+tester);
		}
	});
	var str=value.join(',');
	$("#testername").val(str);
});

var major_num;
$(document).on("click",".choice3",function(){//点击选择弹出面向专业的弹出框
	
	major_num=$(this).closest("tbody").find(".mark").html()-1;
	$("#Selectmajor").modal('show');
	$("#showmajor").val(majorString[major_num]);
	$("#majorName").val("");
	$("#majorCollege").val("");
});

$(document).on("change","#majorName",function(e){//将专业显示在界面中
	var teststring=$("#showmajor").val();
	var testvalue=teststring.split(" ");
	testvalue.push(e.target.value);
	teststring=testvalue.join(" ");
	$("#showmajor").val(teststring);
	
	
});
	
$(document).on("click","#finished3",function(){//点击确定之后将专业在表格中显示出来
	var showmajor=$("#showmajor").val();
	if(showmajor===""){
		bootbox.alert({
			message : "面向专业不能为空",
			size : 'small'
		});
		return;
	}else{
		majorString[major_num]=showmajor;
	}
	$(".tbodyID").each(function(){
		var tea=$(this).find('.mark').html()-1;
		if(tea===major_num){
			$(this).find('#facemajoy').val("面向专业："+showmajor);
		}
	});
	var str=majorString.join(',');
	$("#major").val(str);
});
//实习表中添加一条记录

	
//隐藏实习申请表
$(document).on("click","#closemodal",function(){
	
	$("#Applychart").hide();
	$(".tbodyID").remove();
	calendar.hide();
	teacherString=[];
	value=[];
});




$(document).on("click","#addTbody",function(){//添加一条空表的记录
	var tbodylength=$("#table tbody").size();
	if(screen.width<=1525&&tbodylength>2){
		$("#modalbody").addClass("modalbody");
	}
	if(screen.width<=1708&&screen.width>1525&&tbodylength>3){
		$("#modalbody").addClass("modalbody2");
	}
	if(screen.width>1708&&tbodylength>3){
		$("#modalbody").addClass("modalbody3");
	}
	$("#table tbody:last-child").after(tbodyStyle);
	
	var fromweek=$("#fromweek").val();
	var data_week_0=fromweek.split(',');
	var data_week=[];
	var data_week_1;				
	var data_week_3;
	for(var i in data_week_0){
		data_week_1=data_week_0[i].split('-');					
		var k=0;
		data_week_3=data_week_1[0];
		if(data_week_1[0]==data_week_1[data_week_1.length-1]){
			data_week.push(data_week_1[0]);
		}else{
		   while(data_week_3<data_week_1[data_week_1.length-1]){						
			data_week_3=Number(data_week_1[0])+(k++);
			data_week.push(data_week_3);
		}
		}
	}
	$.unique(data_week.sort(sortNumber));
	for(var week in data_week){
		$("#table tbody:last-child").find("#weekend_option").after(
				"<option value="+data_week[week]+">"+ data_week[week] + "</option>"
				);
	}	
	
	//获得班级的数组
	var composition=$("#class").val();
	var data_composition=composition.split(',');
	$.unique(data_composition.sort(sortNumber));
	for(var composition_0 in data_composition){
		$("#table tbody:last-child").find("#class_one_option").after(
				"<option value="+data_composition[composition_0]+">"+ data_composition[composition_0] + "</option>"
				);
	}	
	
	var tbNum=$("#table").children('tbody').length;
	$("#table tbody:last-child").find(".mark").html(tbNum-2);
	$.ajax({
		type : 'POST',
		dataType : 'json',		
		url : 'getPlanAim.do',  
		async : false,
		cache : false,
		data:{
			"mid":obj[Oneindex].tid
		},
	success : function(data){//
		
		for(i=0;i<data[0].length;i++){//获取实习目的下拉框
			$("#table tbody:last-child").find("#aimID").after(
			"<option class='rest' id="+data[0][i].id+" value="+data[0][i].aim+" data-placement='top' data-toggle='tooltip' title='"+data[0][i].aim+"'>"+ (data[0][i].aim.length>20?data[0][i].aim.substring(0,20)+"...":data[0][i].aim )+ "</option>"

			);
		}
		for(var i=0;i<data[1].length;i++){//获取基地类型
			$("#table tbody:last-child").find("#baseForm").after(
			"<option class='rest' value="+data[1][i].name+">"+ data[1][i].name+ "</option>"
			);
		}
		
	}
});
});

/*$(document).on("show.bs.tooltip","[data-toggle=tooltip]",function(){
	//alert("yes");
	m_tooltip.SetMaxTipWidth(600);
	
});*/
$(document).on("click",".deleteID",function(){//弹出框里面的记录删除
	var judget=$(this).attr("id");
	var rowNum=$(this).closest("tbody").find(".mark").html()-1;
	var tbody=$(this).closest("tbody");
	bootbox.confirm({
		message: "是否删除？",
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
			tbody.remove();
			if(result){			
				if(judget!==""){
					$.ajax({
						url:'deleteClassRecord.do',
						type:"POST",
						dataType:"json",
						data:{
							"planid":judget
						},
						success : function(msg){
							$(".mark").each(function(){
							var htmlValue=$(this).html();
							if(htmlValue>(rowNum+1)){
								$(this).html(htmlValue-1);
								}
							});
							teacherString.splice(rowNum,1);
							showName=teacherString.join(",");
							$("#adviser").val(showName);
							value.splice(rowNum,1);
							var value2=value.join(",");
							$("#testername").val(value2);
							bootbox.alert({
								message : "删除成功",
								size : 'small'
							});											
						}
					});
				}else{
					$(".mark").each(function(){
						var htmlValue=$(this).html();
						if(htmlValue>(rowNum+1)){
							$(this).html(htmlValue-1);
						}
					});
					teacherString.splice(rowNum,1);
					showName=teacherString.join(",");
					$("#adviser").val(showName);
					value.splice(rowNum,1);
					var value2=value.join(",");
					$("#tester").val(value2);
				}
			}
		}
	});
	

});	

//点击导出按钮复原原来的值
$("#exportButton").click(function(){
	$("#finishCondition").val('-1');
});
	
	
$("#save").click(function(){//弹出框的保存
	var x=0;
	var y=0;
	var week="";        var startweek="";
	var endweek="";     var content="";
    var category="";
	var practiceClass="";     var phone="";
	var aim="";     var Tea="";
	var tes="";
	var major="";
	var class_one="";
	var reg=/^[1-9][0-9]*$/;
	$(".tbodyID").each(function(){
		y++;
		week=$(this).find("#weekend").val();
		if(week===""){
			return false;
		}
		
		class_one=$(this).find("#class_one").val();
		if(class_one===""){
			return false;
		}
		
				
		startweek=$(this).find("#startweek").val();
		if(startweek===""){
			return false;
		}
		
		endweek=$(this).find("#endweek").val();
		if(endweek===""){
			return false;
		}
		
		content=$(this).find("#content").val();
		if(content===""){
			return false;
		}
		
		var sSite=$(this).find("#schoolBase").val();		
		if(sSite===""){
			x++;
			return false;
		}
		
		category=$(this).find("#category").val();
		if(category===""){
			return false;
		}
		
		practiceClass=$(this).find("#practiceClass").val();
		if(practiceClass===""){
			return false;
		}
		
		phone=$(this).find("#phone").val();
		if(phone===""){
			return false;
		}
		
		aim=$(this).find("#aim").val();
		if(aim===""){
			return false;
		}
		
		major=$(this).find("#facemajoy").val();
		if(major===""){
			return false;
		}
		
		Tea=$(this).find("#Tea").val();
		if(Tea===""){
			return false;
		}
		
		tes=$(this).find("#tes").val();
		if(tes===""){
			return false;
		}
	});
	
	if(week===""){
		bootbox.alert({
			message : "请填写第"+y+"条记录的实习周次",
			size : 'small'
		});
		return;
		}
	
	if(class_one===""){
		bootbox.alert({
			message : "请填写第"+y+"条记录的班级",
			size : 'small'
		});
		return;
		}
	
	if(startweek===""){
		bootbox.alert({
			message : "请填写第"+y+"条记录的开始时间",
			size : 'small'
		});
		return;
		}
	if(endweek===""){
		bootbox.alert({
			message : "请填写第"+y+"条记录的结束时间",
			size : 'small'
		});
		return;
		}
	var start=startweek.split("-");
	var end=endweek.split("-");
	var time=0;
	if((end[0]-start[0])<0){
		time++;
	}else{
		if(end[0]===start[0]){
			if((end[1]-start[1])<0){
				time++;
			}else{
				if(end[1]===start[1]){
				   if((end[2]-start[2]<0)){
					   time++;
				   }
				   }
			}
		}
	}
	if(time!==0){
		bootbox.alert({
			message : "第"+y+"条记录的结束时间有错误",
			size : 'small'
		});
		return;
	}
	if(content===""){
		bootbox.alert({
			message : "请填写第"+y+"条记录的实习内容",
			size : 'small'
		});
		return;
		}
	if(x!==0){
		bootbox.alert({
			message : "请填写第"+y+"条记录的实习地点",
			size : 'small'
		});
		return;
	}
	
	if(category===""){
		bootbox.alert({
			message : "请选择第"+y+"条记录的实习类别",
			size : 'small'
		});
		return;
		}
	if(practiceClass===""){
		bootbox.alert({
			message : "请选择第"+y+"条记录的实习形式",
			size : 'small'
		});
		return;
		}
	if(phone===""){
		bootbox.alert({
			message : "请填写第"+y+"条记录的联系电话",
			size : 'small'
		});
		return;
		}
	if(aim===""){
		bootbox.alert({
			message : "请选择第"+y+"条记录的实习目的",
			size : 'small'
		});
		return;
		}
	if(major===""){
		bootbox.alert({
			message : "请选择第"+y+"条记录的面向专业",
			size : 'small'
		});
		return;
		}
	if(Tea===""){
		bootbox.alert({
			message : "请选择第"+y+"条记录的指导老师",
			size : 'small'
		});
		return;
		}
	if(tes===""){
		bootbox.alert({
			message : "请选择第"+y+"条记录的实验员",
			size : 'small'
		});
		return;
		}
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
					$(".tbodyID").each(function(){
						if(y!==0){
							str=str+",(";
						}						
						var c=$(this).find(".mark").html()-1;
						str=str+"'"+majorString[c]+"','"+teacherString[c]+"'"+",'"+value[c]+"'";
						
						var x=0;
						$(this).find(".flag").each(function(){
							x++;
							if(x===1){
								if($(this).val()===""){
								str=str+','+"null";
								}else{
									str=str+","+$(this).val();
								}
							}
							if(x<=10&&x>1){
								if($(this).val()===""){
								str=str+','+"null";
								}else{
									str=str+","+"'"+$(this).val()+"'";
								}
							}
							if(x===11){
								str=str+","+$(this).find("option:selected").attr("id");
							}
							if(x===12||x===13){
								if($(this).val()===""){
								str=str+','+"null";
								}else{
									str=str+","+"'"+$(this).val()+"'";
								}
							}
							
						});
						str=str+","+obj[Oneindex].id+",'"+obj[Oneindex].semester+"'"+")";
						y++;
					});
					$.ajax({
						type : 'POST',
						dataType : 'json',		
						url : 'savePlanModify.do',  
						async : false,
						cache : false,
						error : function(request) {
							bootbox.alert({
								message : "保存失败",
								size : 'small'
							});
						},
						data:{
							"courseID":obj[Oneindex].id,							
							"str":str,
						},
						success : function(msg) {							
							bootbox.alert({
								message : "保存成功",
								size : 'small'
							});
							//$("#Applychart").hide();
							
						}
					});
					
				}
			}
	});
});

var timeArray=0;
$(document).on("focus","#startweek",function(){
	timeArray=$(this).closest("tbody").find(".mark").html()-1;
	var o=0,p=0;
	$(".startweek").each(function(){
		if(o===timeArray){
			$("#Stime").val($(this).val());
			return false;
		}
		o++;
	});
	$(".endweek").each(function(){
		if(p===timeArray){
			$("#Etime").val($(this).val());
			return false;
		}
		p++;
	});
	
	$("#time").modal('show');
});
$(document).on("focus","#endweek",function(){
	timeArray=$(this).closest("tbody").find(".mark").html()-1;
	var o=0,p=0;
	$(".startweek").each(function(){
		if(o===timeArray){
			$("#Stime").val($(this).val());
			return false;
		}
		o++;
	});
	$(".endweek").each(function(){
		if(p===timeArray){
			$("#Etime").val($(this).val());
			return false;
		}
		p++;
	});
	$("#time").modal('show');
});
	
$(document).on("click","#finishTime",function(){
	var o=0,p=0;
	$(".startweek").each(function(){
		if(o===timeArray){
			$(this).val($("#Stime").val());
			return false;
		}
		o++;
	});
	$(".endweek").each(function(){
		if(p===timeArray){
			$(this).val($("#Etime").val());
			return false;
		}
		p++;
	});
	$("#time").modal('hide');
});	

} );

function sortNumber(a,b)
{
	return a<b?a:b;
}

	
	

