package com.wxb.springbootlearnwxb.test;

/**
 * @author wangxianbing
 * @date 2021-08-03 16:44:50
 */
public enum OrderType {
    ONE("1","one"),
    TWO("2","two");

    private String code;
    private String name;

    OrderType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static OrderType getEnum(String code){
        for (OrderType orderType : OrderType.values()) {
            if(orderType.getCode().equals(code)){
                return orderType;
            }
        }
        return null;
    }
}
