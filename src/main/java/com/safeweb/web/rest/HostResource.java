package com.safeweb.web.rest;

import com.safeweb.security.AuthoritiesConstants;
import com.safeweb.service.HostService;
import com.safeweb.web.rest.vm.HostModel;
import com.safeweb.web.rest.vm.HostRequest;
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
 * createTime 2020/10/1 15:06
 */
@Slf4j
@Api(tags = {"Host"})
@RestController
@RequestMapping("/api/va/hosts")
public class HostResource {

    @Inject
    private HostService hostService;

    @ApiOperation(
        value = "create host",
        nickname = "createHost",
        response = HostModel.class
    )
    @PostMapping
    @Timed
    @Secured({AuthoritiesConstants.ANONYMOUS})
    public Mono<HostRequest> create(@RequestBody @Valid HostRequest hostRequest) {
        return null;
    }

    @ApiOperation(
        value = "delete host",
        nickname = "deleteHost",
        response = HostModel.class
    )
    @DeleteMapping("/{id:.+}")
    @Timed
    @Secured({AuthoritiesConstants.ANONYMOUS})
    public Mono<HostRequest> delete(@PathVariable("id") Long id) {
        return null;
    }

    @ApiOperation(
        value = "update host",
        nickname = "updateHost",
        response = HostModel.class
    )
    @PutMapping
    @Timed
    @Secured({AuthoritiesConstants.ANONYMOUS})
    public Mono<HostRequest> update(@RequestBody @Valid HostRequest hostRequest) {
        return null;
    }

    @ApiOperation(
        value = "find one host",
        nickname = "findOneById",
        response = HostModel.class
    )
    @GetMapping("/{id:.+}")
    @Timed
    @Secured({AuthoritiesConstants.ANONYMOUS})
    public Mono<HostRequest> findById(@PathVariable("id") Long id) {
        return null;
    }

    @ApiOperation(
        value = "find all host",
        nickname = "findAll",
        response = HostModel.class,
        responseContainer = "list"
    )
    @GetMapping
    @Timed
    @Secured({AuthoritiesConstants.ANONYMOUS})
    public Flux<HostRequest> findAll() {
        return null;
    }

}
