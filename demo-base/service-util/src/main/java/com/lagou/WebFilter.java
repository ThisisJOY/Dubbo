package com.lagou;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcException;

//从当前线程拿出web ip，放入RpcContext
@Activate(group = {CommonConstants.CONSUMER})
public class WebFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String ip = ThreadLocalUtil.get();
        System.out.println("WebFilter:" + ip);
        RpcContext.getContext().setAttachment("ip", ip);
        return invoker.invoke(invocation);
    }
}
