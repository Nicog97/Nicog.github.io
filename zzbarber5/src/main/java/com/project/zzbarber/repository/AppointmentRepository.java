package com.project.zzbarber.repository;

import com.project.zzbarber.model.Appointment;
import com.project.zzbarber.model.Customer;
import com.project.zzbarber.model.TimeAvailable;
import com.project.zzbarber.model.TimeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    @Query("SELECT a FROM Appointment a WHERE a.date = ?1 AND a.time=?2 and a.date>=current_date")
    List<Appointment> findAppointmentByDateAAndTime(Date date, TimeEnum timeEnum);
    @Query("SELECT a FROM Appointment a WHERE a.date = ?1 and a.date>=current_date")
    List<Appointment> findAppointmentByDate(Date date);
    @Query("select a from Appointment a where a.date>=current_date order by a.date, a.time")
    List<Appointment> orderAppointment(List<Appointment> appointments);
    @Query("select a from Appointment a where a.customer=?1 and a.date>=current_date order by a.date,a.time")
    List<Appointment> findByCustomer(Customer customer);
}
