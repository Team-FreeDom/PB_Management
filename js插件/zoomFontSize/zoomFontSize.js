// JavaScript Document
//屏幕的宽度
//屏幕的宽度
	var width_srceen = screen.width ;
	var font_size =  "";
	//窗口缩放时执行
	window.onresize=function(){
		changbody_fontSize(".chang_fontSize");
	}
	//加载时执行
	window.onload = function(){
		$("html").css("-webkit-text-size-adjust"
		,"none");
		font_size = $(".chang_fontSize").css("font-size").split("px")[0];
		changbody_fontSize();
	}
	//根据屏幕的宽度与窗体的倍数进行字体的缩放
	function changbody_fontSize(obj){
		var new_window_width = $(window).width();
		var multiple =new_window_width/width_srceen;
		var hi =font_size * multiple;
		$(".chang_fontSize").css("font-size" ,hi+"px" );
	}
	