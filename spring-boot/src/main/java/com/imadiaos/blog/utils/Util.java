package com.imadiaos.blog.utils;

public class Util {
    private static ThreadLocal<Integer> currentUidContainer = new ThreadLocal<>();

    // 存放uid
    public static void setCurrentUid(Integer uid){
        currentUidContainer.set(uid);
    }

    public static int getCurrentUid(){
        return currentUidContainer.get();
    }
}
