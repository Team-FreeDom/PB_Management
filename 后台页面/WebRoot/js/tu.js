// JavaScript Document
		
        $(function () {
            var options = {
				float:true
            };
            $('.grid-stack').gridstack(options);

            var tudi=new function () {
				this.isModify=false;
                this.serializedData = [];//在loadGrid中构成布局对象，关联土地编号id
				this.serializedData2 = [];//在loadGrid中构成土地信息对象,关联土地编号id
               	this.grid = $('.grid-stack').data('gridstack');
			  	function fill(id,name,plantingContent,landArea,buildingArea,tudi_Afford){
				  $('#tudi_id').val(id);
				  $('#tudi_name').val(name);
				  $('#tudi_plantingContent').val(plantingContent);
				  $('#tudi_landArea').val(landArea);
				  $('#tudi_buildingArea').val(buildingArea);
				  $('#tudi_Afford').val(tudi_Afford);					
				};
				function fuyuan(){
				  $('.grid-stack-item-content').removeClass("gay");
				  $('.grid-stack-item-content').addClass("normal");
				  fill('','','','','','');
				};
				function ishunluan(){
				  var t=parseInt($('#load-grid').children('option:selected').val());
				  if(!_.isEmpty(tudi.serializedData2))
				  {	
					if(t !=  tudi.serializedData2[0].bid){
						 alert("操作失败");
						 return false;
					}
				  }
				  return true;
				};
				
				/////////////////单击删除按钮事件/////////////////////////////////
			  $(document).on("click", ".grid-stack-item .close", function() {
				 
				  var el=$(this).parents('.grid-stack-item');
				  var n=_.findIndex(tudi.serializedData, 'id', $(el).attr('data-gs-id'));
				   if(n==-1)
				   {
					   alert("无此节点，请联系管理员");
					   return false;
				   }
				  if(!ishunluan()){
					   return false;
				  }
				  _.remove(tudi.serializedData, function(n) {
					return n.id==$(el).attr('data-gs-id');
				  });
				  _.remove(tudi.serializedData2, function(n) {
					return n.id==$(el).attr('data-gs-id');
				  });
				  tudi.grid.removeWidget(el);
		  		  fuyuan();
				  this.isModify=true;
				  return false;					
			  });
			  
			  ////////////////////单击结点选择事件/////////////////////////////
			  $(document).on("click", ".grid-stack-item", function() {
				  if(!ishunluan()){
					   return false;
				  }
				  var id=$(this).attr('data-gs-id');
				  var n=_.findIndex(tudi.serializedData2, 'id', id);
				  $('.grid-stack-item-content').removeClass("gay");
				  $('.grid-stack-item-content').addClass("normal");
				  $(this).children('div.grid-stack-item-content').removeClass('normal');
				  $(this).children('div.grid-stack-item-content').addClass('gay');
				  fill(id,tudi.serializedData2[n].lname,tudi.serializedData2[n].plantingContent,tudi.serializedData2[n].landArea,tudi.																 				  serializedData2[n].buildingArea,tudi.serializedData2[n].Afford);
			  }); ///单击结点事件

              
			  
			  //////ajax加载数据loaddate/////////////////////////////
			  this.loaddate = function () {
				  //////////////////////////////此处添加ajax代码，读取数据
                  var items = GridStackUI.Utils.sort(this.serializedData);
                  _.each(items, function (node) {
						var name=_.result(_.find(this.serializedData2, function(chr) {
						  return chr.id == node.id;
						}), 'lname');////对象serializedData2查找关联的lname
                      this.grid.addWidget($('<div><div class="grid-stack-item-content normal"><div class="lname">'+name+'</div><button type="button" class="close" ><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button></div><div></div></div>'),
                          node.x, node.y, node.width, node.height,false,1,4,1,4,node.id);//插入节点
							}, this);
							return true;
	
				  
				  /*data = [//在loadGrid中构成布局对象，关联土地编号id
									  {x: 0, y: 0, width: 2, height: 1,id:'1',lname:'长安气象站',plantingContent:'气候观测',landArea:86,buildingArea:100,Afford:123,bid:1},
									  {x: 0, y: 1, width: 2, height: 1,id:'2',lname:'长安水稻实验田',plantingContent:'玉米 水稻',landArea:86,buildingArea:100,Afford:123,bid:1},
									  {x: 0, y: 2, width: 2, height: 1,id:'3',lname:'长安玉米试验田',plantingContent:'玉米 水稻',landArea:86,buildingArea:100,Afford:123,bid:1},
									  {x: 2, y: 3, width: 2, height: 1,id:'4',lname:'长安油菜实验田',plantingContent:'油菜 水稻',landArea:86,buildingArea:100,Afford:123,bid:1},
									  {x: 1, y: 4, width: 2, height: 1,id:'5',lname:'长安水生态区域',plantingContent:'农田鱼 水稻',landArea:86,buildingArea:100,Afford:123,bid:1},
									  {x: 1, y: 3, width: 2, height: 1,id:'6',lname:'长安林业观察站',plantingContent:'柏林 水稻',landArea:86,buildingArea:100,Afford:123,bid:1},
									  {x: 2, y: 4, width: 2, height: 1,id:'7',lname:'长安生物实验田',plantingContent:'微生物 水稻',landArea:86,buildingArea:100,Afford:123,bid:1},
									  {x: 2, y: 5, width: 2, height: 1,id:'8',lname:'长安动科试验田',plantingContent:'龙虾 水产',landArea:86,buildingArea:100,Afford:123,bid:1}
								  ];*/
				
			  }.bind(this)/////loaddate
			  
			  
			  
              this.loadGrid = function () {
					//alert($('#load-grid').children('option:selected').val());
				  var data;
					if(this.isModify)
					{
						if(confirm('您上次有修改痕迹，是否同步更新到服务器?'))
						{
							
							if(!this.savedata())
							{
								$('#load-grid').val(this.serializedData2[0].bid);
								return false;
							}
						}
					}
					fill('','','','','','');
					this.grid.removeAll();
					
					  $.ajax({
							type : 'POST',						
							dataType : 'json',
							url : 'getLayout_Info.do',
							async : false,
							cache : false,
							error : function(request) {
								alert("error");
							},
							success : function(data1) {
								
								
								  this.serializedData2 = _.map(data1, function (el) {
									  return {
										  id: el.id,
										  lname: el.lname,
										  plantingContent: el.plantingContent,
										  landArea: el.landArea,
										  buildingArea:el.buildingArea,
										  Afford:el.Afford,
										  bid:el.bid
									  };
								  });
				this.serializedData = _.map(data1, function (el) {
									  return {
										  x: el.x,
										  y: el.y,
										  width: el.width,
										  height: el.height,
										  id:el.id
									  };
								  });
                var items = GridStackUI.Utils.sort(this.serializedData);
                _.each(items, function (node) {
						var name=_.result(_.find(this.serializedData2, function(chr) {
						  return chr.id == node.id;
						}), 'lname');////对象serializedData2查找关联的lname
                    this.grid.addWidget($('<div><div class="grid-stack-item-content normal"><div class="lname">'+name+'</div><button type="button" class="close" ><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button></div><div></div></div>'),
                        node.x, node.y, node.width, node.height,false,1,4,1,4,node.id);//插入节点
							}, this);
								}			
						});////ajax end
					/*if(!this.loaddate())
					{
						alert('加载数据失败！任务中断');
						return false;
					}*/
 
                	}.bind(this);////loadgrid
					
			///////////////////savedate///////////////////////////////////
				this.savedata = function(){
					//////
					if(_.isEmpty(this.serializedData))
					{
						alert('空数据，直接发ajax清空命令')
						////////补充删除命令///////
						return true;
					}
                    this.serializedData = _.map($('.grid-stack > .grid-stack-item:visible'), function (el) {
                        el = $(el);
                        var node = el.data('_gridstack_node');
                        return {
                            x: node.x,
                            y: node.y,
                            width: node.width,
                            height: node.height,
							id:node.id.toString()
                        };
                    }, this);
					//var c=this.serializedData.concat(this.serializedData2);
					var c=_.merge(this.serializedData, this.serializedData2);
					//alert(JSON.stringify(c));
                   // $('#saved-data').val(JSON.stringify(this.serializedData, null, '    '));
				   ////////////////ajax上传////////////////////////////////////////////////
				   fuyuan();
				   this.isModify=false;	
				   return true;				
				}.bind(this);
				
				
				///////////////////更新到服务器///////////////////////////////////////////////
                this.saveGrid = function () {
				  if(!ishunluan()){
					   return false;
				  }					
				 this.savedata();
                   
                }.bind(this);
				////////////////////////////////清除结点函数..////////////////////////////////////////
                this.clearGrid = function () {
				  if(!ishunluan()){
					   return false;
				  }
					if(confirm('是否清空本地所有布局?'))
					{
					  this.grid.removeAll();
					  this.serializedData.splice(0,this.serializedData.length);
					  this.serializedData2.splice(0,this.serializedData2.length);
					  fill('','','','','','');
					  this.isModify=true;
					  return false;					
					}
                }.bind(this);		
				//////////////////增加结点函数//////////////////////////////
                this.addGrid = function () {
				  if(!ishunluan()){
					   return false;
				  }
					var id=new Date().getTime().toString();
					var obj2={id:id,lname:'',plantingContent:'',landArea:0,buildingArea:0,Afford:0,bid:parseInt($('#load-grid').children('option:selected').val())};					
					this.serializedData2.push(obj2);
					fill(id,'','',0,0,0);
					$('.grid-stack-item-content').removeClass("gay");
					$('.grid-stack-item-content').addClass("normal");
 					this.grid.addWidget($('<div><div class="grid-stack-item-content gay"><div class="lname"></div><button type="button" class="close" ><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button></div><div></div></div>'),
                            0, 0, 1, 1,true,1,4,1,4,id); 
					var obj={x: parseInt($('.gay').parent().attr('data-gs-x')), y: parseInt($('.gay').parent().attr('data-gs-y')), width: 1, height: 1,id:id};  
					this.serializedData.push(obj);
					$('#tudi_name')[0].focus();  
					this.isModify=true;    
                    return false;
                }.bind(this);
				/////////////////////保存结点信息//////////////////////////////////////
				this.savearray = function(){
				  if(!ishunluan()){
					   return false;
				  }
				   var id=$('#tudi_id').val();
				 if(id==''){
					   alert('请先选择左侧对象，操作失败！');
					   return false;
				  }
				 /*var bid=_.result(_.find(this.serializedData2, function(chr) {
						  return chr.id == id;
						}), 'bid');
				  if(parseInt($('#load-grid').children('option:selected').val()) !=  bid){
					   alert('数据混乱，操作失败！请联系管理员');
					   return false;
				  }*/
				  var n=_.findIndex(this.serializedData2, 'id', id);
				  if(n<0)
				  {
					  alert('数据不存在，操作失败！请联系管理员');
					  return false; 
				  }
				  if($('#tudi_name').val()=='')
				  {
					   alert('土地名称为必填，请补充！');
					   $('#tudi_name')[0].focus();
					  return false; 				  
				  }
				  this.serializedData2[n].lname=$('#tudi_name').val();
				  this.serializedData2[n].plantingContent=$('#tudi_plantingContent').val();
				  this.serializedData2[n].landArea=$('#tudi_landArea').val();
				  this.serializedData2[n].buildingArea= $('#tudi_buildingArea').val();
				  this.serializedData2[n].Afford=$('#tudi_Afford').val();
				  $('.gay > .lname').html($('#tudi_name').val());
				  fuyuan();
				  this.isModify=true;
				}.bind(this)
								
                $('#save-grid').click(this.saveGrid);
                $('#load-grid').change(this.loadGrid);
                $('#clear-grid').click(this.clearGrid);
				$('#add-grid').click(this.addGrid);
				$('#save-array').click(this.savearray);
                this.loadGrid();
            };
        });