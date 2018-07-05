package com.buffalo.fastdfs.model;

import com.buffalo.util.ModelUtil;

public class Attachment extends ModelUtil{
    private String id;
    private String order_id;
    private String order_type;
    private String key_array;

    public Attachment(){
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public String getKey_array() {
        return key_array;
    }

    public void setKey_array(String key_array) {
        this.key_array = key_array;
    }
}
