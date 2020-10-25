package com.safeweb.http;

import com.safeweb.http.handler.HttpJsonRequestEncoder;
import com.safeweb.http.handler.HttpResponseHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * author liuzm
 * createTime 2020/10/19 20:24
 */
public class CustomChannelInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // http客户端编解码器，包括了客户端http请求编码，http响应的解码
        pipeline.addLast(new HttpClientCodec());

        // 用于处理大数据流
        pipeline.addLast(new ChunkedWriteHandler());

        /**
         * 重连handler
         */
//        pipeline.addLast(new ReconnectHandler(nettyClient));


        // 发送业务数据前，进行json编码
        pipeline.addLast(new HttpJsonRequestEncoder());


        pipeline.addLast(new HttpResponseHandler());
    }
}
