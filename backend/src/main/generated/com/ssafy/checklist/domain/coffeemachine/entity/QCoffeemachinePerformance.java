package com.ssafy.checklist.domain.coffeemachine.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCoffeemachinePerformance is a Querydsl query type for CoffeemachinePerformance
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCoffeemachinePerformance extends EntityPathBase<CoffeemachinePerformance> {

    private static final long serialVersionUID = -256638574L;

    public static final QCoffeemachinePerformance coffeemachinePerformance = new QCoffeemachinePerformance("coffeemachinePerformance");

    public final NumberPath<Long> brewing = createNumber("brewing", Long.class);

    public final NumberPath<Long> convenience = createNumber("convenience", Long.class);

    public final NumberPath<Long> pcode = createNumber("pcode", Long.class);

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public final NumberPath<Long> waterVolume = createNumber("waterVolume", Long.class);

    public QCoffeemachinePerformance(String variable) {
        super(CoffeemachinePerformance.class, forVariable(variable));
    }

    public QCoffeemachinePerformance(Path<? extends CoffeemachinePerformance> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCoffeemachinePerformance(PathMetadata metadata) {
        super(CoffeemachinePerformance.class, metadata);
    }

}

