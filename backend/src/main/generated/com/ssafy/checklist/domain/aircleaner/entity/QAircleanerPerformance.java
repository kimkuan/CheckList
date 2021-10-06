package com.ssafy.checklist.domain.aircleaner.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAircleanerPerformance is a Querydsl query type for AircleanerPerformance
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAircleanerPerformance extends EntityPathBase<AircleanerPerformance> {

    private static final long serialVersionUID = -1718739648L;

    public static final QAircleanerPerformance aircleanerPerformance = new QAircleanerPerformance("aircleanerPerformance");

    public final NumberPath<Integer> area = createNumber("area", Integer.class);

    public final NumberPath<Integer> convenience = createNumber("convenience", Integer.class);

    public final NumberPath<Long> pcode = createNumber("pcode", Long.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Integer> sensor = createNumber("sensor", Integer.class);

    public QAircleanerPerformance(String variable) {
        super(AircleanerPerformance.class, forVariable(variable));
    }

    public QAircleanerPerformance(Path<? extends AircleanerPerformance> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAircleanerPerformance(PathMetadata metadata) {
        super(AircleanerPerformance.class, metadata);
    }

}

