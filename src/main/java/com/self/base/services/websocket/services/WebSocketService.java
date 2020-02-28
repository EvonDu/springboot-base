package com.self.base.services.websocket.services;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * WebSocket 服务类
 * 客户端连接地址为: ws://localhost:8080/ws/{id}
 * 其中路径上的 id 可作为参数获取
 */
@Component
@ServerEndpoint("/ws/{id}")
public class WebSocketService {
    /**
     * 客户端连接对象集合
     */
    private static CopyOnWriteArraySet<Session> SessionSet = new CopyOnWriteArraySet<>();

    /**
     * 事件 - 客户端连接
     * @param session       会话
     * @param id            URL参数:id
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("id") long id) {
        SessionSet.add(session);
        System.out.println("[" + session.getId() + "]<用户连接>:聊天室<" + id + ">");
    }

    /**
     * 事件 - 关闭连接
     * @param session   会话
     */
    @OnClose
    public void onClose(Session session) {
        SessionSet.remove(session);
        System.out.println("[" + session.getId() + "]<用户离开>");
    }

    /**
     * 事件 - 接收信息
     * @param message   内容
     * @param session   会话
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("[" + session.getId() + "]<接收信息>:" + message);
    }

    /**
     * 事件 - 发生错误
     * @param session   会话
     * @param error     错误
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("[" + session.getId() + "]<发生错误>:" + error);
    }

    /**
     * 方法 - 发送消息
     * @param session   会话
     * @param message   内容
     */
    public static void sendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 方法 - 群发消息
     * @param message   内容
     */
    public static void sendMessageAll(String message) {
        for (Session session : SessionSet) {
            if(session.isOpen()){
                sendMessage(session, message);
            }
        }
    }

    /**
     * 方法 - 指定ID发送消息
     * @param sessionId 会话ID
     * @param message   内容
     */
    public static void SendMessageById(String sessionId, String message) {
        Session session = null;
        for (Session s : SessionSet) {
            if(s.getId().equals(sessionId)){
                session = s;
                break;
            }
        }
        if(session!=null){
            sendMessage(session, message);
        }
        else{
            System.out.println("没有找到指定ID的会话: " + sessionId);
        }
    }
}
