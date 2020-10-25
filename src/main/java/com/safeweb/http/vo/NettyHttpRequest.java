package com.safeweb.http.vo;

import io.netty.handler.codec.http.HttpMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * author liuzm
 * createTime 2020/10/20 11:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NettyHttpRequest<V> {

    private V requestBody;

    private HttpMethod method = HttpMethod.GET;

    private String url;

    // uri
    private String uri;

    private Integer port;

}
