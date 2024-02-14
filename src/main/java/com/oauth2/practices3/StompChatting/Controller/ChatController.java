package com.oauth2.practices3.StompChatting.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oauth2.practices3.StompChatting.Dto.Request.ChatMessageDto;
import com.oauth2.practices3.StompChatting.Service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ChatController  {

    private final ObjectMapper objectMapper;
    private final SimpMessageSendingOperations template;
    private final ChatService chatService;


    // 1) 연결 성공을 감지
    @EventListener
    public void connectEvent(SessionConnectEvent sessionConnectEvent){
        System.out.println(sessionConnectEvent);
        System.out.println("연결 성공 감지!_!#!#!#!@#!@@#!@!#!$!@");
        //return "redirect:chat/message";
    }

    // 2) 입장 후 첫 번째로 보내는 메세지는 여기로 오게 한다.
    @MessageMapping("/chat/enterUser")
    public void enterUser(String publishMessage, SimpMessageHeaderAccessor headerAccessor) {
        log.info("What Accessor Header got {}", headerAccessor);

        try {
            ChatMessageDto msg = objectMapper.readValue(publishMessage, ChatMessageDto.class);
            msg.setMessageType("ENTER");

            template.convertAndSend("/sub/chat/room/" + msg.getChatRoomId(), msg);

        }catch (Exception e){
            log.error("Exception {}", e.getMessage());
        }
    }

    // 3) TALK 타입 메세지가 WebSocket으로 발행되는 경우, 일로 온다.
    @MessageMapping("/chat/sendMessage")
    public void sendMessage (String publishMessage) {
        log.info("MESSAGE {}", publishMessage);

        ChatMessageDto msg = null;

        try{
            msg = objectMapper.readValue(publishMessage, ChatMessageDto.class);
            msg.setMessageType("TALK");

            ChatMessageDto msgWithImage = null;

            if(msg.getImgCode() != null) {
               msgWithImage  = chatService.BinaryImageChange(msg);
            }else {
                msgWithImage = msg;
            }

            template.convertAndSend("/sub/chat/room/" + msg.getChatRoomId(), msgWithImage);


        }catch (Exception e){
            log.error("Exception {}", e.getMessage());
        }

    }

    // 4) 채팅방 나가기 전에 메세지 보내는 곳
    public void exitChatRoom(String publishMessage) {
       log.info("EXIT_MESSAGE {}", publishMessage);

       ChatMessageDto msg = null;

       try{
           msg = objectMapper.readValue(publishMessage, ChatMessageDto.class);
           msg.setMessageType("QUIT");


           template.convertAndSend("/sub/chat/room/" + msg.getChatRoomId(), msg);

       } catch (JsonMappingException e) {
          log.error("Error {}", e.getMessage());
       } catch (JsonProcessingException e) {
           log.info("Error {}", e.getMessage());
       }
    }

    // 5) 사용자가 App을 끄거나, 방에서 나갔을 때, 그 EVENT를 듣고 실행하는 함수
    @EventListener
    @Transactional // 영속성을 위하여
    public void webSocketDisconnectListener(SessionDisconnectEvent event) {

        // 일단 저는 여기서 Event와 header의 log만 찍었는데, 여기에 자신이 원하는 내용을 실행하면 된다.

        log.info("DisConnEvent {}", event);

        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        log.info("headAccessor {}", headerAccessor);
    }



}
