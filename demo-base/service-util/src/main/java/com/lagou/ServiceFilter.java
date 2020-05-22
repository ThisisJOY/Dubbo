package com.lagou;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcException;

//从RpcContext拿出ip,放入当前线程
@Activate(group = CommonConstants.PROVIDER)
public class ServiceFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        final String ip = RpcContext.getContext().getAttachment("ip");
        System.out.println("ServiceFilter:" + ip);
        ThreadLocalUtil.set(ip);
        return invoker.invoke(invocation);
    }
}
