package com.lagou;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RemoveDataThread implements Runnable {
    private Map<String, List<MethodInfo>> methodTimes;

    public RemoveDataThread(Map<String, List<MethodInfo>> methodTimes) {
        this.methodTimes = methodTimes;
    }

    @Override
    public void run() {
        System.out.println("======删除过期数据======");
        for (Map.Entry<String, List<MethodInfo>> entry : methodTimes.entrySet()) {
            final List<MethodInfo> methodInfos = entry.getValue();
            final Iterator<MethodInfo> iterator = methodInfos.iterator();
            Long removeCon = System.currentTimeMillis() - 60000;
            while (iterator.hasNext()) {
                MethodInfo info = iterator.next();
                if (info.getEndtime() < removeCon) {
                    iterator.remove();
                }
            }
        }
    }
}
