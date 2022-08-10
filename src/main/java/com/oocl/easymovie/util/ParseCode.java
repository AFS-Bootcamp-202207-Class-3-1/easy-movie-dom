package com.oocl.easymovie.util;

public class ParseCode {
    public static int parseCode(String code) {
        String[] split = code.split("-");
        return Integer.parseInt(split[1]);
    }
}
