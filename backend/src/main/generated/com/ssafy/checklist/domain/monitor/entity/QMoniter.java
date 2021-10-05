package com.ssafy.checklist.domain.monitor.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMoniter is a Querydsl query type for Moniter
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMoniter extends EntityPathBase<Moniter> {

    private static final long serialVersionUID = 1500821288L;

    public static final QMoniter moniter = new QMoniter("moniter");

    public final StringPath brand = createString("brand");

    public final StringPath id = createString("id");

    public final StringPath imageUrl = createString("imageUrl");

    public final StringPath modelName = createString("modelName");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public QMoniter(String variable) {
        super(Moniter.class, forVariable(variable));
    }

    public QMoniter(Path<? extends Moniter> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMoniter(PathMetadata metadata) {
        super(Moniter.class, metadata);
    }

}

