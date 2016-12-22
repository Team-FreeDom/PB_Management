// JavaScript Document
/*全选反选函数*/	
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

allCkBox(); 
    	function allCkBox(id){
        var tableBox = document.getElementById(id||"rent-approve-table1"),
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
allCkBox2(); 
function allCkBox2(id){
        var tableBox = document.getElementById(id||"rent-approve-table2"),
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