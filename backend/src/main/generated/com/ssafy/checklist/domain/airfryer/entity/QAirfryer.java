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

    public final StringPath id = createString("id");

    public final StringPath imageUrl = createString("imageUrl");

    public final StringPath modelName = createString("modelName");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

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

