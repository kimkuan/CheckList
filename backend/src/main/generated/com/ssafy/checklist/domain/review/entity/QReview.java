package com.ssafy.checklist.domain.review.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QReview is a Querydsl query type for Review
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QReview extends EntityPathBase<Review> {

    private static final long serialVersionUID = -1346774616L;

    public static final QReview review = new QReview("review");

    public final StringPath category = createString("category");

    public final StringPath id = createString("id");

    public final StringPath photoList = createString("photoList");

    public final StringPath productId = createString("productId");

    public final NumberPath<Integer> score = createNumber("score", Integer.class);

    public final StringPath site = createString("site");

    public final StringPath time = createString("time");

    public final StringPath writer = createString("writer");

    public QReview(String variable) {
        super(Review.class, forVariable(variable));
    }

    public QReview(Path<? extends Review> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReview(PathMetadata metadata) {
        super(Review.class, metadata);
    }

}

