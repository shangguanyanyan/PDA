package com.jxjr.utility;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.kingdee.util:
//			Base64Encoder

public class StringUtils {

	private static final char hexDigits[] = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	private static final String JAVA_KEYWORDS[] = { "assert", "abstract",
			"continue", "for", "new", "switch", "boolean", "default", "goto",
			"null", "synchronized", "break", "do", "if", "package", "this",
			"byte", "double", "implements", "private", "threadsafe", "byvalue",
			"else", "import", "protected", "throw", "case", "extends",
			"instanceof", "public", "transient", "catch", "false", "int",
			"return", "true", "char", "final", "interface", "short", "try",
			"class", "finally", "long", "static", "void", "const", "float",
			"native", "super", "while", "volatile", "strictfp" };
	private static String VOWELS = "AEIOU";
	private static String FRONTV = "EIY";
	private static String VARSON = "CSPTG";
	private static final int SEPERATOR_DEFAULT_SIZE = 9;

	public StringUtils() {
	}

	public static String[] split(String line, int seperator) {
		if (line == null)
			return null;
		line = line.trim();
		if (line.length() == 0)
			return null;
		if (line.indexOf(seperator) < 0)
			return (new String[] { line });
		List v = new ArrayList();
		int i;
		int j;
		for (i = 0; (j = line.indexOf(seperator, i)) >= 0; i = j + 1)
			v.add(line.substring(i, j).trim());

		v.add(line.substring(i).trim());
		return (String[]) v.toArray(new String[v.size()]);
	}

	public static String[] split(String line, String seperator) {
		if (line == null)
			return null;
		line = line.trim();
		if (line.length() == 0)
			return (new String[] { "" });
		if (line.indexOf(seperator) < 0)
			return (new String[] { line });
		List v = new ArrayList();
		int i;
		int j;
		for (i = 0; (j = line.indexOf(seperator, i)) >= 0; i = j
				+ seperator.length())
			v.add(line.substring(i, j).trim());

		v.add(line.substring(i).trim());
		return (String[]) v.toArray(new String[v.size()]);
	}

	public static String fixNumber(long n) {
		return fixNumber(n, 8);
	}

	public static String fixNumber(long n, int len) {
		char b[] = new char[len];
		for (int i = 0; i < len; i++)
			b[i] = '0';

		return (new DecimalFormat(new String(b))).format(n);
	}

	public static String fixNumber(int n) {
		return fixNumber(n, 4);
	}

	public static String fixNumber(int n, int len) {
		return fixNumber(n, len);
	}

	public static final boolean isEmpty(String s) {
		return s == null || s.trim().length() == 0;
	}

	public static boolean equals(String s1, String s2) {
		if (isEmpty(s1))
			return isEmpty(s2);
		else
			return s1.equals(s2);
	}

	public static boolean equalsIgnoreCase(String s1, String s2) {
		if (isEmpty(s1))
			return isEmpty(s2);
		else
			return s1.equalsIgnoreCase(s2);
	}

	public static String numStr4Java(String s, char c, char c1) {
		if (s == null)
			return null;
		StringBuffer stringbuffer = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c2 = s.charAt(i);
			if (c2 == c)
				continue;
			if (c2 == c1)
				stringbuffer.append('.');
			else
				stringbuffer.append(c2);
		}

		return stringbuffer.toString();
	}

	public static String takeCommasFromNumStr(String s) {
		if (s == null)
			return null;
		if (s.indexOf(',') == -1)
			return s;
		else
			return numStr4Java(s, ',', '.');
	}

	public static String[] enumeration2StringArray(Enumeration enumeration,
			int i) {
		int j = 0;
		String as[] = new String[i];
		while (enumeration.hasMoreElements())
			as[j++] = (String) enumeration.nextElement();
		return as;
	}

	public static byte[] chars2bytes(char ac[]) {
		byte abyte0[] = new byte[ac.length * 2];
		int i = 0;
		for (int j = 0; j < ac.length; j++) {
			char c = ac[j];
			char c1 = ac[j];
			abyte0[i++] = (byte) (c >> 8);
			abyte0[i++] = (byte) c1;
		}

		return abyte0;
	}

	public static char[] bytes2chars(byte abyte0[]) throws Exception {
		if (abyte0.length % 2 != 0)
			throw new Exception("Cannot convert an odd number of bytes");
		char ac[] = new char[abyte0.length / 2];
		int i = 0;
		for (int j = 0; j < ac.length; j++) {
			byte byte0 = abyte0[i++];
			byte byte1 = abyte0[i++];
			ac[j] = (char) (byte0 << 8 & 0xff00 | byte1 & 0xff);
		}

		return ac;
	}

	public static String padStringWidth(String s, int i) {
		StringBuffer stringbuffer = null;
		if (s != null) {
			stringbuffer = new StringBuffer(s);
			stringbuffer.setLength(i);
			int j = s.length();
			for (int l = j; l < i; l++)
				stringbuffer.setCharAt(l, ' ');

		} else {
			stringbuffer = new StringBuffer(i);
			stringbuffer.setLength(i);
			for (int k = 0; k < i; k++)
				stringbuffer.setCharAt(k, ' ');

		}
		return stringbuffer.toString();
	}

	public static String padStringWidth(int i, int j) {
		return padStringWidth(String.valueOf(i), j);
	}

	public static String padStringWidth(float f, int i) {
		return padStringWidth(String.valueOf(f), i);
	}

	public static String padStringWidth(long l, int i) {
		return padStringWidth(String.valueOf(l), i);
	}

	public static String padStringWidth(double d, int i) {
		return padStringWidth(String.valueOf(d), i);
	}

	public static String toHexString(long x, int chars) {
		char buf[] = new char[chars];
		for (int charPos = chars; --charPos >= 0;) {
			buf[charPos] = hexDigits[(int) (x & 15L)];
			x >>>= 4;
		}

		return new String(buf);
	}

	public static String GBToUnicode(String str) throws Exception
	{
		return new String(str.getBytes("8859_1"), "GB2312");
	}

	public static String unicodeToGB(String str) throws Exception
	{
		if (str == null || str.length() == 0)
			return str;
		return new String(str.getBytes("GB2312"), "8859_1");
	}

	public static String cnulls(Object obj) {
		return cnulls(obj, "");
	}

	public static String cnulls(Object obj, String defaultValue) {
		if (obj == null)
			return defaultValue;
		else
			return obj.toString();
	}

	public static String cnulls(String str, String defaultValue) {
		if (str == null)
			return defaultValue;
		else
			return str;
	}

	public static String cnulls(String str) {
		if (str == null)
			return "";
		else
			return str;
	}

	public static String formatCurrency(double cy) {
		return NumberFormat.getCurrencyInstance().format(cy);
	}

	public static String formatNumber(double d, int n) {
		String pattern = "#,##0";
		if (n > 0) {
			pattern = (new StringBuilder()).append(pattern).append(".")
					.toString();
			for (int i = 0; i < n; i++)
				pattern = (new StringBuilder()).append(pattern).append("0")
						.toString();

		}
		return (new DecimalFormat(pattern)).format(d);
	}

	public static int compareToIgnoreCase(String str1, String str2) {
		return str1.toLowerCase().compareTo(str2.toLowerCase());
	}

	public static boolean endsWithIgnoreCase(String str, String suffix) {
		return str.toLowerCase().endsWith(suffix.toLowerCase());
	}

	public static int indexOfIgnoreCase(String str, int find) {
		return str.toLowerCase().indexOf(Character.toLowerCase((char) find));
	}

	public static int indexOfIgnoreCase(String str, int find, int start) {
		return str.toLowerCase().indexOf(Character.toLowerCase((char) find),
				start);
	}

	public static int indexOfIgnoreCase(String str, String find) {
		return str.toLowerCase().indexOf(find.toLowerCase());
	}

	public static int indexOfIgnoreCase(String str, String find, int start) {
		return str.toLowerCase().indexOf(find.toLowerCase(), start);
	}

	public static int lastIndexOfIgnoreCase(String str, int find) {
		return str.toLowerCase()
				.lastIndexOf(Character.toLowerCase((char) find));
	}

	public static int lastIndexOfIgnoreCase(String str, int find, int start) {
		return str.toLowerCase().lastIndexOf(
				Character.toLowerCase((char) find), start);
	}

	public static int lastIndexOfIgnoreCase(String str, String find) {
		return str.toLowerCase().lastIndexOf(find.toLowerCase());
	}

	public static int lastIndexOfIgnoreCase(String str, String find, int start) {
		return str.toLowerCase().lastIndexOf(find.toLowerCase(), start);
	}

	public static int occurencesOf(String str, char ch) {
		char s[] = new char[str.length()];
		str.getChars(0, s.length, s, 0);
		int count = 0;
		for (int i = 0; i < s.length; i++)
			if (s[i] == ch)
				count++;

		return count;
	}

	public static int occurencesOfIgnoreCase(String str, char ch) {
		return occurencesOf(str.toLowerCase(), Character.toLowerCase(ch));
	}

	public static final String replace(String str, String oldChars,
			String newChars) {
		int len = newChars.length();
		int pos = str.indexOf(oldChars);
		int lastPos = pos;
		for (; pos > -1; pos = str.indexOf(oldChars, lastPos)) {
			String firstPart = str.substring(0, pos);
			String lastPart = str.substring(pos + oldChars.length(),
					str.length());
			str = (new StringBuilder()).append(firstPart).append(newChars)
					.append(lastPart).toString();
			lastPos = pos + len;
		}

		return str;
	}

	public static String replaceIgnoreCase(String str, String oldChars,
			String newChars) {
		String lowerStr = str.toLowerCase();
		int len = newChars.length();
		int pos = lowerStr.indexOf(oldChars);
		int lastPos = pos;
		for (; pos > -1; pos = str.indexOf(oldChars, lastPos)) {
			String firstPart = str.substring(0, pos);
			String lastPart = str.substring(pos + oldChars.length(),
					str.length());
			str = (new StringBuilder()).append(firstPart).append(newChars)
					.append(lastPart).toString();
			lastPos = pos + len;
		}

		return str;
	}

	public static boolean startsWithIgnoreCase(String str, String prefix) {
		return str.toLowerCase().startsWith(prefix.toLowerCase());
	}

	public static boolean startsWithIgnoreCase(String str, String prefix,
			int start) {
		return str.toLowerCase().startsWith(prefix.toLowerCase(), start);
	}

	public static String stackToString(Throwable e) throws Exception
	{
		StringWriter sw;
		sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return (new StringBuilder()).append("------\r\n").append(sw.toString()).append("------\r\n").toString();
	}

	public static String stackToString(Exception e) throws Exception
	{
		StringWriter sw;
		sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return (new StringBuilder()).append("------\r\n").append(sw.toString()).append("------\r\n").toString();
	}

	public static String byteArrayToString(byte ba[]) {
		int length = ba.length;
		char buf[] = new char[length * 2];
		int i = 0;
		int j = 0;
		while (i < length) {
			int k = ba[i++];
			buf[j++] = HEX_DIGITS[k >>> 4 & 0xf];
			buf[j++] = HEX_DIGITS[k & 0xf];
		}
		return new String(buf);
	}

	public static byte[] hexFromString(String hex) {
		int len = hex.length();
		byte buf[] = new byte[(len + 1) / 2];
		int i = 0;
		int j = 0;
		if (len % 2 == 1)
			buf[j++] = (byte) fromDigit(hex.charAt(i++));
		while (i < len)
			buf[j++] = (byte) (fromDigit(hex.charAt(i++)) << 4 | fromDigit(hex
					.charAt(i++)));
		return buf;
	}

	public static int fromDigit(char ch) {
		if (ch >= '0' && ch <= '9')
			return ch - 48;
		if (ch >= 'A' && ch <= 'F')
			return (ch - 65) + 10;
		if (ch >= 'a' && ch <= 'f')
			return (ch - 97) + 10;
		else
			throw new IllegalArgumentException((new StringBuilder())
					.append("invalid hex digit '").append(ch).append("'")
					.toString());
	}

	public static String escapeXMLstr(String input) {
		if (input == null)
			return null;
		StringBuffer output = new StringBuffer("");
		int len = input.length();
		for (int i = 0; i < len; i++) {
			char ch = input.charAt(i);
			if (ch == '&') {
				output.append("&amp;");
				continue;
			}
			if (ch == '<') {
				output.append("&lt;");
				continue;
			}
			if (ch == '>') {
				output.append("&gt;");
				continue;
			}
			if (ch == '\'') {
				output.append("&apos;");
				continue;
			}
			if (ch == '"')
				output.append("&quot;");
			else
				output.append(ch);
		}

		return output.toString();
	}

	public static String escapeHTMLstr(String input) {
		if (input == null)
			return null;
		StringBuffer output = new StringBuffer("");
		int len = input.length();
		for (int i = 0; i < len; i++) {
			char ch = input.charAt(i);
			if (ch == '&') {
				output.append("&amp;");
				continue;
			}
			if (ch == '<') {
				output.append("&lt;");
				continue;
			}
			if (ch == '>') {
				output.append("&gt;");
				continue;
			}
			if (ch == '"')
				output.append("&quot;");
			else
				output.append(ch);
		}

		return output.toString();
	}

	public static String headCharUpperCase(String str) {
		if (str == null || "".equals(str))
			return str;
		String headChar = str.substring(0, 1).toUpperCase();
		if (str.length() == 1)
			return headChar;
		else
			return (new StringBuilder()).append(headChar)
					.append(str.substring(1, str.length())).toString();
	}

	public static String headCharLowerCase(String str) {
		if (str == null || "".equals(str))
			return str;
		String headChar = str.substring(0, 1).toLowerCase();
		if (str.length() == 1)
			return headChar;
		else
			return (new StringBuilder()).append(headChar)
					.append(str.substring(1, str.length())).toString();
	}

//	public static String objToString(Object obj) throws Exception
//	{
//		ByteArrayOutputStream byteOS;
//		byteOS = new ByteArrayOutputStream(2048);
//		ObjectOutputStream objectOS = new ObjectOutputStream(byteOS);
//		objectOS.writeObject(obj);
//		objectOS.flush();
//		return Base64Encoder.byteArrayToBase64(byteOS.toByteArray());
//	}
//
//	public static Object strToObject(String str) throws Exception {
//		Object obj;
//		ObjectInputStream objectIN = new ObjectInputStream(
//				new ByteArrayInputStream(Base64Encoder.base64ToByteArray(str)));
//		obj = objectIN.readObject();
//		return obj;
//	}

	public static String left(String str, int n) {
		return str.substring(str.length() - n);
	}

	public static String right(String str, int n) {
		return str.substring(str.length() - n, str.length());
	}

	public static boolean isJavaIdentifier(String s) {
		if (isEmpty(s))
			return false;
		char chars[] = s.toCharArray();
		for (int i = 0; i < chars.length; i++)
			if (!Character.isJavaIdentifierPart(chars[i]) || i == 0
					&& !Character.isJavaIdentifierStart(chars[i]))
				return false;

		return !Arrays.asList(JAVA_KEYWORDS).contains(s);
	}

	public static boolean isJavaClassName(String name) {
		if (isEmpty(name))
			return true;
		if (name.startsWith(" ") || name.startsWith(".") || name.endsWith(" ")
				|| name.endsWith("."))
			return false;
		String pks[] = split(name, ".");
		for (int i = 0; i < pks.length; i++)
			if (!isJavaIdentifier(pks[i]))
				return false;

		return true;
	}

	public static boolean isKsqlIdentifierPart(String s) {
		if (!isEmpty(s)) {
			char chars[] = s.toCharArray();
			for (int i = 0; i < chars.length; i++)
				if (!Character.isJavaIdentifierPart(chars[i]))
					return false;

		}
		return true;
	}

	public static boolean isKsqlIdentifier(String s) {
		if (isEmpty(s))
			return false;
		char chars[] = s.toCharArray();
		for (int i = 0; i < chars.length; i++)
			if (!Character.isJavaIdentifierPart(chars[i]) || i == 0
					&& !Character.isJavaIdentifierStart(chars[i]) || i == 0
					&& chars[i] == '$')
				return false;

		return true;
	}

	public static boolean isFileName(String name) {
		return !isEmpty(name) && name.indexOf('<') <= -1
				&& name.indexOf('*') <= -1 && name.indexOf('/') <= -1
				&& name.indexOf(':') <= -1 && name.indexOf('?') <= -1
				&& name.indexOf('\\') <= -1 && name.indexOf('"') <= -1;
	}

	public static String arrayToString(Object objArray[], String operator) {
		if (objArray != null && objArray.length > 0) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < objArray.length; i++)
				sb.append((new StringBuilder()).append(objArray[i])
						.append(operator).toString());

			if (operator != null && operator.length() > 0)
				if (sb.indexOf(operator) == 0)
					sb.delete(0, operator.length());
				else if (sb.lastIndexOf(operator) == sb.length()
						- operator.length())
					sb.delete(sb.length() - operator.length(), sb.length());
			return sb.toString();
		} else {
			return null;
		}
	}

	public static String[] stringArrayAppend(String array[], String appendObj) {
		if (array == null)
			array = new String[0];
		String newArray[] = new String[array.length + 1];
		System.arraycopy(array, 0, newArray, 0, array.length);
		newArray[newArray.length - 1] = appendObj;
		return newArray;
	}

	public static String trim(String str) {
		return str == null ? null : str.trim();
	}

	public static String addBracket(String str) {
		if (isEmpty(str))
			return str;
		else
			return (new StringBuilder()).append("(").append(str).append(")")
					.toString();
	}

	public static String abbreviate(String txt, int len) {
		String result = "";
		String str[] = splitByUpperCaseChar(txt);
		if (str.length < len) {
			String abb = metaphone(str[0], (len - str.length) + 1);
			if (abb.length() == 0)
				abb = str[0].substring(0, (len - str.length) + 1).toUpperCase();
			abb = (new StringBuilder()).append(abb.substring(0, 1))
					.append(abb.substring(1).toLowerCase()).toString();
			result = (new StringBuilder()).append(result).append(abb)
					.toString();
			for (int i = 1; i < str.length; i++)
				result = (new StringBuilder()).append(result)
						.append(str[i].charAt(0)).toString();

		} else {
			for (int i = 0; i < len; i++)
				result = (new StringBuilder()).append(result)
						.append(str[i].charAt(0)).toString();

		}
		return result;
	}

	private static String metaphone(String txt, int maxCodeLen) {
		if (txt == null || txt.length() == 0)
			return "";
		if (maxCodeLen <= 0)
			return "";
		if (maxCodeLen > txt.length())
			return txt;
		if (txt.length() == 1)
			return txt.toUpperCase();
		boolean hard = false;
		char inwd[] = txt.toUpperCase().toCharArray();
		StringBuffer local = new StringBuffer();
		StringBuffer code = new StringBuffer();
		switch (inwd[0]) {
		case 71: // 'G'
		case 75: // 'K'
		case 80: // 'P'
			if (inwd[1] == 'N')
				local.append(inwd, 1, inwd.length - 1);
			else
				local.append(inwd);
			break;

		case 65: // 'A'
			if (inwd[1] == 'E')
				local.append(inwd, 1, inwd.length - 1);
			else
				local.append(inwd);
			break;

		case 87: // 'W'
			if (inwd[1] == 'R') {
				local.append(inwd, 1, inwd.length - 1);
				break;
			}
			if (inwd[1] == 'H') {
				local.append(inwd, 1, inwd.length - 1);
				local.setCharAt(0, 'W');
			} else {
				local.append(inwd);
			}
			break;

		case 88: // 'X'
			inwd[0] = 'S';
			local.append(inwd);
			break;

		default:
			local.append(inwd);
			break;
		}
		int wdsz = local.length();
		int n = 0;
		do {
			if (code.length() >= maxCodeLen || n >= wdsz)
				break;
			char symb = local.charAt(n);
			if (symb != 'C' && isPreviousChar(local, n, symb)) {
				n++;
			} else {
				switch (symb) {
				case 58: // ':'
				case 59: // ';'
				case 60: // '<'
				case 61: // '='
				case 62: // '>'
				case 63: // '?'
				case 64: // '@'
				default:
					break;

				case 65: // 'A'
				case 69: // 'E'
				case 73: // 'I'
				case 79: // 'O'
				case 85: // 'U'
					if (n == 0)
						code.append(symb);
					break;

				case 66: // 'B'
					if (!isPreviousChar(local, n, 'M') || !isLastChar(wdsz, n))
						code.append(symb);
					break;

				case 67: // 'C'
					if (isPreviousChar(local, n, 'S') && !isLastChar(wdsz, n)
							&& FRONTV.indexOf(local.charAt(n + 1)) >= 0)
						break;
					if (regionMatch(local, n, "CIA")) {
						code.append('X');
						break;
					}
					if (!isLastChar(wdsz, n)
							&& FRONTV.indexOf(local.charAt(n + 1)) >= 0) {
						code.append('S');
						break;
					}
					if (isPreviousChar(local, n, 'S')
							&& isNextChar(local, n, 'H')) {
						code.append('K');
						break;
					}
					if (isNextChar(local, n, 'H')) {
						if (n == 0 && wdsz >= 3 && isVowel(local, 2))
							code.append('K');
						else
							code.append('X');
					} else {
						code.append('C');
					}
					break;

				case 68: // 'D'
					if (!isLastChar(wdsz, n + 1) && isNextChar(local, n, 'G')
							&& FRONTV.indexOf(local.charAt(n + 2)) >= 0) {
						code.append('J');
						n += 2;
					} else {
						code.append('D');
					}
					break;

				case 71: // 'G'
					if (isLastChar(wdsz, n + 1)
							&& isNextChar(local, n, 'H')
							|| !isLastChar(wdsz, n + 1)
							&& isNextChar(local, n, 'H')
							&& !isVowel(local, n + 2)
							|| n > 0
							&& (regionMatch(local, n, "GN") || regionMatch(
									local, n, "GNED")))
						break;
					if (isPreviousChar(local, n, 'G'))
						hard = true;
					else
						hard = false;
					if (!isLastChar(wdsz, n)
							&& FRONTV.indexOf(local.charAt(n + 1)) >= 0
							&& !hard)
						code.append('J');
					else
						code.append('G');
					break;

				case 72: // 'H'
					if (!isLastChar(wdsz, n)
							&& (n <= 0 || VARSON.indexOf(local.charAt(n - 1)) < 0)
							&& isVowel(local, n + 1))
						code.append('H');
					break;

				case 48: // '0'
				case 49: // '1'
				case 50: // '2'
				case 51: // '3'
				case 52: // '4'
				case 53: // '5'
				case 54: // '6'
				case 55: // '7'
				case 56: // '8'
				case 57: // '9'
				case 70: // 'F'
				case 74: // 'J'
				case 76: // 'L'
				case 77: // 'M'
				case 78: // 'N'
				case 82: // 'R'
					code.append(symb);
					break;

				case 75: // 'K'
					if (n > 0) {
						if (!isPreviousChar(local, n, 'C'))
							code.append(symb);
					} else {
						code.append(symb);
					}
					break;

				case 80: // 'P'
					code.append(symb);
					break;

				case 81: // 'Q'
					code.append('Q');
					break;

				case 83: // 'S'
					if (regionMatch(local, n, "SH")
							|| regionMatch(local, n, "SIO")
							|| regionMatch(local, n, "SIA"))
						code.append('X');
					else
						code.append('S');
					break;

				case 84: // 'T'
					if (regionMatch(local, n, "TIA")
							|| regionMatch(local, n, "TIO")) {
						code.append('X');
						break;
					}
					if (!regionMatch(local, n, "TCH")
							&& !regionMatch(local, n, "TH"))
						code.append('T');
					break;

				case 86: // 'V'
					code.append('V');
					break;

				case 87: // 'W'
				case 89: // 'Y'
					if (!isLastChar(wdsz, n) && isVowel(local, n + 1))
						code.append(symb);
					break;

				case 88: // 'X'
					code.append('X');
					break;

				case 90: // 'Z'
					code.append('Z');
					break;
				}
				n++;
			}
			if (code.length() > maxCodeLen)
				code.setLength(maxCodeLen);
		} while (true);
		return code.toString();
	}

	private static String[] splitByUpperCaseChar(String line) {
		if (line == null)
			return null;
		line = line.trim();
		if (line.length() == 0)
			return (new String[] { "" });
		List v = new ArrayList();
		int pos = 0;
		for (int i = 0; i < line.length(); i++) {
			char ch = line.charAt(i);
			if ((ch < 'A' || ch > 'Z') && (ch < '0' || ch > '9'))
				continue;
			String str = line.substring(pos, i).trim();
			if (str.length() > 0)
				v.add(str);
			pos = i;
		}

		v.add(line.substring(pos).trim());
		return (String[]) v.toArray(new String[v.size()]);
	}

	private static final boolean isVowel(StringBuffer string, int index) {
		return VOWELS.indexOf(string.charAt(index)) >= 0;
	}

	private static boolean isPreviousChar(StringBuffer string, int index, char c) {
		boolean matches = false;
		if (index > 0 && index < string.length())
			matches = string.charAt(index - 1) == c;
		return matches;
	}

	private static boolean isNextChar(StringBuffer string, int index, char c) {
		boolean matches = false;
		if (index >= 0 && index < string.length() - 1)
			matches = string.charAt(index + 1) == c;
		return matches;
	}

	private static boolean regionMatch(StringBuffer string, int index,
			String test) {
		boolean matches = false;
		if (index >= 0 && (index + test.length()) - 1 < string.length()) {
			String substring = string.substring(index, index + test.length());
			matches = substring.equals(test);
		}
		return matches;
	}

	private static final boolean isLastChar(int wdsz, int n) {
		return n + 1 == wdsz;
	}

	public static String[] fastSplit(String line, int seperator) {
		if (line == null || line.length() == 0)
			return null;
		line = line.trim();
		int pos[] = new int[9];
		int iTimes = calcSeperatorTimes(line, seperator, pos);
		String arr[] = new String[iTimes + 1];
		if (iTimes <= 0)
			arr[0] = line;
		else if (iTimes > 9) {
			int times = 0;
			int i = 0;
			int j;
			while ((j = line.indexOf(seperator, i)) >= 0) {
				arr[times] = line.substring(i, j).trim();
				i = j + 1;
				times++;
			}
			arr[times] = line.substring(i).trim();
		} else {
			arr[0] = line.substring(0, pos[0]).trim();
			for (int i = 0; i < iTimes - 1; i++)
				arr[i + 1] = line.substring(pos[i] + 1, pos[i + 1]).trim();

			arr[iTimes] = line.substring(pos[iTimes - 1] + 1).trim();
		}
		return arr;
	}

	private static int calcSeperatorTimes(String src, int seperator, int pos[]) {
		if (src == null || src.equals(""))
			return 0;
		int times = 0;
		int i = 0;
		int j;
		while ((j = src.indexOf(seperator, i)) >= 0) {
			if (times < 9)
				pos[times] = j;
			i = j + 1;
			times++;
		}
		return times;
	}

	public static String[] fastSplit(String line, String seperator) {
		if (line == null || line.length() == 0)
			return null;
		line = line.trim();
		int pos[] = new int[9];
		int iTimes = calcSeperatorTimes(line, seperator, pos);
		String arr[] = new String[iTimes + 1];
		if (iTimes <= 0)
			arr[0] = line;
		else if (iTimes > 9) {
			int times = 0;
			int i = 0;
			int j;
			while ((j = line.indexOf(seperator, i)) >= 0) {
				arr[times] = line.substring(i, j).trim();
				i = j + 1;
				times++;
			}
			arr[times] = line.substring(i).trim();
		} else {
			arr[0] = line.substring(0, pos[0]).trim();
			for (int i = 0; i < iTimes - 1; i++)
				arr[i + 1] = line.substring(pos[i] + 1, pos[i + 1]).trim();

			arr[iTimes] = line.substring(pos[iTimes - 1] + 1).trim();
		}
		return arr;
	}

	private static int calcSeperatorTimes(String src, String seperator,
			int pos[]) {
		if (src == null || src.equals(""))
			return 0;
		int times = 0;
		int i = 0;
		int j;
		while ((j = src.indexOf(seperator, i)) >= 0) {
			if (times < 9)
				pos[times] = j;
			i = j + 1;
			times++;
		}
		return times;
	}
	
	/**
	 * Author：EricShen
	 * Date：2017-09-01
	 * */
	public static boolean isNumeric(String str){ 		
		return str.matches("-?[0-9]+.*[0-9]*");
//	   Pattern pattern = Pattern.compile("^[0-9]+(.[0-9]+)?$"); 
//	   Matcher isNum = pattern.matcher(str);
//	   if(!isNum.matches() ){
//	       return false; 
//	   } 
//	   return true; 
	}
	
	public static String replaceBlank(String str) {
		String dest = "";
		if (str!=null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}
}
