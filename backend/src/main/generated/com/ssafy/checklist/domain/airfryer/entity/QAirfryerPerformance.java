package com.ssafy.checklist.domain.airfryer.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAirfryerPerformance is a Querydsl query type for AirfryerPerformance
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAirfryerPerformance extends EntityPathBase<AirfryerPerformance> {

    private static final long serialVersionUID = -2094049864L;

    public static final QAirfryerPerformance airfryerPerformance = new QAirfryerPerformance("airfryerPerformance");

    public final NumberPath<Long> control = createNumber("control", Long.class);

    public final NumberPath<Long> convenience = createNumber("convenience", Long.class);

    public final NumberPath<Long> cook = createNumber("cook", Long.class);

    public final NumberPath<Long> management = createNumber("management", Long.class);

    public final NumberPath<Long> pcode = createNumber("pcode", Long.class);

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public final NumberPath<Long> volume = createNumber("volume", Long.class);

    public QAirfryerPerformance(String variable) {
        super(AirfryerPerformance.class, forVariable(variable));
    }

    public QAirfryerPerformance(Path<? extends AirfryerPerformance> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAirfryerPerformance(PathMetadata metadata) {
        super(AirfryerPerformance.class, metadata);
    }

}

