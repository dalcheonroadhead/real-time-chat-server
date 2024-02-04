package com.oauth2.practices3.StompChatting.Controller;

import com.oauth2.practices3.StompChatting.Dto.Response.ApiResponseDto;
import com.oauth2.practices3.StompChatting.Dto.Response.ChatRoomInfoDto;
import com.oauth2.practices3.StompChatting.Entity.ChatRoom;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
// 1) 채팅방 리스트를 주는 getMapping 이번 포스팅 주제가 JPA CRUD가 아님으로 정적으로 주고 넘어가겠습니다...
@RequestMapping("/api/friends")
public class ChatRoomController {


    @GetMapping("/dm")
    public ApiResponseDto<?> getAllChatRoomInfo(){

        List<ChatRoomInfoDto> list = new ArrayList<>();

        list.add(  ChatRoomInfoDto.of(1, ChatRoom.ChatRoomType.ONE,"최덕수",
                "kakaoEmail@kakao.email", "~~~~~~"
                ,true,"현재 칭호 없음"
                ,"아직 채팅이 없습니다."
                , LocalDateTime.now(),0
                , true));

        ApiResponseDto<?> ans = ApiResponseDto.builder()
                .data(list
                ).msg("안녕하세요").build();

        return ans;
    }
}
