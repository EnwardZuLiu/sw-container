package com.safeweb.web.rest;

import com.safeweb.security.AuthoritiesConstants;
import com.safeweb.service.ContainerService;
import com.safeweb.web.rest.vm.ContainerModel;
import com.safeweb.web.rest.vm.ContainerRequest;
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
 * createTime 2020/10/1 15:53
 */
@Slf4j
@Api(tags = {"Container"})
@RestController
@RequestMapping("/api/va/Containers")
public class ContainerResource {

    @Inject
    private ContainerService containerService;

    @ApiOperation(
            value = "create Container",
            nickname = "createContainer",
            response = ContainerModel.class
    )
    @PostMapping
    @Timed
    @Secured({AuthoritiesConstants.ANONYMOUS})
    public Mono<ContainerRequest> create(@RequestBody @Valid ContainerRequest RequestModel) {
        return null;
    }

    @ApiOperation(
            value = "delete Container",
            nickname = "deleteContainer",
            response = ContainerModel.class
    )
    @DeleteMapping("/{id:.+}")
    @Timed
    @Secured({AuthoritiesConstants.ANONYMOUS})
    public Mono<ContainerRequest> delete(@PathVariable("id") Long id) {
        return null;
    }

    @ApiOperation(
            value = "update Container",
            nickname = "updateContainer",
            response = ContainerModel.class
    )
    @PutMapping
    @Timed
    @Secured({AuthoritiesConstants.ANONYMOUS})
    public Mono<ContainerRequest> update(@RequestBody @Valid ContainerRequest requestModel) {
        return null;
    }

    @ApiOperation(
            value = "find one Container",
            nickname = "findOneById",
            response = ContainerModel.class
    )
    @GetMapping("/{id:.+}")
    @Timed
    @Secured({AuthoritiesConstants.ANONYMOUS})
    public Mono<ContainerRequest> findById(@PathVariable("id") Long id) {
        return null;
    }

    @ApiOperation(
            value = "find all Container",
            nickname = "findAll",
            response = ContainerModel.class,
            responseContainer = "list"
    )
    @GetMapping
    @Timed
    @Secured({AuthoritiesConstants.ANONYMOUS})
    public Flux<ContainerRequest> findAll() {
        return null;
    }

}
