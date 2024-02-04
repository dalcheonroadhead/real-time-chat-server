package com.oauth2.practices3.StompChatting.Dto.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseDto<T> {

    private T data;
    private String msg;


    @Builder
    public ApiResponseDto(T data, String msg){
        this.data = data;
        this.msg = msg;
    }

}
