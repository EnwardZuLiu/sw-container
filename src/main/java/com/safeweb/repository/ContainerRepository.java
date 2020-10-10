package com.safeweb.repository;

import com.querydsl.core.types.dsl.StringPath;
import com.safeweb.domain.Container;
import com.safeweb.domain.QContainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface ContainerRepository extends JpaRepository<Container, Long>,
        QuerydslPredicateExecutor<Container>, QuerydslBinderCustomizer<QContainer> {

    @Override
    default void customize(QuerydslBindings bindings, QContainer Container) {
        bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));
    }
}

