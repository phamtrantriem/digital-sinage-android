package com.hadestrb.zibo.Model.RequiredFile;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hadestrb.zibo.Model.MetaData;

import java.util.List;

public class FileWrapper {
    @SerializedName("$")
    @Expose
    private MetaData metaData;

    @SerializedName("file")
    @Expose
    private List<FileItem> listFiles = null;

    public FileWrapper() {
    }

    public FileWrapper(MetaData metaData, List<FileItem> listFiles) {
        this.metaData = metaData;
        this.listFiles = listFiles;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public List<FileItem> getListFiles() {
        return listFiles;
    }

    public void setListFiles(List<FileItem> listFiles) {
        this.listFiles = listFiles;
    }

    @NonNull
    @Override
    public String toString() {
        return "FileWrapper{" +
                "metaData=" + metaData +
                ", listFiles=" + listFiles +
                '}';
    }
}
