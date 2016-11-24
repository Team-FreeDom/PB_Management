//所有未审核申请记录
    $(document).ready(function() {	
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
								
								
							},
							{
								"mData" : "la_id",
								"orderable" : false, // 禁用排序
								"sDefaultContent" : '',
								"sWidth" : "5%",
								"render" : function(
										data, type,
										row) { //render改变该列样式,4个参数，其中参数数量是可变的。

									return data = '<span data-id='
										+ data
										+ ' id='
										+ data
										+ ' onclick="scanOne(this)"  class=" glyphicon glyphicon-search"></span>';

								}
							}
					//data指该行获取到的该列数据
					//row指该行，可用row.name或row[2]获取第3列字段名为name的值
					//type调用数据类型，可用类型“filter”,"display","type","sort",具体用法还未研究
					//meta包含请求行索引，列索引，tables各参数等信息

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
					},										
				});   
        //获取用户名
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
		 $('#PayM').click(function() {		       
		            	repage.draw(true);		         
		    });
		 $('#NoCheck').click(function() {		       
			 Spage.draw(true);		         
        });
    
      //所有交费中的记录
      var repage=$('#tablePay').DataTable( {
        	            "aLengthMenu" : [ 5,10,20,30], //动态指定分页后每页显示的记录数。
						"lengthChange" : true, //是否启用改变每页显示多少条数据的控件
						"bSort" : false,
						"serverSide": true,
						"dom": 'frtip<"bottom"l>',
						//"dom": '<"toolbar">frtip',
						
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
							},
							{
								"mData" : "la_id",
								"orderable" : false, // 禁用排序
								"sDefaultContent" : '',
								"sWidth" : "5%",
								"render" : function(
										data, type,
										row) { //render改变该列样式,4个参数，其中参数数量是可变的。

									return data = '<span data-id='
										+ data
										+ ' id='
										+ data
										+ ' onclick="scanOne2(this)"  class=" glyphicon glyphicon-search"></span>';

								}
							}
					//data指该行获取到的该列数据
					//row指该行，可用row.name或row[2]获取第3列字段名为name的值
					//type调用数据类型，可用类型“filter”,"display","type","sort",具体用法还未研究
					//meta包含请求行索引，列索引，tables各参数等信息
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
					},
					
				});
       //取消交费
	    $('#cancel').click(function() {
	        $.ajax({
	            url: 'cancel.do?flag=1',
	            type: 'post',
	            data: $("#formPay").serializeArray(),
	            success: function(msg) {
	            	alert(msg);
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
	            	alert(msg);
	            	repage.draw(false);
	            }
	        });
	    });
	  //拒绝申请
	    $('#deleteOne').click(function() {
	        $.ajax({
	            url: 'refuseApply.do?flag=1',
	            type: 'post',
	            data: $("#formCheck").serializeArray(),
	            success: function(msg) {
	            	alert(msg);
	            	Spage.draw(false);
	            }
	        });
	    });
	  //同意申请
	    $('#agreeOne').click(function() {
	        $.ajax({
	            url: 'agreeApply.do?flag=0',
	            type: 'post',
	            data: $("#formCheck").serializeArray(),
	            success: function(msg) {
	            	alert(msg);
	            	Spage.draw(false);
	            }
	        });
	    });
    	/*//同意申请
    	function agree(){
    		//jquery获取复选框值
    		alert("不对");
    		var chk_value =[];
    		$('input[name="idname"]:checked').each(function(){
    		chk_value.push($(this).val());
    		});
    		
    		alert(chk_value.length==0 ?'你还没有选择任何内容！':chk_value);  
            $("#formCheck").attr("action","agreeApply.do?flag=0");
    		
    		$("#formCheck").submit();
    	}*/
      //获取用户名
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
	 /**
	 * 土地租赁详情查看table1
	 */
	   function scanOne(obj) {
			var la_id = obj.id;
					$.ajax({
						type : 'POST',
						data : {
							"la_id" : la_id

						},
						dataType : 'json',
						url : 'detail.do',
						async : false,
						cache : false,
						error : function(request) {
							alert("error");
						},
						success : function(data) {

							var i = 0;
							for ( var item in data) {
								//var reason;
								// alert(data[i].status);
								
								$("#basename").val(data[i].basename);
								$("#username").val(data[i].username);
								$("#usercollage").val(data[i].usercollage);
								$("#landoriented").val(data[i].landoriented);
								$("#landname").val(data[i].landname);
								$("#li").val(data[i].li);
								$("#plant").val(data[i].plant);
								}
								i++;
							}
					});

			$("#scan").modal('show');
		}  
	     /**
		 * 土地租赁详情查看table2
		 */
		   function scanOne2(obj) {
				var la_id = obj.id;
						$.ajax({
							type : 'POST',
							data : {
								"la_id" : la_id
							},
							dataType : 'json',
							url : 'detail2.do',
							async : false,
							cache : false,
							error : function(request) {
								alert("error");
							},
							success : function(data) {

								var i = 0;
								for ( var item in data) {															
									$("#basename1").val(data[i].basename);
									$("#username1").val(data[i].username);
									$("#usercollage1").val(data[i].usercollage);
									$("#landoriented1").val(data[i].landoriented);
									$("#landname1").val(data[i].landname);
									$("#li1").val(data[i].li);
									$("#plant1").val(data[i].plant);
									}
									i++;
								}
						});

				$("#scan2").modal('show');
			}  
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
														
														
													},
													{
														"mData" : "la_id",
														"orderable" : false, // 禁用排序
														"sDefaultContent" : '',
														"sWidth" : "5%",
														"render" : function(
																data, type,
																row) { //render改变该列样式,4个参数，其中参数数量是可变的。

															return data = '<span data-id='
																+ data
																+ ' id='
																+ data
																+ ' onclick="scanOne(this)"  class=" glyphicon glyphicon-search"></span>';

														}
													}
											//data指该行获取到的该列数据
											//row指该行，可用row.name或row[2]获取第3列字段名为name的值
											//type调用数据类型，可用类型“filter”,"display","type","sort",具体用法还未研究
											//meta包含请求行索引，列索引，tables各参数等信息

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
										//data指该行获取到的该列数据
										//row指该行，可用row.name或row[2]获取第3列字段名为name的值
										//type调用数据类型，可用类型“filter”,"display","type","sort",具体用法还未研究
										//meta包含请求行索引，列索引，tables各参数等信息
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
										},
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
														
														
													},
													{
														"mData" : "la_id",
														"orderable" : false, // 禁用排序
														"sDefaultContent" : '',
														"sWidth" : "5%",
														"render" : function(
																data, type,
																row) { //render改变该列样式,4个参数，其中参数数量是可变的。

															return data = '<span data-id='
																+ data
																+ ' id='
																+ data
																+ ' onclick="scanOne2(this)"  class=" glyphicon glyphicon-search"></span>';

														}
													}
											//data指该行获取到的该列数据
											//row指该行，可用row.name或row[2]获取第3列字段名为name的值
											//type调用数据类型，可用类型“filter”,"display","type","sort",具体用法还未研究
											//meta包含请求行索引，列索引，tables各参数等信息

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
										//data指该行获取到的该列数据
										//row指该行，可用row.name或row[2]获取第3列字段名为name的值
										//type调用数据类型，可用类型“filter”,"display","type","sort",具体用法还未研究
										//meta包含请求行索引，列索引，tables各参数等信息
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
										},
								});
				                document.getElementById("hide_ul2").style.display = "none";
				                  recovery2();
			}
	    	function showsubmenu() {
				var submenu = document.getElementById("hide_ul");
				if (submenu.style.display == 'none') {
					submenu.style.display = 'block';
				} else {
					submenu.style.display = 'none';
					recovery();
				}			
			}  	
			function hidesubmenu() {
				var submenu = document.getElementById("hide_ul");
				var submenu2 = document.getElementById("hide_ul2");
				submenu2.style.display = 'none';
				recovery2();
				submenu.style.display = 'none';
				recovery();
			}
			 function hidesubmenu2(){
				var submenu2 = document.getElementById("hide_ul2");
				submenu2.style.display = 'none';
				recovery2();
			} 
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
			function showsubmenu2() {
				var submenu2 = document.getElementById("hide_ul2");
				if (submenu2.style.display == 'none') {
					submenu2.style.display = 'block';
				} else {
					submenu2.style.display = 'none';
					recovery2();
				}
			}
