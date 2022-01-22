package com.socket.config.socket;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NettySocketIOEvent {
    private final SocketIOServer server;
    Logger logger = LoggerFactory.getLogger(NettySocketIOEvent.class);


    @Autowired
    public NettySocketIOEvent(SocketIOServer server) {
        this.server = server;
        server.start();
    }

    @OnEvent(value = "eMessage")
    public void onEvent(SocketIOClient client, AckRequest request, Object data) {
        server.getBroadcastOperations().sendEvent("bMessage", data);
    }
}
