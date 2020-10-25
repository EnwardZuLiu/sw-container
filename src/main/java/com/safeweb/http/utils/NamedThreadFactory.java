package com.safeweb.http.utils;

import org.apache.logging.log4j.util.Strings;

import java.util.Objects;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * author liuzm
 * createTime 2020/10/20 11:02
 */
public class NamedThreadFactory implements ThreadFactory {
    private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
    private static final AtomicInteger THREAD_NUMBER = new AtomicInteger(1);
    private final ThreadGroup group;
    private final String namePrefix;

    public NamedThreadFactory(String name) {
        SecurityManager sm = System.getSecurityManager();
        group = Objects.nonNull(sm) ? sm.getThreadGroup() : Thread.currentThread().getThreadGroup();
        if (Strings.isEmpty(name)) {
            name = "pool";
        }
        namePrefix = name + "-" + POOL_NUMBER.getAndIncrement() + "-thread-";
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r, namePrefix + THREAD_NUMBER.getAndIncrement(), 0);
        if (t.isDaemon()) {
            t.setDaemon(false);
        }
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }
}
