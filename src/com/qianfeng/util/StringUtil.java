package com.qianfeng.util;

public class StringUtil {

	public static boolean notEmpty(String arg) {
		// arg为null说明用户没有向服务器提交参数
		// arg为""说明用户没有填写对应的输入框
		return arg != null && !arg.equals("");
	}

	public static boolean isEmpty(String arg) {
		return arg == null || arg.equals("");
	}
}
