// JavaScript Document
        $(function () {
            var options = {
				disableDrag:true,
				disableResize:true
            };
			$('.grid-stack').gridstack(options);
			
            /*$.ajax({                //页面加载时自动执行
				type : 'POST',
				dataType : 'json',
				url : 'baseInfo.do',
				async : false,
				cache : false,
				error : function(request) {
					alert("error");
				},
				success : function(data) {
					var i = 0;
					for ( var item in data) {

						$("#choose-grid").after(
								"<option value="+data[i].bid+">"
										+ data[i].bname + "</option>");

						i++;
					}////end for

				}///end success

			});//end ajax*/
		////添加作物加载
		
		
		////////////////
		var tu_zl=new function () {
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
		  
		  
		  this.loadGrid = function () {
			  var obj=this;
			  fill('','','','','','');
			  this.grid.removeAll();
			  /*$.ajax({  type : 'POST',
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
			  });	//end ajax*/	


				data = [//在loadGrid中构成布局对象，关联土地编号id
									{x: 0, y: 0, width: 2, height: 1,id:'1',lname:'长安气象站',plantingContent:'气候观测',landArea:86,buildingArea:100,Afford:123,bid:1,collage:'农学院',name:'张自忠',planting:'水稻'},
									{x: 0, y: 1, width: 2, height: 1,id:'2',lname:'长安水稻实验田',plantingContent:'玉米 水稻',landArea:86,buildingArea:100,Afford:123,bid:1,collage:'农学院',name:'张自忠',planting:'水稻'},
									{x: 0, y: 2, width: 2, height: 1,id:'3',lname:'长安玉米试验田',plantingContent:'玉米 水稻',landArea:86,buildingArea:100,Afford:123,bid:1,collage:'农学院',name:'张自忠',planting:'水稻'},
									{x: 2, y: 3, width: 2, height: 1,id:'4',lname:'长安油菜实验田',plantingContent:'油菜 水稻',landArea:86,buildingArea:100,Afford:123,bid:1,collage:'农学院',name:'张自忠',planting:'水稻'},
									{x: 1, y: 4, width: 2, height: 1,id:'5',lname:'长安水生态区域',plantingContent:'农田鱼 水稻',landArea:86,buildingArea:100,Afford:123,bid:1,collage:'农学院',name:'张自忠',planting:'水稻'},
									{x: 1, y: 3, width: 2, height: 1,id:'6',lname:'长安林业观察站',plantingContent:'柏林 水稻',landArea:86,buildingArea:100,Afford:123,bid:1,collage:'农学院',name:'张自忠',planting:'水稻'},
									{x: 2, y: 4, width: 2, height: 1,id:'7',lname:'长安生物实验田',plantingContent:'微生物 水稻',landArea:86,buildingArea:100,Afford:123,bid:1,collage:'农学院',name:'张自忠',planting:'水稻'},
									{x: 2, y: 5, width: 2, height: 1,id:'8',lname:'长安动科试验田',plantingContent:'龙虾 水产',landArea:86,buildingArea:100,Afford:123,bid:1,collage:'农学院',name:'张自忠',planting:'水稻'}
								];
				this.serializedData2 = _.map(data, function (el) {
										return {
											id: el.id,
											lname: el.lname,
											plantingContent: el.plantingContent,
											landArea: el.landArea,
											buildingArea:el.buildingArea,
											Afford:el.Afford,
											bid:el.bid,
											collage:el.collage,
											name:el.collage,
											planting:el.planting
										};
									});
				this.serializedData = _.map(data, function (el) {
										return {
											x: el.x,
											y: el.y,
											width: el.width,
											height: el.height,
											id:el.id
										};
									});
									
			   var items = GridStackUI.Utils.sort(data);
												_.each(items, function (node) {
													obj.grid.addWidget($('<div><div class="grid-stack-item-content normal"><span class="lname">'+node.lname+'</span><span class="label label-danger">New</span></div><div></div></div>'),node.x, node.y, node.width, node.height,false,1,4,1,4,node.id);
												  }, obj);//end each
	  
			  
		  }.bind(this);
		}//end tu_zl	
			
			tu_zl.loadGrid();
			
		});//end function