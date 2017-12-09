package ru.denisa.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTelegramUser is a Querydsl query type for TelegramUser
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTelegramUser extends EntityPathBase<TelegramUser> {

    private static final long serialVersionUID = 2100583018L;

    public static final QTelegramUser telegramUser = new QTelegramUser("telegramUser");

    public QTelegramUser(String variable) {
        super(TelegramUser.class, forVariable(variable));
    }

    public QTelegramUser(Path<? extends TelegramUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTelegramUser(PathMetadata metadata) {
        super(TelegramUser.class, metadata);
    }

}

