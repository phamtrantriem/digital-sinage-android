package com.hadestrb.zibo.Model;

import androidx.annotation.NonNull;

public class RegisterDisplay {
    private String hardwareKey;
    private String displayName;
    private String clientType;
    private String clientVersion;
    private String clientCode;
    private String operatingSystem;
    private String macAddress;
    private String xmrChannel;
    private String xmrPubKey;

    public RegisterDisplay(String hardwareKey, String displayName, String clientType, String clientVersion, String clientCode, String operatingSystem, String macAddress, String xmrChannel, String xmrPubKey) {
        this.hardwareKey = hardwareKey;
        this.displayName = displayName;
        this.clientType = clientType;
        this.clientVersion = clientVersion;
        this.clientCode = clientCode;
        this.operatingSystem = operatingSystem;
        this.macAddress = macAddress;
        this.xmrChannel = xmrChannel;
        this.xmrPubKey = xmrPubKey;
    }

    public RegisterDisplay() {

    }

    public String getHardwareKey() {
        return hardwareKey;
    }

    public void setHardwareKey(String hardwareKey) {
        this.hardwareKey = hardwareKey;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getXmrChannel() {
        return xmrChannel;
    }

    public void setXmrChannel(String xmrChannel) {
        this.xmrChannel = xmrChannel;
    }

    public String getXmrPubKey() {
        return xmrPubKey;
    }

    public void setXmrPubKey(String xmrPubKey) {
        this.xmrPubKey = xmrPubKey;
    }

    @NonNull
    @Override
    public String toString() {
        return "RegisterDisplay{" +
                "hardwareKey='" + hardwareKey + '\'' +
                ", displayName='" + displayName + '\'' +
                ", clientType='" + clientType + '\'' +
                ", clientVersion='" + clientVersion + '\'' +
                ", clientCode='" + clientCode + '\'' +
                ", operatingSystem='" + operatingSystem + '\'' +
                ", macAddress='" + macAddress + '\'' +
                ", xmrChannel='" + xmrChannel + '\'' +
                ", xmrPubKey='" + xmrPubKey + '\'' +
                '}';
    }
}
