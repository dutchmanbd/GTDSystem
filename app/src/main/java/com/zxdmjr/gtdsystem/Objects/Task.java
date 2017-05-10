package com.zxdmjr.gtdsystem.Objects;

/**
 * Created by dutchman on 3/28/17.
 */

public class Task {

    private String date;
    private String time;
    private String task;
    private String suggestingSolution;


    public Task(String task, String suggestingSolution){

        this.setTask(task);
        this.setSuggestingSolution(suggestingSolution);
    }

    public Task(String date, String time, String task, String suggestingSolution) {

        this.setDate(date);
        this.setTime(time);
        this.setTask(task);
        this.setSuggestingSolution(suggestingSolution);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getSuggestingSolution() {
        return suggestingSolution;
    }

    public void setSuggestingSolution(String suggestingSolution) {
        this.suggestingSolution = suggestingSolution;
    }
}
