package com.ssafy.checklist.domain.airfryer.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAirfryer is a Querydsl query type for Airfryer
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAirfryer extends EntityPathBase<Airfryer> {

    private static final long serialVersionUID = -227058856L;

    public static final QAirfryer airfryer = new QAirfryer("airfryer");

    public final StringPath brand = createString("brand");

    public final StringPath img = createString("img");

    public final StringPath name = createString("name");

    public final NumberPath<Long> pcode = createNumber("pcode", Long.class);

    public final StringPath power = createString("power");

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public final StringPath size = createString("size");

    public final StringPath spec = createString("spec");

    public final StringPath type = createString("type");

    public final StringPath volume = createString("volume");

    public QAirfryer(String variable) {
        super(Airfryer.class, forVariable(variable));
    }

    public QAirfryer(Path<? extends Airfryer> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAirfryer(PathMetadata metadata) {
        super(Airfryer.class, metadata);
    }

}

