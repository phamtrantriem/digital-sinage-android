package com.hadestrb.zibo.Model.RequiredFile;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FileItem {
    @SerializedName("$")
    @Expose
    private File file;

    public FileItem() {
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public FileItem(File file) {
        this.file = file;
    }

    @NonNull
    @Override
    public String toString() {
        return "FileItem{" +
                "file=" + file +
                '}';
    }
}
