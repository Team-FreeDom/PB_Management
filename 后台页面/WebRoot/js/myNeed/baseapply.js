var deptObj1;
var deptObj2;
var flag1=true;
var flag2=true;
var tag=true;

$(document).ready(function() {

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
			
			for ( var i=0;i<data[0].length;i++) {
				$("#deptSelect").after(
						"<option value="+data[0][i].aid+">"
								+ data[0][i].dept + "</option>");
				$("#applyDept").after(
						"<option value="+data[0][i].aid+" class='dee'>"
								+ data[0][i].dept+ "</option>");
				
			}
			
			for ( var i=0;i<data[1].length;i++) {				
				$("#basetype").after(
						"<option value="+data[1][i].id+">"
								+ data[1][i].name + "</option>");				
				
			}	
			
		}

	});
	
      });


$(document).on("change", "#deptRadio", function() {	
	var type=$(this).val();		
	$(".dee").remove();	   
    $.ajax({
		type : 'POST',
		dataType : 'json',
		data:{
			"typeid":type
		},
		url : 'getBaseSingleDept.do', 
		async : false,
		cache : false,
		error : function(request) {
			bootbox.alert({
				message : "请求异常",
				size : 'small'
			});
		},
		success : function(data) {			
			for ( var i=0;i<data.length;i++) {
				$("#applyDept").after(
						"<option value="+data[i].aid+" class='dee'>"
								+ data[i].dept + "</option>");
				
			}
		}

	});     
        });

$(document).on("blur", "#basename", function() {
	var value=$(this).val();
	
	if(value!=""){
		
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
						$("#display").html("");
						tag=true;
					}else{						
						$("#display").html("该基地名称已存在，请重新输入");
						$("#basename")[0].focus();
						tag=false;
					}
				}

			}); 
	}
	
});

$(document).on("change", "#deptSelectOne", function() {	
	var id= this.value;	
	$(".majorhide").html("");
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
				$("#majorSuo input").each(function(index){					
					var id=$(this).val();				
					if(data[i].mid==id){
						tag=true;
						return;
					}					
				});
				if(!tag){
				$(".majorhide").append(
						"<span class='majorcheck'><input type='checkbox' placeholder='"+id+"' value='"+data[i].mid+"' class='"+data[i].mname+"'/><label>"+data[i].mname+"</label></span>");				
				}
			}
		}

	});
	
});


$(document).on("click", ".majorcheck", function() {
	var obj=$(this).children('input');		
	var str="<span class='majorchoose'><input name='majorid' type='checkbox' checked  value='"+obj.val()+"' class='"+obj.attr('class')+"' placeholder='"+obj.prop("placeholder")+"'/><label>"+obj.attr('class')+"</label></span>";
	this.remove();
	$("#majorSuo").append(str);	
	var tag=$("#majormain").css("display");
	if(tag=="none"){
		$("#majormain").css("display","block");
	}	
});

$(document).on("click", ".majorchoose", function() {	
	var obj=$(this).children('input');
	var id=$("#deptSelectOne").val();	
	if(id!=obj.prop("placeholder")){
		bootbox.alert({
			message : "请选择相应的学院，再进行更改",
			size : 'small'
		});
		obj.prop("checked",true);
	 return;

	}
	var str="<span class='majorcheck'><input type='checkbox' name='majorcheck' value='"+obj.val()+"' class='"+obj.attr('class')+"' placeholder='"+obj.prop("placeholder")+"'/><label>"+obj.attr('class')+"</label></span>";
	this.remove();
	$(".majorhide").append(str);	
	if($("#majorSuo .majorchoose")[0]==null){			
		$("#majormain").css("display","none");
	}	
});

$(document).on("click", "#hit", function() {
	var content=$("#textContent").html();
	if(content==null||content==""){	
	  $("#majormain").css("display","none");
	  $("#majorSuo").html(content);
	  return;
	}else{
	$("#majormain").css("display","block");
	$("#majorSuo").html(content);	
	$("#majorSuo input").prop("hidden",false);
	}
});

$(document).on("click", ".confirm", function() {
	var content=$("#majorSuo").html();	
	$("#textContent").html(content);
	$("#textContent input").prop("hidden",true);
	$(".majorhide").html("");
	if($("#majorSuo .majorchoose")[0]==null){			
		$("#majormain").css("display","none");
	}
	$("#deptSelectOne").val("");
});

$(document).on("click", ".closeit", function() {
	$(".majorhide").html("");
	if($("#majorSuo .majorchoose")[0]==null){		
		$("#majormain").css("display","none");
	}
	$("#deptSelectOne").val("");
});

$(document).on("click", "#submitForm", function() {
	var basename=$("#basename").val();
	var applyName = $("#deptty").find("option:selected").text();//获取下拉列表的文本-- by jimao
	$("#applyNameId").val(applyName);							//获取隐藏input的文本值
	var deptty=$("#deptty").val();
	var basetype=$("#basetype0").val();
	var baseaddress=$("#baseaddress").val();
	var personName=$("#personName").val();
	var personTel=$("#personTel").val();
	var lawperson=$("#lawPerson").val();
	var limit_population=$("#limit-population").val().trim();
	var base_area=$("#base-area").val().trim();
	var filed_area=$("#filed-area").val().trim();
	var collegeNameIt=$("#collegeNameIt").val().trim();
	var collegeTelIt=$("#collegeTelIt").val().trim();
	var unitIt=$("#unitIt").val().trim();
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
		 $("#applyfile").val('');
		 return;
	}
	if(!flag2){
		bootbox.alert({
            message: "上传资料大小不能大于10M",
            size: 'small'
        });
		 $("#applyfile").val('');
		return;
	}
	if($("#basetype0").val()==1){
		if($("#applyfile").val()==""){
			bootbox.alert({
	            message: "请上传校外教学实习基地相关申报材料！",
	            size: 'small'
	        });
			return false;
		}		
	}
	if($("#basetype0").val()==2){
		if($("#applyfile").val()==""){
			bootbox.alert({
	            message: "请上传新农院社会服务基地相关申报材料！",
	            size: 'small'
	        });
			return false;
		}
		
	}
	$("#myForm").submit();
});

$('#applyfile').change(function() {    
    var filepath = $(this).val();
    var file_size = this.files[0].size;
    var size = file_size / 1024;
    var extStart = filepath.lastIndexOf(".");
    var ext = filepath.substring(extStart, filepath.length).toUpperCase();
    if (ext != ".RAR" && ext != ".ZIP") {
        bootbox.alert({
            message: "上传资料仅限于rar,zip压缩包格式",
            size: 'small'
        });
        $("#applyfile").val('');
        flag1=false;
        return false;
    }
    if (size > 1024 * 10) {
        bootbox.alert({
            message: "上传资料大小不能大于10M",
            size: 'small'
        });
        $("#applyfile").val('');
        flag2=false;
        return false;
    }   
    flag1=true;
    flag2=true;
    return false;
});

var formerValue="";
$(document).on("change", "#basetype0", function() {	
	$("#addspan span").remove();
	$("#teachingBaseShow").hide();
	$("#societyBaseShow").hide();
	$("#unitShow").hide();
	$("#unitIt").val('');
	var basetype0=$("#basetype0").val();	
	if(basetype0==1){
		$("#addspan").append("<span class='setTag'>*</span>");
		$("#teachingBaseShow").show();
	}else if(basetype0==2){
		$("#addspan").append("<span class='setTag'>*</span>");
		$("#societyBaseShow").show();
		$(".collegeNameIt").html("服务团队负责人"+'<span class="setTag">*</span>');
		$(".collegeTelIt").html("服务团队负责人电话"+'<span class="setTag">*</span>');
		$("#collegeNameIt").val('');
		$("#collegeTelIt").val('');
		$("#unitShow").show();			
	}
	if(basetype0!=2&&formerValue==2){
		$(".collegeNameIt").html("学院联系人"+'<span class="setTag">*</span>');
		$(".collegeTelIt").html("学院联系人电话"+'<span class="setTag">*</span>');	
		$("#collegeNameIt").val('');
		$("#collegeTelIt").val('');
	}	
	formerValue=basetype0;	
});

