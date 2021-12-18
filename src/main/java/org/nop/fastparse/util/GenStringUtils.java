package org.nop.fastparse.util;

public class GenStringUtils {
    public static String captureName(String name) {
        char[] cs = name.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }
    public static String replaceDot(String name) {
        char[] cs = name.toCharArray();
        cs[cs.length-2] =0 ;
        return String.valueOf(cs);
    }
}
