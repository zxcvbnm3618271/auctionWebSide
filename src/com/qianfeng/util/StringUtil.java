package com.qianfeng.util;

public class StringUtil {

	public static boolean notEmpty(String arg){
		//一种是arg
		return arg!=null&&!arg.equals("");
	}
	public static boolean isEmpty(String arg){
		return arg==null||arg.equals("");
	}
}
