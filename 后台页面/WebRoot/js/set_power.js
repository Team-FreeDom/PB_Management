// JavaScript Document
///权限设置方法：二进制授权法--------0011 = 0001 | 0010授权       回收code = 用户权限&(~权限值)   鉴权num = code & num; (“权限”=“授权码”&“权限”)
//权限字段为long，对应权限种类63种
//权限名对应值    
/*json 客户端收数据格式
			   [sf:[{id:1,name:'系统管理员',upow:3},{id:2,name:'普通教师',upow:7}], id代表角色的唯一性，pow是角色的权限值
                qxm:[{qid:11,value:1,pname:'土地租赁审批'},{qid:12,value:2,pname:'实习审批'}],  qid是权限名的id 12值中的1代表它属于第一类（类别的意思）。value是权限授权码
			   ]
			   
text 发服务器数据格式
				(1,3,"系统管理员"),(2,7,"普通教师"),(3,31,"租赁业务专员"),(4,63,"实习业务专员")
			   
************************************************************后台开发人员请修改两处ajax的服务包名称************************************************			   

var data=[{
			sf:[{id:1,name:'系统管理员',upow:3},{id:2,name:'普通教师',upow:7},{id:3,name:'租赁业务专员',upow:31},{id:4,name:'实习业务专员',upow:63}],
		   qxm:[{qid:11,value:1,pname:'土地租赁审批'},{qid:12,value:2,pname:'实习审批'},{qid:13,value:4,pname:'实习基地审批'},{qid:21,value:8,pname:'基地维护审批'},{qid:22,value:16,pname:'土地租赁维护'},{qid:31,value:32,pname:'租赁分析'}]
	     }]*/

$(function () {

	function closeboxEX() {
			powman.loaddata();	
			powman.dialog.modal('hide');		  
	}
	var powman=new function () {
		this.sfData;
		this.powlist;
		this.dialog;
		
		this.loaddata= function(){
			var obj=this;
///////////////////////////////////////////////////////此处修改///////////////////////////////////
			$.ajax({                
				type : 'POST',
				dataType : 'json',
				url : 'getAdminFunction.do',//后台修改
				async : false,
				cache : false,
				error : function(request) {
				  bootbox.alert({
					  message: "加载失败",
					  size: 'small'
				  });
				},
				success : function(em) {
					obj.sfData=em.sf;
					obj.powlist=em.qxm;
				}
			});
			
			str='<a href="#" class="list-group-item active">管理角色列表</a>';
			 _.map(this.sfData,function(em){			
				 str=str+'<a href="#" class="list-group-item people" upow='+em.upow+' id='+em.id+'>'+em.name+'</a>';
			});
			$('#sflist').html(str);
			$('#powlist1').html('');
			$('#powlist2').html('');
			$('#powlist3').html('');
			_.map(this.powlist,function(em){
				str=' <li><label><input type="checkbox" pow='+em.value+'> '+em.pname+'</label></li>'		
				$('#powlist'+parseInt(em.qid/10)).append(str);				
			});
		}.bind(this);
		
		this.addrole= function(){
			var uname= $('#role_text').val();
			if(uname=='')return false;
			var uid=new Date().getTime();
			$('#sflist .list-group-item-info').removeClass('list-group-item-info');
			$('#sflist').append('<a href="#" class="list-group-item list-group-item-info people" upow=0 id='+uid+'>'+uname+'</a>');
			$('#role_text').val('');
			$('#pow_list input[type="checkbox"]').prop('checked',false);
			
			return false;
		}
		this.delrole= function(){
			var a=$('#sflist .list-group-item-info').attr('upow')
			if(typeof(a) == "undefined")
			{
				 bootbox.alert({
					  message: "请选择下方'角色列表'中的任一项",
					  size: 'small'
				  });
				  return false;
			}
			 bootbox.confirm({ 
					size: "small",
					message: "确定删除该角色吗?", 
					callback: function(result){
						if(result==true)
						{
							$('#sflist .list-group-item-info').remove();
							$('#pow_list input[type="checkbox"]').prop('checked',false);
							$('#role_text').val('');
							
						}
				  }
                });	
			
			return false;
		};
		
		this.modify = function(){
			var uname= $('#role_text').val();
			if(uname=='')return false;
			var a=$('#sflist .list-group-item-info').attr('upow')
			if(typeof(a) == "undefined")
			{
				 bootbox.alert({
					  message: "请选择下方'角色列表'中的任一项",
					  size: 'small'
				  });
				  return false;
			}
			$('#sflist .list-group-item-info').html(uname);
			return false;
		}
		
		this.powsave = function(){
			var str='';
			var i=0;
			$('#sflist .people').each(function() {
				if(i==0)
					str=str+' ('+$(this).attr('id') +','+  $(this).attr('upow')+',"'  + $(this).html()+'")';
				else
					str=str+',('+$(this).attr('id') +','+  $(this).attr('upow')+',"'  + $(this).html()+'")';
				i++;
			});
			var obj=powman;
			///////////////////////////////////////////////////////此处修改///////////////////////////////////
			$.ajax({                
				type : 'POST',
				dataType : 'text',
				data:{
					data:str
				},
				url : 'setAdminFunction.do',//后台修改
				async : false,
				cache : false,
				error : function(request) {
				  bootbox.alert({
					  message: "保存失败",
					  size: 'small'
				  });	
				},
				success : function() {
					  obj.dialog = bootbox.dialog({
						  message: '<p class="text-center">数据提交成功，正返回中......</p>',
						  closeButton: false
					  });
					  window.setTimeout(closeboxEX, 1000);
					  window.setTimeout(function(){
						  location.reload(true);
					  }, 1000);
					  
				}
			});
			return false;	
		}
		
		$(document).on("click", "#sflist .people", function() {
			$('.people').removeClass('list-group-item-info');
			$(this).addClass('list-group-item-info');
			$('#role_text').val($(this).html());
			var upow=parseInt($(this).attr('upow'));
			var checklist=$('#pow_list input[type="checkbox"]');
			$(checklist).prop('checked',false);
			var k;
			$(checklist).each(function() {
                var temp=parseInt($(this).attr('pow'));
				k=upow & temp
				if(k == temp)
					$(this).prop("checked",true);
            });	
		});
		$(document).on("click", "#pow_list input[type='checkbox']", function() {
			var a=$('#sflist .list-group-item-info').attr('upow')
			var is=$(this).prop('checked');
			if(typeof(a) == "undefined")
			{
				$(this).prop('checked',!is);
				 bootbox.alert({
					  message: "请选择左侧'角色列表'中的任一项",
					  size: 'small'
				  });
				  return false;
			}
			a=parseInt(a);
			var b=parseInt($(this).attr('pow'));
			if(is)
				a=a|b;
			else
				a=a&(~b);
			$('#sflist .list-group-item-info').attr('upow',a);
		});
		$('#role_name').click(this.modify);
		//$('#role_add').click(this.addrole);
		//$('#role_del').click(this.delrole);
		$('#pow_update').click(this.powsave);
		this.loaddata();
	};

});