/*    */package com.qianfeng.util;

/*    */
/*    */import java.io.PrintStream;
/*    */
import java.security.MessageDigest;

/*    */
/*    */public class MD5
/*    */{
	/*    */public static final String MD5(String s)
	/*    */{
		/* 5 */char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7',
				'8', '9',
				/* 6 */'a', 'b', 'c', 'd', 'e', 'f' };
		/*    */try {
			/* 8 */byte[] strTemp = s.getBytes();
			/* 9 */MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			/* 10 */mdTemp.update(strTemp);
			/* 11 */byte[] md = mdTemp.digest();
			/* 12 */int j = md.length;
			/* 13 */char[] str = new char[j * 2];
			/* 14 */int k = 0;
			/* 15 */for (int i = 0; i < j; i++) {
				/* 16 */byte byte0 = md[i];
				/* 17 */str[(k++)] = hexDigits[(byte0 >>> 4 & 0xF)];
				/* 18 */str[(k++)] = hexDigits[(byte0 & 0xF)];
				/*    */}
			/* 20 */return new String(str);
		} catch (Exception e) {
			/*    */}
		/* 22 */return null;
		/*    */}

	/*    */
	/*    */public static void main(String[] args) {
		/* 26 */System.out.println(MD5("18942325886"));
		/*    */}
	/*    */
}

/*
 * Location: D:\myeclipseWorkspace\auction_hibernate\src\ Qualified Name:
 * com.zx.util.MD5 JD-Core Version: 0.6.2
 */