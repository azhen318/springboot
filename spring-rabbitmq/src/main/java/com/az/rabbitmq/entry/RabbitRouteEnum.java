package com.az.rabbitmq.entry;

public enum RabbitRouteEnum {
    TESTQUEUE("TESTQUEUE1", "测试队列key"),
    TESTTOPICQUEUE1("*.TEST.*", "topic测试队列key"),
    TESTTOPICQUEUE2("lazy.#", "topic测试队列key");


    private String code;
    private String name;

    RabbitRouteEnum(String code, String name) {
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
