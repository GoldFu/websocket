package com.bahao.websocket.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 注解用于开启使用STOMP协议来传输基于代理（MessageBroker）的消息，这时候控制器（controller）开始支持@MessageMapping,就像是使用@requestMapping一样。
 *
 * @author GoldFu
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //配置消息代理（MessageBroker）。
        //定义了一个（或多个）客户端订阅地址的前缀信息，也就是客户端接收服务端发送消息的前缀信息
        //registry.enableSimpleBroker("/topic", "/user");这句话表示在topic和user这两个域上可以向客户端发消息。
        config.enableSimpleBroker("/topic", "queue", "/user");
        //这句话表示客户单向服务器端发送时的主题上面需要加"/app"作为前缀。
        //// 点对点使用的订阅前缀（客户端订阅路径上会体现出来），不设置的话，默认也是/user/
        config.setApplicationDestinationPrefixes("/user");
        // 点对点使用的订阅前缀（客户端订阅路径上会体现出来），不设置的话，默认也是/user/
        //registry.setUserDestinationPrefix("/user/");


    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //并指定使用SockJS协议。
        //这个和客户端创建连接时的url有关，其中setAllowedOrigins()方法表示允许连接的域名，withSockJS()方法表示支持以SockJS方式连接服务器。
        registry.addEndpoint("/websocket")
                //拦截器
                .addInterceptors(new NecpWebSocketInterceptor())
                // //跨域
                .setAllowedOrigins("*")
                //使用sockJS
                .withSockJS();
    }
}
