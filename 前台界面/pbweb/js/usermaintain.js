// JavaScript Document

$(document).ready(function() {
	  $('#usermaintable').dataTable(
	  {
		  "ajax": {
                url: ""
            },
          "serverSide": true,
		  "bSort": false,
		  "bFilter": false,
		  "aLengthMenu":[5,10,20,30], //动态指定分页后每页显示的记录数。
			"lengthChange":true, //是否启用改变每页显示多少条数据的控件
			"iDisplayLength" : 10,  //默认每页显示多少条记录
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
			},
		  "createdRow": function ( row, data, index ) {
            //行渲染回调,在这里可以对该行dom元素进行任何操作            
            //不使用render，改用jquery文档操作呈现单元格
            var $btnEdit = $('<button type="button" class="btn btn-small btn-primary btn-edit">修改</button>');
            var $btnDel = $('<button type="button" class="btn btn-small btn-danger btn-del">删除</button>');
            $('td', row).eq(5).append($btnEdit).append($btnDel);
        },
		"drawCallback": function( settings ) {
            //渲染完毕后的回调
            //清空全选状态
            $(":checkbox[name='cb-check-all']",$wrapper).prop('checked', false);
            //默认选中第一行
            $("tbody tr",$table).eq(0).click();
        }
	  });
			  
});