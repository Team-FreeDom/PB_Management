// JavaScript Document

						
$(document).ready(function() {
              $('#manageusertable').dataTable(
			  {
				  "processing": true,
        		  "serverSide": true,
				  "bSort": false,
				  "aLengthMenu":[5,10,20,30], //动态指定分页后每页显示的记录数。
					"lengthChange":true, //是否启用改变每页显示多少条数据的控件
					"iDisplayLength" : 10,  //默认每页显示多少条记录
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
											"<option value="+data[i].name+">"
													+ data[i].name + "</option>");
									$("#AworkerclassId").after(
											"<option value="+data[i].name+">"
													+ data[i].name + "</option>");
				
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
												"<option value="+data[i].bname+">"
														+ data[i].bname + "</option>");
										$("#AstatueID").after(
												"<option value="+data[i].bname+">"
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
														+ data[i].dept + "</option>");
										$("#AdivisionID").after(
												"<option value="+data[i].dept+">"
														+ data[i].dept + "</option>");
										i++;
									}
					
								}
					
							});
							
					   $("#save").click(add);
            } );
			
			
				 /*添加数据函数*/
					function add() {
						/*alert("get into the function");*/
									var addJson = {
										"number": $("#name").val(),
										"workerId": $("#workerId").val(),
										"status": $("#status").val(),
										"name": $("#sex").val(),
										"sex": $("#workerclass").val(),
										"borndate": $("#borndate").val(),
										"IDnumber": $("#IDnumber").val(),
										"phone": $("#phone").val(),
										"division": $("#division").val(),
										"password": $("#possword").val(),
									};
									ajax(addJson);
								}
								
					/*详情and修改*/				
					function ajax(obj) {
								var url ="/add.jsp" ;
								if(editFlag){
									url = "/edit.jsp";
								}
								$.ajax({
									url:url ,
									data: {
										"name": obj.name,
										"workerId": obj.position,
										"status": obj.salary,
										"start_date": obj.start_date,
										"office": obj.office,
										"extn": obj.extn
									}, success: function (data) {
										table.ajax.reload();
										$("#myModal").modal("hide");
										$("#myModalLabel").text("新增");
										clear();
										console.log("结果" + data);
									}
								});
							}
								
								
								
								
								
								
								
								
								
								
									
allCkBox2(); 
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
                            }