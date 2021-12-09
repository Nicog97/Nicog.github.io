package com.project.zzbarber.controller;

import com.project.zzbarber.model.Appointment;
import com.project.zzbarber.model.TimeAvailable;
import com.project.zzbarber.service.AppointmentService;
import com.project.zzbarber.service.TimeAvailableService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(path="/appointments")
public class AppointmentsController {
    private final TimeAvailableService timeAvailableService;
    private final AppointmentService appointmentService;

    public AppointmentsController(TimeAvailableService timeAvailableService, AppointmentService appointmentService) {
        this.timeAvailableService = timeAvailableService;
        this.appointmentService = appointmentService;
    }
    @GetMapping
    public String bookingAppointment(Model model) throws IOException {
        List<Appointment> appointment=appointmentService.findAll();
        List<Appointment> orderAppointment=appointmentService.orderApp(appointment);
        model.addAttribute("appointments", orderAppointment);

        return "appointments";

    }
    @PostMapping(path="/{id}")
    public void cancelAppointment(@PathVariable("id") Long id, HttpServletResponse response) throws IOException {
        try {
            Appointment appointment = appointmentService.getById(id);
            List<TimeAvailable> timeAvailables=timeAvailableService.findTimeAvailable(appointment.getDate(),appointment.getTime());
            TimeAvailable timeAvailable= timeAvailables.get(0);
            timeAvailable.setAvailable(true);
            appointmentService.deleteById(id);

        }
        catch (Exception e){System.out.println("qualcosa è andato storto");}

        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect("/appointments");
    }
}
