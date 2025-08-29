package com.webproject.web.excepiton;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {

    private MessageType messageType;

    private String addMessage;

    public String preparedErrorMessage(){
        StringBuilder builder = new StringBuilder();
        builder.append(messageType.getMessage());
        if (addMessage != null){
            builder.append(" :"+addMessage);

        }
        return builder.toString();

    }




}
