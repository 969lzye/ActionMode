package com.example.actionmode;

import android.widget.BaseAdapter;

public class Item {
    private String name;
    private Boolean bo;
    public Item(){
        super();
    }
    public Item(String name,Boolean bo){
        super();
        this.name=name;
        this.bo=bo;
    }
    public String getName(){
        return name;
    }
    public Boolean getBo(){
        return bo;
    }
    public Boolean isBo(){
        return bo;
    }
    public void setBo(Boolean bo){
        this.bo=bo;
    }
}
