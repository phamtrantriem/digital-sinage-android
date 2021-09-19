package com.hadestrb.zibo.Model.Schedule;

public class ScheduleLayout {
    private ScheduleItem item;

    public ScheduleLayout() {
    }

    public ScheduleLayout(ScheduleItem item) {
        this.item = item;
    }

    public ScheduleItem getItem() {
        return item;
    }

    public void setItem(ScheduleItem item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "ScheduleLayout{" +
                "item=" + item +
                '}';
    }
}
