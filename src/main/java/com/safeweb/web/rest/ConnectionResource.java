package com.safeweb.web.rest;

import com.safeweb.security.AuthoritiesConstants;
import com.safeweb.service.ConnectionService;
import com.safeweb.web.rest.vm.ConnectionModel;
import com.safeweb.web.rest.vm.ConnectionRequest;
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
 * createTime 2020/10/1 15:51
 */
@Slf4j
@Api(tags = {"Connection"})
@RestController
@RequestMapping("/api/va/Connections")
public class ConnectionResource {

    @Inject
    private ConnectionService connectionService;

    @ApiOperation(
            value = "create Connection",
            nickname = "createConnection",
            response = ConnectionModel.class
    )
    @PostMapping
    @Timed
    @Secured({AuthoritiesConstants.ANONYMOUS})
    public Mono<ConnectionRequest> create(@RequestBody @Valid ConnectionRequest RequestModel) {
        return null;
    }

    @ApiOperation(
            value = "delete Connection",
            nickname = "deleteConnection",
            response = ConnectionModel.class
    )
    @DeleteMapping("/{id:.+}")
    @Timed
    @Secured({AuthoritiesConstants.ANONYMOUS})
    public Mono<ConnectionRequest> delete(@PathVariable("id") Long id) {
        return null;
    }

    @ApiOperation(
            value = "update Connection",
            nickname = "updateConnection",
            response = ConnectionModel.class
    )
    @PutMapping
    @Timed
    @Secured({AuthoritiesConstants.ANONYMOUS})
    public Mono<ConnectionRequest> update(@RequestBody @Valid ConnectionRequest requestModel) {
        return null;
    }

    @ApiOperation(
            value = "find one Connection",
            nickname = "findOneById",
            response = ConnectionModel.class
    )
    @GetMapping("/{id:.+}")
    @Timed
    @Secured({AuthoritiesConstants.ANONYMOUS})
    public Mono<ConnectionRequest> findById(@PathVariable("id") Long id) {
        return null;
    }

    @ApiOperation(
            value = "find all Connection",
            nickname = "findAll",
            response = ConnectionModel.class,
            responseContainer = "list"
    )
    @GetMapping
    @Timed
    @Secured({AuthoritiesConstants.ANONYMOUS})
    public Flux<ConnectionRequest> findAll() {
        return null;
    }

}
