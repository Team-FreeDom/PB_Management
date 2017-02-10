// JavaScript Document
var obj=[];
var Oneindex;
$(document).ready(function() {
	
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
						 +'<td><input type="text" value="" class="adviser2 inputWidth flag"></td>'
						 +'<td><button type="button" class="btn btn-primary choice" value="">选择</button></td>'
						 +'<td><span class="deleteID" id="">删除</span></td>'
						 +'</tr></tbody>';
	
$("#practiceapplytable tbody").on("click","tr",function(){
	/*Oneindex= $(this).find("span").attr("id");
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
			
			var teachername="";
			var testername="";
			for(var i=0;i<data.length;i++){
				var p=2;//头两个用来存指导老师和实验员
				$("#table tbody:last-child").after(tbodyStyle);
				$("#table tbody:last-child").find(".mark").html(i+1);
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
				$("#table tbody:last-child").find(".deleteID").attr("id",data[i][p].value);
				if(i!==data.length-1){
					teachername=teachername+data[i][0].value+"，";
					testername=testername+data[i][1].value+"，";
				}else{
					teachername=teachername+data[i][0].value;
					testername=testername+data[i][1].value;
				}
				
			}
			$("#testername").val(teachername);
			$("#adviser").val(testername);
		}
	});
	$("#Applychart").show();
});
	
	
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
	
var writeName="";
var showName="";
var currentName="";
var teacherString;
$(document).on("change",".adviser2",function(e){//填写指导老师姓名
	var rowNum=$(this).closest("tbody").find(".mark").html()-1;
	teacherString=showName.split(",");
	writeName=e.target.value;
	if(writeName===""){
		teacherString[rowNum]="null";
	}else{
		teacherString[rowNum]=writeName;
	}
	
	showName=teacherString.join(",");
	$("#adviser").val(showName);
	currentName="";

});
$(document).on("focus",".adviser2",function(e){
	showName=$("#adviser").val();
	currentName=e.target.value;
	writeName="";
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
var selectNum;
var value=[];
var value2="";
var value3=[];
$(document).on("click",".choice",function(){//点击选择弹出 
	
	selectNum=$(this).closest("tbody").find(".mark").html()-1;
	$("#Selectname").modal('show');
	$("#selectTname").val("");
	$("#tester").val(value[selectNum]);
	$("#selectCollege").val("");
});
	
$(document).on("change","#selectTname",function(e){//将实验员姓名显示在界面中，并且在选择的同时根据实验员的职工编号判断有没有选择同一人
	var testString=$("#testername").val;
	value=testString.split(",");
	$.each(obj2,function(index,item){
		if(item.teacherName===e.target.value){
			if($.inArray(item.teacherID,value3)===-1){
				value[selectNum]=e.target.value;
			    value3.push(item.teacherID);
				value2=value.join(",");
				$("#tester").val(value2);
			}else{
				bootbox.alert({
					message : msg.str,
					size : 'small'
				});
			}
		}else{
			value[selectNum]=e.target.value;
			value3.push(item.teacherID);
			value2=value.join(",");
			$("#tester").val(value2);
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




$(document).on("click","#addTbody",function(){//添加一条空表的记录
	$("#table tbody:last-child").after(tbodyStyle);
	var tbNum=$("#table").children('tbody').length;
	$("#table tbody:last-child").find(".mark").html(tbNum-1);
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
		var rowNum=$(this).closest("tbody").find(".mark").html()-1;
		$(".mark").each(function(){
			var htmlValue=$(this).html();
			if(htmlValue>(rowNum+1)){
				$(this).html(htmlValue-1);
			}
		});
		teacherString.splice(rowNum,1);
		showName=teacherString.join(",");
		$("#adviser").val(showName);
		value3.splice(rowNum,1);
		value2=value.join(",");
		$("#tester").val(value2);
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
					/*obj[Oneindex].courseID,obj[Oneindex].termYear
					var p="courceid";
					var q="year";*/
					var str='("'+obj[Oneindex].courseID+'",'+'"'+obj[Oneindex].termYear+'"';
					var str2=str;
					alert(str);
					var y=0;
					$(".tbodyID").each(function(){
						var c=$(this).find(".mark").html()-1;
						str=str+',"'+value[c]+'"';
						if(y!==0){
							str=str+','+str2;
						}
						$(this).find(".flag").each(function(){
							if($(this).val()===""){
								str=str+','+'"null"';
							}else{
								str=str+','+'"'+$(this).val()+'"';
							}
								
						});
						str=str+')';
						y++;
					});
					//alert(str);
					
					$.ajax({
						url:"",
						type:"POST",
						dataType:"json",
						data:{
							"str":str,
							"courseID":obj[Oneindex].courseID,
							"termYear":obj[Oneindex].termYear,
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

} );

	
	

