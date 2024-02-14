package com.oauth2.practices3.StompChatting.Dto.Request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ChatMessageDto {


    // 1) Message의 용도가 무엇인지. 입장 메세지 인지, 그냥 TALK 인지, 퇴장 메세지 인지
    private String messageType;

    // 2) Payload가 담기는 곳
    private String content;

    // 3) 보낸 이
    private long userId;

    // 4) 해당 메세지가 오고간 채팅방 번호
    private long chatRoomId;

    // 5) Base64로 이미지를 받음
    private String imgCode;

    @Builder
    private ChatMessageDto(String messageType, String content, long userId, long chatRoomId, String imgCode){
        this.messageType = messageType;
        this.content = content;
        this.userId = userId;
        this.chatRoomId = chatRoomId;
        this.imgCode = imgCode;
    }

    public static ChatMessageDto of(String messageType, String content, long userId, long chatRoomId, String imgCode) {
        return  builder().messageType(messageType)
                .content(content)
                .userId(userId)
                .chatRoomId(chatRoomId)
                .imgCode(imgCode)
                .build();
    }

    @Override
    public String toString() {
        return "ChatMessageDto{" +
                "messageType='" + messageType + '\'' +
                ", content='" + content + '\'' +
                ", userId=" + userId +
                ", chatRoomId=" + chatRoomId +
                '}';
    }
}
