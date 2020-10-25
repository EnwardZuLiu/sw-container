package com.safeweb.http.vo;

import io.netty.util.concurrent.Promise;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * author liuzm
 * createTime 2020/10/20 11:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NettyHttpRequestContext {
    NettyHttpRequest request;
    Promise<NettyHttpResponse> promise;
}
