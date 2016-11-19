	$("li.menuItem.nav-parent").click(function(){
	  $(this).toggleClass("opened nav-expanded");
	})
	
function showsubmenu(){
	var submenu=document.getElementById("hide_ul");
		if (submenu.style.display == 'none')
		{
			submenu.style.display = 'block';
		}
		else
		{
			submenu.style.display = 'none';
		}
		
	}
	 function hidesubmenu(){
		 var submenu=document.getElementById("hide_ul");
		 submenu.style.display = 'none';
		 }	
		 

			
			
			
$(document).ready(function() {
             var table = $('#rent-approve-table1').dataTable(
			  {
				  "bSort": false,				  
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
			  
			  $('#rent-approve-table1 tbody').on( 'click', 'tr', function () {
					if ( $(this).hasClass('selected') ) {
						$(this).removeClass('selected');
					}
					else {
						table.$('tr.selected').removeClass('selected');
						$(this).addClass('selected');
					}
				} );
			 
				$('#button').click( function () {
					table.row('.selected').remove().draw( false );
				} );	  
            } );
			
 $(document).ready(function(){
				$('#rent-approve-table2').DataTable(
				{
				  "bSort": false,
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
			});
 $(document).ready(function(){
				$('#rent-approve-table3').DataTable(
				{
				  "bSort": false,
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
			});			
 $(document).ready(function(){
				$('#rent-approve-table4').DataTable(
				{
				  "bSort": false,
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
			});						
			
			