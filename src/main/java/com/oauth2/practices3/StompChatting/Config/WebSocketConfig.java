package com.oauth2.practices3.StompChatting.Config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
@Slf4j
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // 1) Socket이 열릴 주소를 정한다.
    //    접근 허용은 어떻게 할 것인지, SockJs를 통한 Http -> ws 변환도 허용할 것인지 정한다.
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-stomp")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }

    @Override
    // 2) 구독 요청과 발행 요청을 구분하는 접두사를 설정
    public void configureMessageBroker(MessageBrokerRegistry registry){

        // 2-1) 클라이언트에서 "sub/~~특정 주소 A" 로 subscribe 요청을 보내면 해당 주소를 '구독'하게 된다.
        //      이제 주소 A로 메세지가 발행될 때마다 그 메세지를 받아볼 수 있게 되는 것이다.
        //      신문 구독하는 거랑 시스템이 같다.
        registry.enableSimpleBroker("/sub");

        // 2-2) 클라이언트에서 "pub/~~특정 주소 A"로 publish 요청을 보내게 되면
        //      해당 주소 A로 메세지를 보내게 된다.
        //      이제 보내진 메세지는 BackEnd에서 특정 작업을 거치고
        //      주소 A를 구독하고 있는 모든 구독자들에게 해당 메세지가 전송된다.
        registry.setApplicationDestinationPrefixes("/pub");

    }

    // 3) 메세지 크기 제한 오류 방지(이 코드가 없으면 base64로 보낼때 소켓 연결이 끊길 수 있음)
    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
        registration.setMessageSizeLimit(1024*1024*1024);
    }

    @Bean
    public ServletServerContainerFactoryBean createServletServerContainerFactoryBean() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(Integer.MAX_VALUE);
        container.setMaxBinaryMessageBufferSize(Integer.MAX_VALUE);
        log.info("Websocket factory returned");
        return container;
    }


}
