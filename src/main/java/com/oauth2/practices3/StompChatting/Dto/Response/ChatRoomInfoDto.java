package com.oauth2.practices3.StompChatting.Dto.Response;

import com.oauth2.practices3.StompChatting.Entity.ChatRoom;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatRoomInfoDto {

    private long roomId;
    private ChatRoom.ChatRoomType chatRoomType;
    private String friendName;
    private String friendEmail;
    private String friendImgUrl;
    private boolean isLogin;
    private String friendAlias;
    private String lastMessage;
    private LocalDateTime lastWrittenMessageTime;
    private int unreadMessageCnt;
    private boolean tenMinute;

    @Builder
    private ChatRoomInfoDto (long roomId, ChatRoom.ChatRoomType chatRoomType, String friendName, String friendEmail, String friendImgUrl,
                             boolean isLogin, String friendAlias, String lastMessage, LocalDateTime lastWrittenMessageTime,
                             int unreadMessageCnt, boolean tenMinute){


        this.roomId = roomId;
        this.chatRoomType = chatRoomType;
        this.friendName = friendName;
        this.friendEmail = friendEmail;
        this.friendImgUrl = friendImgUrl;
        this.isLogin = isLogin;
        this.friendAlias = friendAlias;
        this.lastMessage = lastMessage;
        this.lastWrittenMessageTime = lastWrittenMessageTime;
        this.unreadMessageCnt = unreadMessageCnt;
        this.tenMinute = tenMinute;
    }

    public static ChatRoomInfoDto of (long roomId,ChatRoom.ChatRoomType chatRoomType, String friendName, String friendEmail, String friendImgUrl,
                                      boolean isLogin, String friendAlias, String lastMessage, LocalDateTime lastWrittenMessageTime,
                                      int unreadMessageCnt, boolean tenMinute){
        return builder().roomId(roomId)
                .chatRoomType(chatRoomType)
                .friendName(friendName)
                .friendEmail(friendEmail)
                .friendImgUrl(friendImgUrl)
                .friendAlias(friendAlias)
                .isLogin(isLogin)
                .lastMessage(lastMessage)
                .lastWrittenMessageTime(lastWrittenMessageTime)
                .unreadMessageCnt(unreadMessageCnt)
                .tenMinute(tenMinute)
                .build();

    }

}
