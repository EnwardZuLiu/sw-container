package com.safeweb.http.vo;

import io.netty.handler.codec.http.HttpResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * author liuzm
 * createTime 2020/10/20 11:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NettyHttpResponse {

    private HttpResponseStatus status;

    private Object body;

    public static NettyHttpResponse getInstance(HttpResponseStatus status, Object body) {
        return new NettyHttpResponse(status, body);
    }
}
