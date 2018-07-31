package com.jct.oshri.sunalarm.data;

import java.time.DayOfWeek;
import java.util.EnumSet;

public class SunAlarm {
    private String alarmName;
    private boolean enable;

    private RelativeTime relativeTime;
    private long time;
    private boolean repeatOfWeek;
    String ringtoneUri;
    private int volume;

    private byte days;


    //private EnumSet<Day> days;
    private EnumSet<AlarmType> alarmType;

    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public RelativeTime getRelativeTime() {
        return relativeTime;
    }

    public void setRelativeTime(RelativeTime relativeTime) {
        this.relativeTime = relativeTime;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isRepeatOfWeek() {
        return repeatOfWeek;
    }

    public void setRepeatOfWeek(boolean repeatOfWeek) {
        this.repeatOfWeek = repeatOfWeek;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }


    public EnumSet<AlarmType> getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(EnumSet<AlarmType> alarmType) {
        this.alarmType = alarmType;
    }
}
