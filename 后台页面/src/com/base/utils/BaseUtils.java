package com.base.utils;

public class BaseUtils {
	
	static final int fail = 500; // 执行失败
	static final int success = 200;// 执行成功

	public static String getException(int tag) { // 通过tag参数判断是何种异常，返回相应的异常说明
		String type = null;
		switch (tag) {
		case fail:
			type = "fail";
			break;		
		case success: 
			type="success";
			break;
		}		
		return type;
		}
}
