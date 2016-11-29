
SYT="-请选择年份-";
SMT="-请选择年份-";
BYN=5;//年份范围往前50年
AYN=5;//年份范围往后0年
function YMDselect(){
	this.SelY=document.getElementsByName(arguments[0])[0];
	this.SelM=document.getElementsByName(arguments[1])[0];
	this.SelY.YMD=this;
	this.SelM.YMD=this;
	this.SelY.onchange=function(){YMDselect.SetM(this.YMD)};
	YMDselect.SetY(this)
};
//设置起始年份
YMDselect.SetY=function(YMD){
	dDate = new Date();
	dCurYear = dDate.getFullYear();
	YMD.SelY.options.add(new Option(SYT,'0'));
	for(i = dCurYear+AYN; i>(dCurYear-BYN); i--){
		YMDYT=i;
		YMDYV=i;
		OptY = new Option(YMDYT,YMDYV);
		YMD.SelY.options.add(OptY);
		if(YMD.DefY==YMDYV) OptY.selected=true
	}
	YMDselect.SetM(YMD)
};
//设置结束年份
YMDselect.SetM=function(YMD){
	YMD.SelM.options.add(new Option(SMT,'0'));
	if(YMD.SelY.value>0){
		var  myselect=document.getElementById("year1");	
		var index=myselect.selectedIndex ; 
		var b=parseInt(myselect.options[index].text);
		for(var i=1;i<=12;i++){
			YMDMT=b+i;
			YMDMV=b+i;
			OptM=new Option(YMDMT,YMDMV);
			YMD.SelM.options.add(OptM);
			if(YMD.DefM==YMDMV) OptM.selected=true
		}
	}
	YMDselect.SetD(YMD)
};
