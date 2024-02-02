package com.sp.mobileappliancesdirector;

public class Appliances {

    String MODEL, NAME;

    public Appliances(){}

    public Appliances(String MODEL, String NAME) {
        this.MODEL = MODEL;
        this.NAME = NAME;
    }

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
