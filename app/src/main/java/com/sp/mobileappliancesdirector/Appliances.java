package com.sp.mobileappliancesdirector;

public class Appliances {

    String MODEL, NAME, IMAGE_URL;

    public Appliances(){}

    public Appliances(String MODEL, String NAME, String IMAGE_URL) {
        this.MODEL = MODEL;
        this.NAME = NAME;
        this.IMAGE_URL = IMAGE_URL;
    }

    public String getIMAGE_URL() { return IMAGE_URL; }

    public void setIMAGE_URL(String IMAGE_URL) { this.IMAGE_URL = IMAGE_URL; }

    public String getMODEL() {
        return MODEL;
    }

    public void setMODEL(String MODEL) {
        this.MODEL = MODEL;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }
}
