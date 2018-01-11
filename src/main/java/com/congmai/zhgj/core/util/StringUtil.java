package com.congmai.zhgj.core.util;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	private static final byte[] SPACE_TAB = { 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1,
			1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };
	private static final int O2S_OPT_BIN = 1;
	private static final int O2S_OPT_OCT = 2;
	private static final int O2S_OPT_HEX = 3;
	
	public static String html2Text(String inputString)
    {
        String htmlStr = inputString;
        String textStr = "";
        try
        {
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
            String regEx_html = "<[^>]+>";
            Pattern p_script = Pattern.compile(regEx_script, 2);
            Matcher m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll("");
            Pattern p_style = Pattern.compile(regEx_style, 2);
            Matcher m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll("");
            Pattern p_html = Pattern.compile(regEx_html, 2);
            Matcher m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll("");
            textStr = htmlStr;
        }
        catch(Exception e)
        {
            System.err.println((new StringBuilder("Html2Text: ")).append(e.getMessage()).toString());
        }
        return textStr;
    }
	
	public static String capitalize(String string) {
		return String.valueOf(string.charAt(0)).toUpperCase()
				+ string.substring(1);
	}

	public static boolean isNotEmpty(String str) {
		return (str != null) && (str.length() > 0);
	}

	public static boolean isEmpty(String string) {
		return (string == null) || (string.length() == 0);
	}

	public static boolean compare(String str1, String str2) {
		if ((isEmpty(str1)) && (isEmpty(str2)))
			return true;
		if ((!isEmpty(str1)) && (!isEmpty(str2))) {
			return str1.equals(str2);
		}
		return false;
	}

	public static boolean compareIgnorCase(String str1, String str2) {
		if ((isEmpty(str1)) && (isEmpty(str2)))
			return true;
		if ((!isEmpty(str1)) && (!isEmpty(str2))) {
			return str1.equalsIgnoreCase(str2);
		}
		return false;
	}

	public static String replace(String src, String oldStr, String newStr) {
		if ((isEmpty(src)) || (isEmpty(oldStr)) || (newStr == null)) {
			return src;
		}

		StringBuffer buffer = new StringBuffer(src);
		int index = src.length();

		while ((index = src.lastIndexOf(oldStr, index)) >= 0) {
			buffer.replace(index, index + oldStr.length(), newStr);

			index -= oldStr.length();
		}

		return buffer.toString();
	}

	public static String replaceIgnoreCase(String src, String oldStr,
			String newStr) {
		if ((isEmpty(src)) || (isEmpty(oldStr)) || (newStr == null)) {
			return src;
		}

		String s = src.toLowerCase();
		oldStr = oldStr.toLowerCase();
		StringBuffer buffer = new StringBuffer(src);
		int index = s.length();

		while ((index = s.lastIndexOf(oldStr, index)) >= 0) {
			buffer.replace(index, index + oldStr.length(), newStr);

			index -= oldStr.length();
		}

		return buffer.toString();
	}

	public static String replaceChar(String src, char oldChar, char newChar) {
		StringBuffer buf = new StringBuffer(src);
		int length = buf.length();

		for (int i = 0; i < length; i++) {
			if (buf.charAt(i) == oldChar) {
				buf.setCharAt(i, newChar);
			}
		}
		return new String(buf);
	}

	public static String compressChar(String src, char oldChar) {
		if (src == null) {
			return null;
		}
		StringBuffer buf = new StringBuffer();
		int length = src.length();
		boolean isOldChar = false;

		for (int i = 0; i < length; i++) {
			char c = src.charAt(i);
			if (c == oldChar) {
				if (!isOldChar) {
					buf.append(c);
				}
				isOldChar = true;
			} else {
				buf.append(c);
				isOldChar = false;
			}
		}
		return buf.toString();
	}

	public static String[] split(String str, char c) {
		if ((str == null) || (str.length() == 0)) {
			return new String[0];
		}

		char[] chs = str.toCharArray();
		int count = 0;
		ArrayList vSep = new ArrayList();

		for (int i = 0; i < chs.length; i++) {
			if (chs[i] == c) {
				count++;
				vSep.add(new Integer(i));
			}
		}

		int[] sep = new int[count + 2];
		for (int i = 0; i < count; i++) {
			sep[(i + 1)] = ((Integer) vSep.get(i)).intValue();
		}
		sep[0] = -1;
		sep[(count + 1)] = str.length();

		String[] ret = new String[count + 1];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = str.substring(sep[i] + 1, sep[(i + 1)]);
		}
		return ret;
	}

	public static String[] split(String str, String delimiter) {
		ArrayList array = new ArrayList();
		int index = 0;
		int begin = 0;
		String[] result = new String[0];

		if (isEmpty(str)) {
			return new String[0];
		}
		while (true) {
			index = str.indexOf(delimiter, begin);

			if (index == begin) {
				if (index >= 0) {
					array.add("");
				}
				begin += delimiter.length();
				continue;
			}
			if (index > begin) {
				int end = index;

				array.add(str.substring(begin, end));

				begin = index + delimiter.length();
				continue;
			}
			if ((begin < 0) || (begin >= str.length()))
				break;
			array.add(str.substring(begin));

			break;
		}

		if (str.endsWith(delimiter)) {
			array.add("");
		}
		if (array.size() > 0) {
			result = new String[array.size()];

			array.toArray(result);
		}

		return result;
	}

	public static String join(Object[] array, String separator) {
		if (separator == null) {
			separator = "";
		}
		int arraySize = array.length;
		int bufSize = arraySize == 0 ? 0
				: (array[0].toString().length() + separator.length())
						* arraySize;
		StringBuffer buf = new StringBuffer(bufSize);

		for (int i = 0; i < arraySize; i++) {
			if (i > 0) {
				buf.append(separator);
			}
			buf.append(array[i]);
		}
		return buf.toString();
	}

	public static boolean isCharInRange(char c, char start, char end) {
		return (c >= start) && (c <= end);
	}

	public static boolean isWhiteSpace(byte c) {
		return SPACE_TAB[c] == 1;
	}

	public static boolean isValidName(String txt) {
		for (int i = 0; i < txt.length(); i++) {
			char cur = txt.charAt(i);

			if ((!Character.isLetter(cur)) && (!Character.isDigit(cur))
					&& (cur != '#') && (cur != '$') && (cur != '&')
					&& (cur != '+') && (cur != '-') && (cur != '_')
					&& (cur != '~') && (cur != '@'))
				return false;
		}
		return true;
	}

	public static String getInvalidNameErrMsg() {
		return "Name must be consisted of letter,digit,#,$,&,+,-,_,~,@.";
	}

	public static boolean isValidEmail(String theEmail) {
		if (theEmail == null)
			return false;
		if (theEmail.length() == 0) {
			return false;
		}
		return theEmail.length() <= 78;
	}

	public static String trimLeft(String str, char c) {
		if (str == null) {
			return null;
		}
		int length = str.length();
		if (length == 0) {
			return "";
		}
		StringBuffer buffer = new StringBuffer(str);
		int index;
		for (index = 0; (index < length) && (buffer.charAt(index) == c); index++)
			;
		if (index == length) {
			return "";
		}
		return buffer.substring(index);
	}

	public static String trimRight(String str, char c) {
		if (str == null) {
			return null;
		}
		int length = str.length();
		if (length == 0) {
			return "";
		}
		StringBuffer buffer = new StringBuffer(str);
		int index;
		for (index = length - 1; (index >= 0) && (buffer.charAt(index) == c); index--)
			;
		if ((index < 0) && (buffer.charAt(0) == c)) {
			return "";
		}
		return buffer.substring(0, index + 1);
	}

	public static String trimLeft(String str) {
		return trimLeft(str, ' ');
	}

	public static String trimRight(String str) {
		return trimRight(str, ' ');
	}

	public static boolean includePatternChar(String pattern) {
		int len = pattern.length();
		for (int i = 0; i < len; i++) {
			char c = pattern.charAt(i);
			if ((c == '*') || (c == '?'))
				return true;
		}
		return false;
	}

	private static String o2s(char cs, char cm, int width, int prec,
			int option, Object obj) {
		StringBuffer buf = new StringBuffer();

		if (obj == null) {
			buf.append("(null)");
		} else if (((obj instanceof Short)) || ((obj instanceof Integer))) {
			int value;
			if ((obj instanceof Short))
				value = ((Short) obj).intValue();
			else
				value = ((Integer) obj).intValue();
			if (option == 1)
				buf.append(Integer.toBinaryString(value));
			else if (option == 2)
				buf.append(Integer.toOctalString(value));
			else if (option == 3)
				buf.append(Integer.toHexString(value));
			else
				buf.append(Integer.toString(value));
		} else if ((obj instanceof Long)) {
			long value = ((Long) obj).longValue();
			if (option == 1)
				buf.append(Long.toBinaryString(value));
			else if (option == 2)
				buf.append(Long.toOctalString(value));
			else if (option == 3)
				buf.append(Long.toHexString(value));
			else
				buf.append(Long.toString(value));
		} else if ((obj instanceof Float)) {
			float value = ((Float) obj).floatValue();
			buf.append(Float.toString(value));
			if (prec >= 0) {
				int l1 = buf.length();
				int pos = -1;
				for (int i1 = 0; i1 < l1; i1++) {
					if (buf.charAt(i1) == '.')
						pos = i1;
				}
				if (pos > 0) {
					int l2 = l1 - pos - 1;
					if (l2 > prec) {
						String tmp;
						if (prec == 0)
							tmp = buf.substring(0, pos + prec);
						else
							tmp = buf.substring(0, pos + 1 + prec);
						buf = new StringBuffer(tmp);
					} else if (l2 < prec) {
						buf.append(strMake('0', prec - l2));
					}
				} else {
					buf.append(strMake('0', prec));
				}
			}
		} else if ((obj instanceof Double)) {
			double value = ((Double) obj).doubleValue();
			buf.append(Double.toString(value));
			if (prec >= 0) {
				int l1 = buf.length();
				int pos = -1;
				for (int i1 = 0; i1 < l1; i1++) {
					if (buf.charAt(i1) == '.')
						pos = i1;
				}
				if (pos > 0) {
					int l2 = l1 - pos - 1;
					if (l2 > prec) {
						String tmp;
						if (prec == 0)
							tmp = buf.substring(0, pos + prec);
						else
							tmp = buf.substring(0, pos + 1 + prec);
						buf = new StringBuffer(tmp);
					} else if (l2 < prec) {
						buf.append(strMake('0', prec - l2));
					}
				} else {
					buf.append(strMake('0', prec));
				}
			}
		} else {
			buf.append(obj.toString());
		}
		int length = buf.length();
		char c0 = '\000';
		if (length > 0) {
			c0 = buf.charAt(0);
			if ((c0 == '+') || (c0 == '-') || (c0 == '.'))
				buf.deleteCharAt(0);
			else {
				c0 = '\000';
			}
		}
		if (length < width) {
			if ((cs == '-') || (cs == 0))
				buf.insert(0, strMake(cm, width - length));
			else {
				buf.append(strMake(cm, width - length));
			}
		}
		if (c0 != 0) {
			buf.insert(0, c0);
		}
		return buf.toString();
	}

	private static _STRTOI _strtoi(String str) {
		StringBuffer buf = new StringBuffer(str);

		_STRTOI r = new _STRTOI(null);

		int length = buf.length();
		int i;
		for (i = 0; i < length; i++) {
			char c = buf.charAt(i);
			if ((c < '0') || (c > '9')) {
				r.lastc = c;
				break;
			}
		}

		if (i > 0) {
			r.value = Integer.parseInt(str.substring(0, i));
			r.position = i;
		}

		return r;
	}

	public static String strMake(char c, int length) {
		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < length; i++) {
			buf.append(c);
		}
		return buf.toString();
	}

	public static String strFormat(String str, Object[] params) {
		StringBuffer r = new StringBuffer();

		StringBuffer buf = new StringBuffer(str);

		int length = buf.length();
		int olength = params == null ? 0 : params.length;

		char cs = '\000';
		char cm = '\000';
		int width = -1;
		int prec = -1;
		int option = 0;
		boolean parse = false;
		int i, j;
		for (i = j = 0; i < length; i++) {
			if (j >= olength) {
				break;
			}
			char c = buf.charAt(i);
			if (c == '%') {
				i++;
				if (i >= length)
					break;
				char c1 = buf.charAt(i);
				if (c1 == c) {
					r = r.append(c);
					continue;
				}
				parse = true;
				cs = cm = 0;
				width = prec = -1;
				option = 0;
				c = c1;
				break;
			}

			if (!parse) {
				r.append(c);
			} else if ((cs == 0) && ((c == '+') || (c == '-'))) {
				cs = c;
			} else if ((cs != 0) && (cm == 0)) {
				cm = c;
			} else if (width < 0) {
				_STRTOI rtmp = _strtoi(buf.substring(i));
				width = rtmp.value;
				if (rtmp.lastc == '.')
					i += rtmp.position;
				else {
					i += rtmp.position - 1;
				}

			} else if (prec == -1) {
				_STRTOI rtmp = _strtoi(buf.substring(i));
				prec = rtmp.position == 0 ? -2 : rtmp.value;
				i += rtmp.position - 1;
			} else {
				parse = false;
				boolean bo2s = true;
				boolean uc = false;
				boolean lc = false;
				switch (c) {
				case 'S':
				case 's':
					break;
				case 'v':
					lc = true;
					break;
				case 'V':
					uc = true;
					break;
				case 'B':
				case 'b':
					option = 1;
					break;
				case 'O':
				case 'o':
					option = 2;
					break;
				case 'x':
					lc = true;
					option = 3;
					break;
				case 'X':
					uc = true;
					option = 3;
				}

				if (bo2s) {
					String tmp = o2s(cs, cm, width, prec < 0 ? -1 : prec,
							option, params[(j++)]);
					if (uc)
						tmp = tmp.toUpperCase();
					else if (lc)
						tmp = tmp.toLowerCase();
					r.append(tmp);
				}
			}
		}
		if (i < length) {
			r = r.append(replace(buf.substring(i), "%%", "%"));
		}
		return r.toString();
	}



	public static String longToIp4(long ip) {
		if (ip < 0L) {
			return "";
		}
		byte b1 = (byte) (int) (ip >> 24);
		byte b2 = (byte) (int) (ip >> 16);
		byte b3 = (byte) (int) (ip >> 8);
		byte b4 = (byte) (int) ip;

		StringBuffer sbuf = new StringBuffer();

		sbuf.append(b1 & 0xFF);
		sbuf.append(".");
		sbuf.append(b2 & 0xFF);
		sbuf.append(".");
		sbuf.append(b3 & 0xFF);
		sbuf.append(".");
		sbuf.append(b4 & 0xFF);

		return sbuf.toString();
	}

	public static boolean bytesEqual(byte[] b1, byte[] b2) {
		if (b1 == b2) {
			return true;
		}
		if ((b1 == null) || (b2 == null)) {
			return false;
		}
		if (b1.length != b2.length) {
			return false;
		}
		for (int i = 0; i < b1.length; i++) {
			if (b1[i] != b2[i]) {
				return false;
			}
		}
		return true;
	}

	public static byte[] base64Decode(byte[] abyte0) {
		int i = abyte0.length / 4 * 3;
		if (i == 0)
			return abyte0;
		if (abyte0[(abyte0.length - 1)] == 61) {
			i--;
			if (abyte0[(abyte0.length - 2)] == 61)
				i--;
		}
		byte[] abyte1 = new byte[i];
		int k = 0;
		int l = 0;
		for (int j = abyte0.length; j > 0; j -= 4) {
			byte byte0 = C1.pem_convert_array[(abyte0[(k++)] & 0xFF)];
			byte byte1 = C1.pem_convert_array[(abyte0[(k++)] & 0xFF)];
			abyte1[(l++)] = (byte) (byte0 << 2 & 0xFC | byte1 >>> 4 & 0x3);
			if (abyte0[k] == 61)
				return abyte1;
			byte0 = byte1;
			byte1 = C1.pem_convert_array[(abyte0[(k++)] & 0xFF)];
			abyte1[(l++)] = (byte) (byte0 << 4 & 0xF0 | byte1 >>> 2 & 0xF);
			if (abyte0[k] == 61)
				return abyte1;
			byte0 = byte1;
			byte1 = C1.pem_convert_array[(abyte0[(k++)] & 0xFF)];
			abyte1[(l++)] = (byte) (byte0 << 6 & 0xC0 | byte1 & 0x3F);
		}
		return abyte1;
	}

	public static final String gbEncoding(String gbString) {
		char[] utfBytes = gbString.toCharArray();
		String unicodeBytes = "";
		for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
			String hexB = Integer.toHexString(utfBytes[byteIndex]);
			if (hexB.length() <= 2) {
				hexB = "00" + hexB;
			}
			unicodeBytes = unicodeBytes + "\\u" + hexB;
		}
		return unicodeBytes;
	}

	private static class _STRTOI {
		public int value = 0;
		public int position = 0;
		public char lastc = '\000';

		private _STRTOI() {
		}

		_STRTOI(_STRTOI param_STRTOI) {
			this();
		}
	}

	static class C1 {
		private static final char[] pem_array = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
				.toCharArray();

		private static final byte[] pem_convert_array = new byte[256];

		static {
			for (int i = 0; i < 255; i++) {
				pem_convert_array[i] = -1;
			}
			for (int j = 0; j < pem_array.length; j++)
				pem_convert_array[pem_array[j]] = (byte) j;
		}
	}
	
	
	private static final int GB_SP_DIFF = 160;  
	 private static final int[] secPosvalueList = {  
	       1601, 1637, 1833, 2078, 2274, 2302, 2433, 2594, 2787,  
	       3106, 3212, 3472, 3635, 3722, 3730, 3858, 4027, 4086,  
	       4390, 4558, 4684, 4925, 5249, 5600};  
	 private static final char[] firstLetter = {  
	       'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j',  
	       'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',  
	       't', 'w', 'x', 'y', 'z'}; 
	 
	private static char convert(byte[] bytes) {  
		  
	     char result = '-';  
	     int secPosvalue = 0;  
	     int i;  
	     for (i = 0; i < bytes.length; i++) {  
	       bytes[i] -= GB_SP_DIFF;  
	     }  
	     secPosvalue = bytes[0] * 100 + bytes[1];  
	     for (i = 0; i < 23; i++) {  
	       if (secPosvalue >= secPosvalueList[i] &&  
	           secPosvalue < secPosvalueList[i + 1]) {  
	         result = firstLetter[i];  
	         break;  
	       }  
	     }  
	     return result;  
	 } 
	
	public static String getFirstLetter(String oriStr) {  
	     String str = oriStr.toLowerCase();  
	     StringBuffer buffer = new StringBuffer();  
	     char ch;  
	     char[] temp;  
	     for (int i = 0; i < str.length(); i++) {   
	       ch = str.charAt(i);  
	       temp = new char[] {  
	           ch};  
	       byte[] uniCode = new String(temp).getBytes();  
	       if (uniCode[0] < 128 && uniCode[0] > 0) {  
	         buffer.append(temp);  
	       }  
	       else {  
	         buffer.append(convert(uniCode));  
	       }  
	     }  
	     return buffer.toString().toUpperCase();  
	 }
	public static String getValidateNumber() { 
		String sRand="";
		Random rad=new Random();
		for (int i=0;i<6;i++){
			String rand=String.valueOf(rad.nextInt(10));
			sRand+=rand;
		}
		return sRand;
	}
	public static String sum(String str1,String str2) {  
	     Double d1 = isEmpty(str1)?0:Double.valueOf(str1);
	     Double d2 = isEmpty(str2)?0:Double.valueOf(str2);
	     String s = String.valueOf(d1+d2);
	     if(s.indexOf(".") > 0){
	         s = s.replaceAll("0+?$", "");//去掉多余的0
	         s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
	     }
	     return s;  
	 }
	
}