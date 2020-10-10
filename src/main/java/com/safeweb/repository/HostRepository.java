package com.safeweb.repository;

import com.querydsl.core.types.dsl.StringPath;
import com.safeweb.domain.Host;
import com.safeweb.domain.QHost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface HostRepository extends JpaRepository<Host, Long>,
        QuerydslPredicateExecutor<Host>, QuerydslBinderCustomizer<QHost> {

    @Override
    default void customize(QuerydslBindings bindings, QHost Host) {
        bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));
    }
}

