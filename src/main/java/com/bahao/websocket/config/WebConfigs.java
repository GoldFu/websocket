package com.bahao.websocket.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/**
 * @Description: 启动配置bean文件
 * @Author GoldFu
 * @Date 2019/5/30
 * @Version V1.0
 **/
@Configuration
@EnableWebSocket
@Slf4j
public class WebConfigs implements  WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        System.out.println("---------------------------"+myHandler());
        registry.addHandler(myHandler(), "/ws/*")
                .addInterceptors(new HttpSessionHandshakeInterceptor())
                .setAllowedOrigins("/ws/*");
    }
    @Bean
    public WebSocketHandler myHandler() {
        return new UserConnectionHandler();
    }

}
