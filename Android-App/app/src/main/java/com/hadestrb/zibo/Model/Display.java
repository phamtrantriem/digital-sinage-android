package com.hadestrb.zibo.Model;

public class Display {
    private String date;
    private String timezone;
    private String status;
    private String code;
    private String message;
    private String version_instructions;
    private String checkSchedule;
    private String checkRf;

    public Display() {
    }

    public Display(String date, String timezone, String status, String code, String message, String version_instructions, String checkSchedule, String checkRf) {
        this.date = date;
        this.timezone = timezone;
        this.status = status;
        this.code = code;
        this.message = message;
        this.version_instructions = version_instructions;
        this.checkSchedule = checkSchedule;
        this.checkRf = checkRf;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getVersion_instructions() {
        return version_instructions;
    }

    public void setVersion_instructions(String version_instructions) {
        this.version_instructions = version_instructions;
    }

    public String getCheckSchedule() {
        return checkSchedule;
    }

    public void setCheckSchedule(String checkSchedule) {
        this.checkSchedule = checkSchedule;
    }

    public String getCheckRf() {
        return checkRf;
    }

    public void setCheckRf(String checkRf) {
        this.checkRf = checkRf;
    }

    @Override
    public String toString() {
        return "Display{" +
                "date='" + date + '\'' +
                ", timezone='" + timezone + '\'' +
                ", status='" + status + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", version_instructions='" + version_instructions + '\'' +
                ", checkSchedule='" + checkSchedule + '\'' +
                ", checkRf='" + checkRf + '\'' +
                '}';
    }
}
