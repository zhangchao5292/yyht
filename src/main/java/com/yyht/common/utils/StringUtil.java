package com.yyht.common.utils;

/**
 * @Title:字符串处理
 * @Description:
 * @author lanzhaoyi
 * @date 2017年5月26日 下午2:41:37
 */
public class StringUtil {

	public static boolean isNullOrEmpty(String value) {
		return (value == null || value.trim().length() == 0);
	}

	/**
	 * @Title:hideMobile
	 * @Descript:影藏手机号（中间四位）
	 * @param mobile
	 * @return
	 * @author lanzhaoyi
	 * @CreateTime:2017年5月31日 下午5:52:54
	 */
	public static String hideMobile(String mobile) {
		int strLen;
		if (mobile == null || (strLen = mobile.length()) == 0) {
			return "";
		}
		StringBuilder strMobileBuilder = new StringBuilder();
		for (int i = 0; i < strLen; i++) {
			if (i >= 3 && i <= 6) {
				strMobileBuilder.append("*");
			} else {
				strMobileBuilder.append(mobile.charAt(i));
			}
		}
		return strMobileBuilder.toString();
	}
}
