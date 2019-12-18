package com.bahao.websocket.ctrl;


import com.alibaba.fastjson.JSONObject;
import com.bahao.websocket.config.UserConnectionHandler;
import com.bahao.websocket.entity.Greeting;
import com.bahao.websocket.entity.HelloMessage;
import netscape.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
public class GreetingCtrl {
    @Autowired
    private SimpMessageSendingOperations simpMessageSendingOperations;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    //convertAndSend(destination, payload);            //将消息广播到特定订阅路径中，类似@SendTo
    //convertAndSendToUser(user, destination, payload);//将消息推送到固定的用户订阅路径中，类似@SendToUser

//
//    @MessageMapping("/hello")//@MessageMapping和@RequestMapping功能类似，用于设置URL映射地址，浏览器向服务器发起请求，需要通过该地址。
//    @SendTo("/topic/greetings")//如果服务器接受到了消息，就会对订阅了@SendTo括号中的地址传送消息。
//    public Greeting greeting(HelloMessage message) throws Exception {
//        System.out.println("=================" + message.getName());
//        Thread.sleep(1000); // simulated delay
//        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
//    }
    //发送消息给指定用户
private UserConnectionHandler userConnectionHandler =new UserConnectionHandler();

    /**
     * //如果服务器接受到了消息，就会对订阅了@SendTo括号中的地址传送消息。
     *
     * @param greeting
     * @return
     * @throws Exception
     */
    @MessageMapping("/group")//@MessageMapping和@RequestMapping功能类似，用于设置URL映射地址，浏览器向服务器发起请求，需要通过该地址。
    @SendTo("/topic/group")
    public Greeting Group(@RequestBody @Valid Greeting greeting) throws Exception {
        Thread.sleep(1000);//延迟
        JSONObject obj = new JSONObject();
        obj.put("data", "返回了" + greeting);

//        boolean hasSend = userConnectionHandler.sendMessageToUser(12345, textMessage);
//        System.out.println(hasSend);
//        Message message = new Message();
//        if (hasSend) {
//            message.setStatus(1);
//            message.setMessage("成功接收");
//        }else{
//            message.setStatus(0);
//            message.setMessage("接收失败");
//        }
        //  simpMessageSendingOperations.convertAndSend("/topic/group", greeting);
        //messagingTemplate.convertAndSend("/topic/group", greeting);
        return greeting;
    }
//    /**
//     * 广播消息，不指定用户，所有订阅此的用户都能收到消息
//     * @param greeting
//     */
//    @MessageMapping("/group")
//   // @SendTo("/topic/group")
//    public void broadcast(@RequestBody @Valid Greeting greeting) {
//        System.out.println("----------------------------------------------"+greeting.toString());
//        simpMessageSendingOperations.convertAndSend("/topic/group", greeting);
//    }

    /**
     * 这里用的是@SendToUser，这就是发送给单一客户端的标志。本例中，
     * 客户端接收一对一消息的主题应该是“/user/” + 用户Id + “/message” ,这里的用户id可以是一个普通的字符串，只要每个用户端都使用自己的id并且服务端知道每个用户的id就行。
     *
     * @return
     */
//    @MessageMapping("/message")
//    @SendToUser("/message")
//    public Greeting handleSubscribe() {
//        System.out.println("this is the @SubscribeMapping('/marco')");
//        return new Greeting("I am a msg from SubscribeMapping('/macro').");
//    }
//
//    /**
//     * @param message
//     * @return
//     * @throws Exception
//     * @MessageMapping和@RequestMapping功能类似，用于设置URL映射地址，浏览器向服务器发起请求，需要通过该地址。
//     */
    @MessageMapping("/single")
    public JSONObject Single(Principal principal, @RequestBody @Valid HelloMessage message) throws Exception {
        message.setName(message.getToUserId());
        System.out.println("========这个是推送给=========" + message.getToUserId());
        System.out.println("----------------------------------------------");
        System.out.println("========这个是自己的id=========" + message.getFromUserId());
        //   messagingTemplate.convertAndSendToUser(message.getToUserId(), "/user/single", "服武器主动推送的点对点消息");
        messagingTemplate.convertAndSendToUser(message.getToUserId(), "/single", message.getContent());
        Thread.sleep(1000);
        JSONObject obj = new JSONObject();
        obj.put("data", "返回了" + message);
        return obj;
    }

    /**
     * 为特定用户指定目的地，最后消息会被发布在 /user/queue/shouts
     *
     * @param message
     */
//    @MessageMapping("/single")
//    @SendToUser("/topic/single")
//    public void singleUser(@RequestBody @Valid HelloMessage message) {
//        System.out.println("----------------------------------------------");
//        System.out.println("========这个是自己的id=========" + message.getFromUserId());
//        //    mSession.getBasicRemote().sendText(message.getFromUserId() + ":"+message.getContent());
//        messagingTemplate.convertAndSendToUser("456", "/single", "Message");
//        messagingTemplate.convertAndSendToUser("456", "/topic/single", message);
//        messagingTemplate.convertAndSendToUser("456", "/queue/single", "服武器主动推送的点对点消息", createHeaders("456"));
//    }
//    @MessageMapping("/single")
//    @SendToUser("/topic/single")
//    public HelloMessage userStomp(Principal principal, @RequestBody @Valid HelloMessage message) {
//        System.out.println("========这个是推送给=========" + message.getToUserId());
//        System.out.println("----------------------------------------------");
//        System.out.println("========这个是自己的id=========" + message.getFromUserId());
//        System.out.println("认证的名字是：{}，收到的消息是：{}"+principal.getName()+message);
//        return message;
//    }


}
