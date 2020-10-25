package com.safeweb.http;

import com.safeweb.http.utils.NamedThreadFactory;
import com.safeweb.http.vo.NettyHttpRequest;
import com.safeweb.http.vo.NettyHttpRequestContext;
import com.safeweb.http.vo.NettyHttpResponse;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.DefaultThreadFactory;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.Promise;
import lombok.extern.slf4j.Slf4j;

import java.io.Closeable;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * author liuzm
 * createTime 2020/10/19 20:03
 */
@Slf4j
public class NettyClient implements Closeable {

    private static final DefaultEventLoop NETTY_RESPONSE_PROMISE_NOTIFY_EVENT_LOOP =  new DefaultEventLoop(null, new NamedThreadFactory("NettyResponsePromiseNotify"));

    private Channel channel;

    public void initConnection() {
        log.debug("init connection starting ...");

        Bootstrap bootstrap = createBootStrap();
        ChannelFuture future = bootstrap.connect("192.168.50.83", 9090);
        boolean ret = future.awaitUninterruptibly(2000, TimeUnit.MILLISECONDS);


        boolean initSuccess = ret && future.isSuccess();
        if (!initSuccess) {
            throw new RuntimeException("连接失败");
        }

        cleanOldChannel(future, channel);
    }

    private void cleanOldChannel(ChannelFuture future, Channel oldChannel) {
        if (Objects.nonNull(oldChannel)) {
            oldChannel.close();
        }
        this.channel = future.channel();
    }

    private Bootstrap createBootStrap() {
        Bootstrap bootstrap = new Bootstrap()
            .channel(NioSocketChannel.class)
            .group(createGroup(2, "NettyClientWorker"));  // 使用什么来处理客户端事件

        bootstrap.handler(new CustomChannelInitializer()); // 添加消息处理方式
        bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2000);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        return bootstrap;
    }

    private EventLoopGroup createGroup(int number, String threadFactoryName) {
        ThreadFactory threadFactory = new DefaultThreadFactory(threadFactoryName, true);
        return new NioEventLoopGroup(number, threadFactory);
    }


    @Override
    public void close() throws IOException {
        channel.attr(AttributeKey.valueOf("ACTIVE_CLOSE")).set(true);
        channel.close().syncUninterruptibly();
    }

    public NettyHttpResponse doGet(String uri) {
        NettyHttpRequest request = new NettyHttpRequest();
        request.setMethod(HttpMethod.GET);
        request.setUri(uri);
        return this.doHttpRequest(request);
    }

    public NettyHttpResponse doPost(String uri, Object requestBody) {
        NettyHttpRequest request = new NettyHttpRequest();
        request.setMethod(HttpMethod.POST);
        request.setUri(uri);
        request.setRequestBody(requestBody);
        return this.doHttpRequest(request);
    }

    private NettyHttpResponse doHttpRequest(NettyHttpRequest request) {
        Promise<NettyHttpResponse> defaultPromise = NETTY_RESPONSE_PROMISE_NOTIFY_EVENT_LOOP.newPromise();
        NettyHttpRequestContext context = new NettyHttpRequestContext(request, defaultPromise);
        channel.attr(AttributeKey.valueOf("CURRENT_REQ_BOUND_WITH_THE_CHANNEL")).set(context);
        ChannelFuture channelFuture = channel.writeAndFlush(request);
        channelFuture.addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                log.debug("{} request send compile", Thread.currentThread().getName());
            }
        });
        return get(defaultPromise);
    }

    /**
     * 同步阻塞4s，如果请求还没有请求完成，则直接中断线程
     * @param future
     * @param <V>
     * @return
     */
    private <V> V get(Promise<V> future) {
        if (!future.isDone()) {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            future.addListener(new GenericFutureListener<Future<? super V>>() {
                @Override
                public void operationComplete(Future<? super V> future) throws Exception {
                    if (future.isDone()) {
                        countDownLatch.countDown();
                    }
                }
            });

            boolean interrupted = false;
            if (!future.isDone()) {
                try {
                    countDownLatch.await(4, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    interrupted = true;
                }
            }

            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }

        if (future.isSuccess()) {
            return future.getNow();
        }
        log.warn("请求响应超时");
        return null;
    }

}
