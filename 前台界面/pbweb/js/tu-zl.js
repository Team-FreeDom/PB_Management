// JavaScript Document
        $(function () {
            var options = {
				disableDrag:true,
				disableResize:true
            };
			$('.grid-stack').gridstack(options);
            /*获得基地------start*/
            $.ajax({                //页面加载时自动执行
				type : 'POST',
				dataType : 'json',
				url : 'baseInfo.do',
				async : false,
				cache : false,
				error : function(request) {
				  bootbox.alert({
					  message: "加载区域失败",
					  size: 'small'
				  });	
				},
				success : function(data) {
					var i = 0;
					for ( var item in data) {
						$("#choose-grid").after(
								"<option value="+data[i].bid+">"
										+ data[i].bname + "</option>");
						i++;
					}
				}
			});			

		var tu_zl=new function () {
		  this.choose_count=0;
		  this.serializedData = [];//在loadGrid中构成布局对象，关联土地编号id
		  this.dialog;
		  this.grid = $('.grid-stack').data('gridstack');	
		  function fill(id,name,plantingContent,landArea,buildingArea,tudi_Afford){
			$('#tudi_id').val(id);
			$('#tudi_name').val(name);
			$('#tudi_plantingContent').val(plantingContent);
			$('#tudi_landArea').val(landArea);
			$('#tudi_buildingArea').val(buildingArea);
			$('#tudi_Afford').val(tudi_Afford);					
		  };		  
		  function closeboxEX() {
			$('#land_lease_table').html('');
			tu_zl.loadGrid();	
			tu_zl.dialog.modal('hide');		  
			$('#landModal').modal('hide');
		  }
		  function closebox() {	
			tu_zl.dialog.modal('hide');		  
		  }			  
		  this.loadGrid = function () {
			  var bid=$('#choose-grid').children('option:selected').val();
			  var obj=this;
			  fill('','','','','','');
			  this.grid.removeAll();
			  var obj=this;
			  $.ajax({  type : 'POST',
						  dataType : 'json',
						  data:{"bid":bid},
						  url : 'getDifferLayout.do',////修改此处服务器文件即可
						  async : false,
						  cache : false,
						  error : function(request) {
							   bootbox.alert({
									message: "数据加载失败！",
									size: 'small'
								});
						  },
						  success : function(data) {
						  obj.serializedData=data
						  var items = GridStackUI.Utils.sort(obj.serializedData);
						  _.each(items, function (node) {
							  if(node.name!='')
							  obj.grid.addWidget($('<div><div class="grid-stack-item-content Havetorent"><span class="lname">从事：'+node.planting+'</span><span class="label label-warning  Lineup">'+node.name+'</span></div><div></div></div>'),node.x, node.y, node.width, node.height,false,1,4,1,4,node.id);
							  else
							  obj.grid.addWidget($('<div><div class="grid-stack-item-content normal"><label class="checkbox-inline lname"><input type="checkbox" class="ck" id='+node.id+' value='+node.id+'>'+node.lname+'</label><span class="label label-primary Lineup "><span class="glyphicon glyphicon-user pull-right"></span>'+node.Lineup+'</span></div><div></div></div>'),node.x, node.y, node.width, node.height,false,1,4,1,4,node.id);													
							}, obj);//end each
								  
						 }//end success
			  });	//end ajax	

/////////////////////转换服务器代码时删除////////////-----------------------------////////////////////////////		
				/*this.serializedData = [//测试数据，ajax数据格式
									{x: 0, y: 0, width: 2, height: 1,id:'1',lname:'长安气象站',plantingContent:'气候观测',landArea:86,buildingArea:100,Afford:123,bid:1,collage:'农学院',name:'王献之',planting:'气候观测',Lineup:0,data:[{name:'王1',planting:'西瓜',ptime:'2014'},{name:'王双',planting:'西瓜',ptime:'2013'},{name:'王双',planting:'西瓜',ptime:'2015'}]
								    },
									
									{x: 0, y: 1, width: 2, height: 1,id:'2',lname:'长安水稻实验田',plantingContent:'玉米 水稻',landArea:86,buildingArea:100,Afford:123,bid:1,collage:'农学院',name:'',planting:'玉米',Lineup:0,data:[{name:'王2',planting:'西瓜',ptime:'2013'},{name:'王双',planting:'西瓜',ptime:'2014'},{name:'王双',planting:'西瓜',ptime:'2015'}]   
									},
									
									{x: 0, y: 2, width: 2, height: 1,id:'3',lname:'长安玉米试验田',plantingContent:'玉米 水稻',landArea:86,buildingArea:100,Afford:123,bid:1,collage:'农学院',name:'汪仲贤',planting:'油菜',Lineup:0,data:[{name:'王3',planting:'西瓜',ptime:'2013'},{name:'王双',planting:'西瓜',ptime:'2014'},{name:'王双',planting:'西瓜',ptime:'2014'}]   
									},
									
									{x: 2, y: 3, width: 2, height: 1,id:'4',lname:'长安油菜实验田',plantingContent:'油菜 水稻',landArea:86,buildingArea:100,Afford:123,bid:1,collage:'农学院',name:'',planting:'水稻',Lineup:3,data:[{name:'王4',planting:'西瓜',ptime:'2013'},{name:'王双',planting:'西瓜',ptime:'2014'},{name:'王双',planting:'西瓜',ptime:'2014'}]   
									},
									
									{x: 1, y: 4, width: 2, height: 1,id:'5',lname:'长安水生态区域',plantingContent:'农田鱼 水稻',landArea:86,buildingArea:100,Afford:123,bid:1,collage:'农学院',name:'',planting:'农田鱼',Lineup:2,data:[{name:'王5',planting:'西瓜',ptime:'2013'},{name:'王双',planting:'西瓜',ptime:'2014'},{name:'王双',planting:'西瓜',ptime:'2014'}]   
									},
									
									{x: 1, y: 3, width: 2, height: 1,id:'6',lname:'长安林业观察站',plantingContent:'柏林 水稻',landArea:86,buildingArea:100,Afford:123,bid:1,collage:'农学院',name:'',planting:'柏林',Lineup:1,data:[{name:'王6',planting:'西瓜',ptime:'2013'},{name:'王双',planting:'西瓜',ptime:'2014'},{name:'王双',planting:'西瓜',ptime:'2014'}]   
									},
									
									{x: 2, y: 4, width: 2, height: 1,id:'7',lname:'长安生物实验田',plantingContent:'微生物 水稻',landArea:86,buildingArea:100,Afford:123,bid:1,collage:'农学院',name:'',planting:'微生物',Lineup:5,data:[{name:'王7',planting:'西瓜',ptime:'2013'},{name:'王双',planting:'西瓜',ptime:'2014'},{name:'王双',planting:'西瓜',ptime:'2014'}]   
									},
									
									{x: 2, y: 5, width: 2, height: 1,id:'8',lname:'长安动科试验田',plantingContent:'龙虾 水产',landArea:86,buildingArea:100,Afford:123,bid:1,collage:'农学院',name:'吴雪松',planting:'龙虾',Lineup:0,data:[{name:'王8',planting:'西瓜',ptime:'2016'},{name:'王双',planting:'西瓜',ptime:'2014'},{name:'王双',planting:'西瓜',ptime:'2014'}]   
									}
								];
							
			   var items = GridStackUI.Utils.sort(this.serializedData);
				_.each(items, function (node) {
					if(node.name!='')
					obj.grid.addWidget($('<div><div class="grid-stack-item-content Havetorent"><span class="lname">从事：'+node.planting+'</span><span class="label label-warning  Lineup">'+node.name+'</span></div><div></div></div>'),node.x, node.y, node.width, node.height,false,1,4,1,4,node.id);
					else
					obj.grid.addWidget($('<div><div class="grid-stack-item-content normal"><label class="checkbox-inline lname"><input type="checkbox" class="ck" id='+node.id+' value='+node.id+'>'+node.lname+'</label><span class="label label-primary Lineup "><span class="glyphicon glyphicon-user pull-right"></span>'+node.Lineup+'</span></div><div></div></div>'),node.x, node.y, node.width, node.height,false,1,4,1,4,node.id);													
				  }, obj);//end each////////////////////////----------------------------------/////////////////////////////////////////*/
		  }.bind(this);


		  this.savedate = function () {
			  if(this.choose_count<=0){
				 bootbox.alert({
					  message: "您没有选择土地",
					  size: 'small'
				  }); 
				  return false;
			  }
			  var str=' ';
			  var plan;
			  var landid;
			  var userid='180042';////////////根据登陆后获得userid,userid类型应该为varchar型
			  var stime = $('#stime').val();
			  var etime = $('#etime').val();
			  for (var i=1;i<=this.choose_count;i++)
			  {
				 plan = $('#plan'+i).val();
			  	if(plan=='')
				{
				 bootbox.alert({
					  message: "从事工作为必填项目",
					  size: 'small'
				  });
				  $('#plan'+i)[0].focus();
				  return false;					
				}
				landid = $('#tuname'+i).attr('tid');
				str=str+"'"+userid+"'('"+landid+"','"+stime+"','"+etime+"','"+plan+"','"+userid+"'";
				if(i==this.choose_count)
				str=str+",2)";
				else
				str=str+",2),";
			  }//end for
			 // alert(str);////INSERT INTO tab_comp VALUES(item1, price1, qty1),(item2, price2, qty2),(item3, price3, qty3);批量插入语句
			  var obj=this;
			  $.ajax({                //以文本方式提交申请
				  type : 'POST',
				  dataType : 'text',
				  data: str,
				  url : 'baseInfo.do',//修改
				  async : false,
				  cache : false,
				  error : function(request) {
					obj.dialog=bootbox.alert({
						message: "网络故障，请稍后重试",
						size: 'small'
					});
				  },///error
				  success : function(data) {
					  fill('','','','','','');
					  $('#field_rent').empty();
					  obj.dialog = bootbox.dialog({
						  message: '<p class="text-center">数据提交成功，正返回中......</p>',
						  closeButton: false
					  });
					  window.setTimeout(closeboxEX, 1000);					  
				  }//success
			  });//ajax			  	  
			  
		  }.bind(this);

		  $(document).on("click", ".grid-stack-item", function() {
			  var id=$(this).attr('data-gs-id');
			  var oBox= document.getElementById(id);
			  oBox.click();
		  }).bind(this);;

		  $(document).on("click", "#sum_app", function() {
			  var i=1;
			  var str='';
			  var id=-1;
			  var n=-1;
			  $.each($('.ck'), function(){
				  if($(this).is(':checked'))
				  {
				  	id = $(this).attr('id');
					n=_.findIndex(tu_zl.serializedData, 'id', id);
					str=str+'<div class="form-group ">\
					<div class="input-group">\
					  <label>土地租赁'+i+'：</label>\
					  <input class="form-control" type="text" value='+tu_zl.serializedData[n].lname+' id="tuname'+i+'" tid='+id+' disabled>\
					  </div>\
					</div>\
					<div class="form-group">\
					  <div class="input-group">\
						<label>推荐从事：</label>\
						<input class="form-control" type="text" value='+tu_zl.serializedData[n].plantingContent+' id="tplan'+i+'" disabled>\
					  </div>\
					</div>\
					<div class="form-group">\
					  <div class="input-group">\
					  <label>申请从事：</label>\
					  <input type="text" class="form-control" id="plan'+i+'" placeholder="必填从事项目">\
					  </div>\
					</div>\
					<p></p> '
					
					i++;
				  }//end if
			  });//end each
			  if(str==''){
				  $('#sub_land_apply').attr('disabled',"true");
				  return false;
			  }
			  $('#land_lease_table').html(str);
			  $('#sub_land_apply').removeAttr("disabled");
			  tu_zl.choose_count=i-1;
		  });
		  
		   $(document).on("click", ".ck", function() {
			  var id=$(this).attr('id');
			  var n=_.findIndex(tu_zl.serializedData, 'id', id);
			  if(tu_zl.serializedData[n].name!='')return false;
			  if( $(this).is(':checked'))
			  {
				fill(id,tu_zl.serializedData[n].lname,tu_zl.serializedData[n].plantingContent,tu_zl.serializedData[n].landArea,tu_zl.serializedData[n].buildingArea,
				tu_zl.serializedData[n].Afford);
				 var testArray=tu_zl.serializedData[n].data;
				 var data=new Array();
				 i=0;
				 _.map(testArray, function (el) {
					 data[i]=new Array();
					 data[i][0]=el.name;
					 data[i][1]=el.planting;
					 data[i][2]=el.ptime;
					 i++;
				 }); 
				 $('#field_rent').dataTable( {
					"destroy": true,
						"data": data,
						"paging":   false,
						"searching":false,
						"ordering": false,
						"info":     false,
						"order": [[ 2, "asc" ]]
				 });			  
			  }
			  else{
			  fill('','','','','','');
			  $('#field_rent').empty(); 
			  }
		   });//end click 
		   
		   
		   
		   
		  $('#choose-grid').change(this.loadGrid);
		  $('#sub_land_apply').click(this.savedate);
		}//end tu_zl	
			
		});//end function