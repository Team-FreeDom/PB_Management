package com.base.utils;

public class BaseUtils {

	static final int doubleKey = -6;// 主键重复
	static final int otherKey = -7; // 其他异常问题
	static final int success = 200;// 执行成功

	public static String getException(int tag) { // 通过tag参数判断是何种异常，返回相应的异常说明
		String type = null;
		switch (tag) {
		case doubleKey:
			type = "存在相同的记录";
			break;
		case otherKey: 
			type="操作失败";
			break;		
		case success: 
			type="操作成功";
			break;
		}		
		return type;

		}
}
