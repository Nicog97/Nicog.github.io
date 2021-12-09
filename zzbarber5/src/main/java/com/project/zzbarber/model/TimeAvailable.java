package com.project.zzbarber.model;

import javax.persistence.*;
import java.util.Date;

@Entity

public class TimeAvailable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    @Column
    private Date date;
    @Column
    private TimeEnum time;
    private boolean available;

    public TimeAvailable() {
        this.available = true;
    }

    public Long getId() {
        return id;
    }

    public void setDate(Long id) {
        this.id = id;
    }

    public TimeEnum getTime(){
        return this.time;
    }
    public Date getDate(){
        return this.date;
    }
    public void setDate(Date date){
        this.date=date;
    }
    public void setTime(TimeEnum time){
        this.time=time;
    }


    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
