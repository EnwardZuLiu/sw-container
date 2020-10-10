package com.safeweb.repository;

import com.querydsl.core.types.dsl.StringPath;
import com.safeweb.domain.Image;
import com.safeweb.domain.QImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;


/**
 * author liuzm
 * createTime 14:07
 */
@Repository
public interface ImageRepository extends JpaRepository<Image, Long>,
        QuerydslPredicateExecutor<Image>, QuerydslBinderCustomizer<QImage> {

    @Override
    default void customize(QuerydslBindings bindings, QImage Image) {
        bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));
    }
}

