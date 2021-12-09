package com.project.zzbarber.service;

import com.project.zzbarber.model.TimeAvailable;
import com.project.zzbarber.model.TimeEnum;
import com.project.zzbarber.repository.TimeAvailableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class TimeAvailableService {
    private final TimeAvailableRepository timeAvailableRepository;

    @Autowired
    public TimeAvailableService(TimeAvailableRepository timeAvailableRepository) {
        this.timeAvailableRepository = timeAvailableRepository;
    }
    public List<TimeAvailable> checkTimeAvailable(Date date){
        return timeAvailableRepository.findTimeAvailableByDate(date);
    }
    public void delete(TimeAvailable timeAvailable){
        timeAvailableRepository.delete(timeAvailable);
    }
    public List<TimeAvailable> findTimeAvailable(Date date, TimeEnum timeEnum){
        return timeAvailableRepository.findTimeAvailableByDateAAndTimeEnum(date,timeEnum);
    }

    public void addTimeAvailable(Date date){

        TimeAvailable timeAvailable1= new TimeAvailable();
        timeAvailable1.setDate(date);
        timeAvailable1.setTime(TimeEnum.TimeEnum10_00);
        timeAvailableRepository.save(timeAvailable1);
        TimeAvailable timeAvailable2= new TimeAvailable();
        timeAvailable2.setDate(date);
        timeAvailable2.setTime(TimeEnum.TimeEnum10_30);
        timeAvailableRepository.save(timeAvailable2);
        TimeAvailable timeAvailable3= new TimeAvailable();
        timeAvailable3.setDate(date);
        timeAvailable3.setTime(TimeEnum.TimeEnum11_00);
        timeAvailableRepository.save(timeAvailable3);
        TimeAvailable timeAvailable4= new TimeAvailable();
        timeAvailable4.setDate(date);
        timeAvailable4.setTime(TimeEnum.TimeEnum11_30);
        timeAvailableRepository.save(timeAvailable4);
        TimeAvailable timeAvailable5= new TimeAvailable();
        timeAvailable5.setDate(date);
        timeAvailable5.setTime(TimeEnum.TimeEnum12_00);
        timeAvailableRepository.save(timeAvailable5);
        TimeAvailable timeAvailable6= new TimeAvailable();
        timeAvailable6.setDate(date);
        timeAvailable6.setTime(TimeEnum.TimeEnum14_00);
        timeAvailableRepository.save(timeAvailable6);
        TimeAvailable timeAvailable7= new TimeAvailable();
        timeAvailable7.setDate(date);
        timeAvailable7.setTime(TimeEnum.TimeEnum14_30);
        timeAvailableRepository.save(timeAvailable7);
        TimeAvailable timeAvailable8= new TimeAvailable();
        timeAvailable8.setDate(date);
        timeAvailable8.setTime(TimeEnum.TimeEnum15_00);
        timeAvailableRepository.save(timeAvailable8);
        TimeAvailable timeAvailable9= new TimeAvailable();
        timeAvailable9.setDate(date);
        timeAvailable9.setTime(TimeEnum.TimeEnum15_30);
        timeAvailableRepository.save(timeAvailable9);
        TimeAvailable timeAvailable10= new TimeAvailable();
        timeAvailable10.setDate(date);
        timeAvailable10.setTime(TimeEnum.TimeEnum16_00);
        timeAvailableRepository.save(timeAvailable10);
        TimeAvailable timeAvailable11= new TimeAvailable();
        timeAvailable11.setDate(date);
        timeAvailable11.setTime(TimeEnum.TimeEnum16_30);
        timeAvailableRepository.save(timeAvailable11);
        TimeAvailable timeAvailable12= new TimeAvailable();
        timeAvailable12.setDate(date);
        timeAvailable12.setTime(TimeEnum.TimeEnum17_00);
        timeAvailableRepository.save(timeAvailable12);
        TimeAvailable timeAvailable13= new TimeAvailable();
        timeAvailable13.setDate(date);
        timeAvailable13.setTime(TimeEnum.TimeEnum17_30);
        timeAvailableRepository.save(timeAvailable13);
        TimeAvailable timeAvailable14= new TimeAvailable();
        timeAvailable14.setDate(date);
        timeAvailable14.setTime(TimeEnum.TimeEnum18_00);
        timeAvailableRepository.save(timeAvailable14);
        TimeAvailable timeAvailable15= new TimeAvailable();
        timeAvailable15.setDate(date);
        timeAvailable15.setTime(TimeEnum.TimeEnum18_30);
        timeAvailableRepository.save(timeAvailable15);

    }
    public List<TimeAvailable> orderTimeAvailable (Date date){
        return timeAvailableRepository.orderTimeAvailable(date);
    }
    public List<TimeAvailable> notAvailable(Date date)
    {
        return timeAvailableRepository.notAvailableTime(date);
    }
    public void restoreTimeAvailable(Date date, TimeEnum timeEnum){
        TimeAvailable timeAvailable= new TimeAvailable();
        timeAvailable.setDate(date);
        timeAvailable.setTime(timeEnum);
        timeAvailableRepository.save(timeAvailable);

    }
}
