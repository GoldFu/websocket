package com.bahao.websocket.entity;

import java.security.Principal;

public class WebsocketUserVO implements Principal {
    private  String id;
    public WebsocketUserVO(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return id;
    }
}
