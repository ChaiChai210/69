package com.colin.tomvod.utils;

public class StringUtils {
    private static final String URL = "(https?)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";
//    private static final String URL = "(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";
    public static boolean isEmpty(String source) {

        if (source == null || "".equals(source) || "null".equals(source))
            return true;

        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }
    public static boolean isUrl(String source) {
        return source.matches(URL);
    }
}
