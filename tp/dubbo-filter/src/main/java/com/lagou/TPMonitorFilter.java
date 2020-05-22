package com.lagou;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Activate(group = CommonConstants.CONSUMER)
public class TPMonitorFilter implements Filter, Runnable {
    private Map<String, List<MethodInfo>> methodTimes = new ConcurrentHashMap<>();

    public TPMonitorFilter() {
        //每隔5秒打印线程使用情况
        Executors.newSingleThreadScheduledExecutor()
                .scheduleWithFixedDelay(this, 5, 5, TimeUnit.SECONDS);

        Executors.newSingleThreadScheduledExecutor()
                .scheduleWithFixedDelay(new RemoveDataThread(methodTimes), 10, 60, TimeUnit.SECONDS);
    }

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Result result = null;
        long takeTime = 0L;
        try {
            long startTime = System.currentTimeMillis();
            result = invoker.invoke(invocation);

            if (result.getException() instanceof Exception) {
                throw new Exception(result.getException());
            }
            takeTime = System.currentTimeMillis() - startTime;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }

        String methodName = invocation.getMethodName();
        List<MethodInfo> methodInfos = methodTimes.get(methodName);
        if (methodInfos == null) {
            methodInfos = new ArrayList<>();
            methodTimes.put(methodName, methodInfos);
        }
        methodInfos.add(new MethodInfo(methodName, takeTime, System.currentTimeMillis()));
        return result;
    }

    // rate is 0.9 or 0.99
    private long getTP(List<MethodInfo> methodInfos, Double rate) {
        // 构建临时数组保存最近1分钟之内的数据
        List<MethodInfo> within1Min = new ArrayList<>();

        // 计算最近1分钟的TP90, TP99
        long now = System.currentTimeMillis();

        for (int i = 0; i < methodInfos.size(); i++) {
            MethodInfo methodInfo = methodInfos.get(i);
            Long methodEndtime = methodInfo.getEndtime();
            if (methodEndtime >= now - 60000 && methodEndtime <= now) {
                within1Min.add(methodInfo);
            }
        }
        within1Min.sort(new Comparator<MethodInfo>() {
            @Override
            public int compare(MethodInfo o1, MethodInfo o2) {
                Long l = o1.getTakeTime() - o2.getTakeTime();
                return l.intValue();
            }
        });
        Double idx = within1Min.size() * rate;
        return within1Min.get(idx.intValue() -1).getTakeTime();
    }

    @Override
    public void run() {
        for (Map.Entry<String, List<MethodInfo>> entry : methodTimes.entrySet()) {
            String methodName = entry.getKey();
            List<MethodInfo> methodInfos = entry.getValue();
            long tp90 = getTP(methodInfos, 0.9);
            System.out.println(methodName + " TP90 耗时: " + tp90 + " ms");
            long tp99 = getTP(methodInfos, 0.99);
            System.out.println(methodName + " TP99 耗时: " + tp99 + " ms");
        }

    }
}
