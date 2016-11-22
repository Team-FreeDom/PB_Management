// JavaScript Document
$(function () {

	
	var powman=new function () {
		this.sfData=[{id:1,name:'系统管理员'},{id:2,name:'普通教师'},{id:3,name:'租赁业务专员'},{id:4,name:'实习业务专员'}
		];
		this.powlist=[{id:11,pname:'土地租赁审批'},{id:12,pname:'实习审批'},{id:13,pname:'实习基地审批'},{id:4,pname:'基地维护审批'},{id:21,pname:'土地租赁维护'},{id:31,pname:'租赁分析'}];
		this.powData=[{id:11,v:true},{id:12,v:true},{id:13,pname:'实习基地审批',v:true},{id:4,pname:'基地维护审批',v:true}];
		
		this.loaddata= function(){
			str='<a href="#" class="list-group-item active">管理身份列表</a>';
			 _.map(this.sfData,function(em){			
				 str=str+'<a href="#" class="list-group-item people" id='+em.id+'>'+em.name+'</a>';
			});
			$('#sflist').html(str);
			str='';
			$('#powlist1').html('');
			$('#powlist2').html('');
			$('#powlist3').html('');
			_.map(this.powlist,function(em){
				str=' <li><label><input type="checkbox" id='+em.id+'> '+em.pname+'</label></li>'		
				$('#powlist'+parseInt(em.id/10)).append(str);				
			});
		}.bind(this);
		$(document).on("click", "#sflist .people", function() {
			$('.people').removeClass('list-group-item-info');
			$(this).addClass('list-group-item-info');
			
			var n=_.findIndex(powman.powData, 'id', parseInt($(this).attr('id')));
			
			
		});
		this.loaddata();
	};




});