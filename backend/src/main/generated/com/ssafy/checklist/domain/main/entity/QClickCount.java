package com.ssafy.checklist.domain.main.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QClickCount is a Querydsl query type for ClickCount
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QClickCount extends EntityPathBase<ClickCount> {

    private static final long serialVersionUID = 1790377368L;

    public static final QClickCount clickCount = new QClickCount("clickCount");

    public final StringPath category = createString("category");

    public final NumberPath<Long> count = createNumber("count", Long.class);

    public final NumberPath<Long> pcode = createNumber("pcode", Long.class);

    public QClickCount(String variable) {
        super(ClickCount.class, forVariable(variable));
    }

    public QClickCount(Path<? extends ClickCount> path) {
        super(path.getType(), path.getMetadata());
    }

    public QClickCount(PathMetadata metadata) {
        super(ClickCount.class, metadata);
    }

}

