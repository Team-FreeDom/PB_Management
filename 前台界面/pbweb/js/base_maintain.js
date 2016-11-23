// JavaScript Document
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
function showsubmenu2(){
	var submenu=document.getElementById("hide_ul2");
		if (submenu.style.display == 'none')
		{
			submenu.style.display = 'block';
		}
		else
		{
			submenu.style.display = 'none';
		}
		
	}
	 function hidesubmenu2(){
		 var submenu=document.getElementById("hide_ul2");
		 submenu.style.display = 'none';
		 }


$(document).ready(function() {
              $('#base_maintain').dataTable(
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
			  
            } );


allCkBox2(); 
		function allCkBox2(id){
        var tableBox = document.getElementById(id||"base_maintain"),
             ck = tableBox.getElementsByClassName("ck"),
             ckAll = tableBox.getElementsByClassName("ck-all")[0],
             ckRe = tableBox.getElementsByClassName("ck-re")[0];
             ckAll.onchange = function(){
                                    allCk(this.checked);
                                };
              ckRe.onchange = function(){
                                    reCk();
                                };
        function allCk(bool){
                            for(var i =0; i<ck.length;i++){
                                   ck[i].checked = bool;
                                    }
                             }
                        
         function reCk(){
                         for(var i =0; i<ck.length;i++){
                                ck[i].checked ? ck[i].checked = false : ck[i].checked = true;
                                    }
                                }
                            }
							
							
		$("#file-0").fileinput({
	        'allowedFileExtensions' : ['jpg', 'png','gif'],
	    });
		 $(document).ready(function() {
	        $("#test-upload").fileinput({
	            'showPreview' : false,
	            'allowedFileExtensions' : ['jpg', 'png','gif'],
	            'elErrorContainer': '#errorBlock'
	        });
	        
	    });					