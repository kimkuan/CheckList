package com.ssafy.checklist.domain.coffeemachine.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCoffeemachine is a Querydsl query type for Coffeemachine
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCoffeemachine extends EntityPathBase<Coffeemachine> {

    private static final long serialVersionUID = 736596926L;

    public static final QCoffeemachine coffeemachine = new QCoffeemachine("coffeemachine");

    public final StringPath brand = createString("brand");

    public final StringPath heatTime = createString("heatTime");

    public final StringPath img = createString("img");

    public final StringPath name = createString("name");

    public final NumberPath<Long> pcode = createNumber("pcode", Long.class);

    public final StringPath pressure = createString("pressure");

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public final StringPath spec = createString("spec");

    public final StringPath waterVolume = createString("waterVolume");

    public QCoffeemachine(String variable) {
        super(Coffeemachine.class, forVariable(variable));
    }

    public QCoffeemachine(Path<? extends Coffeemachine> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCoffeemachine(PathMetadata metadata) {
        super(Coffeemachine.class, metadata);
    }

}

