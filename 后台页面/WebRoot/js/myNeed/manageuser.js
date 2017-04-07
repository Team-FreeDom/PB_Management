 //结束导出任务时隐藏模态框
$("#certainExport").click(function(){
        	 $("#lead").modal("hide");

         })

        //导出功能刷选部门
        	 $.ajax({
        	type : 'POST',
 			dataType : 'json',
 			url : 'Checkdept.do',
 			async : false,
 			cache : false,
 			error : function(request) {
 				bootbox.alert({
         			  message: "请求异常",
         			  size: 'small'
         		  });
 			},
 			success : function(data) {
 				for (var i=0;i<data.length;i++){
 					$("#EdeptID").after(
 							"<option value="+data[i].college+">"
 									+data[i].college+"</option>");
 				}
 			}
        });
//每次点击导出清空select
$("#exportid").click(function(){
	$("#dept").val("1");

});
//增加模态框每次点击赋空值
$("#addOne").click(function(){
			$("#workerId").val("");
			$("#name").val("");
			$("#demo2").val("");
			$("#sex2").prop("checked",false);
			$("#sex1").prop("checked",false);
			$("#IDnumber").val("");
			$("#password").val("");
			$("#Adivision").val("1");
			$("#Awkclass").val("1");
			$("#Astatus").val("1");
		    $("#phone").val("");
	        $("#IDnumber1").val("");
	        $("#password").val("");
			 });
   //限制修改用户id
    function stop(){
    	 bootbox.alert({
			  message: "对不起,此id不可修改",
			  size: 'small'
		  });
    }
    $(document).ready(function() {

        var userInfo=$('#manageusertable').DataTable(
		  {
			  "aLengthMenu" : [5,10,20,30], //动态指定分页后每页显示的记录数。
				"lengthChange" : true, //是否启用改变每页显示多少条数据的控件
				"bSort" : false,
				"serverSide": true,
				"iDisplayLength" : 10,
				"bFilter": true,
				"dom": 'frtip<"bottom"l>',
	             "bDestroy":true,
					"ajax" : {
						"url" : "manger.do",
						"type" : "POST"
					},
					"aoColumns" : [                                        /* [{la_id:1},{startime:"2016-11-15"},{endtime:"2017-11-15"},{basename:"长安基地1号"},{li:"土地编号"},{username:"申请人"},{usercollage:"信息科学技术学院"},{time:"租用次数"},{plant:"种植内容"}] */
									   {
										"mData" : "number",
										"orderable" : true, // 禁用排序
										"sDefaultContent" : "",
										"sWidth" : "4%",
									},
									{ //aoColumns设置列时，不可以任意指定列，必须列出所有列。
										"mData" : "id",
										"orderable" : true, // 禁用排序
										"sDefaultContent" : "",
										"sWidth" : "4%"
									},
									{
										"mData" : "attritube",
										"orderable" : true, // 禁用排序
										"sDefaultContent" : "",
										"sWidth" : "6%",

									},
									{
										"mData" : "username",
										"orderable" : true, // 禁用排序
										"sDefaultContent" : "",
										"sWidth" : "6%"
									},
									{
										"mData" : "sex",
										"orderable" : false, // 禁用排序
										"sDefaultContent" : "",
										"sWidth" : "4%"
									},
									{
										"mData" : "category",
										"orderable" : true, // 禁用排序
										"sDefaultContent" : "",
										"sWidth" : "8%"
									},
									{
										"mData" : "birth",
										"orderable" : false, // 禁用排序
										"sDefaultContent" : "",
										"sWidth" : "8%",


									},

									{
										"mData" : "idcard",
										"orderable" : false, // 禁用排序
										"sDefaultContent" : "",
										"sWidth" : "10%",


									},
									{
										"mData" : "telephone",
										"orderable" : false, // 禁用排序
										"sDefaultContent" : "",
										"sWidth" : "8%",


									},
									{
										"mData" : "dept",
										"orderable" : false, // 禁用排序
										"sDefaultContent" : "",
										"sWidth" : "10%",


									},
									{
										"mData" : "id",
										"orderable" : false, // 禁用排序
										"sDefaultContent" : '',
										"sWidth" : "5%",
										"render" : function(
												data, type,
												row) { //render改变该列样式,4个参数，其中参数数量是可变的。
											var data=row.id;
											return data = '<button type="button"  id='
												+ row.id
												+ ' onclick="editOne(this)" class="btn btn-warning btn-xs" data-id='+data+' id="frame1_edit" data-target="#edit" data-toggle="modal">修改</button>';
										}
									}
							//data指该行获取到的该列数据
							//row指该行，可用row.name或row[2]获取第3列字段名为name的值
							//type调用数据类型，可用类型“filter”,"display","type","sort",具体用法还未研究
							//meta包含请求行索引，列索引，tables各参数等信息

							],

				columnDefs :
				[{
					"orderable" : false, // 禁用排序
					"targets" : [0], // 指定的列
					"data" : "number",
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
        //获取部门和人员属性
        $.ajax({
 			type : 'POST',
 			dataType : 'json',
 			url : 'getAllInfo.do',
 			async : false,
 			cache : false,
 			error : function(request) {
 				bootbox.alert({
         			  message: "请求异常",
         			  size: 'small'
         		  });
 			},
 			success : function(data) {
 				
 				 for (var i=0;i<data[0].length;i++) {
 					$("#EstatusID").after(
 							"<option value="+data[0][i].name+">"
 									+data[0][i].name+"</option>");
 									$("#AstatusID").after(
 				 							"<option value="+data[0][i].name+">"
 				 									+data[0][i].name+"</option>");

 				 }
 				 for ( var i=0;i<data[1].length;i++) {
 					$("#EdivisionID").after(
 							"<option value="+data[1][i].dept+">"
 									+data[1][i].dept+"</option>");
 					$("#AdivisionID").after(
 							"<option value="+data[1][i].dept+">"
 									+data[1][i].dept+"</option>");
 				}
 				for ( var i=0;i<data[2].length;i++) {
 					$("#EworkerclassId").after(
 							"<option value="+data[2][i].titles+">"
 									+data[2][i].titles+"</option>");
 					$("#AworkerclassId").after(
 							"<option value="+data[2][i].titles+">"
 									+data[2][i].titles+"</option>");
 				}
 			}
 		});

      //删除人员基本信息
      var flag=0;
        $('#deleteOne').click(function() {
          flag=0;
        	$("#manageusertable input[name='idname']").each(function () {
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
        	    		$.ajax({
                            url: 'deInfo.do',
                            type: 'post',
                            data: $("#formUserInfo").serializeArray(),
                            success: function(msg) {
                            	 bootbox.alert({
                       			  message: msg,
                       			  size: 'small'
                       		  });
                            	userInfo.draw(false);
                              $("#ck2").prop("checked", false);
                              $("#ck1").prop("checked", false);
                            }
                        });
        	    	}
        	    }
        	});

		}
        });
      //增加人员基本信息
        $('#save').click(function() {
				if($("#workerId").val()==""){
					 bootbox.alert({
						  message: "请填写员工编号",
						  size: 'small'
					  });
					}
				else if($("#name").val()==""){
					bootbox.alert({
						  message: "请填写姓名",
						  size: 'small'
					  });
					}
				else if($("#sex1").prop("checked") == false&&$("#sex2").prop("checked") == false){
					bootbox.alert({
						  message: "请填写性别",
						  size: 'small'
					  });
					}
				else if($("#Awkclass").val()=="1"){
					bootbox.alert({
						  message: "请选择员工类别",
						  size: 'small'
					  });
					}
				else if($("#Astatus").val()=="1"){
					bootbox.alert({
						  message: "请选择身份属性",
						  size: 'small'
					  });
					}
				else if($("#Adivision").val()=="1"){
					bootbox.alert({
						  message: "请选择部门",
						  size: 'small'
					  });
					}
				else if($("#phone").val()==""){
					bootbox.alert({
						  message: "请填写电话号码",
						  size: 'small'
					  });
					}
				else if($("#IDnumber1").val()==""){
					bootbox.alert({
						  message: "请填写身份证号码",
						  size: 'small'
					  });
					}
				else if($("#password").val()==""){
					bootbox.alert({
						  message: "请填写密码",
						  size: 'small'
					  });
				}
				else{ $.ajax({
                url: 'addInfo.do',
                type: 'post',
                data: $("#adduserform").serializeArray(),
                error : function(request) {
                	bootbox.alert({
             			  message: "增加失败",
             			  size: 'small'
             		  });
     			},
                success: function(msg) {
                	if(msg==0){
                		msg="增加成功";
                	}else if(msg==1){
                		msg="增加失败，员工编号已存在";
                	}
                	bootbox.alert({
             			  message: msg,
             			  size: 'small'
             		  });
                	userInfo.draw(false);
                  $("#ck2").prop("checked", false);
                  $("#ck1").prop("checked", false);
                }
            });
            $("#add").modal("hide");
				}
        });
});
    //查看详情及修改
    function editOne(obj) {

		var id = obj.id;
				$.ajax({
					type : 'POST',
					data : {
						"iddetail" : id
					},
					dataType : 'json',
					url : 'Mangerdetail.do',
					async : false,
					cache : false,
					error : function(request) {
						bootbox.alert({
		           			  message: "加载失败",
		           			  size: 'small'
		           		  });
					},
					success : function(data) {
							$("#EworkerId").val(data[0].id);
							$("#Ename").val(data[0].username);
							if(data[0].sex != ''){
							$("#sex[value=" + data[0].sex + "]").prop("checked",true);}
              $("#Edivision").val(data[0].dept);//部门
							$("#demo").val(data[0].birth);//出生日期
              $("#Estatus").val(data[0].attritube);
              $("#Eworkerclass").val(data[0].category);
							$("#Ephone").val(data[0].telephone);
							$("#IDnumber").val(data[0].idcard);
							$("#Epassword").val(data[0].password);
						}
				});

	}
	
    $("#ck1").on("click", function () {
		if ($(this).prop("checked") === true) {
			$("#manageusertable input[name='idname']").prop("checked", true);
		} else {
			$("#manageusertable input[name='idname']").prop("checked", false);
		}
		$("#ck2").prop("checked", false);

	 });

	  $("#ck2").click(function () {//反选
    		$("#manageusertable input[name='idname']").each(function () {
        	$(this).prop("checked", !$(this).prop("checked"));
    	});
	  $("#ck1").prop("checked", false);
		 });
