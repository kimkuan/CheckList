package com.ssafy.checklist.domain.monitor.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMonitorPerformance is a Querydsl query type for MonitorPerformance
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMonitorPerformance extends EntityPathBase<MonitorPerformance> {

    private static final long serialVersionUID = -2091927310L;

    public static final QMonitorPerformance monitorPerformance = new QMonitorPerformance("monitorPerformance");

    public final NumberPath<Integer> convenience = createNumber("convenience", Integer.class);

    public final NumberPath<Long> pcode = createNumber("pcode", Long.class);

    public final NumberPath<Integer> pixel = createNumber("pixel", Integer.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Integer> screenChange = createNumber("screenChange", Integer.class);

    public final NumberPath<Integer> size = createNumber("size", Integer.class);

    public QMonitorPerformance(String variable) {
        super(MonitorPerformance.class, forVariable(variable));
    }

    public QMonitorPerformance(Path<? extends MonitorPerformance> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMonitorPerformance(PathMetadata metadata) {
        super(MonitorPerformance.class, metadata);
    }

}

