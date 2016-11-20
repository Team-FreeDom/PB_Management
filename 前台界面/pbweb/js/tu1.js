// JavaScript Document
		
        $(function () {
            var options = {
				float:true
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
					  message: "加载失败",
					  size: 'small'
				  });
				},
				success : function(data) {
					var i = 0;
					for ( var item in data) {

						$("#load-gridh").after(
								"<option value="+data[i].bid+">"
										+ data[i].bname + "</option>");

						i++;
					}

				}

			});
            /*获得基地------end*/

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
						
						  bootbox.alert({
							  message: "操作失败",
							  size: 'small'
						  });
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
					  
					  bootbox.alert({
						  message: "无此节点，操作失败",
						  size: 'small'
					  });
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
				  return true;					
			  });
			  
			  ////////////////////单击结点选择事件/////////////////////////////
			  $(document).on("click", ".grid-stack-item", function() {
				  if(!ishunluan()){
					   return false;
				  }
				  var id=$(this).attr('data-gs-id');
				  var n=_.findIndex(tudi.serializedData2, 'id', id);
				  if(n<0)
				  return false;
				  $('.grid-stack-item-content').removeClass("gay");
				  $('.grid-stack-item-content').addClass("normal");
				  $(this).children('div.grid-stack-item-content').removeClass('normal');
				  $(this).children('div.grid-stack-item-content').addClass('gay');
				  fill(id,tudi.serializedData2[n].lname,tudi.serializedData2[n].plantingContent,tudi.serializedData2[n].landArea,tudi.serializedData2[n].buildingArea,tudi.serializedData2[n].Afford);
				  return true;
			  }); ///单击结点事件	  
			  
              this.loadGrid = function () {
            	  var bid=$('#load-grid').children('option:selected').val();
					var obj;
					if(this.isModify)
					{
						if(confirm('您上次的操作尚未保存，是否取消跳转?'))
						{	
							//this.savedata();
							$('#load-grid').val(this.serializedData2[0].bid);
							return false;
							
						}
					}
					fill('','','','','','');
					this.grid.removeAll();
					obj=this;
					
					$.ajax({  type : 'POST',
								dataType : 'json',
								data:{"bid":bid},
								url : 'getDifferLayout.do',
								async : false,
								cache : false,
								error : function(request) {
									
									 bootbox.alert({
										  message: "数据加载失败！",
										  size: 'small'
									  });
								},
								success : function(data) {
									
									obj.serializedData2 = _.map(data, function (el) {
														  return {
															  id: el.id,
															  lname: el.lname,
															  plantingContent: el.plantingContent,
															  landArea: el.landArea,
															  buildingArea:el.buildingArea,
															  Afford:el.afford,
															  bid:el.bid
														  };
													  });
								  obj.serializedData = _.map(data, function (el) {
														  return {
															  x: el.x,
															  y: el.y,
															  width: el.width,
															  height: el.height,
															  id:el.id
														  };
													  });	
								  var items = GridStackUI.Utils.sort(obj.serializedData);
								  _.each(items, function (node) {
									  var name=_.result(_.find(obj.serializedData2, function(chr) {
										return chr.id == node.id;
									  }), 'lname');////对象serializedData2查找关联的lname
									  obj.grid.addWidget($('<div><div class="grid-stack-item-content normal"><div class="lname">'+name+'</div><button type="button" class="close" ><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button></div><div></div></div>'),node.x, node.y, node.width, node.height,false,1,4,1,4,node.id);//����ڵ�
									}, obj);//end each	
										
							   }//end success
					});	//end ajax				
					
                	}.bind(this);////loadgrid
					
			///////////////////savedate///////////////////////////////////
				this.savedata = function(){
					//////
					var c="";
					bid=$('#load-grid').children('option:selected').val();
					var tag=1;
					
				
					if(_.isEmpty(this.serializedData))
					{
						tag=0;//1.
						
						//return true;
					}else
						{
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
					c=_.merge(this.serializedData, this.serializedData2);
						}
////////////////////////////////将JSON.stringify(c)发送过去////////////////////////////////////////////////				
					$.ajax({  type : 'POST',
								dataType : 'json',
								data:{"layInfo":JSON.stringify(c),
									   "bid":bid, 
								       "tag":tag
								      },
								url : 'updateLayout_Info.do',
								async : false,
								cache : false,
								error : function(request) {
									bootbox.alert({
										message: "加载失败!",
										size: 'small'
									});
								},
								success : function(data1) {
									bootbox.alert({
										message: "已同步至服务器，保存成功!",
										size: 'small'
									});
							   }
					});	
					
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
				////////////////////////////////清除结点函数////////////////////////////////////////
                this.clearGrid = function () {
				  if(!ishunluan()){
					   return false;
				  }
				  var obj=this;

				 bootbox.confirm({ 
					size: "small",
					message: "确定清空本地节点吗?", 
					callback: function(result){
						if(result==true)
						{
							obj.grid.removeAll();
							obj.serializedData.splice(0,obj.serializedData.length);
							obj.serializedData2.splice(0,obj.serializedData2.length);
							fill('','','','','','');
							obj.isModify=true;
						}
					}
				  })
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
					  bootbox.alert({
						  message: "请先选择左侧节点",
						  size: 'small'
					  });
					   return false;
				  }

				  var n=_.findIndex(this.serializedData2, 'id', id);
				  if(n<0)
				  {
					  bootbox.alert({
						  message: "无此节点，操作失败",
						  size: 'small'
					  });
					  return false; 
				  }
				  if($('#tudi_name').val()=='')
				  {
					  bootbox.alert({
						  message: "土地名称为必填项， 请补充！",
						  size: 'small'
					  });					  
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
               
            };
        });