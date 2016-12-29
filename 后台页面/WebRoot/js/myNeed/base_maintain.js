// JavaScript Document
var obj = [];
$(document).ready(function() {
					// 分页表格
					var page = $('#baseMaintain').dataTable({
						
										"processing" : true,
										"serverSide" : true,
										"bSort" : false,
										"aLengthMenu" : [ 5, 10, 20, 30 ], // 动态指定分页后每页显示的记录数。
										"lengthChange" : true, // 是否启用改变每页显示多少条数据的控件
										"iDisplayLength" : 5, // 默认每页显示多少条记录
										"dom" : 'ftipr<"bottom"l>',
										"ajax" : {
											"url" : "",
											"type" : "POST"
										},
										"aoColumns" : [
												{ // aoColumns设置列时，不可以任意指定列，必须列出所有列。
													"mData" : "bid",
													"orderable" : false, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "4%"
												},{
													"mData" : "bid",													
													"orderable" : true, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "8%",

												},{
													"mData" : "time",													
													"orderable" : true, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "8%",

												},
												{ // aoColumns设置列时，不可以任意指定列，必须列出所有列。
													"mData" : "name",
													"orderable" : false, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "10%"
												},
												{ // aoColumns设置列时，不可以任意指定列，必须列出所有列。
													"mData" : "type",
													"orderable" : false, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "8%"
												},
												{
													"mData" : "applydp",
													"orderable" : false, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "10%",

												},
												{
													"mData" : "landarea",
													"orderable" : false, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "6%"
												},

												{
													"mData" : "constructionarea",
													"orderable" : false, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "6%"
												},
												{
													"mData" : "undertake",
													"orderable" : false, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "6%"
												},
												{
													"mData" : "land_address",
													"orderable" : false, // 禁用排序
													"sDefaultContent" : "",
													"visible" : false,
													"sWidth" : "6%"
												},
												{
													"mData" : "username",
													"orderable" : false, // 禁用排序
													"visible" : false,
													"sDefaultContent" : "",
													"sWidth" : "8%"
												},
												{
													"mData" : "phone",
													"visible" : false,
													"orderable" : false, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "8%",

												},
												{
													"mData" : "major",
													"visible" : false,
													"orderable" : false, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "8%",

												},
												{
													"mData" : "material_path",
													"visible" : false,
													"orderable" : false, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "8%",

												},
												{
													"mData" : "userid",
													"visible" : false,
													"orderable" : false, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "8%",

												},
												
												{
													"mData" : "limitTime",
													"visible" : false,
													"orderable" : false, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "8%",

												},
												{
													"mData" : "star",													
													"orderable" : true, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "8%",
													"render":function(data,type, row) {
														var str1="";
														var str2="";
														for(var i=0;i<data;i++){
															str1=str1+"<span class='icon-star star-color' name='color' id='color'></span>";
															i++;
														}														
														for(var i=0;i<5-data;i++){
															str2=str2+"<span class='icon-star' name='nocolor' id='nocolor'></span>";
															i++;
														}
														return str1+str2;
														
													}

												},
												{
													"mData" : "bid",
													"orderable" : false, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "8%",
													"render" : function(data,type, row) {
														   obj.push(row);														
															return data = '<span id="update" class="icon-search" value="'+(obj.length-1)+'">查看</span>';
														
													}
												} ],

										"columnDefs" : [ {
											"orderable" : false, // 禁用排序
											"targets" : [ 0 ], // 指定的列
											"data" : "bid",
											"render" : function(data, type, row) {
												
												return '<label><input type="checkbox" name="recordcheck" value="'+data+'" class="ck"></label>';
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
					
					/*1.获得导出的申报部门
					 *2.获得导出的基地类型
					 * 
					 */
					 $.ajax({							
							url : '',
							async : true,
							type : "POST",
							dataType : "json",
							cache : false, 
							success : function(data) {
								for ( var i=0;i<data[0].length;i++) {
									$("#daodept").after(
											"<option value="+data[0][i].aid+">"
													+ data[0][i].dept + "</option>");
									$("#shaiDepth").after(
											"<option value="+data[0][i].aid+" class='dee'>"
													+ data[0][i].dept+ "</option>");
									
								}
								
								for ( var i=0;i<data[1].length;i++) {				
									$("#daobase").after(
											"<option value="+data[1][i].id+">"
													+ data[1][i].name + "</option>");		
									$("#shaiTypeh").after(
											"<option value="+data[1][i].id+">"
													+ data[1][i].name + "</option>");
									
								}	
								
							},
							error : function(data) {
								alert("请求异常");
							}
						});
					 
					 $(document).on("click", "#color", function() {
						 
						 this.removeClass("star-color");
						 $(this).prop("id","nocolor");
						 page.draw(false);
					 });
					 
                     $(document).on("click", "#nocolor", function() {
						 
						 this.addClass("star-color");
						 $(this).prop("id","color");
						 page.draw(false);
					 });
					 
					 
					//点击删除按钮,判断是否已选择
					 $(document).on("click", "#delete", function() {	
						 
						 var chk_value =[];
							$('input[name="recordcheck"]:checked').each(function(){
							chk_value.push($(this).val());
							});							
							 if(chk_value.length==0)
							 {
								 bootbox.alert({
										message : "您还没有选择任何内容",
										size : 'small'
									});
								 return;
							  }
							 $("#deleteOneModal").modal('show');
							 
					 });	
					 
					 $(document).on("click", "#daoclose", function() {
						 $("#daobaseh").val("");
						 $("#daodepth").val("");
						 $("#daostarh").val("");
						 
					 });
					 
					 $(document).on("click", "#closeimport", function() {	 
						 
						 $("#fileResource").val("");
					 });
					 
					 
                    $(document).on("click", "#update", function() {	 
                    	var index=$(this).val();                    	
                    	$("#basenamed").val(obj[index].name);
    					$("#basetyped").val(obj[index].type);
    					$("#dept0d").val(obj[index].applydp);
    					$("#landaread").val(obj[index].landarea);
    					$("#buildingaread").val(obj[index].constructionarea);
    					$("#undertakeCountd").val(obj[index].undertake);
    					$("#usernamed").val(obj[index].username);
    					$("#userphoned").val(obj[index].phone);
    					$("#major_orientedd").html(obj[index].major);
    					$("#linkAddressd").html(obj[index].land_address);
    					$("#resourced").prop("href",obj[index].material_path);
    					$("#setdated").val(obj[index].time);
						$("#validdated").val(obj[index].limitTime);
						
						$("#edit").modal('show');
						 
					 });
					 
					 
					//确认删除 
					 $(document).on("click", "#delSubmit", function() {	
						 
						 var recordstr='(';
						 $("input[type='checkbox'][name='recordcheck']:checked").each(function() {	
							 
							 if(i!=0){
									recordstr=recordstr+","+$(this).val();									
								}else{
									recordstr=recordstr+$(this).val();									
								}					
												
									i++;
						 });
						 
						 recordstr=recordstr+')';
						 
						 $.ajax({
								data : {
									"recordstr" : recordstr
								},
								url : '',
								async : true,
								type : "POST",
								dataType : "json",
								cache : false, 
								success : function(data) {									
									page.draw(false); 
								},
								error : function(data) {
									alert("请求异常");
								}
							});
					 });
					 
					 $(document).on("click", "#confirmButton", function() {	
						 
						var basetype=$("#daobaseh option:checked").val();
						var applydept=$("#daodepth option:checked").val();
						var star=$("#daostarh option:checked").val();
						
						 $.ajax({
								data : {
									"basetype" : basetype,
									"applydept":applydept,
									"star":star
								},
								url : '',
								async : true,
								type : "POST",
								dataType : "json",
								cache : false, 
								success : function(data) {									
									$("#export").modal('hide'); 
								},
								error : function(data) {
									alert("请求异常");
								}							
					 });
						 
					 });
					 
         $(document).on("click", "#closebase", function() {	
						 
				$("#basename").val("");
				$("#deptRadio[value='1']").prop("checked",true);
				$("#deptty").val("");
				$("#basetype0").val("");
				$("#textContent").html("");
				$("#limit-population").val("");
				$("#filed-area").val("");
				$("#building-area").val("");
				$("#baseaddress").val("");
				$("#personName").val("");
				$("#personTel").val("");
				$("#applyfile").val("");
				$("#majorSuo").html("");
					 });	
         
         $(document).on("click", "#finishshai", function() {	
        	 
        	 var basetype=$("#shaiType option:selected").val();
        	 var dept=$("#shaiDept option:selected").val();
        	 var star=$("#starLink option:selected").val();
        	 
        	 page = $('#baseMaintain').dataTable({
        		 
        			"processing" : true,
        			"serverSide" : true,
        			"bSort" : false,
        			"bDestroy":true,
        			"aLengthMenu" : [ 5, 10, 20, 30 ], // 动态指定分页后每页显示的记录数。
        			"lengthChange" : true, // 是否启用改变每页显示多少条数据的控件
        			"iDisplayLength" : 5, // 默认每页显示多少条记录
        			"dom" : 'ftipr<"bottom"l>',
        			"ajax" : {
        				"url" : "",
        				"type" : "POST",
        				"data":{
        					"basetype":basetype,
        					"dept":dept,
        					"star":star
        				}
        			},
        			"aoColumns" : [
        					{ // aoColumns设置列时，不可以任意指定列，必须列出所有列。
        						"mData" : "id",
        						"orderable" : false, // 禁用排序
        						"sDefaultContent" : "",
        						"sWidth" : "4%"
        					},{
        						"mData" : "bid",													
        						"orderable" : true, // 禁用排序
        						"sDefaultContent" : "",
        						"sWidth" : "8%",

        					},{
        						"mData" : "time",													
        						"orderable" : true, // 禁用排序
        						"sDefaultContent" : "",
        						"sWidth" : "8%",

        					},
        					{ // aoColumns设置列时，不可以任意指定列，必须列出所有列。
        						"mData" : "name",
        						"orderable" : false, // 禁用排序
        						"sDefaultContent" : "",
        						"sWidth" : "10%"
        					},
        					{ // aoColumns设置列时，不可以任意指定列，必须列出所有列。
        						"mData" : "type",
        						"orderable" : false, // 禁用排序
        						"sDefaultContent" : "",
        						"sWidth" : "8%"
        					},
        					{
        						"mData" : "applydp",
        						"orderable" : false, // 禁用排序
        						"sDefaultContent" : "",
        						"sWidth" : "10%",

        					},
        					{
        						"mData" : "landarea",
        						"orderable" : false, // 禁用排序
        						"sDefaultContent" : "",
        						"sWidth" : "6%"
        					},

        					{
        						"mData" : "constructionarea",
        						"orderable" : false, // 禁用排序
        						"sDefaultContent" : "",
        						"sWidth" : "6%"
        					},
        					{
        						"mData" : "undertake",
        						"orderable" : false, // 禁用排序
        						"sDefaultContent" : "",
        						"sWidth" : "6%"
        					},
        					{
        						"mData" : "land_address",
        						"orderable" : false, // 禁用排序
        						"sDefaultContent" : "",
        						"visible" : false,
        						"sWidth" : "6%"
        					},
        					{
        						"mData" : "username",
        						"orderable" : false, // 禁用排序
        						"visible" : false,
        						"sDefaultContent" : "",
        						"sWidth" : "8%"
        					},
        					{
        						"mData" : "phone",
        						"visible" : false,
        						"orderable" : false, // 禁用排序
        						"sDefaultContent" : "",
        						"sWidth" : "8%",

        					},
        					{
        						"mData" : "major",
        						"visible" : false,
        						"orderable" : false, // 禁用排序
        						"sDefaultContent" : "",
        						"sWidth" : "8%",

        					},
        					{
        						"mData" : "material_path",
        						"visible" : false,
        						"orderable" : false, // 禁用排序
        						"sDefaultContent" : "",
        						"sWidth" : "8%",

        					},
        					{
        						"mData" : "userid",
        						"visible" : false,
        						"orderable" : false, // 禁用排序
        						"sDefaultContent" : "",
        						"sWidth" : "8%",

        					},
        					
        					{
        						"mData" : "limitTime",
        						"visible" : false,
        						"orderable" : false, // 禁用排序
        						"sDefaultContent" : "",
        						"sWidth" : "8%",

        					},
        					{
        						"mData" : "start",													
        						"orderable" : true, // 禁用排序
        						"sDefaultContent" : "",
        						"sWidth" : "8%",
        						"render":function(data,type, row) {
        							var str1="";
        							var str2="";
        							for(var i=0;i<data;i++){
        								str1=str1+"<span class='icon-star star-color' name='color' id='color'></span>";
        								i++;
        							}														
        							for(var i=0;i<5-data;i++){
        								str2=str2+"<span class='icon-star' name='nocolor' id='nocolor'></span>";
        								i++;
        							}
        							return str1+str2;
        							
        						}

        					},
        					{
        						"mData" : "id",
        						"orderable" : false, // 禁用排序
        						"sDefaultContent" : "",
        						"sWidth" : "8%",
        						"render" : function(data,type, row) {
        							   obj.push(row);														
        								return data = '<span id="update" class="icon-search" value="'+(obj.length-1)+'">查看</span>';
        							
        						}
        					} ],

        			"columnDefs" : [ {
        				"orderable" : false, // 禁用排序
        				"targets" : [ 0 ], // 指定的列
        				"data" : "id",
        				"render" : function(data, type, row) {
        					
        					return '<label><input type="checkbox" name="recordcheck" value="'+data+'" class="ck"></label>';
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
        		
        		$(".icon-filter").click();    	 
        	 
         });
					 					 
				
});



// //全选反选
$("#ck1").on("click",function() {
			if ($(this).prop("checked") == true) {
				$("#baseMaintain input[name='recordcheck']").prop(
						"checked", true);
			} else {
				$("#baseMaintain input[name='recordcheck']").prop(
						"checked", false);
			}
		});

$(".icon-filter").on("click", function () {
	recovery();
	$('#hide_ul').toggle();
});

function recovery(){	
	$("#shaiType").val("-1");
	$("#shaiDept").val("-1");
	$("#starLink").val("-1");
	
}


					