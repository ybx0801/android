package com.example.clock;

public class music {
    private String name;
    private String path;
    music(){}
    music(String path,String name){
        this.name=name;
        this.path=path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }
}
