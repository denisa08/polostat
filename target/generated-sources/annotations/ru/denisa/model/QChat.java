package ru.denisa.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QChat is a Querydsl query type for Chat
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QChat extends EntityPathBase<Chat> {

    private static final long serialVersionUID = -2092897450L;

    public static final QChat chat = new QChat("chat");

    public final org.telegram.telegrambots.api.objects.QChat _super = new org.telegram.telegrambots.api.objects.QChat(this);

    //inherited
    public final BooleanPath allMembersAreAdministrators = _super.allMembersAreAdministrators;

    //inherited
    public final SimplePath<org.telegram.telegrambots.api.objects.Message> canSetStickerSet = _super.canSetStickerSet;

    //inherited
    public final BooleanPath channelChat = _super.channelChat;

    //inherited
    public final StringPath description = _super.description;

    public final BooleanPath enable = createBoolean("enable");

    //inherited
    public final StringPath firstName = _super.firstName;

    //inherited
    public final BooleanPath groupChat = _super.groupChat;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath inviteLink = _super.inviteLink;

    //inherited
    public final StringPath lastName = _super.lastName;

    public final StringPath name = createString("name");

    //inherited
    public final SimplePath<org.telegram.telegrambots.api.objects.ChatPhoto> photo = _super.photo;

    //inherited
    public final SimplePath<org.telegram.telegrambots.api.objects.Message> pinnedMessage = _super.pinnedMessage;

    public final BooleanPath sendfrom10pct = createBoolean("sendfrom10pct");

    public final BooleanPath sendfrom15pct = createBoolean("sendfrom15pct");

    public final BooleanPath sendfrom20pct = createBoolean("sendfrom20pct");

    public final BooleanPath sendfrom30pct = createBoolean("sendfrom30pct");

    public final BooleanPath sendfrom5pct = createBoolean("sendfrom5pct");

    public final BooleanPath sendfrom7pct = createBoolean("sendfrom7pct");

    //inherited
    public final StringPath stickerSetName = _super.stickerSetName;

    //inherited
    public final BooleanPath superGroupChat = _super.superGroupChat;

    //inherited
    public final StringPath title = _super.title;

    //inherited
    public final StringPath type = _super.type;

    //inherited
    public final BooleanPath userChat = _super.userChat;

    //inherited
    public final StringPath userName = _super.userName;

    public QChat(String variable) {
        super(Chat.class, forVariable(variable));
    }

    public QChat(Path<? extends Chat> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChat(PathMetadata metadata) {
        super(Chat.class, metadata);
    }

}

