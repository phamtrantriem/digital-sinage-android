package com.hadestrb.zibo.Model.Schedule;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ScheduleItem {
    @SerializedName("$")
    @Expose
    private Schedule item;

    @SerializedName("dependents")
    @Expose
    private List<ScheduleDependents> dependents;

    public ScheduleItem() {
    }

    public ScheduleItem(Schedule item, List<ScheduleDependents> dependents) {
        this.item = item;
        this.dependents = dependents;
    }

    public Schedule getItem() {
        return item;
    }

    public void setItem(Schedule item) {
        this.item = item;
    }

    public List<ScheduleDependents> getDependents() {
        return dependents;
    }

    public void setDependents(List<ScheduleDependents> dependents) {
        this.dependents = dependents;
    }

    @NonNull
    @Override
    public String toString() {
        return "ScheduleItem{" +
                "item='" + item + '\'' +
                ", dependents=" + dependents +
                '}';
    }
}
