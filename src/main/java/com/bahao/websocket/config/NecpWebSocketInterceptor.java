package com.bahao.websocket.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;
@Slf4j
public class NecpWebSocketInterceptor implements HandshakeInterceptor {

    /**
     * 在握手之前执行该方法, 继续握手返回true, 中断握手返回false. 通过attributes参数设置WebSocketSession的属性
     * @param request
     * @param response
     * @param handle
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler handle, Map<String, Object> map) throws Exception {
        log.info("==============啦啦啦，握手开始啦=================");
        return true;
    }

    /**
     * 在握手之后执行该方法. 无论是否握手成功都指明了响应状态码和相应头.
     * @param request
     * @param response
     * @param handle
     * @param e
     */
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler handle, Exception e) {
//        logger.info("啦啦啦，握手成功啦");
        log.info("==============啦啦啦，握手成功啦=================");
    }
}