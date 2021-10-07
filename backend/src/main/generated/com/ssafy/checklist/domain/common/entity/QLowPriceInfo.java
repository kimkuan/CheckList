package com.ssafy.checklist.domain.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QLowPriceInfo is a Querydsl query type for LowPriceInfo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLowPriceInfo extends EntityPathBase<LowPriceInfo> {

    private static final long serialVersionUID = 2010892806L;

    public static final QLowPriceInfo lowPriceInfo = new QLowPriceInfo("lowPriceInfo");

    public final StringPath category = createString("category");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath img = createString("img");

    public final StringPath link = createString("link");

    public final NumberPath<Long> pcode = createNumber("pcode", Long.class);

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public final StringPath site = createString("site");

    public QLowPriceInfo(String variable) {
        super(LowPriceInfo.class, forVariable(variable));
    }

    public QLowPriceInfo(Path<? extends LowPriceInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLowPriceInfo(PathMetadata metadata) {
        super(LowPriceInfo.class, metadata);
    }

}

