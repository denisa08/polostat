package org.telegram.telegrambots.api.objects;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QChat is a Querydsl query type for Chat
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QChat extends BeanPath<Chat> {

    private static final long serialVersionUID = -1760795324L;

    public static final QChat chat = new QChat("chat");

    public final BooleanPath allMembersAreAdministrators = createBoolean("allMembersAreAdministrators");

    public final SimplePath<Message> canSetStickerSet = createSimple("canSetStickerSet", Message.class);

    public final BooleanPath channelChat = createBoolean("channelChat");

    public final StringPath description = createString("description");

    public final StringPath firstName = createString("firstName");

    public final BooleanPath groupChat = createBoolean("groupChat");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath inviteLink = createString("inviteLink");

    public final StringPath lastName = createString("lastName");

    public final SimplePath<ChatPhoto> photo = createSimple("photo", ChatPhoto.class);

    public final SimplePath<Message> pinnedMessage = createSimple("pinnedMessage", Message.class);

    public final StringPath stickerSetName = createString("stickerSetName");

    public final BooleanPath superGroupChat = createBoolean("superGroupChat");

    public final StringPath title = createString("title");

    public final StringPath type = createString("type");

    public final BooleanPath userChat = createBoolean("userChat");

    public final StringPath userName = createString("userName");

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

