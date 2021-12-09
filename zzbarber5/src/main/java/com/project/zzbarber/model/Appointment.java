package com.project.zzbarber.model;

import com.project.zzbarber.AppointmentStatus;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity

public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @Column
    private Date date;

    @Column
    private TimeEnum time;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer", nullable = false)
    private Customer customer;
    public Long getId() {
        return id;
    }

    public void setDate(Long id) {
        this.id = id;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public TimeEnum getTime() {
        return time;
    }

    public void setTime(TimeEnum time) {
        this.time = time;
    }

}
