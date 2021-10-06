package com.ssafy.checklist.domain.main.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QFilter is a Querydsl query type for Filter
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFilter extends EntityPathBase<Filter> {

    private static final long serialVersionUID = 2111519017L;

    public static final QFilter filter = new QFilter("filter");

    public final StringPath category = createString("category");

    public final StringPath condition = createString("condition");

    public QFilter(String variable) {
        super(Filter.class, forVariable(variable));
    }

    public QFilter(Path<? extends Filter> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFilter(PathMetadata metadata) {
        super(Filter.class, metadata);
    }

}

