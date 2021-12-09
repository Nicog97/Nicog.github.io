package com.project.zzbarber.controller;

import com.project.zzbarber.model.Appointment;
import com.project.zzbarber.model.Customer;
import com.project.zzbarber.model.TimeAvailable;
import com.project.zzbarber.model.TimeEnum;
import com.project.zzbarber.service.AppointmentService;
import com.project.zzbarber.service.TimeAvailableService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/confirm")
public class BookingController {
    private final TimeAvailableService timeAvailableService;
    private final AppointmentService appointmentService;

    public BookingController(TimeAvailableService timeAvailableService, AppointmentService appointmentService) {
        this.timeAvailableService = timeAvailableService;
        this.appointmentService = appointmentService;
    }
    @GetMapping
    public String bookingAppointment(Model model, HttpServletRequest request) throws IOException {

        Customer customer=(Customer) request.getSession().getAttribute("customer");
        List<Appointment> appointments= appointmentService.findByCustomer(customer);

        model.addAttribute("appointments", appointments);
        return "confirm";

    }
    @PostMapping(path="/{id}")
    public void cancelAppointment(@PathVariable("id") Long id, HttpServletResponse response) throws IOException {
        try {
            Appointment appointment = appointmentService.getById(id);
            List<TimeAvailable> timeAvailables=timeAvailableService.findTimeAvailable(appointment.getDate(),appointment.getTime());
            TimeAvailable timeAvailable= timeAvailables.get(0);
            timeAvailable.setAvailable(true);


            appointmentService.deleteById(appointment.getId());

            boolean b=timeAvailable.isAvailable();

        }
        catch (Exception e){System.out.println("qualcosa è andato storto");}

        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect("/confirm");
    }
}
