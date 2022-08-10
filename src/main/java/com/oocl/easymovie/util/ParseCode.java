package com.oocl.easymovie.util;

import com.oocl.easymovie.exception.CodeIllegalException;

public class ParseCode {

    public static final int CODE_LENGTH = 3;
    public static final String PRE_CODE = "coffee";
    public static final String SUF_CODE = "studio";
    public static final String CODE_REGEX = "-";

    public static int parseCode(String code) {
        String[] split = code.split(CODE_REGEX);
        if (split.length != CODE_LENGTH || !PRE_CODE.equals(split[0]) || !SUF_CODE.equals(split[2])) {
            throw new CodeIllegalException();
        }
        try {
            return Integer.parseInt(split[1]);
        } catch (NumberFormatException e) {
            throw new CodeIllegalException();
        }
    }
}
