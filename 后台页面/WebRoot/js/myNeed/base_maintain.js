// JavaScript Document
var obj = [];
var tag1=true;
var XN=0;
$(document).ready(function() {

					var tag_0=$("#tag_0").text();
					if(tag_0=="false"){
						bootbox.alert({
							message : "您导入的Excel文件格式有错,请重新选择",
							size : 'small'
						});
					}else if(tag_0=="true"){
						var tag_1=$("#tag_1").text();
						if(tag_1=="1"){
							bootbox.alert({
								message : "您导入了已存在的基地名称，请重新导入",
								size : 'small'
							});
						}else if(tag_1=="2"){
							bootbox.alert({
								message : "您导入数据中存在相同的基地名称，请重新导入",
								size : 'small'
							});
						}else if(tag_1=="500"){
							bootbox.alert({
								message : "导入失败",
								size : 'small'
							});
						}else if(tag_1=="200"){
							bootbox.alert({
								message : "导入成功",
								size : 'small'
							});
						}										
					}
					
					var tage_0=$("#tage_0").text();
					if(tage_0=="0"){
						bootbox.alert({
							message : "目前没有任何数据用于导出",
							size : 'small'
						});
					}else if(tage_0=="500"){
						bootbox.alert({
							message : "导出失败",
							size : 'small'
						});
					}
					
					
					$.ajax({
						type : 'POST',
						dataType : 'json',		
						url : 'BaseApplyAllInfo.do',  
						async : false,
						cache : false,
						error : function(request) {
							bootbox.alert({
								message : "请求异常",
								size : 'small'
							});
						},
						success : function(data) {																				
							for ( var i=0;i<data[1].length;i++) {	
								if(i !== 5){
								$("#edit").find("#basetype1").after(
										"<option value="+data[1][i].name+">"
												+ data[1][i].name + "</option>");	
								$("#edit_nong").find("#basetype1").after(
										"<option value="+data[1][i].name+">"
												+ data[1][i].name + "</option>");	
								}
							}	
							for ( var i=0;i<data[0].length;i++){
						
								$("#deptSelect1").after(
										"<option value="+data[0][i].aid+">"
												+ data[0][i].dept + "</option>");
							}
							
						}

					});
					
					// 分页表格
					var page = $('#baseMaintain')
							.DataTable(
									{

										"processing" : true,
										"serverSide" : true,
										"bSort" : false,
										"aLengthMenu" : [ 5, 10, 20, 30 ], // 动态指定分页后每页显示的记录数。
										"lengthChange" : true, // 是否启用改变每页显示多少条数据的控件
										"iDisplayLength" : 10, // 默认每页显示多少条记录
										"ordering":true,
										"filter" : true,
										"dom" : 'ftipr<"bottom"l>',
										"ajax" : {
											"url" : "sendBaseinfo.do",
											"type" : "POST"
										},
										"aoColumns" : [
												{ // aoColumns设置列时，不可以任意指定列，必须列出所有列。
													"mData" : "id",
													"orderable" : false, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "4%"
												},
												{
													"mData" : "buildtime",
													"orderable" : true, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "10%",

												},
												{
													"mData" : "id",
													"orderable" : true, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "6%",
													"render":function(data,
															type, row) {
														var rexp_1=/^[a-zA-Z]+[0-9]+$/;
														var rexp_2=/^[a-zA-Z]+[0-9]+A[0-9]+$/;
														 if(rexp_1.test(data)||rexp_2.test(data)){
															 var digitIndex=data.replace(/[^a-zA-Z]+/ig,"@").indexOf('@');
															 return  data='#'+data.substring(0,digitIndex+8);
														 }else{
															 return data='#'+data;
														 }		
													}

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
													"mData" : "star",
													"orderable" : true, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "8%",
													"render" : function(data,
															type, row) {
														var str1 = "";
														var str2 = "";
														for (var i = 0; i < data; i++) {
															str1 = str1
																	+ "<span class='icon-star star-flag' name='color' id='color' value='"
																	+(i+1)
																	+"'></span>";
														}
														for (var i = 0; i < 5 - data; i++) {
															str2 = str2
																	+ "<span class='icon-star-empty star-flag' name='nocolor' id='nocolor' value='"
																	+(data+i+1)
																	+"'></span>";															
														}
														return str1 + str2;

													}

												},
												{
													"mData" : "land_address",
													"orderable" : false, // 禁用排序
													"sDefaultContent" : "",
													"visible" : false,
													"sWidth" : "6%"
												},
												{
													"mData" : "resperson",
													"orderable" : false, // 禁用排序
													"visible" : false,
													"sDefaultContent" : "",
													"sWidth" : "8%"
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
													"mData" : "facemajor",
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
													"mData" : "valid_date",
													"visible" : false,
													"orderable" : false, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "8%",

												},												
												{
													"mData" : "id",
													"orderable" : false, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "8%",
													"render" : function(data,
															type, row) {
														obj.push(row);														
														return data = '<span id="updateDetail" class="icon-edit edit-color" value="'
																+ (obj.length - 1)
																+ '"></span>';

													}
												} ],

										"columnDefs" : [ {
											"orderable" : false, // 禁用排序
											"targets" : [ 0 ], // 指定的列
											"data" : "id",
											"render" : function(data, type, row) {											
												return '<label><input type="checkbox" name="recordcheck" value="'
														+ data
														+ '" class="ck" id="checkHa"></label>';
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

					/*
					 * 1.获得导出的申报部门 2.获得导出的基地类型
					 * 
					 */
					
					
					
					// star星级点击的实时改变
$(document).on("click", "#color", function() {
						
						var starNum=$(this).attr('value');
						if(XN==0){
							$("#starget .star-flag").each(function(){
									if($(this).attr('value')>starNum){
									$(this).removeClass("icon-star");
									$(this).addClass("icon-star-empty");
									$(this).prop("id", "nocolor");
								}
							});
						}else{
							$(".Xstar .star-flag").each(function(){
									if($(this).attr('value')>starNum){
									$(this).removeClass("icon-star");
									$(this).addClass("icon-star-empty");
									$(this).prop("id", "nocolor");
								}
							});
						}
						
					});

					$(document).on("click", "#nocolor", function() {
						
						var starNum=$(this).attr('value');
						if(XN==0){
							$("#starget .star-flag").each(function(){
								$(this).removeClass("icon-star-empty");
								$(this).addClass("icon-star");
								$(this).prop("id", "color");
								if(--starNum==0){
									return false;
								}
							});
						}else{
							$(".Xstar .star-flag").each(function(){
								$(this).removeClass("icon-star-empty");
								$(this).addClass("icon-star");
								$(this).prop("id", "color");
								if(--starNum==0){
									return false;
								}
							});
						}
												
					});
					
					$(document).on("click","#delateStar",function(){
						$("#starget .star-flag").each(function(){
							
								$(this).removeClass("icon-star");
								$(this).addClass("icon-star-empty");
								$(this).prop("id", "nocolor");
							
						});		   
					});

					// 点击删除按钮,判断是否已选择
					$("#delete").click(function() {

						var chk_value = [];
						$('input[name="recordcheck"]:checked').each(function() {
							chk_value.push($(this).val());
						});
						if (chk_value.length == 0) {
							bootbox.alert({
								message : "您还没有选择任何内容",
								size : 'small'
							});
							return;
						}
						$("#deleteOneModal").modal('show');

					});

					
					$("#closeimport").click(function() {

						$("#fileResource").val("");
					});
					
					$(document).on("click", "#ZJ", function() {							
						$("#add").css("display","block");
					});
					
					$(document).on("click", "#closebas", function() {					
						$("#add").css("display","none");
						$("#add input").val('');
						$(".colle").prop('checked',true);
						$("#add select").val('');
					});
					var index;
					// 点击修改图标，填充修改模态框中的内容
					$(document).on("click", "#updateDetail", function() {						
						index = $(this).attr("value");
						var type=obj[index].type;
						var reg_area= /^\d+\.?\d*$/;
						var object_this;
						
						/*if(type=="新农院社会服务基地"||type=="校外教学实习基地"){
							$("#CJ1").html("基地创建时间");
						}*/
						if(type=="校外教学实习基地"){
							$("#CJ1").html("基地创建时间");
						}
						if(type=="新农院社会服务基地"){
							XN=1;
							object_this=$("#edit_nong");
							object_this.find("input").val('');
							object_this.find("#unitName").val(obj[index].cooperativeUnit);
						}else{
							XN=0;
							object_this=$("#edit");
							object_this.find("input").val('');
						}
						object_this.find("select").val('平方米');
						
						//截断字符串
					    var src=obj[index].id;
					    var rexp_1=/^[a-zA-Z]+[0-9]+$/;
						var rexp_2=/^[a-zA-Z]+[0-9]+A[0-9]+$/;
						var digitIndex;
						if(rexp_1.test(src)||rexp_2.test(src)){							
							 digitIndex=src.replace(/[^a-zA-Z]+/ig,"@").indexOf('@');							
							 object_this.find("#baseid").val('#'+src.substring(0,digitIndex+8));								
						 }else{
							 object_this.find("#baseid").val('#'+obj[index].id);
						 }
						 						
						 object_this.find("#hiddenbaseid").val('#'+obj[index].id);
						
						object_this.find("#basenamed").val(obj[index].name);
						object_this.find("#basetyped").val(obj[index].type);
						object_this.find("#dept0d").val(obj[index].applydp);
						
						var landarea=obj[index].landarea;						
						if(landarea.match(reg_area)){
							object_this.find("#landaread").val(landarea);
						}else if(landarea.substring(landarea.length-1)=="亩"){
							object_this.find("#landaread").val(landarea.substring(0,landarea.length-1));
							object_this.find("#landarea_select").val("亩");
						}else if(landarea.substring(landarea.length-3)=="平方米"){
							object_this.find("#landaread").val(landarea.substring(0,landarea.length-3));
							object_this.find("#landarea_select").val("平方米");
						}
                        var buildingarea=obj[index].constructionarea;
                        if(buildingarea.match(reg_area)){
                        	object_this.find("#buildingaread").val(buildingarea);
                        }else if(buildingarea.substring(buildingarea.length-3)=="平方米"){
                        	object_this.find("#buildingaread").val(buildingarea.substring(0,buildingarea.length-3));
                        }						
						
						object_this.find("#undertakeCountd").val(obj[index].undertake);
						object_this.find("#usernamed").val(obj[index].username);
						object_this.find("#userphoned").val(obj[index].phone);

						//将传来的专业名进行分开
						var str1 = obj[index].facemajor;
						var str2 = obj[index].majorid;
						var facemajors = new Array();
						var facemajorid = new Array();
						facemajors=str1.split(",");
						facemajorid=str2.split(",");
						var majorString3 = "";
						for (var i=0;i<facemajors.length ;i++ )
						{
							majorString3 = majorString3+"<span class='majorchoose1'><input name='majorid1' hidden='' value='"+facemajorid[i]+"'><label>"+facemajors[i]+"</label></span>";
						} 
						object_this.find("#major_orientedd").html(majorString3);
						object_this.find("#major_orientedd1").html(majorString3);
						object_this.find("#linkAddressd").val(obj[index].land_address);
				
						object_this.find("#collegenamed").val(obj[index].collegeName);
						object_this.find("#collegephoned").val(obj[index].collegePhone);
						object_this.find("#resourced").prop("href", obj[index].material_path);
						
						if(obj[index].material_path=="null"||obj[index].material_path==""||obj[index].material_path==null){			
							object_this.find("#resourcetr").prop("hidden",true); 
						}else{		
							object_this.find("#resourcetr").prop("hidden",false); 
							object_this.find("#resourced").prop("href", obj[index].material_path);
						}
						object_this.find("#setdated").val(obj[index].buildtime);
						object_this.find("#adddate").val(obj[index].endtime);						
						object_this.find("#starget").html("<span id='delateStar' class='icon-remove-sign'></span>"+$(this).closest('tr').find('td:eq(9)').html());
						object_this.find("#personDuty").val(obj[index].resperson);
											
						object_this.show();

					});
					
					/*$("#chooseMajor").click(function(){
						$("#Selectmajor").modal('show');
					});*/

					// 确认删除
					$("#delSubmit").click(function() {										
										var recordstr = '(';
										var i = 0;									
										$(
												"input[type='checkbox'][name='recordcheck']:checked")
												.each(
														function() {

															if (i != 0) {
																recordstr = recordstr
																		+ ",'"
																		+ $(
																				this)
																				.val()+"'";
															} else {
																recordstr = recordstr
																		+ "'"+$(
																				this)
																				.val()+"'";
															}

															i++;
														});

										recordstr = recordstr + ')';										
										$.ajax({
											data : {
												"recordstr" : recordstr
											},
											url : 'delBaseinfo.do',
											async : true,
											type : "POST",
											dataType : "json",
											cache : false,
											success : function(data) {											
													bootbox.alert({
														message : data.flag,
														size : 'small'
													});
													$("#deleteOneModal").modal('hide');											
													page.draw(false);																								
											},
											error : function(data) {
												alert("请求异常");
											}
										});
									});

					// 确认导出
					$("#confirmButton").click(function() {
					  $("#export").modal('hide');                      
					});
					
					$("#exportButton").click(function() {
					   //将导出列表下的option去除
					    $("#daobaseh option:gt(0)").remove();
					    $("#daodepth option:gt(0)").remove();
					   //选项恢复初始值
						$("#daobaseh").val("-1");
						$("#daodepth").val("-1");
						$("#daostarh").val("-1");
						
						$.ajax({
							url : 'getManyinfo.do',
							async : true,
							type : "POST",
							dataType : "json",
							cache : false,
							success : function(data) {
								for (var i = 0; i < data[0].length; i++) {				
									$("#daodept")
											.after(
													"<option class='rest' value="
															+ data[0][i].dept
															+ " class='dee'>"
															+ data[0][i].dept
															+ "</option>");

								}

								for (var i = 0; i < data[1].length; i++) {
									$("#daobase")
											.after(
													"<option value="
															+ data[1][i].id + ">"
															+ data[1][i].name
															+ "</option>");
									
									
								}

							},
							error : function(data) {
								alert("请求异常");
							}
						});

					});
					var basename="";
					$(document).on("focus", "#basenamed", function(){						
						 basename=obj[index].name;							
					});
					$(document).on("blur", "#basenamed", function() {
						var value=$(this).val();											
						if(value!=basename){							
							 $.ajax({
									type : 'POST',
									data:{
										"name":value
									},
									dataType:'text',
									url : 'CheckName.do', 
									async : false,
									cache : false,
									error : function(request) {
										bootbox.alert({
											message : "请求异常",
											size : 'small'
										});
									},
									success : function(data) {					
										if(data=="false"){						
											/*$("#display").html("");*/
											tag1=true;
										}else{						
											/*$("#display").html("该基地名称已存在，请重新输入");
											$("#basenamed")[0].focus();*/
											/*bootbox.alert({
												message : "该基地名称已存在,请重新输入",
												size : 'small'
											});*/
											$("#basenamed")[0].focus();											
											alert("该基地名称已存在,请重新输入");
											tag1=false;
										}
									}

								}); 
						}else{
							tag1=true;
						}
						
					});
					//确认修改
					$(document).on("click", "#saverun_0,#saverun_1", function(){						
						var thisId=$(this).prop("id");
						var object_this=(thisId=="saverun_0"?$("#edit"):$("#edit_nong"));						
						var basenamed=object_this.find("#basenamed").val();						
						var basetyped=object_this.find("#basetyped").val();						
						var landaread=object_this.find("#landaread").val();					
						var buildingaread=object_this.find("#buildingaread").val().trim();
						var undertakeCountd=object_this.find("#undertakeCountd").val();
						var reg=/^\d+$/;
						var reg_area= /^\d+\.?\d*$/;
						if(undertakeCountd==""){
							undertakeCountd=0;
						}else{
							if(!undertakeCountd.match(reg)){
								bootbox.alert({
									message : "可承担人数只能为整数",
									size : 'small'
								});
							 return;
							}
						}
						if(landaread!=""){
							if(!landaread.match(reg_area)){
								bootbox.alert({
									message : "土地面积只能为整数或小数",
									size : 'small'
								});
							 return;
							}else{
								landaread=landaread+object_this.find("#landarea_select").val();	
							}
							}
						
						if(buildingaread!=""){
							if(!buildingaread.match(reg_area)){
								bootbox.alert({
									message : "建筑面积只能为整数或小数",
									size : 'small'
								});
							 return;
							}else{
								buildingaread=buildingaread+object_this.find("#buildingaread_select").val();	
							}
							}
						var usernamed=object_this.find("#usernamed").val();
						var userphoned=object_this.find("#userphoned").val();
						var collegenamed=object_this.find("#collegenamed").val();
						var collegephoned=object_this.find("#collegephoned").val();
						
						var cooperativeUnit=(thisId=="saverun_0"?"":object_this.find("#unitName").val());
						var tag=(thisId=="saverun_0"?0:1);
						
						//$("#major_orientedd").html(obj[index].facemajor);
						
						var linkAddressd=object_this.find("#linkAddressd").val();
						var personDuty=object_this.find("#personDuty").val();//法定责任人						
						var setdated=object_this.find("#setdated").val();						
						var adddate=object_this.find("#adddate").val();
						
						if(!tag1){		
							 bootbox.alert({
									message : "该基地名称已存在，请重新输入",
									size : 'small'
								});							
							 return;
						}
						if(basenamed==""){
							bootbox.alert({
								message : "请填写基地名称",
								size : 'small'
							});
							return;
						}						
						if(basetyped==""){
							bootbox.alert({
								message : "请填写基地类型",
								size : 'small'
							});
							return;
						}
						if(adddate==""){
							bootbox.alert({
								message : "请填写截止日期",
								size : 'small'
							});
							return;
						}
						
						
						var start=setdated.split("-");
						var end=adddate.split("-");
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
						
						// 获得隐藏框
						var baseid=object_this.find("#hiddenbaseid").val();
						baseid=baseid.substring(1);
						var majorString = "";

						var i=0;
						var ZY=0;
						if(XN==0){
							$(".elseText input[name = 'majorid1'").each(function(){
								ZY++;
								var id = $(this).val();
								majorString =majorString + "('"+baseid+"','"+id+"'),";
//								if(id==-1){
//									majorString =majorString + "('"+baseid+"','"+id+"'),";
//								}
							
							});
							$(".Estar .icon-star").each(function() {
								i++;							
							});
						}else{							
							$(".XNtext input[name = 'majorid1'").each(function(){
								ZY++;
								var id = $(this).val();
								majorString =majorString + "('"+baseid+"','"+id+"'),";
//								alert(majorString);
							});
							$(".Xstar .icon-star").each(function() {
								i++;							
							});
						}
						if(ZY==0){
							majorString =majorString + "('"+baseid+"','-1'),";
							
						}
						majorString = majorString.substring(0,majorString.length-1);
						
//						if(majorString==""){
//							majorString = majorString+""
//						}
						var star=i;											
						if(time!==0){
							bootbox.alert({
								message : "截止日期超过创建日期,请更改",
								size : 'small'
							});
							return;
						}
						$.ajax({
							data : {
								"baseid" : baseid,
								"star" : star,
								"adddate" : adddate,
							    "basenamed":basenamed,								
								"basetyped":basetyped,
								"landaread":landaread,									
							    "buildingaread":buildingaread,								
								"undertakeCountd":undertakeCountd,
								"usernamed":usernamed,
								"userphoned":userphoned,								
								"linkAddressd":linkAddressd,
								"personDuty":personDuty,//法定责任人
								"collegenamed":collegenamed,
								"collegephoned":collegephoned,
								"cooperativeUnit":cooperativeUnit,
								"tag":tag,
								"majorString":majorString//面向专业
							},
							url : 'updateBaseInfo.do',
							async : true,
							type : "POST",
							dataType : "json",
							cache : false,
							success : function(data) {
								bootbox.alert({
									message : data.flag,
									size : 'small'
								});
								object_this.hide();
								$("#adddate").val("");
								page.draw(false);
							},
							error : function(data) {
								bootbox.alert({
									message : "请求异常",
									size : 'small'
								});
							}
						});
						
						
					});
					
					$(".closeModal_edit_nong").click(function() {
						$("#edit_nong").hide();
					});
					
					$(".closeModal_edit").click(function() {
						$("#edit").hide();
					});

					$("#closebase").click(function() {

						$("#basename").val("");
						$("#deptRadio[value='1']").prop("checked", true);
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
					
					$(document).on("click", "#cleark", function() {	
						$("#adddate").val("");
					});
					
					//增加框的js控制
					$(document).on("click", "#submitForm_0", function() {
						var basename=$("#basename").val();
						var applyName = $("#deptty").find("option:selected").text();//获取下拉列表的文本-- by jimao
						$("#applyNameId").val(applyName);							//获取隐藏input的文本值
						var deptty=$("#deptty").val();
						var basetype=$("#basetype0").val();
						var baseaddress=$("#baseaddress").val();
						var personName=$("#personName").val();
						var personTel=$("#personTel").val();
						var lawperson=$("#lawPerson").val();
						var validdastart=$("#validdastart").val();
						var validdaend=$("#validdaend").val();
						var limit_population=$("#limit-population").val().trim();
						var collegeNameIt=$("#collegeNameIt").val().trim();
						var collegeTelIt=$("#collegeTelIt").val().trim();
						var unitIt=$("#unitIt").val().trim();	
						var base_area=$("#base-area").val().trim();
						var filed_area=$("#filed-area").val().trim();
						var reg=/^\d+$/;
						var reg_area= /^\d+\.?\d*$/;
						var msgCollege;
						if(!tag){		
							 bootbox.alert({
									message : "该基地名称已存在，请重新输入",
									size : 'small'
								});
							 return;
						}	
						if(basename==""){
							 bootbox.alert({
									message : "请填写基地名称",
									size : 'small'
								});
							 return;
						}
						if(deptty==""){
							 bootbox.alert({
									message : "请选择申报部门",
									size : 'small'
								});
							 return;
						}
						if(basetype==""){
							 bootbox.alert({
									message : "请选择基地类型",
									size : 'small'
								});
							 return;
						}
						if(limit_population!=""){
							if(!limit_population.match(reg)){
								bootbox.alert({
									message : "可承担人数只能为整数",
									size : 'small'
								});
							 return;
							}
							}
						if(filed_area!=""){
							if(!filed_area.match(reg_area)){
								bootbox.alert({
									message : "土地面积只能为整数或小数",
									size : 'small'
								});
							 return;
							}
							}
						
						if(base_area!=""){
							if(!base_area.match(reg_area)){
								bootbox.alert({
									message : "建筑面积只能为整数或小数",
									size : 'small'
								});
							 return;
							}
							}
						if(baseaddress==""){
							bootbox.alert({
								message : "请填写通信地址",
								size : 'small'
							});
						 return;		
						}
						if(lawperson==""){
							bootbox.alert({
								message : "请填写法定责任人",
								size : 'small'
							});
						 return;	
						}
						if(personName==""){
							bootbox.alert({
								message : "请填写基地联系人",
								size : 'small'
							});
						 return;	
						}
						if(personTel==""){
							bootbox.alert({
								message : "请填写基地联系人电话",
								size : 'small'
							});
						 return;	
						}
						if(collegeNameIt==""){
							msgCollege=(basetype==2?"请填写服务团队负责人":"请填写学院联系人");
							bootbox.alert({
								message : msgCollege,
								size : 'small'
							});
						 return;	
						}
						if(collegeTelIt==""){
							msgCollege=(basetype==2?"请填写服务团队负责人电话":"请填写学院联系人电话");
							bootbox.alert({
								message : msgCollege,
								size : 'small'
							});
						 return;	
						}
						if(unitIt==""){
							if(basetype==2){
								bootbox.alert({
									message : "请填写合作单位名称",
									size : 'small'
								});
							 return;
							}
						}
						
						if(!flag1){
							 bootbox.alert({
						            message: "上传资料仅限于rar,zip压缩包格式",
						            size: 'small'
						        });
							 return;
						}
						if(!flag2){
							bootbox.alert({
					            message: "上传资料大小不能大于10M",
					            size: 'small'
					        });
							return;
						}
						if(validdastart==""){
							 bootbox.alert({
									message : "请填写创建日期",
									size : 'small'
								});
							 return;
						}
						if(validdaend==""){
							 bootbox.alert({
									message : "请填写截止日期",
									size : 'small'
								});
							 return;
						}
						var start=validdastart.split("-");
						var end=validdaend.split("-");
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
								message : "截止日期超过创建日期,请更改",
								size : 'small'
							});
							return;
						}
						$("#myForm").submit();
					});

					$("#finishshai").click(function() {
                            obj=[];
										var basetype = $(
												"#shaiType option:selected")
												.val();
										var dept;										
										if($("#tag_2").text()==null||$("#tag_2").text()==""){
											dept = $(
											"#shaiDept option:selected")
											.val();
										}else{
											dept=$("#tag_2").text();
										}
										
										var star = $(
												"#starLink option:selected")
												.val();

										page = $('#baseMaintain')
												.DataTable(
														{

															"processing" : true,
															"serverSide" : true,
															"bSort" : false,
															"bDestroy" : true,
															"aLengthMenu" :[ 5, 10, 20, 30 ], // 动态指定分页后每页显示的记录数。
															"lengthChange" : true, // 是否启用改变每页显示多少条数据的控件
															"iDisplayLength" : 10, // 默认每页显示多少条记录
															"ordering":true,
															"dom" : 'ftipr<"bottom"l>',
															"ajax" : {
																"url" : "getshaiBaseinfo.do",
																"type" : "POST",
																"data" : {
																	"basetype" : basetype,
																	"dept" : dept,
																	"star" : star
																}
															},
															"aoColumns" : [
																	{ // aoColumns设置列时，不可以任意指定列，必须列出所有列。
																		"mData" : "id",
																		"orderable" : false, // 禁用排序
																		"sDefaultContent" : "",
																		"sWidth" : "4%"
																	},
																	{
																		"mData" : "buildtime",
																		"orderable" : true, // 禁用排序
																		"sDefaultContent" : "",
																		"sWidth" : "8%",

																	},
																	{
																		"mData" : "id",
																		"orderable" : true, // 禁用排序
																		"sDefaultContent" : "",
																		"sWidth" : "8%",
																		"render":function(data,
																				type, row) {
																			var rexp_1=/^[a-zA-Z]+[0-9]+$/;
																			var rexp_2=/^[a-zA-Z]+[0-9]+A[0-9]+$/;
																			 if(rexp_1.test(data)||rexp_2.test(data)){
																				 var digitIndex=data.replace(/[^a-zA-Z]+/ig,"@").indexOf('@');
																				 return  data='#'+data.substring(0,digitIndex+8);
																			 }else{
																				 return data='#'+data;
																			 }	
																			}

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
																	},{
																		"mData" : "star",
																		"orderable" : true, // 禁用排序
																		"sDefaultContent" : "",
																		"sWidth" : "8%",
																		"render" : function(data,
																				type, row) {
																			var str1 = "";
																			var str2 = "";
																			for (var i = 0; i < data; i++) {
																				str1 = str1
																						+ "<span class='icon-star star-flag' name='color' id='color' value='"
																						+(i+1)
																						+"'></span>";
																			}
																			for (var i = 0; i < 5 - data; i++) {
																				str2 = str2
																						+ "<span class='icon-star-empty star-flag' name='nocolor' id='nocolor' value='"
																						+(data+i+1)
																						+"'></span>";															
																			}
																			return str1 + str2;

																		}

																	},
																	{
																		"mData" : "land_address",
																		"orderable" : false, // 禁用排序
																		"sDefaultContent" : "",
																		"visible" : false,
																		"sWidth" : "6%"
																	},
																	{
																		"mData" : "resperson",
																		"orderable" : false, // 禁用排序
																		"visible" : false,
																		"sDefaultContent" : "",
																		"sWidth" : "8%"
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
																		"mData" : "facemajor",
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
																		"mData" : "valid_date",
																		"visible" : false,
																		"orderable" : false, // 禁用排序
																		"sDefaultContent" : "",
																		"sWidth" : "8%",

																	},
																	
																	{
																		"mData" : "id",
																		"orderable" : false, // 禁用排序
																		"sDefaultContent" : "",
																		"sWidth" : "8%",
																		"render" : function(
																				data,
																				type,
																				row) {
																			obj.push(row);
																			return data = '<span id="updateDetail" class="icon-edit edit-color" value="'
																					+ (obj.length - 1)
																					+ '"></span>';
																			

																		}
																	} ],

															"columnDefs" : [ {
																"orderable" : false, // 禁用排序
																"targets" : [ 0 ], // 指定的列
																"data" : "id",
																"render" : function(
																		data,
																		type,
																		row) {

																	return '<label><input type="checkbox" name="recordcheck" value="'
																			+ data
																			+ '" class="ck" id="checkHa"></label>';
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

										$('#hide_ul').toggle();

									});

				});
//限制导入的文件不能为空
$("#certainimport").click(function(){
	var fireStr=$("#fileResource").val();
	if($("#fileResource").val()===""){
		bootbox.alert({
			message : "导入的文件不能为空",
			size : 'small'
		});
		return;
	}
	var fireL=fireStr.lastIndexOf(".");
	fireStr=fireStr.substring(fireL);
	if(fireStr!=".xls"&&fireStr!=".xlsx"){
		bootbox.alert({
			message : "请导入Excel格式文档",
			size : 'small'
		});
		$("#fileResource").val("");
		return;
	}
	$("#daoruform").submit();
});
// //全选反选
$("#ck1").on("click", function() {
	if ($(this).prop("checked") == true) {
		$("#baseMaintain input[name='recordcheck']").prop("checked", true);
	} else {
		$("#baseMaintain input[name='recordcheck']").prop("checked", false);
	}
});

$(".icon-filter").on("click", function() {
	//将刷选内的option选项去除
	$("#shaiType option:gt(0)").remove();
	$("#shaiDept option:gt(0)").remove();
	//将刷选的各值恢复成初始值
	$("#shaiType").val("-1");
	$("#shaiDept").val("-1");
	$("#starLink").val("-1");
	$.ajax({
		url : 'getManyinfo.do',
		async : true,
		type : "POST",
		dataType : "json",
		cache : false,
		success : function(data) {
			
			for (var i = 0; i < data[0].length; i++) {				
				$("#shaiDepth")
						.after(
								"<option class='rest' value="
										+ data[0][i].dept
										+ " class='dee'>"
										+ data[0][i].dept
										+ "</option>");

			}

			for (var i = 0; i < data[1].length; i++) {				
				$("#shaiTypeh")
						.after(
								"<option value="
										+ data[1][i].id + ">"
										+ data[1][i].name
										+ "</option>");
				
				
			}

		},
		error : function(data) {
			bootbox.alert({
				message : "请求异常",
				size : 'small'
			});		
		}
	});
	$('#hide_ul').toggle();
});



$(document).on("change", "#deptSelectOne1", function() {	
	var id= this.value;	
	$(".majorhide1").html("");
	if(id==""){
		return;
	}
		$.ajax({
		type : 'POST',
		dataType : 'json',
		data:{
			"aid":id
		},
		url : 'getMajor.do',  //��ȡרҵ
		async : false,
		cache : false,
		error : function(request) {
			bootbox.alert({
				message : "请求异常",
				size : 'small'
			});
		},
		success : function(data) {
			var tag;
			for ( var i=0;i<data.length;i++) {
				tag=false;
				$("#majorSuo1 input").each(function(index){					
					var id=$(this).val();				
					if(data[i].mid==id){
						tag=true;
						return;
					}					
				});
				if(!tag){
				$(".majorhide1").append(
						"<span class='majorcheck1'><input type='checkbox' placeholder='"+id+"' value='"+data[i].mid+"' class='"+data[i].mname+"'/><label>"+data[i].mname+"</label></span>");				
				}
			}
			for ( var i=0;i<data.length;i++) {
				tag=false;
				$("#majorSuo2 input").each(function(index){					
					var id=$(this).val();				
					if(data[i].mid==id){
						tag=true;
						return;
					}					
				});
				if(!tag){
				$(".majorhide2").append(
						"<span class='majorcheck2'><input type='checkbox' placeholder='"+id+"' value='"+data[i].mid+"' class='"+data[i].mname+"'/><label>"+data[i].mname+"</label></span>");				
				}
			}
		}

	});
	
});



$(document).on("click", ".majorcheck1", function() {
	var obj=$(this).children('input');		
	var str="<span class='majorchoose1'><input name='majorid1' type='checkbox' checked  value='"+obj.val()+"' class='"+obj.attr('class')+"' placeholder='"+obj.prop("placeholder")+"'/><label>"+obj.attr('class')+"</label></span>";
	this.remove();
	$("#majorSuo1").append(str);	
	var tag=$("#majormain1").css("display");
	if(tag=="none"){
		$("#majormain1").css("display","block");
	}	
});

$(document).on("click", ".majorchoose1", function() {	
	var obj=$(this).children('input');
	var id=$("#deptSelectOne1").val();	
	if(id!=obj.prop("placeholder")){
		bootbox.alert({
			message : "请选择相应的学院，再进行更改",
			size : 'small'
		});
		obj.prop("checked",true);
	 return;

	}
	var str="<span class='majorcheck1'><input type='checkbox' name='majorcheck1' value='"+obj.val()+"' class='"+obj.attr('class')+"' placeholder='"+obj.prop("placeholder")+"'/><label>"+obj.attr('class')+"</label></span>";
	this.remove();
	$(".majorhide1").append(str);	
	if($("#majorSuo1 .majorchoose1")[0]==null){			
		$("#majormain1").css("display","none");
	}	
});

$(document).on("click", "#chooseMajor", function() {
	$("#deptSelectOne1").val("");
	$(".majorhide1 span[class = majorcheck1]").html("");
	$("#majorSuo1").html("");
	if($("#majorSuo1 .majorchoose1")[0]==null){			
		$("#majormain1").css("display","none");
	}
});

$(document).on("click", ".confirm1", function() {
	var content=$("#majorSuo1").html();
	if(XN==1){
		$(".XNtext").html(content);
		$(".XNtext input").prop("hidden",true);		
	}else{
		$(".elseText").html(content);
		$(".elseText input").prop("hidden",true);
	}
	$(".majorhide1").html("");
	if($("#majorSuo1 .majorchoose1")[0]==null){			
		$("#majormain1").css("display","none");
	}
	$("#deptSelectOne1").val("");
});


