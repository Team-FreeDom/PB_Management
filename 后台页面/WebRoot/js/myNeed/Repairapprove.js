
var obj=[];//表格中每一行（row）数组
var obj2=[];
$(document).ready(function() {
/*筛选的显示与收藏*/
$(".icon-filter").on("click", function () {
	$("#searchbase").val("");
	$("#searchname").val("");
	$("#searchbase2").val("");
	$("#searchname2").val("");
	$('.hide_ul').toggle();
});
			
//审批中表格的全选反选
$(".ck1").on("click", function () {
		if ($(this).prop("checked") === true) {
			$("#Approveing input[name='idname1']").prop("checked", true);
		} else {
			$("#Approveing input[name='idname1']").prop("checked", false);
		}		
});
				 
$(".ck2").click(function () {//反选  
	if ($(this).prop("checked") === true) {
		$("#Repairing input[name='idname2']").prop("checked", true);
	} else {
		$("#Repairing input[name='idname2']").prop("checked", false);
	}	
 }); 
				 

				 			
			//审核中表格
              var Approvetable =$('#Approveing').DataTable(
			  {
				  "processing": true,
        		  "serverSide": true,
				  "bSort": false,
				  "aLengthMenu":[ 5, 10, 20, 30 ], //动态指定分页后每页显示的记录数。
					"lengthChange":true, //是否启用改变每页显示多少条数据的控件
					"iDisplayLength" : 10,  //默认每页显示多少条记录
					"bFilter": true,
					"ordering":true,
					"dom":'ftipr<"bottom"l>',
					"ajax" : {
							"url" : "getNoRepair.do",
							"type" : "POST"
						},  
					"aoColumns" : [                                        
									{
										"mData" : "id",
										"orderable" : true, // 禁用排序
										"sDefaultContent" : "",
										
									},
									{ 
										"mData" : "pro_name",
										"orderable" : false, // 禁用排序
										"sDefaultContent" : "",
									},
									{
										"mData" : "basename",
										"orderable" : true, // 禁用排序
										"sDefaultContent" : "",

									},
									{
										"mData" : "username",
										"orderable" : true, // 禁用排序
										"sDefaultContent" : "",

									},
									{
										"mData" : "apply_time",
										"orderable" : true, // 禁用排序
										"sDefaultContent" : "",
									},
									{
										"mData" : "money",
										"orderable" : false, // 禁用排序
										"sDefaultContent" : "",
									},	
									{
										"mData" : "userid",
										"orderable" : false, // 禁用排序
										"visible" :false,
										"sDefaultContent" : "",
									},
									{
										"mData" : "address",
										"orderable" : false, // 禁用排序
										"visible" :false,
										"sDefaultContent" : "",
									},
									{
										"mData" : "reason",
										"orderable" : false, // 禁用排序
										"visible" :false,
										"sDefaultContent" : "",
									},
									{
										"mData" : "file",
										"orderable" : false, // 禁用排序
										"visible" :false,
										"sDefaultContent" : "",
									},
									
									{
										"mData" : "id",
										"orderable" : false, // 禁用排序
										"sDefaultContent" : '',
										"render":function(data,type,row){					
										obj.push(row);							
										return data="<span type='button' class='icon-search' value='"+(obj.length-1)
										+ "' id='scanDetail'></span>";
										}
									}
							],
							
							"columnDefs" :
							[{
								"orderable" : false, // 禁用排序
								"targets" : [0], // 指定的列
								"data" : "id",
								"render" : function(data, type, row) {
									return '<input type="checkbox" value="'
								+ data
								+ '" data-id="'+(obj.length)+'" name="idname1" class="ck"/>';
								}
							}],
							
                    	"language": {
                        "lengthMenu": "每页 _MENU_ 条记录",
                        "zeroRecords": "没有找到记录",
                        "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
                        "infoEmpty": "无记录",
						"sSearch": "模糊查询：",
                        "infoFiltered": "(从 _MAX_ 条记录过滤)",
						"oPaginate": {
						   "sFirst": "首页",
						   "sPrevious": " 上一页 ",
						   "sNext": " 下一页 ",
						   "sLast": " 尾页 "
					   }
                    }
			  });
              
            //维修中表格	  
              var repair=$('#Repairing').DataTable(
             {
             	"aLengthMenu" : [ 5, 10, 20, 30 ], // 动态指定分页后每页显示的记录数。
             	"lengthChange" : true, // 是否启用改变每页显示多少条数据的控件
             	"bSort" : true,
             	"ordering":true,
             	"serverSide" : true,
             	"bFilter": true,
             	"ordering":true,	
             	"dom": 'frtip<"bottom"l>',
             	"iDisplayLength": 10,	
             	"ajax" : {
             			"url" : "getAgreeRepair.do",
             			"type" : "POST"
             			},
             	"aoColumns" : [
             			{
             										"mData" : "id",
             										"orderable" : true, // 禁用排序
             										"sDefaultContent" : "",
             										
             									},
             									{ 
             										"mData" : "pro_name",
             										"orderable" : false, // 禁用排序
             										"sDefaultContent" : "",
             									},
             									{
             										"mData" : "basename",
             										"orderable" : true, // 禁用排序
             										"sDefaultContent" : "",

             									},
             									{
             										"mData" : "username",
             										"orderable" : true, // 禁用排序
             										"sDefaultContent" : "",

             									},
             									{
             										"mData" : "apply_time",
             										"orderable" : true, // 禁用排序
             										"sDefaultContent" : "",
             									},
             									{
             										"mData" : "money",
             										"orderable" : false, // 禁用排序
             										"sDefaultContent" : "",
             									},	
             									{
             										"mData" : "userid",
             										"orderable" : false, // 禁用排序
             										"visible" :false,
             										"sDefaultContent" : "",
             									},
             									{
             										"mData" : "address",
             										"orderable" : false, // 禁用排序
             										"visible" :false,
             										"sDefaultContent" : "",
             									},
             									{
             										"mData" : "reason",
             										"orderable" : false, // 禁用排序
             										"visible" :false,
             										"sDefaultContent" : "",
             									},
             									{
             										"mData" : "file",
             										"orderable" : false, // 禁用排序
             										"visible" :false,
             										"sDefaultContent" : "",
             									},
             									
             									{
             										"mData" : "id",
             										"orderable" : false, // 禁用排序
             										"sDefaultContent" : '',
             										"render":function(data,type,row){					
             										obj2.push(row);							
             										return data="<span type='button' class='icon-search' value='"+(obj2.length-1)
             										+ "' id='scanDetail2'></span>";
             										}
             									}
             			],
             		"columnDefs" : [ {
             			"orderable" : false, // 禁用排序
             			"targets" : [ 0 ], // 指定的列
             			"data" : "id",
             			"render" : function(data,type,row) {					
             					return '<input type="checkbox" value="'+ (obj2.length)+ '" name="idname2" class="ck"  />';
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
              
              
//获取基地名称列表
$.ajax({
	url : 'getInfoApply.do',
	type : 'post',
	dataType : 'json',			
	success : function(data) {
		for ( var i=0;i<data[0].length;i++) {			
			$("#searchnamed").after("<option class='rest' value="+data[0][i].username+">"+ data[0][i].username + "</option>");
			}
		for ( var i=0;i<data[1].length;i++) {
				$("#searchbaseid").after("<option class='rest' value="+data[1][i].id+">"+ data[1][i].basename + "</option>");				
				}		
		for ( var i=0;i<data[2].length;i++) {			
			$("#searchnamed2").after("<option class='rest' value="+data[2][i].username+">"+ data[2][i].username + "</option>");
			}
		for ( var i=0;i<data[3].length;i++) {			
			$("#searchbaseid2").after("<option class='rest' value="+data[3][i].id+">"+ data[3][i].basename + "</option>");
			}
		
		}
});	

//查看详情1
$(document).on("click", "#scanDetail", function() {	

	var index=$(this).attr("value");	
	$("#projectname").val(obj[index].pro_name);
	$("#basename").val(obj[index].basename);
	$("#name").val(obj[index].username);
	$("#time").val(obj[index].apply_time);
	$("#budget").val(obj[index].money);
	$("#address").val(obj[index].address);
	$("#reason").val(obj[index].reason);
	if(obj[index].file=="null"||obj[index].file==""||obj[index].file==null){			
		$("#resourcetr").prop("hidden",true); 
	}else{		
		$("#resourcetr").prop("hidden",false); 
		$("#linkaddress").prop("href",obj[index].file);
	}
	
	$("#Checkdetal").modal('show');
	
});		

//查看详情2
$(document).on("click", "#scanDetail2", function() {	

	var index=$(this).attr("value");	
	$("#projectname").val(obj2[index].pro_name);
	$("#basename").val(obj2[index].basename);
	$("#name").val(obj2[index].username);
	$("#time").val(obj2[index].apply_time);
	$("#budget").val(obj2[index].money);
	$("#address").val(obj2[index].address);
	$("#reason").val(obj2[index].reason);
	if(obj2[index].file=="null"||obj2[index].file==""||obj2[index].file==null){			
		$("#resourcetr").prop("hidden",true); 
	}else{		
		$("#resourcetr").prop("hidden",false); 
		$("#linkaddress").prop("href",obj2[index].file);
	}
	
	
	$("#Checkdetal").modal('show');
	
});

			  //同意申请
			  var flag=0;
			  $(document).on("click","#agree",function(){			  
				  
				  flag=0;
					$("#Approveing input[name='idname1']").each(function () {
							if($(this).prop("checked")==true){
								flag=1;
					  			return false;
						}
					});					
					if(flag==0){
						bootbox.alert({
							  message: "您还没有选择任何内容",
							  size: 'small'
						  });
								}
					else{
							bootbox.confirm({
							message: "确定同意申请",
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
									var i=0;
									var agreestr = '(';//同意记录的格式(1,2,3,4,5)
									var recorddigit='(';
									var keyid;
									var infostr="[";
									var userid;
									var basename;
									$("input[type='checkbox'][name='idname1']:checked").each(function() {
															keyid = $(this).data("id");
															userid=obj[keyid].userid;
															basename=obj[keyid].basename;
															if (i != 0) {
																agreestr = agreestr+ ','+ $(this).val();
																infostr=infostr+',{userid:"'+userid+'",basename:"'+ basename+'"}';
																recorddigit=recorddigit+','+this.className;
															}
															else{
																agreestr = agreestr+ $(this).val();
																infostr=infostr+'{userid:"'+userid+'",basename:"'+ basename+'"}';
																recorddigit=recorddigit+this.className;
																}
															i++;
														});
										agreestr = agreestr + ')';
										infostr=infostr+']';
										 recorddigit=recorddigit+')'; 
										$.ajax({
											url : 'agreeRepairApply.do',
											type : 'post',
											dataType : 'json',
											data : {
												"agreestr" : agreestr,
												"infostr":infostr,
											},
											success : function(msg) {											
												if(msg==0){
													bootbox.alert({
														message : "同意申请失败请刷新页面",
														size : 'small'
													});
												}else{
													bootbox.alert({
														message : "同意申请成功",
														size : 'small'
													});
												getInfoApply();
												Approvetable.draw(false);
												repair.draw(false);
												}																								
											}
										});//end

								}
							}
						});
			
					}
				  
				  });
				  
			  //拒绝申请
			  $(document).on("click","#saverefuse",function(){	
				  
				  flag=0;
					$("#Approveing input[name='idname1']").each(function () {
							if($(this).prop("checked")==true){
								flag=1;
					  			return false;
						}
					});
					if(flag==0){
						bootbox.alert({
							  message: "您还没有选择任何内容",
							  size: 'small'
						  });
								}
					else{
							bootbox.confirm({
							message: "确定拒绝申请",
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
									var i=0;
									var recordstr = '';//同意记录的格式(1,2,3,4,5)
									var recorddigit='(';
									var infostr="[";
									var userid;
									var basename;
									var refuseReason;
									$("input[type='checkbox'][name='checkRefuse']:checked").each(function() {
															userid = $(this).val();															
															basename=$(this).closest('tr').find('td:eq(2) input').val();
															refuseReason=$(this).closest('tr').find('td:eq(6) textarea').val();
															if (i != 0) {
																recordstr=recordstr+",("+this.className+",12,'"+refuseReason+"')";
																infostr=infostr+',{userid:"'+userid+'",basename:"'+ basename+'"}';
																recorddigit=recorddigit+','+this.className;
															}
															else{
																recordstr=recordstr+"("+this.className+",12,'"+refuseReason+"')";
																infostr=infostr+'{userid:"'+userid+'",basename:"'+ basename+'"}';
																recorddigit=recorddigit+this.className;
																}
															i++;
														});									    
										infostr=infostr+']';	
										recorddigit=recorddigit+')';  
										$.ajax({
											url : 'refuseRepairApply.do',
											type : 'post',
											dataType : 'json',
											data : {
												"recorddigit":recorddigit,
												"recordstr" :recordstr,
												"infostr":infostr,
											},
											success : function(msg) {											
												if(msg==0){
													bootbox.alert({
														message : "拒绝失败请刷新页面",
														size : 'small'
													});
													$("#refuseModal").modal('hide');
												}else{
													bootbox.alert({
													message : "拒绝报修成功",
													size : 'small'
												});
													$("#refuseModal").modal('hide');
													getInfoApply();
													Approvetable.draw(false);
													repair.draw(false);
												}
																								
											}
										});//end

								}
							}
						});
			
					}
				  
				  });
			  
			  //维修完成
			  $(document).on("click","#storeInfo",function(){
				  var moneyNum=0;
				  var finishNum=0;
				  $(".actualAmount").each(function(){
					  finishNum++;
					  var value=$(this).val();
					  value=value.trim();
					  var dataFormatWeek=/^[0-9]+\.?[0-9]*$/;
						if(value!=""){
							if (!dataFormatWeek.exec(value)) {
								moneyNum++;
								return false;
							} 
						}
				  });
				  if(moneyNum!=0){
					  bootbox.alert({
							message : "第"+finishNum+"条的实际金额格式不对，只能输入数字",
							size : 'small'
						});
					  return;
				  }
				  flag=0;
					$("#Repairing input[name='idname2']").each(function () {
							if($(this).prop("checked")==true){
								flag=1;
					  			return false;
						}
					});
					if(flag==0){
						bootbox.alert({
							  message: "您还没有选择任何内容",
							  size: 'small'
						  });
								}
					else{
							bootbox.confirm({
							message: "确定维修完成",
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
									var i=0;
									var recordstr = '';//完成记录的格式(1,2,3,4,5)									
									var infostr="[";
									var userid;
									var basename;
									var money;
									$("input[type='checkbox'][name='checkRefuse']:checked").each(function() {
															userid = $(this).val();															
															basename=$(this).closest('tr').find('td:eq(2) input').val();
															money=$(this).closest('tr').find('td:eq(6) input').val();
															if(money==""){
																money=0;
															}
															if (i != 0) {
																recordstr=recordstr+",("+this.className+",15,"+money+")";
																infostr=infostr+',{userid:"'+userid+'",basename:"'+ basename+'"}';
															}
															else{
																recordstr=recordstr+"("+this.className+",15,"+money+")";
																infostr=infostr+'{userid:"'+userid+'",basename:"'+ basename+'"}';
																}
															i++;
														});									    
										infostr=infostr+']';										
										$.ajax({
											url : 'repairFinish.do',
											type : 'post',
											dataType : 'json',
											data : {
												"recordstr" :recordstr,
												"infostr":infostr
											},
											success : function(msg) {
												bootbox.alert({
													message : msg.str,
													size : 'small'
												});
												$("#myfinishedModal").modal('hide');
												getInfoApply();
												repair.draw(false);
											}
										});//end

								}
							}
						});
			
					}
				  
				  });
				  
$("#refuse").click(function (){
	var i=0;
	$("input[type='checkbox'][name='idname1']:checked").each(function() {
		i++;
	});
	if(i==0){
		bootbox.alert({
			message : "请至少选择一项内容",
			size : 'small'
		});
		return;
	}
	
	$(".Rtr").remove();
	$("input[type='checkbox'][name='idname1']:checked").each(function() {
	index=$(this).data("id");
	$("#refusetable").append('<tr class="Rtr"><td><input type="checkbox" name="checkRefuse" class='+obj[index].id+' checked hidden value="'+obj[index].userid+'"></td><td>基地名称：</td><td><input type="text" class="form-control" value="'+obj[index].basename+'" disabled></td>'
	+'<td>申请人: </td><td><input type="text" class="form-control" value="'+obj[index].username+'" disabled></td>'
	+'<td>拒绝理由: </td><td><textarea row=1 col=1 id="reasonH"></textarea></td>'+'</tr>');
		});
	$("#refuseModal").modal('show');
	});
				  
//维修中——完成操作
$("#finished").click(function (){
	var i=0;
	$("input[type='checkbox'][name='idname2']:checked").each(function() {
		i++;
	});
	if(i==0){
		bootbox.alert({
			message : "请至少选择一项内容",
			size : 'small'
		});
		return;
	}
	
		$(".Rtr").remove();
		$("input[type='checkbox'][name='idname2']:checked").each(function() {
			index=$(this).val();
			$("#finishtable").append('<tr class="Rtr"><td><input type="checkbox" name="checkRefuse" class='+obj2[index].id+' checked hidden value="'+obj2[index].userid+'"></td><td>基地名称：</td><td><input type="text" value="'+obj2[index].basename+'" class="form-control" disabled></td>'
			+'<td>申请人: </td><td><input type="text" class="form-control"  value="'+obj2[index].username+'" disabled></td>'
			+'<td>实际金额: </td><td><input class="form-contro actualAmount"/></td>'+'</tr>');
		});
		$("#myfinishedModal").modal('show');
});
			  


//筛选功能1
$(document).on("click","#finish",function() {
	var obj=[];
	var baseid = $("#searchbase option:selected").val();	
	var userid = $("#searchname option:selected").val();
	Approvetable=$('#Approveing').DataTable(
		{
		"aLengthMenu" : [ 5, 10, 20, 30 ], // 动态指定分页后每页显示的记录数。
		"lengthChange" : true, // 是否启用改变每页显示多少条数据的控件
		"bSort" : true,
		"serverSide" : true,
		"iDisplayLength": 10,// //默认每页显示多少条记录
		"bDestroy" : true,
		"bFilter": true,
		"ordering":true,
		"filter":true,
		"dom" : 'ftipr<"bottom"l>',
		"ajax" : {
			"data" : {
				"baseid" : baseid,
				"userid" : userid				
			},
			"url" : "getChooseRepair1.do",
			"type" : "POST"
		},

		"aoColumns" : [                                        
						{
							"mData" : "id",
							"orderable" : true, // 禁用排序
							"sDefaultContent" : "",
							
						},
						{ 
							"mData" : "pro_name",
							"orderable" : false, // 禁用排序
							"sDefaultContent" : "",
						},
						{
							"mData" : "basename",
							"orderable" : true, // 禁用排序
							"sDefaultContent" : "",

						},
						{
							"mData" : "username",
							"orderable" : true, // 禁用排序
							"sDefaultContent" : "",

						},
						{
							"mData" : "apply_time",
							"orderable" : true, // 禁用排序
							"sDefaultContent" : "",
						},
						{
							"mData" : "money",
							"orderable" : false, // 禁用排序
							"sDefaultContent" : "",
						},	
						{
							"mData" : "userid",
							"orderable" : false, // 禁用排序
							"visible" :false,
							"sDefaultContent" : "",
						},
						{
							"mData" : "address",
							"orderable" : false, // 禁用排序
							"visible" :false,
							"sDefaultContent" : "",
						},
						{
							"mData" : "reason",
							"orderable" : false, // 禁用排序
							"visible" :false,
							"sDefaultContent" : "",
						},
						{
							"mData" : "file",
							"orderable" : false, // 禁用排序
							"visible" :false,
							"sDefaultContent" : "",
						},
						
						{
							"mData" : "id",
							"orderable" : false, // 禁用排序
							"sDefaultContent" : '',
							"render":function(data,type,row){					
							obj.push(row);							
							return data="<span type='button' class='icon-search' value='"+(obj.length-1)
							+ "' id='scanDetail'></span>";
							}
						}
				],
				
				"columnDefs" :
				[{
					"orderable" : false, // 禁用排序
					"targets" : [0], // 指定的列
					"data" : "id",
					"render" : function(data, type, row) {
						return '<input type="checkbox" value="'
					+ data
					+ '" data-id="'+(obj.length)+'" name="idname1" class="ck"/>';
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
	$('.hide_ul').toggle();
 });
//筛选功能2
$(document).on("click","#finish2",function() {
	var obj2=[];
	var baseid = $("#searchbase2 option:selected").val();	
	var userid = $("#searchname2 option:selected").val();
	 repair=$('#Repairing').DataTable(
		{
		"aLengthMenu" : [ 5, 10, 20, 30 ], // 动态指定分页后每页显示的记录数。
		"lengthChange" : true, // 是否启用改变每页显示多少条数据的控件
		"bSort" : true,
		"serverSide" : true,
		"iDisplayLength": 10,// //默认每页显示多少条记录
		"bDestroy" : true,
		"ordering":true,
		"bFilter": true,
		"filter":true,
		"dom" : 'ftipr<"bottom"l>',
		"ajax" : {
			"data" : {
				"baseid" : baseid,
				"userid" : userid
			},
			"url" : "getChooseRepair2.do",
			"type" : "POST"
		},

		"aoColumns" : [
		   			{
		   										"mData" : "id",
		   										"orderable" : true, // 禁用排序
		   										"sDefaultContent" : "",
		   										
		   									},
		   									{ 
		   										"mData" : "pro_name",
		   										"orderable" : false, // 禁用排序
		   										"sDefaultContent" : "",
		   									},
		   									{
		   										"mData" : "basename",
		   										"orderable" : true, // 禁用排序
		   										"sDefaultContent" : "",

		   									},
		   									{
		   										"mData" : "username",
		   										"orderable" : true, // 禁用排序
		   										"sDefaultContent" : "",

		   									},
		   									{
		   										"mData" : "apply_time",
		   										"orderable" : true, // 禁用排序
		   										"sDefaultContent" : "",
		   									},
		   									{
		   										"mData" : "money",
		   										"orderable" : false, // 禁用排序
		   										"sDefaultContent" : "",
		   									},	
		   									{
		   										"mData" : "userid",
		   										"orderable" : false, // 禁用排序
		   										"visible" :false,
		   										"sDefaultContent" : "",
		   									},
		   									{
		   										"mData" : "address",
		   										"orderable" : false, // 禁用排序
		   										"visible" :false,
		   										"sDefaultContent" : "",
		   									},
		   									{
		   										"mData" : "reason",
		   										"orderable" : false, // 禁用排序
		   										"visible" :false,
		   										"sDefaultContent" : "",
		   									},
		   									{
		   										"mData" : "file",
		   										"orderable" : false, // 禁用排序
		   										"visible" :false,
		   										"sDefaultContent" : "",
		   									},
		   									
		   									{
		   										"mData" : "id",
		   										"orderable" : false, // 禁用排序
		   										"sDefaultContent" : '',
		   										"render":function(data,type,row){					
		   										obj2.push(row);							
		   										return data="<span type='button' class='icon-search' value='"+(obj2.length-1)
		   										+ "' id='scanDetail2'></span>";
		   										}
		   									}
		   			],
		   		"columnDefs" : [ {
		   			"orderable" : false, // 禁用排序
		   			"targets" : [ 0 ], // 指定的列
		   			"data" : "id",
		   			"render" : function(data,type,row) {		   					
		   					return '<input type="checkbox" value="'+ (obj2.length)+ '" name="idname2" class="ck"  />';
		   					}
		   			} ],
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
	$('.hide_ul').toggle();
 });
 
 } );

function getInfoApply(){
	$(".rest").remove();
	//获取基地名称列表
	$.ajax({
		url : 'getInfoApply.do',
		type : 'post',
		dataType : 'json',			
		success : function(data) {
			for ( var i=0;i<data[0].length;i++) {			
				$("#searchnamed").after("<option class='rest' value="+data[0][i].username+">"+ data[0][i].username + "</option>");
				}
			for ( var i=0;i<data[1].length;i++) {
					$("#searchbaseid").after("<option class='rest' value="+data[1][i].id+">"+ data[1][i].basename + "</option>");				
					}		
			for ( var i=0;i<data[2].length;i++) {			
				$("#searchnamed2").after("<option class='rest' value="+data[2][i].username+">"+ data[2][i].username + "</option>");
				}
			for ( var i=0;i<data[3].length;i++) {			
				$("#searchbaseid2").after("<option class='rest' value="+data[3][i].id+">"+ data[3][i].basename + "</option>");
				}
			
			}
	});
}