package com.project.zzbarber.repository;

import com.project.zzbarber.model.Appointment;
import com.project.zzbarber.model.CompositeKey;
import com.project.zzbarber.model.TimeAvailable;
import com.project.zzbarber.model.TimeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TimeAvailableRepository extends JpaRepository<TimeAvailable, Long > {
    @Query("SELECT t FROM TimeAvailable t WHERE t.date = ?1 ")
    List<TimeAvailable> findTimeAvailableByDate(Date date);
    @Query("SELECT t FROM TimeAvailable t WHERE t.date = ?1 and t.time=?2")
    List<TimeAvailable> findTimeAvailableByDateAAndTimeEnum(Date date, TimeEnum timeEnum);
    @Query("select t from TimeAvailable t where t.date=?1 and t.available=true order by  t.time")
    List<TimeAvailable> orderTimeAvailable(Date date);
    @Query("select t from TimeAvailable t where t.date=?1 and t.date>=current_date and t.available=false order by  t.time")
    List<TimeAvailable> notAvailableTime (Date date);
}
