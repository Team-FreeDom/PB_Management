// JavaScript Document
var obj=[];
function a(){
	alert("on")
	}						
$(document).ready(function() {
				//分页表格 
              var applytable =$('#Repairmanage').dataTable(
			  {
				  "processing": true,
        		  "serverSide": true,
				  "bSort": false,
				  "aLengthMenu":[5,10,20,30], //动态指定分页后每页显示的记录数。
					"lengthChange":true, //是否启用改变每页显示多少条数据的控件
					"iDisplayLength" : 5,  //默认每页显示多少条记录
					"dom":'ftipr<"bottom"l>',
					"ajax" : {
							"url" : "xxx.do",
							"type" : "POST"
						},  
					"aoColumns" : [                                        
									{
										"mData" : "id",
										"orderable" : true, // 禁用排序
										"sDefaultContent" : "",
										
									},
									{ //aoColumns设置列时，不可以任意指定列，必须列出所有列。
										"mData" : "projectname",
										"orderable" : true, // 禁用排序
										"sDefaultContent" : "",
									},
									{
										"mData" : "basename",
										"orderable" : true, // 禁用排序
										"sDefaultContent" : "",

									},
									{
										"mData" : "name",
										"orderable" : true, // 禁用排序
										"sDefaultContent" : "",

									},
									{
										"mData" : "time",
										"orderable" : true, // 禁用排序
										"sDefaultContent" : "",
									},
									{
										"mData" : "money",
										"orderable" : false, // 禁用排序
										"sDefaultContent" : "",
									},
									{
										"mData" : "status",
										"orderable" : true, // 禁用排序
										"sDefaultContent" : "",
									},
									{
										"mData" : "budget",
										"orderable" : true, // 禁用排序
										"visible" :false,
										"sDefaultContent" : "",
									},
									{
										"mData" : "reason",
										"orderable" : true, // 禁用排序
										"visible" :false,
										"sDefaultContent" : "",
									},
									{
										"mData" : "address",
										"orderable" : true, // 禁用排序
										"visible" :false,
										"sDefaultContent" : "",
									},
									{
										"mData" : "linkaddress",
										"orderable" : true, // 禁用排序
										"visible" :false,
										"sDefaultContent" : "",
									},
									
									{
										"mData" : "id",
										"orderable" : false, // 禁用排序
										"sDefaultContent" : '',
										"render":function(data,type,row){					
											obj.push(row);							
											return data="<button type='button' class='btn btn-warning btn-xs' value='"+(obj.length-1)
														+ "' id='checkdetale'>查看</button>";
										}
									}
							],
					
					
					"columnDefs" :
							[{
								"orderable" : false, // 禁用排序
								"targets" : [0], // 指定的列
								"data" : "id",
								"render" : function(data, type, row) {
									 data=row.id;
									return '<input type="checkbox" value="'+ data + '" name="idname" class="ck"  />';
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
					  
	  //获取基地列表
        $.ajax({
 			type : 'POST',
 			dataType : 'json',
 			url : '.do',
 			async : false,
 			cache : false,
 			error : function(request) {
 				bootbox.alert({
         			  message: "error",
         			  size: 'small'
         		  });
 			},
 			success : function(data) {
 				 for (var i=0;i<data[0].length;i++) {
 					$("#EbasenameID").after(
 							"<option value="+data[0][i].basename+">"
 									+data[0][i].basename+"</option>");
 									$("#AbasenameID").after(
 				 							"<option value="+data[0][i].name+">"
 				 									+data[0][i].basename+"</option>");
													$("#SbasenameID").after(
														"<option value="+data[0][i].name+">"
																+data[0][i].basename+"</option>");

 				 }
 			}
 		}); 
		
					//删除操作	 
					var flag=0;
					$('#delete').click(function() {
					  flag=0;
						$("#Repairmanage input[name='idname']").each(function () {
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
							message: "是否确认删除",
							size: 'small',
							buttons: {
			
								confirm: {
									label: 'Yes',
									className: 'btn-success'
								},
								cancel: {
									label: 'No',
									className: 'btn-danger'
								},
							},
							callback: function (result) {
								if(result){
									
									var deletstr = '(';//删除记录的格式(1,2,3,4,5)
									var keyid;
									$("input[type='checkbox'][name='idname']:checked").each(function() {
															keyid = $(this).data("id");
															if (i != 0) {
																deletstr = deletstr+ ','+ $(this).val();
															}
															else{
																deletstr = deletstr+ $(this).val();
																}
															i++;
														});
										deletstr = deletstr + ')';
										$.ajax({
											url : '',
											type : 'post',
											dataType : 'json',
											data : {
												"deletstr" : deletstr,
											},
											success : function(msg) {
												bootbox.alert({
													message : msg.str,
													size : 'small'
												});
												applytable.draw(false);
											}
										});//end

								}
							}
						});
			
					}
					});
//点击增加清空数据函数
$("#ZJ").click(function(){
		$("#Aprojectname").val("");
		$("#Abasename").val("1");
		$("#Aname").val("");
		$("#Atime").val("");
		$("#Abudget").val("");
		$("#Aaddress").val("");
		$("#Areason").val("");
})					
//增加操作
$("#save").click(function(){
		if($("#Aprojectname").val()==""){
				bootbox.alert({
				message : "请填写项目名称",
				size : 'small'
			});	
			return 0;
		}
		else if($("#Abasename").val()=="1"){
				bootbox.alert({
				message : "请选择基地名称",
				size : 'small'
				});	
				return 0;
		}
					
		else if($("#Aname").val()==""){
				bootbox.alert({
				message : "请填写申报人姓名",
				size : 'small'
				});	
				return 0;
		}
		else if($("#Abudget").val()==""){
				bootbox.alert({
				message : "请填写预算金额",
				size : 'small'
				});	
				return 0;
		}
		else if($("Aaddress").val()==""){
				bootbox.alert({
				message : "请填写具体地址",
				size : 'small'
				});	
				return 0;
		}
		else if($("#Areason").val()==""){
				bootbox.alert({
				message : "请填写原因说明",
				size : 'small'
				});	
				return 0;
		}
		$("#applyaddform").submit();
					
})
					 
//修改操作
$(document).on("click", "#checkdetale", function() {	
	
	var index=$(this).val();
	
	$("#Eprojectname").val(obj[index].projectname);
	$("#Ebasename").val(obj[index].basename);
	$("#Ename").val(obj[index].name);
	$("#Etime").val(obj[index].time);
	$("#Ebudget").val(obj[index].budget);
	$("#Emoney").val(obj[index].money);
	$("#Eaddress").val(obj[index].address);
	$("#Ereason").val(obj[index].reason);
	$("#Elink").prop("href",obj[index].linkaddress);
	$("#edit").modal('show');
	
});
$("#saverun").click(function(){
				bootbox.confirm({
				message: "是否确认修改",
				buttons: {
						confirm: {
								label: 'Yes',
								className: 'btn-success'
								},
						cancel: {
								label: 'No',
								className: 'btn-danger'
								},
							},
						callback: function (result) {
								if(result){
									$("#applyeditform").submit();
									}
								}
							});
})
//文件上传限制								
$('.file').change(function() {    
    var filepath = $(this).val();
    var file_size = this.files[0].size;
    var size = file_size / 1024;
    var extStart = filepath.lastIndexOf(".");
    var ext = filepath.substring(extStart, filepath.length).toUpperCase();
    if (ext != ".RAR" && ext != ".Z") {
        bootbox.alert({
            message: "上传资料仅限于rar压缩包格式ʽ",
            size: 'small'
        });
        flag1=false;
        return false;
    }
    if (size > 1024 * 10) {
        bootbox.alert({
            message: "上传资料大小不能大于10M",
            size: 'small'
        });
        flag2=false;
        return false;
    }   
    flag1=true;
    flag2=true;
    return false;
});

				////全选反选
				var flag=0;
				$("#ck1").on("click", function () {
					if ($(this).prop("checked") === true) {
						$("#Repairmanage input[name='allcheckbox']").prop("checked", true);
						
					} else {
						$("#Repairmanage input[name='allcheckbox']").prop("checked", false);
						
					}
					$("#ck2").prop("checked", false);
				 });
				 
				  $("#ck2").click(function () {//反选  
                		$("#Repairmanage input[name='allcheckbox']").each(function () {  
                    	$(this).prop("checked", !$(this).prop("checked"));
						 
                	}); 
					$("#ck1").prop("checked", false); 
           		 }); 
	
$("#import").click(function (){//每次点击导出是清空数据
	$("#Sbasename").val("1");
	$("#year").val("");
	});	
	
$("#export").click(function (){
	
	var basename=$("#Sbasename option:selected").val();
	var year=$("#year").val();
	if(basename=="1"){
		basename=null;
	}
	if(year==''){
		year="-1";
		}
	$.ajax(
	{
		url:'',
		type:POST,
		datatype:'json',
		data:{
			"basename": basename,
			"year":year,
			}
		}
	
	);
	
	})
	
	
				 					 					
});