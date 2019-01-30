package com.az.rabbitmq.entry;

public enum RabbitQueue {

    TESTQUEUE("TESTQUEUE", "测试队列"),
    TOPICTEST1("TOPICTEST1", "topic测试队列"),
    TOPICTEST2("TOPICTEST2", "topic测试队列");

    private String code;
    private String name;

    RabbitQueue(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;

    }

}
