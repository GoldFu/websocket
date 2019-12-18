var stompClient = null;

//连接状态
function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        //显示
        $("#conversation").show();
    } else {
        //隐藏
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        stompClient.subscribe('/user/200/single', function (greeting) {
            console.log(greeting+"获取返回的数据=========" , 666666666666666)
            //动态添加到面板上去
            //showGreeting(JSON.parse(greeting.body).content);
            showGreeting(greeting.body+"----------"+"我是:");
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    var obj = {"fromUserId":  $("#fromUserId").val(), "toUserId": $("#toUserId").val(), "content": $("#content").val()};
    console.log(JSON.stringify(obj)+"发送的消息")
    stompClient.send("/user/single", {}, JSON.stringify(obj));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });

    $("#send").click(function () {
        console.log("已经发送数据了");
        sendName();
    });
});

