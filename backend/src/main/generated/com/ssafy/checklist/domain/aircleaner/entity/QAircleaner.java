package com.ssafy.checklist.domain.aircleaner.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAircleaner is a Querydsl query type for Aircleaner
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAircleaner extends EntityPathBase<Aircleaner> {

    private static final long serialVersionUID = 176814288L;

    public static final QAircleaner aircleaner = new QAircleaner("aircleaner");

    public final StringPath area = createString("area");

    public final StringPath brand = createString("brand");

    public final StringPath dust = createString("dust");

    public final StringPath img = createString("img");

    public final StringPath name = createString("name");

    public final NumberPath<Long> pcode = createNumber("pcode", Long.class);

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public final StringPath spec = createString("spec");

    public final StringPath type = createString("type");

    public QAircleaner(String variable) {
        super(Aircleaner.class, forVariable(variable));
    }

    public QAircleaner(Path<? extends Aircleaner> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAircleaner(PathMetadata metadata) {
        super(Aircleaner.class, metadata);
    }

}

