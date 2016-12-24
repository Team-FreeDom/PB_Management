// JavaScript Document
$(document).ready(function() {
	/*筛选的显示与收藏*/
				$(".icon-filter").on("click", function () {
					$('.hide_ul').toggle(500);
				});
				
	/*全选和反选*/
				$("#ck1").on("click", function () {
					if ($(this).prop("checked") === true) {
						$("#Approveing input[name='allcheckbox']").prop("checked", true);
						
					} else {
						$("#Approveing input[name='allcheckbox']").prop("checked", false);
						
					}
					$("#ck2").prop("checked", false);
				 });
				 
				  $("#ck2").click(function () {//反选  
                		$("#Approveing input[name='allcheckbox']").each(function () {  
                    	$(this).prop("checked", !$(this).prop("checked"));
						 
                	}); 
					$("#ck1").prop("checked", false); 
           		 });
				 			
	
              $('#Approveing').dataTable(
			  {
				  "bSort": false,
				  "bFilter": false,
				  "aLengthMenu":[5,7,9,12], //动态指定分页后每页显示的记录数。
					"lengthChange":true, //是否启用改变每页显示多少条数据的控件
					"iDisplayLength" : 5,  //默认每页显示多少条记录
					"dom":'ftipr<"bottom"l>',
                    "language": {
                        "lengthMenu": "每页 _MENU_ 条记录",
                        "zeroRecords": "没有找到记录",
                        "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
                        "infoEmpty": "无记录",
                        "infoFiltered": "(从 _MAX_ 条记录过滤)",
						"sSearch": "模糊查询：",
						"oPaginate": {
						   "sFirst": "首页",
						   "sPrevious": " 上一页 ",
						   "sNext": " 下一页 ",
						   "sLast": " 尾页 "
					   }
                    }
			  });
			  
			  
			  $('#Repairing').dataTable(
			  {
				  "bSort": false,
				  "bFilter": false,
				  "aLengthMenu":[2,4,6,8], //动态指定分页后每页显示的记录数。
					"lengthChange":true, //是否启用改变每页显示多少条数据的控件
					"iDisplayLength" : 2,  //默认每页显示多少条记录
					"dom":'ftipr<"bottom"l>',
                    "language": {
                        "lengthMenu": "每页 _MENU_ 条记录",
                        "zeroRecords": "没有找到记录",
                        "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
                        "infoEmpty": "无记录",
                        "infoFiltered": "(从 _MAX_ 条记录过滤)",
						"sSearch": "模糊查询：",
						"oPaginate": {
						   "sFirst": "首页",
						   "sPrevious": " 上一页 ",
						   "sNext": " 下一页 ",
						   "sLast": " 尾页 "
					   }
                    }
			  });
			  
            } );