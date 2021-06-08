package com.example.registration;

public class Homelist {
    private int pid;
    private String boatname,image,packagee,desccription,rate,ownerName;

    public Homelist(int pid, String boatname, String image, String packagee, String desccription, String rate, String ownerName) {
        this.pid = pid;
        this.boatname = boatname;
        this.image = image;
        this.packagee = packagee;
        this.desccription = desccription;
        this.rate = rate;
        this.ownerName=ownerName;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getBoatname() {
        return boatname;
    }

    public void setBoatname(String boatname) {
        this.boatname = boatname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPackagee() {
        return packagee;
    }

    public void setPackagee(String packagee) {
        this.packagee = packagee;
    }

    public String getDesccription() {
        return desccription;
    }

    public void setDesccription(String desccription) {
        this.desccription = desccription;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}