package com.hadestrb.zibo.Model.RequiredFile;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class File {
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("md5")
    @Expose
    private String md5;
    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("saveAs")
    @Expose
    private String saveAs;
    @SerializedName("download")
    @Expose
    private String download;

    public File() {
    }

    public File(String type, String id, String size, String md5, String path, String saveAs, String download) {
        this.type = type;
        this.id = id;
        this.size = size;
        this.md5 = md5;
        this.path = path;
        this.saveAs = saveAs;
        this.download = download;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSaveAs() {
        return saveAs;
    }

    public void setSaveAs(String saveAs) {
        this.saveAs = saveAs;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    @NonNull
    @Override
    public String toString() {
        return "File{" +
                "type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", size='" + size + '\'' +
                ", md5='" + md5 + '\'' +
                ", path='" + path + '\'' +
                ", saveAs='" + saveAs + '\'' +
                ", download='" + download + '\'' +
                '}';
    }
}
