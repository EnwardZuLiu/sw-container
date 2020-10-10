package com.safeweb.web.rest.vm;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Tolerate;

/**
 * author liuzm
 * createTime 2020/10/1 15:38
 */
@Data
@Builder
@ToString(exclude = { "password" })
public class HostModel {

    @Tolerate
    HostModel(){}

}
