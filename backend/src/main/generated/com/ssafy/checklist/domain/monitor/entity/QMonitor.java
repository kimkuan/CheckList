package com.ssafy.checklist.domain.monitor.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMonitor is a Querydsl query type for Monitor
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMonitor extends EntityPathBase<Monitor> {

    private static final long serialVersionUID = 1500821598L;

    public static final QMonitor monitor = new QMonitor("monitor");

    public final StringPath brand = createString("brand");

    public final NumberPath<Integer> hz = createNumber("hz", Integer.class);

    public final StringPath img = createString("img");

    public final StringPath name = createString("name");

    public final NumberPath<Long> pcode = createNumber("pcode", Long.class);

    public final StringPath pixel = createString("pixel");

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public final StringPath size = createString("size");

    public final StringPath spec = createString("spec");

    public QMonitor(String variable) {
        super(Monitor.class, forVariable(variable));
    }

    public QMonitor(Path<? extends Monitor> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMonitor(PathMetadata metadata) {
        super(Monitor.class, metadata);
    }

}

