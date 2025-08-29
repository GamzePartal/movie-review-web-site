package com.webproject.web.excepiton;

import lombok.Getter;

@Getter
public enum MessageType {

    NO_RECORD_EXIST("1001","kayıt bulunamadı"),
    GENERAL_EXCEPTION("1002","Genel bir hata oluştu");

    String code;
    String message;

    MessageType(String code, String message){
        this.code=code;
        this.message=message;
    }


}
