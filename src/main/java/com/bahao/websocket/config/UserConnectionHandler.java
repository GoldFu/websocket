package com.bahao.websocket.config;

import com.alibaba.fastjson.JSONObject;
import com.bahao.websocket.entity.MessageEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 *
 */
@Slf4j
public class UserConnectionHandler extends TextWebSocketHandler {

    /**
     * 建立连接后触发的回调
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.warn("用户连接成功->{}",session);
        System.out.println("-------------------"+"用户连接成功->{}"+session);
    }

    /**
     * 收到消息时触发的回调
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        TextMessage textMessage = (TextMessage)message;
        log.warn("收到消息->{}",textMessage);
        System.out.println("-------------------"+"收到消息->{}"+textMessage);
        MessageEntity messageEntity = JSONObject.parseObject(textMessage.getPayload(), MessageEntity.class);
        WebSocketCountUtil.onlineCountAdd(session,messageEntity.getContent());
    }

    /**
     * 传输消息出错时触发的回调
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println("传输消息出错"+"afterConnectionClosed");
        System.out.println("-------------------"+"传输消息出错->{}"+"afterConnectionClosed");
        System.out.println(session);
    }

    /**
     * 断开连接后触发的回调
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        log.warn("断开连接->{}",session);
        System.out.println("-------------------"+"断开连接->{}"+"afterConnectionClosed");
        WebSocketCountUtil.onlineCountReduce(session);
    }

    /**
     * 是否处理分片消息
     */
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
