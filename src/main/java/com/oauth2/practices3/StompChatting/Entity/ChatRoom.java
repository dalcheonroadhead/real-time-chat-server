package com.oauth2.practices3.StompChatting.Entity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;


import java.io.Serializable;


@Entity
@Getter
@Setter
@NoArgsConstructor
// 삭제 명령이 들어왔을 때, 대신 실행할 명령어를 지정
@SQLDelete(sql = "UPDATE chat_room set deleted_at = CONVERT_TZ(NOW(), 'UTC', 'Asia/Seoul') where id = ?")
public class ChatRoom  implements Serializable {

    private static final long serialVersionUID = 6494678977089006639L;

    public enum ChatRoomType {
        ONE, MANY, DEAD
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "chat_room_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ChatRoomType chatRoomType;

    @Column(name = "chat_owner_name", nullable = false, length = 15)
    private String chatOwnerName;

    @Column(name = "chat_owner_email", nullable = false, length = 50)
    private String chatOwnerEmail;

    @Column(name = "user_cnt")
    private int userCnt;

    @Column(name = "ten_minute", nullable = false)
    private boolean tenMinute;

    @Column(name = "win_point", nullable = true)
    private int winPoint;

    @Column(name = "lose_point", nullable = true)
    private  int losePoint;


    @Builder
    private ChatRoom( ChatRoomType chatRoomType, String chatOwnerName,
                      String chatOwnerEmail, int userCnt, boolean tenMinute, int winPoint, int losePoint){
        this.chatRoomType = chatRoomType;
        this.chatOwnerName = chatOwnerName;
        this.chatOwnerEmail = chatOwnerEmail;
        this.userCnt = userCnt;
        this.tenMinute = tenMinute;
        this.winPoint = winPoint;
        this.losePoint = losePoint;
    }

    public static ChatRoom of ( ChatRoomType chatRoomType, String chatOwnerName,
                                String chatOwnerEmail, int userCnt, boolean tenMinute
            , int winPoint, int losePoint){
        return builder()
                .chatRoomType(chatRoomType)
                .chatOwnerName(chatOwnerName)
                .chatOwnerEmail(chatOwnerEmail)
                .userCnt(userCnt)
                .tenMinute(tenMinute)
                .winPoint(winPoint)
                .losePoint(losePoint)
                .build();
    }

    @Override
    public String toString() {
        return "ChatRoom{" +
                "id=" + id +
                ", chatRoomType=" + chatRoomType +
                ", chatOwnerName='" + chatOwnerName + '\'' +
                ", chatOwnerEmail='" + chatOwnerEmail + '\'' +
                ", userCnt=" + userCnt +
                ", tenMinute=" + tenMinute +
                ", winPoint=" + winPoint +
                ", losePoint=" + losePoint +
                '}';
    }
}

/*
 * 1) 기본키 생성을 DB에 위임
 * */