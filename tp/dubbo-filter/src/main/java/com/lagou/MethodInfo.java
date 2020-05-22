package com.lagou;

public class MethodInfo {
    private String methodName;
    private Long takeTime;
    private Long endtime;

    public MethodInfo(String methodName, Long takeTime, Long endtime) {
        this.methodName = methodName;
        this.takeTime = takeTime;
        this.endtime = endtime;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Long getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(Long takeTime) {
        this.takeTime = takeTime;
    }

    public Long getEndtime() {
        return endtime;
    }

    public void setEndtime(Long endtime) {
        this.endtime = endtime;
    }
}
