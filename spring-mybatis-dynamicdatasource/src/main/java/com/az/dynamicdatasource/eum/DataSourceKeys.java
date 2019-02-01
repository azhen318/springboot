package com.az.dynamicdatasource.eum;

public enum DataSourceKeys {

    master("master"),salveA("salveA");


    private String name;

    DataSourceKeys(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
