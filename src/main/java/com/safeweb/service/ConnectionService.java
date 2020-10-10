package com.safeweb.service;

import com.safeweb.repository.ConnectionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;


/**
 * author liuzm
 * createTime 2020/10/1 14:58
 */
@Slf4j
@Service
@Transactional
public class ConnectionService {

    @Inject
    private ConnectionRepository connectionRepository;

}
