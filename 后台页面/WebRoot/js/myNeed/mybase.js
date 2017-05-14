var obj1=[];
var obj2=[];
var page1;
$(document).ready(function() {	
				
            page1=$('#mybasetable1').DataTable(
			  {
				  "aLengthMenu" : [ 5, 10, 20, 30 ], // 动态指定分页后每页显示的记录数。
					"lengthChange" : true, // 是否启用改变每页显示多少条数据的控件
					"bSort" : true,
					"ordering":true,
					"serverSide" : true,
					"iDisplayLength": 5,				
					"dom" : 'tipr<"bottom"l>',
					"ajax" : {
						"url" : "MybaseInfo.do",
						"type" : "POST"
					},
					"aoColumns" : [ 
					{ // aoColumns设置列时，不可以任意指定列，必须列出所有列。
						"mData" : "name",
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "10%"
					}, { // aoColumns设置列时，不可以任意指定列，必须列出所有列。
						"mData" : "type",
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "6%"
					},{
						"mData" : "applydp",
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%",

					}, {
						"mData" : "resperson",
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "6%"
					}, 
					
					{
						"mData" : "username",					
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "6%"
					},
					{
						"mData" : "phone",					
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "6%"
					},
					{
						"mData" : "land_address",
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"visible":false,
						"sWidth" : "6%"
					},{
						"mData" : "resperson",
						"visible":false,
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%"
					},
					{
						"mData" : "username",
						"visible":false,
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%"
					}, {
						"mData" : "phone",
						"orderable" : false, // 禁用排序
						"visible":false,
						"sDefaultContent" : "",
						"sWidth" : "8%",

					}, {
						"mData" : "mmajor",
						"visible":false,
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%",

					},
					{
						"mData" : "material_path",
						"visible":false,
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%",

					},
					 {
						"mData" : "userid",
						"visible":false,
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%",

					},{
						"mData" : "applytime",
						"visible":false,
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%",

					},{
						"mData" : "valid_date",
						"visible":false,
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%",

					},{
						"mData" : "reason",
						"visible":false,
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%",

					},
					
					{
						"mData" : "statusdigital",
						"visible":true,
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%",

					}, {
						"mData" : "statusid",
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%",
						"render":function(data,type, row){
							obj1.push(row);
							if(data==2||data==18){
								return data='<button type="button" class="btn btn-warning btn-xs" id="scan" value="'+(obj1.length-1)+'$1">查看</button>'+
                                    '<button type="button" class="btn btn-danger btn-xs" id="cancel" value="'+(obj1.length-1)+'">撤回</button>';
							}else if(data==17){
								return data='<button type="button" class="btn btn-success btn-xs" id="change" value="'+(obj1.length-1)+'$1">续期</button>';
							}else{
								return data='<button type="button" class="btn btn-warning btn-xs" id="scan" value="'+(obj1.length-1)+'$1">查看</button>';
							}							
						}
					}
					],					
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
			  
			  
			var  page2=$('#mybasetable2').DataTable(
			  {
				  "aLengthMenu" : [ 5, 10, 20, 30 ], // 动态指定分页后每页显示的记录数。
					"lengthChange" : true, // 是否启用改变每页显示多少条数据的控件
					"bSort" : true,
					"ordering":true,
					"serverSide" : true,
					"iDisplayLength": 5,				
					"dom" : 'tipr<"bottom"l>',
					"ajax" : {
						"url" : "MybaseInfo2.do",
						"type" : "POST"
					},
					"aoColumns" : [ 
					{ // aoColumns设置列时，不可以任意指定列，必须列出所有列。
						"mData" : "name",
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "10%"
					}, { // aoColumns设置列时，不可以任意指定列，必须列出所有列。
						"mData" : "type",
						"orderable" : true, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "6%"
					},{
						"mData" : "applydp",
						"orderable" : true, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%",

					}, {
						"mData" : "resperson",
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "6%"
					}, 
					
					{
						"mData" : "username",					
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "6%"
					},
					{
						"mData" : "phone",					
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "6%"
					},
					{
						"mData" : "land_address",
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"visible":false,
						"sWidth" : "6%"
					},{
						"mData" : "resperson",
						"visible":false,
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%"
					},
					{
						"mData" : "username",
						"visible":false,
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%"
					}, {
						"mData" : "phone",
						"visible":false,
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%",

					}, {
						"mData" : "mmajor",
						"visible":false,
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%",

					},
					{
						"mData" : "material_path",
						"visible":false,
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%",

					},
					 {
						"mData" : "userid",
						"visible":false,
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%",

					},{
						"mData" : "applytime",
						"visible":false,
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%",

					},{
						"mData" : "valid_date",
						"visible":false,
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%",

					},{
						"mData" : "reason",
						"visible":false,
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%",

					},{
						"mData" : "statusdigital",
						"visible":true,
						"orderable" : true, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%",

					}, {
						"mData" : "id",
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%",
						"render":function(data,type, row){
							obj2.push(row);
							if(data==17){
								return data='<span class="icon-edit" id="change" value="'+(obj2.length-1)+'$2"></span>';
							}else{
								return data='<span class="icon-search" id="scan" value="'+(obj2.length-1)+'$2"></span>';
							}
												
						}
					}
					],					
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
			  
			  $(document).on("click", "#scan", function() {	
				  
					var str=$(this).attr("value");					
					var position=str.indexOf('$');
					var index=str.substring(0,position);
					var tag=str.substring(position+1);
					var object=[];
					if(tag==1){
						object=obj1;
					}else{
						object=obj2;
					}
					
					var type=object[index].type;					
					var result=(type=="新农院社会服务基地");
					var domThis;
					if(result){
						domThis=$("#fontTable_1");
						domThis.find("#unitName").val(object[index].cooperativeUnit);
					}else{
						domThis=$("#fontTable_0");
					}				
						
					var statusid=object[index].statusid;				
					domThis.find("#basename").val(object[index].name);
					domThis.find("#basetype").val(object[index].type);
					domThis.find("#dept0").val(object[index].applydp);
					domThis.find("#landarea").val(object[index].landarea);
					domThis.find("#buildingarea").val(object[index].constructionarea);
					domThis.find("#undertakeCount").val(object[index].undertake);
					domThis.find("#username").val(object[index].username);
					domThis.find("#userphone").val(object[index].phone);
					domThis.find("#major_oriented").html(object[index].mmajor);
					domThis.find("#linkAddress").html(object[index].land_address);
					domThis.find("#collegename").val(object[index].collegeName);
					domThis.find("#collegephone").val(object[index].collegePhone);
					var resperson=object[index].resperson;
					if(resperson=="null"){
						domThis.find("#dutyPerson").val("");
					}else{
						domThis.find("#dutyPerson").val(resperson);	
					}
					domThis.find("#baseid").val('#'+object[index].bid);					
					
					
					if(object[index].material_path=="null"||object[index].material_path==""||object[index].material_path==null){			
						domThis.find("#resourcetr").prop("hidden",true); 
					}else{		
						domThis.find("#resourcetr").prop("hidden",false); 
						domThis.find("#resource").prop("href",object[index].material_path);
					}
					
					domThis.find("#reason").html('');
					domThis.find("#hideReason").prop("hidden",true);
					
					if(statusid==6||statusid==18){
						domThis.find("#setdate").val(object[index].buildtime);
						domThis.find("#validdate").val(object[index].endtime);
						domThis.find("#hidecol").prop("hidden",false);
					}else if(statusid==12){
						domThis.find("#reason").html(object[index].reason);
						domThis.find("#hideReason").prop("hidden",false);
					}
					domThis.modal('show');
					
				});
			  
			  $(document).on("click", "#change", function() {	
				  
					var str=$(this).attr("value");					
					var position=str.indexOf('$');
					var index=str.substring(0,position);
					var tag=str.substring(position+1);
					var object=[];
					if(tag==1){
						object=obj1;
					}else{
						object=obj2;
					}
					
					var type=object[index].type;
					var object_this;
					if(type=="新农院社会服务基地"){
						object_this=$("#dateMyTable_nong");
						object_this.find("#unitNamet").val(object[index].cooperativeUnit);
					}else{
						object_this=$("#dateMyTable");
					}
						
					var statusid=object[index].statusid;	
					
					object_this.find("#baseidt").val('#'+object[index].bid);
					object_this.find("#basenamet").val(object[index].name);
					object_this.find("#basetypet").val(object[index].type);
					object_this.find("#dept0t").val(object[index].applydp);
					object_this.find("#landareat").val(object[index].landarea);
					object_this.find("#buildingareat").val(object[index].constructionarea);
					object_this.find("#undertakeCountt").val(object[index].undertake);
					object_this.find("#usernamet").val(object[index].username);
					object_this.find("#userphonet").val(object[index].phone);
					object_this.find("#collegenamet").val(object[index].collegeName);
					object_this.find("#collegephonet").val(object[index].collegePhone);
					object_this.find("#major_orientedt").html(object[index].mmajor);
					object_this.find("#linkAddresst").html(object[index].land_address);				
					object_this.find("#starttime").val(object[index].buildtime);
					object_this.find("#adddate").val(object[index].endtime);
					object_this.find("#tag").val(object[index].id);
					var resperson=object[index].resperson;
					if(resperson!=null&&resperson!=""){
						object_this.find("#dutyPersont").val(resperson);
					}else{
						object_this.find("#dutyPersont").val("");
					}										
					
					
					if(object[index].material_path=="null"||object[index].material_path==""||object[index].material_path==null){			
						object_this.find("#resourcetrt").prop("hidden",true); 
					}else{		
						object_this.find("#resourcetrt").prop("hidden",false); 
						object_this.find("#resourcet").prop("href",object[index].material_path);						
					}
				
					object_this.modal('show');
					
				});
			  
			  $(document).on("click", "#closeDe", function() {
				  $("#hidecol").prop("hidden",true);
				  $("#hideReason").prop("hidden",true);
				  $("#adddate").val("");
				  $("#fontTable").modal('hide');
			  });

				$(document).on("click", "#cancel", function() {
					$("#cancelSubmit").val($(this).val());
					$("#cancelOneModal").modal('show');	
				});
				
				$(document).on("click", "#cleark", function() {
					$("#adddate").val("");
				});
				
				$(document).on("click", "#saveit,#saveit_nong", function() {
					
					var thisId=$(this).prop("id");
					var object_this=(thisId=="saveit"?$("#dateMyTable"):$("#dateMyTable_nong"));
					var id=object_this.find("#tag").val();					
					var adddate=object_this.find("#adddate").val();				
					if(adddate==""){
						bootbox.alert({
							message : "请填写续期",
							size : 'small'
						});
						return;
					}
					$.ajax({
						data : {
							"id" : id,							
							"adddate" : adddate,						
						},
						url : 'updateMyBaseDate.do',
						async : true,
						type : "POST",
						dataType : "json",
						cache : false,
						success : function(data) {
							if(data.flag=="success"){
								bootbox.alert({
							        message: "操作成功",
							        size: 'small'
							    });
								object_this.modal('hide');
								object_this.find("#adddate").val("");
								page1.draw(false);
								page2.draw(false);
							}else if(data.flag=="fail"){
								bootbox.alert({
							        message: "操作失败",
							        size: 'small'
							    });
							}
							
						},
						error : function(data) {
							bootbox.alert({
						        message: "请求异常",
						        size: 'small'
						    });
						}
					});
				});

				//撤回按钮，传给后台id
				$(document).on("click", "#cancelSubmit", function() {					
					var index=$(this).val();					
				  	var basename=obj1[index].name;
				  	var userid=obj1[index].userid;
				  	var info_str='[{userid:"'+userid+'",basename:"'+basename+'"}]';
				  	var id=obj1[index].id;
				  	$.ajax({
				  		type : 'POST',
				  		//dataType : 'json',
				  		data:{
				  			"id":id,
				  			"infostr":info_str,
				  			"tag":obj1[index].statusid
				  		},
				  		url : 'recall.do',   //
				  		async : false,
				  		cache : false,
				  		error : function(request) {
				  			bootbox.alert({
				  		        message: "撤回失败",
				  		        size: 'small'
				  		    });
				  		},
				  		success : function(msg) {
				  			$("#cancelOneModal").modal('hide');
				  			if(msg==0){				  				
				  				bootbox.alert({
					  				message : "撤回失败,请刷新页面",
					  				size : 'small'
					  			});	
				  			}else if(msg==200){				  			
				  			bootbox.alert({
				  				message :"操作成功",
				  				size : 'small'
				  			});	
				  			page1.draw(false);
				  			page2.draw(false);
				  			}else if(msg==500){
				  				bootbox.alert({
					  				message :"操作失败",
					  				size : 'small'
					  			});
				  			}				  						  			
				  			
				  		}

				  	});
				  	
				  });
				
				
				$(document).on("click", "#finish", function() {
					obj2=[];
					var status=$("#status option:selected").val();					
					 page2=$('#mybasetable2').DataTable(
							  {
								  "aLengthMenu" : [ 5, 10, 20, 30 ], // 动态指定分页后每页显示的记录数。
									"lengthChange" : true, // 是否启用改变每页显示多少条数据的控件
									"bSort" : true,
									"ordering":true,
									"serverSide" : true,
									"iDisplayLength": 5,
									"bDestroy":true,
									"dom" : 'tipr<"bottom"l>',
									"ajax" : {
										"url" : "getStatus.do",
										"type" : "POST",
										"data":{"status":status}
									},
									"aoColumns" : [ 
									{ // aoColumns设置列时，不可以任意指定列，必须列出所有列。
										"mData" : "name",
										"orderable" : false, // 禁用排序
										"sDefaultContent" : "",
										"sWidth" : "10%"
									}, { // aoColumns设置列时，不可以任意指定列，必须列出所有列。
										"mData" : "type",
										"orderable" : true, // 禁用排序
										"sDefaultContent" : "",
										"sWidth" : "6%"
									},{
										"mData" : "applydp",
										"orderable" : true, // 禁用排序
										"sDefaultContent" : "",
										"sWidth" : "8%",

									}, {
										"mData" : "resperson",
										"orderable" : false, // 禁用排序
										"sDefaultContent" : "",
										"sWidth" : "6%"
									}, 
									
									{
										"mData" : "username",					
										"orderable" : false, // 禁用排序
										"sDefaultContent" : "",
										"sWidth" : "6%"
									},
									{
										"mData" : "phone",					
										"orderable" : false, // 禁用排序
										"sDefaultContent" : "",
										"sWidth" : "6%"
									},
									{
										"mData" : "land_address",
										"orderable" : false, // 禁用排序
										"sDefaultContent" : "",
										"visible":false,
										"sWidth" : "6%"
									},{
										"mData" : "resperson",
										"visible":false,
										"orderable" : false, // 禁用排序
										"sDefaultContent" : "",
										"sWidth" : "8%"
									},
									{
										"mData" : "username",
										"visible":false,
										"orderable" : false, // 禁用排序
										"sDefaultContent" : "",
										"sWidth" : "8%"
									}, {
										"mData" : "phone",
										"visible":false,
										"orderable" : false, // 禁用排序
										"sDefaultContent" : "",
										"sWidth" : "8%",

									}, {
										"mData" : "major",
										"visible":false,
										"orderable" : false, // 禁用排序
										"sDefaultContent" : "",
										"sWidth" : "8%",

									},
									{
										"mData" : "material_path",
										"visible":false,
										"orderable" : false, // 禁用排序
										"sDefaultContent" : "",
										"sWidth" : "8%",

									},
									 {
										"mData" : "userid",
										"visible":false,
										"orderable" : false, // 禁用排序
										"sDefaultContent" : "",
										"sWidth" : "8%",

									},{
										"mData" : "time",
										"visible":false,
										"orderable" : false, // 禁用排序
										"sDefaultContent" : "",
										"sWidth" : "8%",

									},{
										"mData" : "limitTime",
										"visible":false,
										"orderable" : false, // 禁用排序
										"sDefaultContent" : "",
										"sWidth" : "8%",

									},{
										"mData" : "reason",
										"visible":false,
										"orderable" : false, // 禁用排序
										"sDefaultContent" : "",
										"sWidth" : "8%",

									},{
										"mData" : "statusdigital",
										"visible":true,
										"orderable" : false, // 禁用排序
										"sDefaultContent" : "",
										"sWidth" : "8%",

									}, {
										"mData" : "id",
										"orderable" : false, // 禁用排序
										"sDefaultContent" : "",
										"sWidth" : "8%",
										"render":function(data,type, row){
											   obj2.push(row);
												if(data==17){
													return data='<span class="icon-edit" id="change" value="'+(obj2.length-1)+'$2"></span>';
												}else{
													return data='<span class="icon-search" id="scan" value="'+(obj2.length-1)+'$2"></span>';
												}
																
										}
									}
									],					
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
					 $(".icon-filter").click();
					
				});
				
			  
            } );

$(".icon-filter").on("click", function () {
	$("#status").val("-2");
	$('#hide_ul').toggle();
});




	



			