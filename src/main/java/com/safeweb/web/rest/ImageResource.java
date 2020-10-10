package com.safeweb.web.rest;

import com.safeweb.security.AuthoritiesConstants;
import com.safeweb.service.ImageService;
import com.safeweb.web.rest.vm.ImageModel;
import com.safeweb.web.rest.vm.ImageRequest;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import javax.validation.Valid;


/**
 * author liuzm
 * createTime 2020/10/1 15:48
 */
@Slf4j
@Api(tags = {"Image"})
@RestController
@RequestMapping("/api/va/Images")
public class ImageResource {

    @Inject
    private ImageService imageService;

    @ApiOperation(
            value = "create Image",
            nickname = "createImage",
            response = ImageModel.class
    )
    @PostMapping
    @Timed
    @Secured({AuthoritiesConstants.ANONYMOUS})
    public Mono<ImageRequest> create(@RequestBody @Valid ImageRequest RequestModel) {
        return null;
    }

    @ApiOperation(
            value = "delete Image",
            nickname = "deleteImage",
            response = ImageModel.class
    )
    @DeleteMapping("/{id:.+}")
    @Timed
    @Secured({AuthoritiesConstants.ANONYMOUS})
    public Mono<ImageRequest> delete(@PathVariable("id") Long id) {
        return null;
    }

    @ApiOperation(
            value = "update Image",
            nickname = "updateImage",
            response = ImageModel.class
    )
    @PutMapping
    @Timed
    @Secured({AuthoritiesConstants.ANONYMOUS})
    public Mono<ImageRequest> update(@RequestBody @Valid ImageRequest requestModel) {
        return null;
    }

    @ApiOperation(
            value = "find one Image",
            nickname = "findOneById",
            response = ImageModel.class
    )
    @GetMapping("/{id:.+}")
    @Timed
    @Secured({AuthoritiesConstants.ANONYMOUS})
    public Mono<ImageRequest> findById(@PathVariable("id") Long id) {
        return null;
    }

    @ApiOperation(
            value = "find all Image",
            nickname = "findAll",
            response = ImageModel.class,
            responseContainer = "list"
    )
    @GetMapping
    @Timed
    @Secured({AuthoritiesConstants.ANONYMOUS})
    public Flux<ImageRequest> findAll() {
        return null;
    }

}
