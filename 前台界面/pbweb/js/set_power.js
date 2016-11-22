// JavaScript Document
$(function () {

	
	var tudi=new function () {
		this.sfData=[{id:1,name:'系统管理员'},{id:2,name:'普通教师'},{id:3,name:'租赁业务专员'},{id:4,name:'实习业务专员'}
		];
		this.powData=[{id:11,pname:'土地租赁审批',v:true},{id:12,pname:'实习审批',v:true},{id:13,pname:'实习基地审批',v:true},{id:4,pname:'基地维护审批',v:true},{id:21,pname:'土地租赁维护',v:true}
		];
		
		this.loaddata= function(){
			str='<a href="#" class="list-group-item active">管理身份列表</a>';
			 _.map(this.sfData,function(em){
				 str=str+'<a href="#" class="list-group-item list-group-item-info" id='+em.id+'>'+em.name+'</a>'
			});
			$('#sflist').html(str);
			str='';
			$('#powlist1').html('');
			$('#powlist2').html('');
			$('#powlist3').html('');
			_.map(this.powData,function(em){
				str=' <li><label><input type="checkbox" id='+em.id+' checked='+em.v+'> '+em.pname+'</label></li>'
				
				$('#powlist'+parseInt(em.id/10)).append(str);				
			});
		}.bind(this);
		this.loaddata();
	};




});