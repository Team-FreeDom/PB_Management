//所有未审核申请记录
    $(document).ready(function() {
	////全选反选
	$("#ck1").on("click", function () {
		if ($(this).prop("checked") === true) {
			$("#tableCheck input[name='idname']").prop("checked", true);
		} else {
			$("#tableCheck input[name='idname']").prop("checked", false);
		}
     });
	 $("#ck2").on("click", function () {
		if ($(this).prop("checked") === true) {
			$("#tablePay input[name='idname']").prop("checked", true);
		} else {
			$("#tablePay input[name='idname']").prop("checked", false);
		}
     });
	$(".icon-filter").on("click", function () {
		$('.hide_ul').toggle(500);
	});
	 $('#PayM').click(function() {		       
		 repage.draw(true);		         
	 });
	 $('#NoCheck').click(function() {		       
		 Spage.draw(true);		         
	});
  //拒绝申请
	$('#deleteOne').click(function() {
		var record_str='(';////申请记录id格(1,2,3,4,5)
		var info_str='['; //////消息格式[{userid:"180042",msg:"长安基地#821321"},{userid:"180043",msg:"长安基地#845621"}]
		var jsonback='';/*json对象格式{
						              recordstr:'(1,2,3,4,5)',
						              infostr:[{userid:"180042",msg:"长安基地#821321"},{userid:"180043",msg:"长安基地#845621"}]
									  }*/
		var i=0;
		var userid='180052';////表中缺少此字段，需要补充
		
		$("input[type='checkbox'][name='checkList']:checked").each(function() {
			if(i!=0)					
			{
				record_str = record_str+','+$(this).val();
				info_str=info_str+',{userid:"'+userid+'",msg:"'+$(this).closest('tr').find('td:eq(3)').text()+'#'+$(this).closest('tr').find('td:eq(4)').text()+'"}'
			}
			else
			{
				record_str = record_str+$(this).val();
				info_str=info_str+'{userid:"'+userid+'",msg:"'+$(this).closest('tr').find('td:eq(3)').text()+'#'+$(this).closest('tr').find('td:eq(4)').text()+'"}'
			}
			i++;
		});
		record_str=record_str+')';
		info_str=info_str+']';
		jsonback="{recordstr:'"+record_str+"',infostr:"+info_str+"}";		
		$.ajax({
			url: 'refuseApply.do?flag=1',
			type: 'post',
			data: jsonback,
			success: function(msg) {
				//////////将所有的alert替换成以下/////////////////////////////////////////////////////////////////////////////////////统一风格/////////////////////////////
				bootbox.alert({
					  message: msg,
					  size: 'small'
				  });
				Spage.draw(false);
			}
		});
	});
  //同意申请
  ////////////状态值1：  2：  3：    4：    。。。。。。。
	$('#agreeOne').click(function() {
		var landid_str='(';////土地编号格式("1","2","3","4","5")
		var record_str='(';////申请记录id格式(1,2,3,4,5)
		var info_str='['; //////消息格式[{userid:"180042",msg:"长安基地#821321"},{userid:"180043",msg:"长安基地#845621"}]
		var jsonback='';/*json对象格式{
			                          landstr:'("1","2","3","4","5")',
						              recordstr:'(1,2,3,4,5)',
						              infostr:[{userid:"180042",msg:"长安基地#821321"},{userid:"180043",msg:"长安基地#845621"}]
									  }*/
		var i=0;
		var userid='180052';////表中缺少此字段，需要补充
		$("input[type='checkbox'][name='checkList']:checked").each(function() {
			if(i!=0)					
			{
				record_str = record_str+','+$(this).val();
				landid_str = landid_str+',"'+$(this).closest('tr').find('td:eq(4)').text()+'"';
				info_str=info_str+',{userid:"'+userid+'",msg:"'+$(this).closest('tr').find('td:eq(3)').text()+'#'+$(this).closest('tr').find('td:eq(4)').text()+'"}'
			}
			else
			{
				record_str = record_str+$(this).val();
				landid_str = landid_str+'"'+$(this).closest('tr').find('td:eq(4)').text()+'"';
				info_str=info_str+'{userid:"'+userid+'",msg:"'+$(this).closest('tr').find('td:eq(3)').text()+'#'+$(this).closest('tr').find('td:eq(4)').text()+'"}'
			}
			i++;
		});
		landid_str=landid_str+')';
		record_str=record_str+')';
		info_str=info_str+']';
		jsonback="{landstr:'"+landid_str+"',recordstr:'"+record_str+"',infostr:"+info_str+"}";
		$.ajax({
			url: 'agreeApply.do?flag=0',
			type: 'post',
			data: jsonback,
			success: function(msg) {
				bootbox.alert({
					  message: msg,
					  size: 'small'
				  });
				Spage.draw(false);
			}
		});
	});
	/////////////////////////////参照上面方法，修改‘取消交费’和'确认交费'/////////////////////////////////////////////
       //取消交费
	$('#cancel').click(function() {
		$.ajax({
			url: 'cancel.do?flag=1',
			type: 'post',
			data: $("#formPay").serializeArray(),
			success: function(msg) {
				bootbox.alert({
					  message: msg,
					  size: 'small'
				  });
				repage.draw(false);
			}
		});
	});
  //确认交费
	$('#confim').click(function() {
		$.ajax({
			url: 'confirm.do?flag=0',
			type: 'post',
			data: $("#formPay").serializeArray(),
			success: function(msg) {
				bootbox.alert({
					  message: msg,
					  size: 'small'
				  });
				repage.draw(false);
			}
		});
	});
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	  function recovery() {
		  document.getElementById("basenameid").value = "";
		  document.getElementById("usernameid").value = "";
		  document.getElementById("usercollageid").value = "";			
	  }
	  function recovery2() {
		  document.getElementById("basenameid2").value = "";
		  document.getElementById("usernameid2").value = "";
		  document.getElementById("usercollageid2").value = "";		
	  }
       var Spage= $('#tableCheck').DataTable( {
        	            "aLengthMenu" : [5,10,20,30], //动态指定分页后每页显示的记录数。
						"lengthChange" : true, //是否启用改变每页显示多少条数据的控件
						"bSort" : false,						
						"serverSide": true,
						"TotalRecords":[10],						
						"dom": 'frtip<"bottom"l>',												
			             "bDestroy":true,

			"ajax" : {
				"url" : "checkApplyRecord.do?flag=0",
				"type" : "POST"
			},
			"aoColumns" : [                                        /* [{la_id:1},{startime:"2016-11-15"},{endtime:"2017-11-15"},{basename:"长安基地1号"},{li:"土地编号"},{username:"申请人"},{usercollage:"信息科学技术学院"},{time:"租用次数"},{plant:"种植内容"}] */
							   {
								"mData" : "la_id",
								"orderable" : true, // 禁用排序
								"sDefaultContent" : "",
								"sWidth" : "2%",							
							},   									               
							{ //aoColumns设置列时，不可以任意指定列，必须列出所有列。
								"mData" : "startime",
								"orderable" : true, // 禁用排序
								"sDefaultContent" : "",
								"sWidth" : "2%"
							},
							{
								"mData" : "endtime",
								"orderable" : true, // 禁用排序
								"sDefaultContent" : "",
								"sWidth" : "6%",

							},
							{
								"mData" : "basename",
								"orderable" : true, // 禁用排序
								"sDefaultContent" : "",
								"sWidth" : "6%"
							},
							{
								"mData" : "li",
								"orderable" : false, // 禁用排序
								"sDefaultContent" : "",
								"sWidth" : "10%"
							},
							{
								"mData" : "username",
								"orderable" : true, // 禁用排序
								"sDefaultContent" : "",
								"sWidth" : "8%"
							},
							{
								"mData" : "usercollage",
								"orderable" : false, // 禁用排序
								"sDefaultContent" : "",
								"sWidth" : "8%",
								
								
							},	
							{
								"mData" : "times",
								"orderable" : false, // 禁用排序
								"sDefaultContent" : "",
								"sWidth" : "8%",
								
								
							},
							{
								"mData" : "plant",
								"orderable" : false, // 禁用排序
								"sDefaultContent" : "",
								"sWidth" : "8%",
								
								
							}

					],
					 "columnDefs" : 
						[{
							"orderable" : false, // 禁用排序
							"targets" : [0], // 指定的列
							"data" : "la_id",
							"render" : function(data, type, row) {
								data=row.la_id;
								return '<input type="checkbox" value="'+ data + '" name="idname"/>';
							}
						}], 					 
					"language" : {
						"lengthMenu" : "每页 _MENU_ 条记录",
						"zeroRecords" : "没有找到记录",
						"info" : "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
						"infoEmpty" : "无记录",
						"infoFiltered" : "(从 _MAX_ 条记录过滤)",
						"sSearch" : "模糊查询：",
						"oPaginate" : {
							"sFirst" : "首页",
							"sPrevious" : " 上一页 ",
							"sNext" : " 下一页 ",
							"sLast" : " 尾页 "
						}
					}										
				});   
        //获取用户名////////////////////////////修改后台，一次性取值//////////////////////////////////////////
        $.ajax({
			type : 'POST',
			dataType : 'json',
			url : 'selectal.do',
			async : false,
			cache : false,
			error : function(request) {
				alert("error");
			},
			success : function(data) {
				var i = 0;
				for ( var item in data) {

					$("#applicantId2").after(
							"<option value="+data[i].name+">"
									+ data[i].name + "</option>");

					i++;
				}
			}
		}); 
      //获取基地名
		$.ajax({
			type : 'POST',
			dataType : 'json',
			url : 'BaseInfoR.do',
			async : false,
			cache : false,
			error : function(request) {
				alert("error");
			},
			success : function(data) {
				var i = 0;
				for ( var item in data) {
					$("#selectallbase2").after(
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
					$("#selectdept2").after(
							"<option value="+data[i].dept+">"
									+ data[i].dept + "</option>");
					i++;
				}
			}
		});
		
    });
         $.ajax({
			type : 'POST',
			dataType : 'json',
			url : 'selectal.do',
			async : false,
			cache : false,
			error : function(request) {
				alert("error");
			},
			success : function(data) {
				var i = 0;
				for ( var item in data) {

					$("#applicantId").after(
							"<option value="+data[i].name+">"
									+ data[i].name + "</option>");

					i++;
				}

			}

		}); 
		//获取基地名
		$.ajax({
			type : 'POST',
			dataType : 'json',
			url : 'BaseInfoR.do',
			async : false,
			cache : false,
			error : function(request) {
				alert("error");
			},
			success : function(data) {
				var i = 0;
				for ( var item in data) {
					$("#selectallbase").after(
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
					$("#selectdept").after(
							"<option value="+data[i].dept+">"
									+ data[i].dept + "</option>");
					i++;
				}

			}

		});
		//获取用户名////////////////////////////修改后台，一次性取值//////////////////////////////////////////
		
		
		

    
      //所有交费中的记录
      var repage=$('#tablePay').DataTable( {
        	            "aLengthMenu" : [ 5,10,20,30], //动态指定分页后每页显示的记录数。
						"lengthChange" : true, //是否启用改变每页显示多少条数据的控件
						"bSort" : false,
						"serverSide": true,
						"dom": 'frtip<"bottom"l>',
			             "bDestroy":true,
			"ajax" : {
				"url" : "agApply.do?flag=1",
				"type" : "POST"
			},
			"aoColumns" : [
						   {
								"mData" : "la_id",
								"orderable" : true, // 禁用排序
								"sDefaultContent" : "",
								"sWidth" : "2%",							
							},   
										               
							{ //aoColumns设置列时，不可以任意指定列，必须列出所有列。
								"mData" : "startime",
								"orderable" : true, // 禁用排序
								"sDefaultContent" : "",
								"sWidth" : "2%"
							},
							{
								"mData" : "endtime",
								"orderable" : true, // 禁用排序
								"sDefaultContent" : "",
								"sWidth" : "6%",
							},
							{
								"mData" : "basename",
								"orderable" : true, // 禁用排序
								"sDefaultContent" : "",
								"sWidth" : "6%"
							},
							{
								"mData" : "li",
								"orderable" : false, // 禁用排序
								"sDefaultContent" : "",
								"sWidth" : "10%"
							},
							{
								"mData" : "username",
								"orderable" : true, // 禁用排序
								"sDefaultContent" : "",
								"sWidth" : "8%"
							},
							{
								"mData" : "usercollage",
								"orderable" : false, // 禁用排序
								"sDefaultContent" : "",
								"sWidth" : "8%",													
							},						
							{
								"mData" : "times",
								"orderable" : false, // 禁用排序
								"sDefaultContent" : "",
								"sWidth" : "8%",														
							},
							{
								"mData" : "plant",
								"orderable" : false, // 禁用排序
								"sDefaultContent" : "",
								"sWidth" : "8%",														
							}
					],
					"columnDefs" : 
						[{
							"orderable" : false, // 禁用排序
							"targets" : [0], // 指定的列
							"data" : "la_id",
							"render" : function(data, type, row) {
								data=row.la_id;
								return '<input type="checkbox" value="'+ data + '" name="idname" class="ck"  />';
							}
						}], 
					"language" : {
						"lengthMenu" : "每页 _MENU_ 条记录",
						"zeroRecords" : "没有找到记录",
						"info" : "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
						"infoEmpty" : "无记录",
						"infoFiltered" : "(从 _MAX_ 条记录过滤)",
						"sSearch" : "模糊查询：",
						"oPaginate" : {
							"sFirst" : "首页",
							"sPrevious" : " 上一页 ",
							"sNext" : " 下一页 ",
							"sLast" : " 尾页 "
						}
					}
					
				});



		 //筛选功能(刷选tableCheck)
		function Select(){	         
				var basenameid= document.getElementById("basenameid").value;
				var deptid = document.getElementById("dept").value;
				var usernameid = document.getElementById("usernameid").value;
				$('#tableCheck')
						.DataTable(
								{
									"aLengthMenu" : [ 2, 4, 6, 8, 10 ], //动态指定分页后每页显示的记录数。
									"lengthChange" : true, //是否启用改变每页显示多少条数据的控件
									"bSort" : false,
									"serverSide": true,
									//"iDisplayLength" : 4, //默认每页显示多少条记录
									"bDestroy" : true,
									"dom" : 'ftipr<"bottom"l>',

									"ajax" : {
										"data" : {
											"basename" : basenameid,
											"username" : usernameid,
											"usercollage" :deptid,
										
										},
										"url" : "Selet.do?flag=0",
										"type" : "POST"
									},

									"aoColumns" : [
												   {
														"mData" : "la_id",
														"orderable" : true, // 禁用排序
														"sDefaultContent" : "",
														"sWidth" : "2%",
														
													},   
																               
													{ //aoColumns设置列时，不可以任意指定列，必须列出所有列。
														"mData" : "startime",
														"orderable" : true, // 禁用排序
														"sDefaultContent" : "",
														"sWidth" : "2%"
													},
													{
														"mData" : "endtime",
														"orderable" : true, // 禁用排序
														"sDefaultContent" : "",
														"sWidth" : "6%",

													},
													{
														"mData" : "basename",
														"orderable" : true, // 禁用排序
														"sDefaultContent" : "",
														"sWidth" : "6%"
													},
													{
														"mData" : "li",
														"orderable" : false, // 禁用排序
														"sDefaultContent" : "",
														"sWidth" : "10%"
													},
													{
														"mData" : "username",
														"orderable" : true, // 禁用排序
														"sDefaultContent" : "",
														"sWidth" : "8%"
													},
													{
														"mData" : "usercollage",
														"orderable" : false, // 禁用排序
														"sDefaultContent" : "",
														"sWidth" : "8%",
														
														
													},
													
													{
														"mData" : "times",
														"orderable" : false, // 禁用排序
														"sDefaultContent" : "",
														"sWidth" : "8%",
														
														
													},
													{
														"mData" : "plant",
														"orderable" : false, // 禁用排序
														"sDefaultContent" : "",
														"sWidth" : "8%",
														
														
													}
											],
											"columnDefs" : 
												[{
													"orderable" : false, // 禁用排序
													"targets" : [0], // 指定的列
													"data" : "la_id",
													"render" : function(data, type, row) {
														data=row.la_id;
														return '<input type="checkbox" value="'+ data + '" name="idname" class="ck"  />';
													}
												}], 
										"language" : {
											"lengthMenu" : "每页 _MENU_ 条记录",
											"zeroRecords" : "没有找到记录",
											"info" : "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
											"infoEmpty" : "无记录",
											"infoFiltered" : "(从 _MAX_ 条记录过滤)",
											"sSearch" : "模糊查询：",
											"oPaginate" : {
												"sFirst" : "首页",
												"sPrevious" : " 上一页 ",
												"sNext" : " 下一页 ",
												"sLast" : " 尾页 "
											}
										}
								});
				  document.getElementById("hide_ul").style.display = "none";
				  recovery();
			}
		    //筛选功能2(刷选tablePay)
		    function Select2(){		           
				var basenameid= document.getElementById("basenameid2").value;
				var deptid = document.getElementById("dept2").value;
				var usernameid = document.getElementById("usernameid2").value;
				$('#tablePay')
						.DataTable(
								{
									"aLengthMenu" : [ 2, 4, 6, 8, 10 ], //动态指定分页后每页显示的记录数。
									"lengthChange" : true, //是否启用改变每页显示多少条数据的控件
									"bSort" : false,
									"serverSide": true,
									//"iDisplayLength" : 4, //默认每页显示多少条记录
									"bDestroy" : true,
									"dom" : 'ftipr<"bottom"l>',

									"ajax" : {
										"data" : {
											"basename" : basenameid,
											"username" : usernameid,
											"usercollage" :deptid,
										
										},
										"url" : "Select2.do?flag=1",
										"type" : "POST"
									},

									"aoColumns" : [
												   {
														"mData" : "la_id",
														"orderable" : true, // 禁用排序
														"sDefaultContent" : "",
														"sWidth" : "2%",
														
													},   
																               
													{ //aoColumns设置列时，不可以任意指定列，必须列出所有列。
														"mData" : "startime",
														"orderable" : true, // 禁用排序
														"sDefaultContent" : "",
														"sWidth" : "2%"
													},
													{
														"mData" : "endtime",
														"orderable" : true, // 禁用排序
														"sDefaultContent" : "",
														"sWidth" : "6%",

													},
													{
														"mData" : "basename",
														"orderable" : true, // 禁用排序
														"sDefaultContent" : "",
														"sWidth" : "6%"
													},
													{
														"mData" : "li",
														"orderable" : false, // 禁用排序
														"sDefaultContent" : "",
														"sWidth" : "10%"
													},
													{
														"mData" : "username",
														"orderable" : true, // 禁用排序
														"sDefaultContent" : "",
														"sWidth" : "8%"
													},
													{
														"mData" : "usercollage",
														"orderable" : false, // 禁用排序
														"sDefaultContent" : "",
														"sWidth" : "8%",
														
														
													},
													
													{
														"mData" : "times",
														"orderable" : false, // 禁用排序
														"sDefaultContent" : "",
														"sWidth" : "8%",
														
														
													},
													{
														"mData" : "plant",
														"orderable" : false, // 禁用排序
														"sDefaultContent" : "",
														"sWidth" : "8%",
														
														
													}
											],
											"columnDefs" : 
												[{
													"orderable" : false, // 禁用排序
													"targets" : [0], // 指定的列
													"data" : "la_id",
													"render" : function(data, type, row) {
														data=row.la_id;
														return '<input type="checkbox" value="'+ data + '" name="idname" class="ck"  />';
													}
												}], 
										"language" : {
											"lengthMenu" : "每页 _MENU_ 条记录",
											"zeroRecords" : "没有找到记录",
											"info" : "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
											"infoEmpty" : "无记录",
											"infoFiltered" : "(从 _MAX_ 条记录过滤)",
											"sSearch" : "模糊查询：",
											"oPaginate" : {
												"sFirst" : "首页",
												"sPrevious" : " 上一页 ",
												"sNext" : " 下一页 ",
												"sLast" : " 尾页 "
											}
										}
								});
				                document.getElementById("hide_ul2").style.display = "none";
				                  recovery2();
			}
 	


