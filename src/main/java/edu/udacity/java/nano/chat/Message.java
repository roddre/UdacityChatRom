package edu.udacity.java.nano.chat;

/**
 * WebSocket message model
 */
public class Message {
    // TODO: add message model.

    private String username;
    private String msg;
    private String type;
    private int onlineCount;

    public Message(){}

    public Message(String username, String msg, String type, int onlineCount) {
        this.username = username;
        this.msg = msg;
        this.type = type;
        this.onlineCount = onlineCount;
    }

    public String getUsername(){
        return username;
    }
    public void setUsername(String name){
        this.username = name;
    }
    public String getMsg(){
        return msg;
    }
    public void setMsg(String msg){
        this.msg = msg;
    }
    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type = type;
    }
    public void setOnlineCount(int onlineCount) {
       this.onlineCount = onlineCount;
    }
    public int getOnlineCount(){
        return onlineCount;
    }
}
