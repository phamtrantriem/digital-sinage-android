package com.hadestrb.zibo.Model.Schedule;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ScheduleDependents {
    @SerializedName("file")
    @Expose
    private List<String> file;

    public ScheduleDependents() {
    }

    public ScheduleDependents(List<String> file) {
        this.file = file;
    }

    public List<String> getFile() {
        return file;
    }

    public void setFile(ArrayList<String> file) {
        this.file = file;
    }

    @NonNull
    @Override
    public String toString() {
        return "ScheduleDependents{" +
                "file=" + file +
                '}';
    }
}
