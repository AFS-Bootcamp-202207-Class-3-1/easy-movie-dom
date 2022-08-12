package com.oocl.easymovie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class CustomizeWebSocketHandler extends TextWebSocketHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        log.info("与客户端建立连接...");
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        session.close(CloseStatus.SERVER_ERROR);
        log.error("连接异常", exception);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        log.info("与客户端断开连接...");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 获取客户端发送的消息
        String msg = message.getPayload();
        log.info("客户端发送消息:{}", msg);
        // 发送消息给客户端
        session.sendMessage(new TextMessage(msg));
        // 关闭连接
        // session.close(CloseStatus.NORMAL);
    }
}