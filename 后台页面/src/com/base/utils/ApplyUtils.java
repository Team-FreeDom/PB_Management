package com.base.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class ApplyUtils {
	/**  
	    * 获取汉字串拼音首字母，英文字符不变  
	    *  
	    * @param chinese 汉字串  
	    * @return 汉语拼音首字母  
	    */   
	public static String getFirstSpell(String chinese) {   
	       StringBuffer pybf = new StringBuffer();   
	       char[] arr = chinese.toCharArray();   
	       HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();   
	       defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);   
	       defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);   
	       for (int i = 0; i < arr.length; i++) {   
	               if (arr[i] > 128) {   
	                       try {   
	                               String[] _t = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);   
	                               if (_t != null) {   
	                                       pybf.append(_t[0].charAt(0));   
	                               }   
	                       } catch (BadHanyuPinyinOutputFormatCombination e) {   
	                               e.printStackTrace();   
	                       }   
	               } else {   
	                       pybf.append(arr[i]);   
	               }   
	       }   
	       return pybf.toString().replaceAll("\\W", "").trim().toUpperCase();   
	   }
	
	public static boolean judgeEng(String str){
		boolean flag;
		//System.out.println(str+"gggg");
		//System.out.println("wosho");
		 String regex = ".*[a-zA-z].*";
		if(str.matches(regex)){
			flag=true;
			
		}else{
			flag=false;
			
		}
		return flag;
	}
	
	/*public static void main(String args[]) {	
		String str = "1494209156703";
		  String regex = ".*[a-zA-z].*";
		  if(str.matches(regex)){
			  System.out.println("11111");
		  }else{
			  System.out.println("13333331111");
			  
		  }
		
	}*/
}
