package com.rgbitsoft.blog.vo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Accessors(chain=true)
@ToString
public abstract class LoginUserId {
    private String loginUserId;
    public LoginUserId() {

    }
    public LoginUserId(String loginUserId){
        this.loginUserId = loginUserId;
    }


}
