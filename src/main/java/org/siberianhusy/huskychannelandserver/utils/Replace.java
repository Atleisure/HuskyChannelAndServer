package org.siberianhusy.huskychannelandserver.utils;

public class Replace {
    public static String replaceName(String content,String name){
        return org.siberianhusy.huskybotapi.utils.Replace.replaceColor(content.replace("name",name));
    }
}
