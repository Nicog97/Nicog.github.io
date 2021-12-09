package com.project.zzbarber.service;

import com.project.zzbarber.model.Appointment;
import com.project.zzbarber.model.Customer;
import com.project.zzbarber.model.TimeAvailable;
import com.project.zzbarber.model.TimeEnum;
import com.project.zzbarber.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Component
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }
    public List<Appointment> checkAppointment(Date date) {
        return appointmentRepository.findAppointmentByDate(date);
    }
        public List<Appointment> findAppointment(Date date, TimeEnum timeEnum){
            return appointmentRepository.findAppointmentByDateAAndTime(date,timeEnum);
        }
    public void delete(Appointment appointment ){
        appointmentRepository.delete(appointment);
    }
    public void addAppointment(Appointment appointment) {

            List<Appointment> appointments=appointmentRepository.findAppointmentByDateAAndTime(appointment.getDate(), appointment.getTime());
            if(appointments.isEmpty()){
                appointmentRepository.save(appointment);
            }

    }
    public List<Appointment> orderApp (List<Appointment> appointments){
        return appointmentRepository.orderAppointment(appointments);
    }
    public List<Appointment> findByCustomer (Customer customer){
        return appointmentRepository.findByCustomer(customer);
    }

    public Appointment getById(Long appointmentId) {
        return appointmentRepository.getById(appointmentId);
    }



    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }




    public Appointment create(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }


    public Appointment update(Long appointmentId, Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public void deleteById(Long appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }
}
