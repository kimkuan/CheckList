package com.ssafy.checklist.domain.review.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QReviewWordCloud is a Querydsl query type for ReviewWordCloud
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QReviewWordCloud extends EntityPathBase<ReviewWordCloud> {

    private static final long serialVersionUID = 745447555L;

    public static final QReviewWordCloud reviewWordCloud = new QReviewWordCloud("reviewWordCloud");

    public final NumberPath<Long> pcode = createNumber("pcode", Long.class);

    public final StringPath wordcloud = createString("wordcloud");

    public QReviewWordCloud(String variable) {
        super(ReviewWordCloud.class, forVariable(variable));
    }

    public QReviewWordCloud(Path<? extends ReviewWordCloud> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReviewWordCloud(PathMetadata metadata) {
        super(ReviewWordCloud.class, metadata);
    }

}

