package com.bahao.websocket.entity;

import lombok.Data;

@Data
public class HelloMessage {

    private String name;

    private String id;

    private String chatId;

    private String fromUserId;

    private String toUserId;

    private String content;

    private String type;

    public HelloMessage() {
    }

    public HelloMessage(String name,String fromUserId,String toUserId,String content) {
        this.name = name;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
