// JavaScript Document
function showsubmenu(li){
	
	var submenu=li.getElementsByTagName("ul")[0];
		
		if (submenu.style.display == 'none')
		{
			submenu.style.display = 'block';
			
		}
		
		else
		{
			submenu.style.display = 'none';
		}
		
	}
/*输出年月日*/	
function timer(){  
        var time=new Date();
		var year=time.getFullYear();
		var month=time.getMonth()+1;
		var day=time.getDate();
        var my=document.getElementsByName("date");
		for(i=0;i<my.length;i++){
			my[i].innerHTML=year+"-"+month+"-"+day;
			}         
    }  
    setInterval("timer()", 1000); 