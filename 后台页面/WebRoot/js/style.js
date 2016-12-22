// JavaScript Document
/*全选反选函数*/	
allCkBox(); 
    	function allCkBox(id){
        var tableBox = document.getElementById(id||"tableCheck"),
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
        var tableBox = document.getElementById(id||"tablePay"),
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