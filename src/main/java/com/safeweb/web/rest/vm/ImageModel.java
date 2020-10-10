package com.safeweb.web.rest.vm;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotNull;


/**
 * author liuzm
 * createTime 2020/10/1 15:49
 */
@Data
@Builder
@ToString(exclude = {"password"})
public class ImageModel {
    @Tolerate
    ImageModel() {
    }
}
