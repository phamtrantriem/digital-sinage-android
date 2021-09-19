package com.hadestrb.zibo.Model.Schedule;

import android.text.Layout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hadestrb.zibo.Model.MetaData;

import java.util.List;

public class ScheduleWrapper {
    @SerializedName("$")
    @Expose
    private MetaData metaData;

    @SerializedName("layout")
    @Expose
    private List sLayout = null;

    @SerializedName("default")
    @Expose
    private List sDefault = null;

    public ScheduleWrapper() {
    }

    public ScheduleWrapper(MetaData metaData, List sLayout, List sDefault) {
        this.metaData = metaData;
        this.sLayout = sLayout;
        this.sDefault = sDefault;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public List getsLayout() {
        return sLayout;
    }

    public void setsLayout(List sLayout) {
        this.sLayout = sLayout;
    }

    public List getsDefault() {
        return sDefault;
    }

    public void setsDefault(List sDefault) {
        this.sDefault = sDefault;
    }

    @Override
    public String toString() {
        return "ScheduleWrapper{" +
                "metaData=" + metaData +
                ", sLayout=" + sLayout +
                ", sDefault=" + sDefault +
                '}';
    }
}
