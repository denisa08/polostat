package ru.denisa.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSequence is a Querydsl query type for Sequence
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSequence extends EntityPathBase<Sequence> {

    private static final long serialVersionUID = -763715393L;

    public static final QSequence sequence1 = new QSequence("sequence1");

    public final StringPath id = createString("id");

    public final NumberPath<Long> sequence = createNumber("sequence", Long.class);

    public QSequence(String variable) {
        super(Sequence.class, forVariable(variable));
    }

    public QSequence(Path<? extends Sequence> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSequence(PathMetadata metadata) {
        super(Sequence.class, metadata);
    }

}

