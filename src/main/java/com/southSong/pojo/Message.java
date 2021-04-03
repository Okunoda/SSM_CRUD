package com.southSong.pojo;

import java.util.HashMap;
import java.util.Map;


//创建一个自定义的返回类型，之后前端使用js和ajax自行解析，使得程序具有通用性
public class Message {
    private Integer code;
    private Map<String , Object> extend = new HashMap<>();
    private String mes;

    @Override
    public String toString() {
        return "Message{" +
                "code=" + code +
                ", extend=" + extend +
                ", mes='" + mes + '\'' +
                '}';
    }

    public static Message success(){
        Message mes = new Message();
        mes.setMes("处理成功！");
        mes.setCode(100);
        return mes;
    }
    public static Message defeat(){
        Message mes = new Message();
        mes.setMes("处理失败！");
        mes.setCode(200);
        return mes;
    }

    public Message add(String key , Object value){
        this.getExtend().put(key, value);
        return this;
    }


    public String getMes() {
        return mes;
    }
    public void setMes(String mes) {
        this.mes = mes;
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
}
