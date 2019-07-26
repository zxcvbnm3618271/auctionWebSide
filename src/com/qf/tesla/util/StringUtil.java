package com.qf.tesla.util;

import javax.persistence.Entity;

@Entity
public class StringUtil {
	public static boolean notEmpty(String arg) {
		// 前者是用户没有向服务器提供参数，后者是没有填写对应的输入框。
		return arg != null && !arg.equals("");
	}

	public static boolean isEmpty(String arg) {
		// 前者是用户没有向服务器提供参数，后者是没有填写对应的输入框。
		return arg == null || arg.equals("");
	}

}
