package edu.udacity.java.nano.chat;

import com.alibaba.fastjson.JSON;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.json.JsonParser;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket Server
 *
 * @see ServerEndpoint WebSocket Client
 * @see Session   WebSocket Session
 */

@Component
@ServerEndpoint("/chat")
public class WebSocketChatServer {

    /**
     * All chat sessions.
     */
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();

    private static void sendMessageToAll(String msg) {
        //TODO: add send message method.
        try {
            for (Session x : onlineSessions.values()) {
                x.getBasicRemote().sendText(msg);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Open connection, 1) add session, 2) add user.
     */
    @OnOpen
    public void onOpen(Session session) {
        //TODO: add on open connection.
        onlineSessions.put(session.getId(), session);
        Message message = new Message(null, "Entering", Type.ENTER, onlineSessions.size());
        message.setUsername(session.getId());
        String jsonMsg = JSON.toJSONString(message);
        sendMessageToAll(jsonMsg);
    }

    /**
     * Send message, 1) get username and session, 2) send message to all.
     */
    @OnMessage
    public void onMessage(Session session, String jsonStr) {
        //TODO: add send message.
        Message message = JSON.parseObject(jsonStr, Message.class);
        message.setType(Type.SPEAK);
        message.setOnlineCount(onlineSessions.values().size());
        String jsonMsg = JSON.toJSONString(message);
        sendMessageToAll(jsonMsg);
    }

    /**
     * Close connection, 1) remove session, 2) update user.
     */
    @OnClose
    public void onClose(Session session) {
        //TODO: add close connection.
        Message message = new Message(null, "Has left the chat", Type.SPEAK, onlineSessions.size()-1);
        onlineSessions.remove(session.getId());
        sendMessageToAll(JSON.toJSONString(message));
    }

    /**
     * Print exception.
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

}
