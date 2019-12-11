package com.example.clock;

public class clock {
    private String hour;
    private String minute;
    private String path;
    private String clock_switch;
    clock(){}
    clock(String hour,String minute,String path,String clock_switch){
        this.hour=hour;
        this.minute=minute;
        this.path=path;
        this.clock_switch=clock_switch;
    }

    public String getHour() {
        return hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public void setClock_switch(String clock_switch) {
        this.clock_switch = clock_switch;
    }

    public String isClock_switch() {
        return clock_switch;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
