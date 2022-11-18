package com.example.agro.Models;

public class Schedule {
    public String id;
    public long start,end;

    public Schedule() {
    }

    public Schedule(String id, long start, long end) {
        this.id = id;
        this.start = start;
        this.end = end;
    }

    public Schedule(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public String getId() {
        return id;
    }

    public long getStart() {
        return start;
    }

    public long getEnd() {
        return end;
    }
}
