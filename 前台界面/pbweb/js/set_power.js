// JavaScript Document
///权限设置方法：二进制授权法--------0011 = 0001 | 0010授权       回收code = 用户权限&(~权限值)   鉴权num = code & num; (“权限”=“授权码”&“权限”)
//权限字段为long，对应权限种类63种
//权限名对应值    
/*json 数据格式[sf:[{id:1,name:'系统管理员',upow:3},{id:2,name:'普通教师',pow:7}], id代表身份的唯一性，pow是身份的权限值
                qxm:[{qid:11,value:1,pname:'土地租赁审批'},{qid:12,value:2,pname:'实习审批'}],  qid是权限名的id 12值中的1代表它属于第一类（类别的意思）。value是权限授权码
			   ]*/
var data=[{
			sf:[{id:1,name:'系统管理员',upow:3},{id:2,name:'普通教师',upow:7},{id:3,name:'租赁业务专员',upow:31},{id:4,name:'实习业务专员',upow:63}],
		   qxm:[{qid:11,value:1,pname:'土地租赁审批'},{qid:12,value:2,pname:'实习审批'},{qid:13,value:4,pname:'实习基地审批'},{qid:21,value:8,pname:'基地维护审批'},{qid:22,value:16,pname:'土地租赁维护'},{qid:31,value:32,pname:'租赁分析'}]
	     }]

$(function () {

	
	var powman=new function () {
		this.sfData=data[0].sf
		this.powlist=data[0].qxm

		
		this.loaddata= function(){
			str='<a href="#" class="list-group-item active">管理身份列表</a>';
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
		
		$(document).on("click", "#sflist .people", function() {
			$('.people').removeClass('list-group-item-info');
			$(this).addClass('list-group-item-info');
			
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
					  message: "请选择左侧'身份列表'中的任一项",
					  size: 'small'
				  });
			}
			a=parseInt(a);
			var b=parseInt($(this).attr('pow'));
			if(is)
				a=a|b;
			else
				a=a&(~b);
			$('#sflist .list-group-item-info').attr('upow',a);
		});
		this.loaddata();
	};




});