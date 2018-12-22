package com.example.dhanashri.mukteshwarigurupeethwardh;


public class AnnualMemberPlanet {

    String NAME;
    String MOBILE_NO;
    String CITY;

    public AnnualMemberPlanet(String name, String mobile_no, String city){

        this.NAME=name;
        this.MOBILE_NO=mobile_no;
        this.CITY=city;

    }

    public String getNAME()
    {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getMOBILE_NO() {
        return MOBILE_NO;
    }

    public void setMOBILE_NO(String MOBILE_NO) {
        this.MOBILE_NO = MOBILE_NO;
    }

    public String getCITY() {
        return CITY;
    }

    public void setCITY(String CITY) {
        this.CITY = CITY;
    }
}
