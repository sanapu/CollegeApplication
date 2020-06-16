package com.college.application.utils;

public class StringUtils {
    public static boolean isCamelCase(String str)
    {
        if(str == null || str.isEmpty())
            return false;
        if(Character.isUpperCase(str.charAt(0))){
            if(str.substring(1).equals(str.substring(1).toLowerCase())){
                return true;
            }
        }
        return false;
    }
}
