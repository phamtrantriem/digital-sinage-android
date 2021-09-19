package com.hadestrb.zibo.Model.Schedule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Schedule {
    @SerializedName("file")
    @Expose
    private String file;

    @SerializedName("fromdt")
    @Expose
    private String fromdt;

    @SerializedName("todt")
    @Expose
    private String todt;

    @SerializedName("scheduleid")
    @Expose
    private String scheduleid;

    @SerializedName("priority")
    @Expose
    private String priority;

    @SerializedName("syncEvent")
    @Expose
    private String syncEvent;

    @SerializedName("shareOfVoice")
    @Expose
    private String shareOfVoice;

    @SerializedName("duration")
    @Expose
    private String duration;

    @SerializedName("isGeoAware")
    @Expose
    private String isGeoAware;

    @SerializedName("geoLocation")
    @Expose
    private String geoLocation;

    public Schedule() {
    }

    public Schedule(String file, String fromdt, String todt, String scheduleid, String priority, String syncEvent, String shareOfVoice, String duration, String isGeoAware, String geoLocation) {
        this.file = file;
        this.fromdt = fromdt;
        this.todt = todt;
        this.scheduleid = scheduleid;
        this.priority = priority;
        this.syncEvent = syncEvent;
        this.shareOfVoice = shareOfVoice;
        this.duration = duration;
        this.isGeoAware = isGeoAware;
        this.geoLocation = geoLocation;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getFromdt() {
        return fromdt;
    }

    public void setFromdt(String fromdt) {
        this.fromdt = fromdt;
    }

    public String getTodt() {
        return todt;
    }

    public void setTodt(String todt) {
        this.todt = todt;
    }

    public String getScheduleid() {
        return scheduleid;
    }

    public void setScheduleid(String scheduleid) {
        this.scheduleid = scheduleid;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getSyncEvent() {
        return syncEvent;
    }

    public void setSyncEvent(String syncEvent) {
        this.syncEvent = syncEvent;
    }

    public String getShareOfVoice() {
        return shareOfVoice;
    }

    public void setShareOfVoice(String shareOfVoice) {
        this.shareOfVoice = shareOfVoice;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getIsGeoAware() {
        return isGeoAware;
    }

    public void setIsGeoAware(String isGeoAware) {
        this.isGeoAware = isGeoAware;
    }

    public String getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(String geoLocation) {
        this.geoLocation = geoLocation;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "file='" + file + '\'' +
                ", fromdt='" + fromdt + '\'' +
                ", todt='" + todt + '\'' +
                ", scheduleid='" + scheduleid + '\'' +
                ", priority='" + priority + '\'' +
                ", syncEvent='" + syncEvent + '\'' +
                ", shareOfVoice='" + shareOfVoice + '\'' +
                ", duration='" + duration + '\'' +
                ", isGeoAware='" + isGeoAware + '\'' +
                ", geoLocation='" + geoLocation + '\'' +
                '}';
    }
}
