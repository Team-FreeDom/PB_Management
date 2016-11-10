	$("li.menuItem.nav-parent").click(function(){
	  $(this).toggleClass("opened nav-expanded");
	})
	
	
 $(document).ready(function() {
              $('#table1').dataTable(
			  {
				  "bSort": false,
				  "bFilter": false,
				  
				  "scrollY": 75,
				  "aLengthMenu":[2,4,5,8], //动态指定分页后每页显示的记录数。
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
			
 $(document).ready(function(){
				$('#table2').DataTable(
				{
					"aLengthMenu":[5,10,15,20], //动态指定分页后每页显示的记录数。
					"lengthChange":true, //是否启用改变每页显示多少条数据的控件
					"iDisplayLength" : 5,  //默认每页显示多少条记录
				  "bSort": false,
				  "dom":'<"#dropdown_menu"f>tipr<"bottom"l>',
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
			});
			
$(document).ready(function() {
              $('#rent-approve-table1').dataTable(
			  {
				  "bSort": false,
				  
				  "bLengthChange": false,
				  
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
			
 $(document).ready(function(){
				$('#rent-approve-table2').DataTable(
				{
				  "bSort": false,
				  "bLengthChange": false,
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
			});
 $(document).ready(function(){
				$('#rent-approve-table3').DataTable(
				{
				  "bSort": false,
				  "bLengthChange": false,
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
			});			
 $(document).ready(function(){
				$('#rent-approve-table4').DataTable(
				{
				  "bSort": false,
				  "bLengthChange": false,
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
			});						