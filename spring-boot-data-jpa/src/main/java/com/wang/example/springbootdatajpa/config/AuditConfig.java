package com.wang.example.springbootdatajpa.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/11/14 23:29
 * @description
 */
@Component
public class AuditConfig implements AuditorAware {
    /**
     * Returns the current auditor of the application.
     *
     * @return the current auditor
     */
    @Override
    public Optional getCurrentAuditor() {
        return Optional.of("allen");
    }
}
