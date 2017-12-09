package ru.denisa.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAccessToken is a Querydsl query type for AccessToken
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAccessToken extends EntityPathBase<AccessToken> {

    private static final long serialVersionUID = -1640881865L;

    public static final QAccessToken accessToken = new QAccessToken("accessToken");

    public final BooleanPath expired = createBoolean("expired");

    public final DateTimePath<java.util.Date> expiry = createDateTime("expiry", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath token = createString("token");

    public final ListPath<AppUser, QAppUser> user = this.<AppUser, QAppUser>createList("user", AppUser.class, QAppUser.class, PathInits.DIRECT2);

    public QAccessToken(String variable) {
        super(AccessToken.class, forVariable(variable));
    }

    public QAccessToken(Path<? extends AccessToken> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAccessToken(PathMetadata metadata) {
        super(AccessToken.class, metadata);
    }

}

