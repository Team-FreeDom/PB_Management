 

var deptObj1;
var deptObj2;
var flag1=true;
var flag2=true;

$(document).ready(function() {

	$.ajax({
		type : 'POST',
		dataType : 'json',		
		url : '',  //��ȡ�������
		async : false,
		cache : false,
		error : function(request) {
			alert("error");
		},
		success : function(data) {
			
			for ( var i=0;i<data[1].length;i++) {
				$("#basetype").after(
						"<option value="+data[1][i].id+">"
								+ data[1][i].name + "</option>");				
				
			}
			for ( var i=0;i<data[2].length;i++) {
				$("#deptSelect").after(
						"<option value="+data[1][i].id+">"
								+ data[1][i].name + "</option>");				
				
			}
			
		}

	});
	
      });


$(document).on("change", "#deptRadio", function() {	
	var type=this.value;	
	$(".dee").remove();	
    if(type==1){
    	if(deptObj1!=null){
    		for ( var i=0;i<deptObj1[1].length;i++) {
				$("#applyDept").after(
						"<option value="+deptObj1[1][i].aid+" class='dee'>"
								+ deptObj1[1][i].dept + "</option>");
    		
    		return;
    	}
    }  
    }
    if(type==2){
    	if(deptObj2!=null){    		
        		for ( var i=0;i<deptObj2[1].length;i++) {
    				$("#applyDept").after(
    						"<option value="+deptObj2[1][i].aid+" class='dee'>"
    								+ deptObj2[1][i].dept + "</option>");
    		return;
    	}
    }  
    	}
    
    $.ajax({
		type : 'POST',
		dataType : 'json',
		data:{
			"typeid":type
		},
		url : '',  //��ȡ����
		async : false,
		cache : false,
		error : function(request) {
			alert("error");
		},
		success : function(data) {
			
			for ( var i=0;i<data[1].length;i++) {
				$("#applyDept").after(
						"<option value="+data[1][i].aid+" class='dee'>"
								+ data[1][i].dept + "</option>");
				
			}
			if(type==1){
				deptObj1=data;
			}else if(type==2){
				deptObj2=data;
			}			
		}

	});     
        });

$(document).on("change", "#deptSelectOne", function() {	
	var id= this.value;
	$.ajax({
		type : 'POST',
		dataType : 'json',
		data:{
			"aid":aid
		},
		url : '',  //��ȡרҵ
		async : false,
		cache : false,
		error : function(request) {
			alert("error");
		},
		success : function(data) {
			var tag=false;
			for ( var i=0;i<data[1].length;i++) {
				
				$("#majorSuo").each(function(index){
					var id=this.value;
					if(data[1].mid==id){
						tag=true;
						break;
					}					
				});
				if(!tag){
				$(".majorhide").append(
						"<span class='majorcheck'><input type='checkbox' value='"+data[1].mid+"' class='"+data[1].mname+"'/><label>"+data[1].mname+"</label></span>");				
				}
			}			
		}

	});
	
});

$(document).on("click", ".majorcheck", function() {
	var obj=$(this).children('input');	
	var str="<span class='majorchoose'><input name='majorid' type='checkbox' checked  value='"+obj.val()+"' class='"+obj.attr('class')+"'/><label>"+obj.attr('class')+"</label></span>";
	this.remove();
	$("#majorSuo").append(str);	
	var tag=$("#majormain").css("display");
	if(tag=="none"){
		$("#majormain").css("display","block");
	}	
});

$(document).on("click", ".majorchoose", function() {
	var obj=$(this).children('input');
	var str="<span class='majorcheck'><input type='checkbox' name='majorcheck' value='"+obj.val()+"' class='"+obj.attr('class')+"'/><label>"+obj.attr('class')+"</label></span>";
	this.remove();
	$(".majorhide").append(str);	
	if($(".majorchoose")[0]==null){			
		$("#majormain").css("display","none");
	}	
});

$(document).on("click", "#hit", function() {
	var content=$("#textContent").html();
	$("#majorSuo").html(content);	
	$("#majorSuo input").prop("hidden",false);
});

$(document).on("click", ".confirm", function() {
	var content=$("#majorSuo").html();	
	$("#textContent").html(content);
	$("#textContent input").prop("hidden",true);
	$(".majorhide").html("");
	if($(".majorchoose")[0]==null){	
		alert("ha");
		$("#majormain").css("display","none");
	}
});

$(document).on("click", ".closeit", function() {
	$(".majorhide").html("");
	if($(".majorchoose")[0]==null){		
		$("#majormain").css("display","none");
	}
});

$(document).on("click", "#submitForm", function() {
	var basename=$("basename").val();
	var deptty=$("deptty").val();
	var basetype=$("basetype0").val();
	var baseaddress=$("baseaddress").val();
	var personName=$("personName").val();
	var personTel=$("personTel").val();
	
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
	if(baseaddress==""){
		bootbox.alert({
			message : "请填写通信地址",
			size : 'small'
		});
	 return;		
	}
	if(personName==""){
		bootbox.alert({
			message : "请填写联系人姓名",
			size : 'small'
		});
	 return;	
	}
	if(personTel==""){
		bootbox.alert({
			message : "请填写联系人电话",
			size : 'small'
		});
	 return;	
	}
	if(!flag1){
		 bootbox.alert({
	            message: "上传资料仅限于rar压缩包格式ʽ",
	            size: 'small'
	        });
		 return;
	}
	if(!flag2){
		bootbox.alert({
            message: "上传资料大小不能大于10M",
            size: 'small'
        });
	}
	$("#myForm").submit();
});

$('#applyfile').change(function() {    
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

