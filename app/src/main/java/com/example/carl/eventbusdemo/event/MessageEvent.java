package com.example.carl.eventbusdemo.event;
//3.创建发送消息类
public class MessageEvent {
    public String name;

    public MessageEvent(String name) {
        this.name = name;
    }
}
