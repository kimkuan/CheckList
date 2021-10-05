package com.ssafy.checklist.domain.foodprocessor.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QFoodprocessor is a Querydsl query type for Foodprocessor
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFoodprocessor extends EntityPathBase<Foodprocessor> {

    private static final long serialVersionUID = -764454562L;

    public static final QFoodprocessor foodprocessor = new QFoodprocessor("foodprocessor");

    public final StringPath brand = createString("brand");

    public final StringPath form = createString("form");

    public final StringPath img = createString("img");

    public final StringPath name = createString("name");

    public final NumberPath<Long> pcode = createNumber("pcode", Long.class);

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public final StringPath processType = createString("processType");

    public final StringPath spec = createString("spec");

    public final StringPath volume = createString("volume");

    public QFoodprocessor(String variable) {
        super(Foodprocessor.class, forVariable(variable));
    }

    public QFoodprocessor(Path<? extends Foodprocessor> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFoodprocessor(PathMetadata metadata) {
        super(Foodprocessor.class, metadata);
    }

}

