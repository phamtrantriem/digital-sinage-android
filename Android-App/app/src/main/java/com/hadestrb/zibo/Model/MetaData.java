package com.hadestrb.zibo.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MetaData {
    @SerializedName("generated")
    @Expose
    private String generated;
    @SerializedName("fitlerFrom")
    @Expose
    private String fitlerFrom;
    @SerializedName("fitlerTo")
    @Expose
    private String fitlerTo;

    public MetaData() {
    }

    public MetaData(String generated, String fitlerFrom, String fitlerTo) {
        this.generated = generated;
        this.fitlerFrom = fitlerFrom;
        this.fitlerTo = fitlerTo;
    }

    public String getGenerated() {
        return generated;
    }

    public void setGenerated(String generated) {
        this.generated = generated;
    }

    public String getFitlerFrom() {
        return fitlerFrom;
    }

    public void setFitlerFrom(String fitlerFrom) {
        this.fitlerFrom = fitlerFrom;
    }

    public String getFitlerTo() {
        return fitlerTo;
    }

    public void setFitlerTo(String fitlerTo) {
        this.fitlerTo = fitlerTo;
    }

    @Override
    public String toString() {
        return "MetaData{" +
                "generated='" + generated + '\'' +
                ", fitlerFrom='" + fitlerFrom + '\'' +
                ", fitlerTo='" + fitlerTo + '\'' +
                '}';
    }
}
