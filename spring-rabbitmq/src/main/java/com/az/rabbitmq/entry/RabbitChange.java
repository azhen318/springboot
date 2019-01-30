package com.az.rabbitmq.entry;

public enum RabbitChange {

    CONTRACT_FANOUT("CONTRACT_FANOUT", "消息分发"),
    CONTRACT_TOPIC("CONTRACT_TOPIC", "消息订阅"),
    CONTRACT_DIRECT("CONTRACT_DIRECT", "点对点");

    private String code;

    private String text;

    RabbitChange(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
