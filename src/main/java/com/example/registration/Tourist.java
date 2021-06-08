package com.example.registration;

public class Tourist {
    private int tid;

    private String name,mobile;

    public Tourist(int tid, String name, String mobile) {
        this.tid = tid;
        this.name = name;
        this.mobile = mobile;

    }

    public int getTid() {
        return tid;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }


}
