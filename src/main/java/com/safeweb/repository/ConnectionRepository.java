package com.safeweb.repository;

import com.querydsl.core.types.dsl.StringPath;
import com.safeweb.domain.Connection;
import com.safeweb.domain.QConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection, Long>,
        QuerydslPredicateExecutor<Connection>, QuerydslBinderCustomizer<QConnection> {

    @Override
    default void customize(QuerydslBindings bindings, QConnection Connection) {
        bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));
    }
}

