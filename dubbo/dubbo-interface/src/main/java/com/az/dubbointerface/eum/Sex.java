package com.az.dubbointerface.eum;

import lombok.Data;

public enum Sex {

    Male("男性",1),Female("女性",0);

    private String sex;

    private Integer code;


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    Sex(String sex, Integer code){
        this.sex=sex;
        this.code=code;
    }

    public Sex getSexByCode(Integer code){
        Sex[] sexs=Sex.values();
        for (Sex sex:sexs
             ) {
            if(sex.getCode().equals(code))
                return sex;
        }

        return null;
    }

}
