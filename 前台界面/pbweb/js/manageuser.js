// JavaScript Document
function a(){
	alert("on")
	}						
$(document).ready(function() {
	
			////全选反选
			var flag=0;
				$("#ck1").on("click", function () {
					if ($(this).prop("checked") === true) {
						$("#manageusertable input[name='allcheckbox']").prop("checked", true);
						
					} else {
						$("#manageusertable input[name='allcheckbox']").prop("checked", false);
						
					}
					$("#ck2").prop("checked", false);
				 });
				 
				  $("#ck2").click(function () {//反选  
                		$("#manageusertable input[name='allcheckbox']").each(function () {  
                    	$(this).prop("checked", !$(this).prop("checked"));
						 
                	}); 
					$("#ck1").prop("checked", false); 
           		 }); 
				 
				/* $("#add").click(function () {
					 
           		 }); */
				 
				 $("#ZJ").click(function(){
					 
					 $("#workerId").val("");
			        $("#sex1").prop("checked",false);
					$("#sex2").prop("checked",false);
			        $("#IDnumber").val("");
			        $("#password").val("");
					$("#Adivision").val("1");
					$("#Awkclass").val("1");
					$("#Astatus").val("1");
					 })
					 
					 
				
					 
				 $("#delete").click(function () {  
                		$("#manageusertable input[name='allcheckbox']").each(function () {
							if($(this).prop("checked")==true){
								flag=1;
								}
                	}); 
					
					if(flag==0){
								alert("请先选择");
								} 
           		 });
				 
				 $("#save").click(function(){
					 
					if($("#workerId").val()==""){
						alert("请填写员工编号");
						}
					else if($("#name").val()==""){
						alert("请填写姓名");
						}
					else if($("#sex1").prop("checked") === false && $("#sex2").prop("checked") === false){
						alert("请填写性别");
						}
			       
					else if($("#Awkclass").val()=="1"){
						alert("请选择员工类别");
						}
					else if($("#Astatus").val()=="1"){
						alert("请选择身份属性");
						}
					else if($("#Adivision").val()=="1"){
						alert("请选择部门");
						}
					else if($("phone").val()==""){
						alert("请填写电话号码");
						}
					else if($("#IDnumber").val()==""){
						alert("你填写身份证");
						}
					else if($("#password").val()==""){
						alert("你填写密码！！！");
						}
					 })
				 
              var table =$('#manageusertable').dataTable(
			  {
				  "processing": true,
        		  "serverSide": true,
				  "bSort": false,
				  "aLengthMenu":[5,10,20,30], //动态指定分页后每页显示的记录数。
					"lengthChange":true, //是否启用改变每页显示多少条数据的控件
					"iDisplayLength" : 5,  //默认每页显示多少条记录
					"dom":'ftipr<"bottom"l>',
					"ajax" : {
							"url" : "landRentInfo.do",
							"type" : "POST"
						},  
					columns: [
						{"data": "number"},
						{"data": "workerId"},
						{"data": "status"},
						{"data": "name"},
						{"data": "sex"},
						{"data": "workerclass"},
						{"data": "borndate"},
						{"data": "IDnumber"},
						{"data": "phone"},
						{"data": "division"},
						{"data": "run",
						"render" : function(data, type, row) { //render改变该列样式,4个参数，其中参数数量是可变的。
								return data = '<button type="button" class="btn btn-warning btn-xs" data-id='+data+' data-toggle="modal" data-target="#edit">修改</button> <button type="button" class="btn btn-danger btn-xs">删除</button>';
								}
						}
					],
					
					columnDefs : 
					[{
						"orderable" : false, // 禁用排序
						"targets" : [0], // 指定的列
						"data" : "number",
						"render" : function(data, type, row) {
							data=row.run;
							return '<input type="checkbox" class="ck" value="'+ data + '" name="idname"  />';
						}
					}],
					
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
					  
					  //获取员工类别
						 $.ajax({
							type : 'POST',
							dataType : 'json',
							url : 'workerclassInfoR.do',
							async : false,
							cache : false,
							error : function(request) {
								alert("error");
							},
							success : function(data) {
								var i = 0;
								for ( var item in data) {
				
									$("#EworkerclassId").after(
											"<option value="+data[i].classname+">"
													+ data[i].classname + "</option>");
									$("#AworkerclassId").after(
											"<option value="+data[i].classID+">"
													+ data[i].classname + "</option>");
				
									i++;
								}
				
							}
				
						}); 
						
						//获取身份属性
							$.ajax({
								type : 'POST',
								dataType : 'json',
								url : 'EstatueIDInfoR.do',
								async : false,
								cache : false,
								error : function(request) {
									alert("error");
								},
								success : function(data) {
									var i = 0;
									for ( var item in data) {
										$("#EstatueID").after(
												"<option value="+data[i].statuename+">"
														+ data[i].bname + "</option>");
										$("#AstatueID").after(
												"<option value="+data[i].statuename+">"
														+ data[i].bname + "</option>");
										i++;
									}
					
								}
					
							});
							//获取部门信息
							$.ajax({
								type : 'POST',
								dataType : 'json',
								url : 'selectCo.do',
								async : false,
								cache : false,
								error : function(request) {
									alert("error");
								},
								success : function(data) {
									var i = 0;
									for ( var item in data) {
										$("#EdivisionID").after(
												"<option value="+data[i].dept+">"
														+ data[i].divisionname + "</option>");
										$("#AdivisionID").after(
												"<option value="+data[i].dept+">"
														+ data[i].divisionname + "</option>");
										i++;
									}
					
								}
					
							});
							
					   $("#save").click(add);
            } );
			
			
				 /*添加数据函数*/
					function add() {
						/*alert("get into the function");*/
						var status=document.getElementById("Astatus").value;
						var workerclass=document.getElementById("Aworkerclass").value;
						var division=document.getElementById("Adivision").value;
									var addJson = {
										"number": $("#Anumber").val(),
										"workerId": $("#AworkerId").val(),
										"status": status,
										"sex": $("#sex").val(),
										"workerclass": workerclass,
										"borndate": $("#Aborndate").val(),
										"IDnumber": $("#AIDnumber").val(),
										"phone": $("#Aphone").val(),
										"division": division,
										"password": $("#Apossword").val(),
									};
									ajax(addJson);
								}
								
					function ajax(obj) {
								var url ="/add.jsp" ;
								/*if(editFlag){
									url = "/edit.jsp";
								}*/
								$.ajax({
									url:url ,
									data: {
										"name": obj.name,
										"workerId": obj.workId,
										"status": obj.status,
										"sex": obj.sex,
										"workerclass": obj.workerclass,
										"borndate": obj.borndate,
										"IDnumber": obj.IDnumber,
										"phone": obj.phone,
										"division": obj.phone,
										"password": obj.phone,
									}, success: function (data) {
										table.ajax.reload();
										$("#add").modal("hide");
										clear();
									}
								});
							}
								
					/*详情and修改*/
					
					function theEdit(obj) {
									var lr_id = obj.id;
									$.ajax({
										type : 'POST',
										data : {
											"run" : run
										},
										dataType : 'json',
										url : 'getSingleRentInfo.do',
										async : false,
										cache : false,
										error : function(request) {
											alert("error");
										},
										success : function(data) {
								
											var i = 0;
											for ( var item in data) {
								
												$("#Ename").val(data[i].name);
												$("#Esex").val(data[i].sex);
												$("#EworkerId").val(data[i].workerId);
												$("#Eworkerclass option[value=" + data[i].wkclass + "]").attr(
														"selected", true);
								
												$("#Estatus option[value=" + data[i].sta + "]").attr(
														"selected", true);
												$("#Edivision option[value=" + data[i].divi + "]").attr(
														"selected", true);
												$("#Eborndate").val(data[i].borndate);
												$("#EIDnumber").val(data[i].IDnumber);
												$("#Ephone").val(data[i].phone);
												$("#Epassword").val(data[i].password);
												i++;
											}
								
										}
								
									});
								}
						function getvalue() {
						/*alert("get into the function");*/
						var status=document.getElementById("Estatus").value;
						var workerclass=document.getElementById("Eworkerclass").value;
						var division=document.getElementById("Edivision").value;
									var editJson = {
										"number": $("#Enumber").val(),
										"workerId": $("#EworkerId").val(),
										"status": status,
										"sex": $("#sex").val(),
										"workerclass": workerclass,
										"borndate": $("#Eborndate").val(),
										"IDnumber": $("#EIDnumber").val(),
										"phone": $("#Ephone").val(),
										"division": division,
										"password": $("#Epossword").val(),
									};
									ajaxedit(editJson);
								}
								
					function ajaxedit(obj) {
								var url = "/edit.jsp";
								$.ajax({
									url:url ,
									data: {
										"name": obj.name,
										"workerId": obj.workId,
										"status": obj.status,
										"sex": obj.sex,
										"workerclass": obj.workerclass,
										"borndate": obj.borndate,
										"IDnumber": obj.IDnumber,
										"phone": obj.phone,
										"division": obj.phone,
										"password": obj.phone,
									}, success: function (data) {
										table.ajax.reload();
										$("#edit").modal("hide");
										clear();
									}
								});
							}

	
				
					
								
								
								
								
								
								
								
								
								
								
									
/*allCkBox2(); 
		function allCkBox2(id){
        var tableBox = document.getElementById(id||"manageusertable"),
             ck = tableBox.getElementsByClassName("ck"),
             ckAll = tableBox.getElementsByClassName("ck-all")[0],
             ckRe = tableBox.getElementsByClassName("ck-re")[0];
             ckAll.onchange = function(){
                                    allCk(this.checked);
                                };
              ckRe.onchange = function(){
                                    reCk();
                                };
        function allCk(bool){
                            for(var i =0; i<ck.length;i++){
                                   ck[i].checked = bool;
                                    }
                             }
                        
         function reCk(){
                         for(var i =0; i<ck.length;i++){
                                ck[i].checked ? ck[i].checked = false : ck[i].checked = true;
                                    }
                                }
                            }*/