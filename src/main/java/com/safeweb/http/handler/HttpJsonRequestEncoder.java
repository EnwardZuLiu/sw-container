package com.safeweb.http.handler;

import com.safeweb.http.vo.NettyHttpRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;

import java.util.List;

/**
 * author liuzm
 * createTime 2020/10/20 17:21
 */
public class HttpJsonRequestEncoder extends MessageToMessageEncoder<NettyHttpRequest> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, NettyHttpRequest request, List<Object> list) throws Exception {
        FullHttpRequest req;
        if (request.getMethod().equals(HttpMethod.GET)) {

        } else if (request.getMethod().equals(HttpMethod.POST)) {

        } else {
            throw new RuntimeException("Not Support method");
        }

    }
}
