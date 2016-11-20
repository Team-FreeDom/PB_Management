$("li.menuItem.nav-parent").click(function(){
	  $(this).toggleClass("opened nav-expanded");
	});

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