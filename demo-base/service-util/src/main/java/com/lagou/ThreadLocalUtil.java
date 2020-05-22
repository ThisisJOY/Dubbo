package com.lagou;

public class ThreadLocalUtil {
    private static ThreadLocal<String> threadLocal = new ThreadLocal();

    public static void set(String ip){
        threadLocal.set(ip);
    }
    public static String get(){
        return threadLocal.get();
    }
}
