package cn.jxh.ssm.common.utils;

public class Utils {

    /**
     * @param str      被转化字符串
     * @param defValue 转化失败后的默认值
     * @return
     */
    public static int parseInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return defValue;
        }
    }

    /**
     * @param str      被转化字符串
     * @param defValue 转化失败后的默认值
     * @return
     */
    public static double parseDouble(String str, double defValue) {
        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
            return defValue;
        }
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean strIsNull(String str) {
        return ((str == null) || "".equals(str));
    }

    /**
     * 去空格，如为null则转化为空字符串
     *
     * @param str
     * @return
     */
    public static String trim(String str) {
        if (str == null) {
            return "";
        }
        return str.trim();
    }

    public static String trim(Object obj) {
        if (obj == null) {
            return "";
        } else {
            return trim(obj.toString());
        }
    }

    public static void main(String[] args) {

    }

}
