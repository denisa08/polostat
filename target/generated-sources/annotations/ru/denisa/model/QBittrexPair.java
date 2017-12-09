package ru.denisa.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBittrexPair is a Querydsl query type for BittrexPair
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBittrexPair extends EntityPathBase<BittrexPair> {

    private static final long serialVersionUID = -1867548550L;

    public static final QBittrexPair bittrexPair = new QBittrexPair("bittrexPair");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QBittrexPair(String variable) {
        super(BittrexPair.class, forVariable(variable));
    }

    public QBittrexPair(Path<? extends BittrexPair> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBittrexPair(PathMetadata metadata) {
        super(BittrexPair.class, metadata);
    }

}

