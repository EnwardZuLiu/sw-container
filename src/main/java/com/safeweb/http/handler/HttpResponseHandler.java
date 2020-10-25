package com.safeweb.http.handler;

import com.safeweb.http.vo.NettyHttpRequestContext;
import com.safeweb.http.vo.NettyHttpResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Promise;
import lombok.extern.slf4j.Slf4j;

/**
 * author liuzm
 * createTime 2020/10/20 17:27
 */
@Slf4j
public class HttpResponseHandler extends SimpleChannelInboundHandler<FullHttpResponse> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpResponse msg) throws Exception {
        NettyHttpResponse response = NettyHttpResponse.getInstance(msg.status(), msg.content());
        NettyHttpRequestContext context = (NettyHttpRequestContext)ctx.channel().attr(AttributeKey.valueOf("CURRENT_REQ_BOUND_WITH_THE_CHANNEL")).get();

        Promise<NettyHttpResponse> promise = context.getPromise();
        promise.setSuccess(response);
    }
}
