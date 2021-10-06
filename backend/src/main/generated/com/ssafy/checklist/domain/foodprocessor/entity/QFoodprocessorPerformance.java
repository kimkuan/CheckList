package com.ssafy.checklist.domain.foodprocessor.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QFoodprocessorPerformance is a Querydsl query type for FoodprocessorPerformance
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFoodprocessorPerformance extends EntityPathBase<FoodprocessorPerformance> {

    private static final long serialVersionUID = 1125545970L;

    public static final QFoodprocessorPerformance foodprocessorPerformance = new QFoodprocessorPerformance("foodprocessorPerformance");

    public final NumberPath<Integer> convenience = createNumber("convenience", Integer.class);

    public final NumberPath<Integer> management = createNumber("management", Integer.class);

    public final NumberPath<Long> pcode = createNumber("pcode", Long.class);

    public final NumberPath<Integer> performance = createNumber("performance", Integer.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Integer> processing = createNumber("processing", Integer.class);

    public QFoodprocessorPerformance(String variable) {
        super(FoodprocessorPerformance.class, forVariable(variable));
    }

    public QFoodprocessorPerformance(Path<? extends FoodprocessorPerformance> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFoodprocessorPerformance(PathMetadata metadata) {
        super(FoodprocessorPerformance.class, metadata);
    }

}

