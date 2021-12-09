package com.project.zzbarber.controller;


import com.project.zzbarber.model.Appointment;
import com.project.zzbarber.model.Customer;
import com.project.zzbarber.model.TimeAvailable;
import com.project.zzbarber.model.TimeEnum;
import com.project.zzbarber.service.AppointmentService;
import com.project.zzbarber.service.CustomerService;
import com.project.zzbarber.service.TimeAvailableService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(path = "/time")
public class TimeController {
    public final AppointmentService appointmentService;
    public final CustomerService customerService;
    public final TimeAvailableService timeAvailableService;

    @Autowired
    public TimeController(AppointmentService appointmentService, CustomerService customerService, TimeAvailableService timeAvailableService) {
        this.appointmentService = appointmentService;
        this.customerService = customerService;
        this.timeAvailableService = timeAvailableService;
    }

    @GetMapping
    public String time(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Date date = (Date) request.getSession().getAttribute("data");
        Customer customer=(Customer) request.getSession().getAttribute("customer");

        List<TimeAvailable> timeAvailablesOrdered=timeAvailableService.orderTimeAvailable(date);
        List<TimeAvailable> notAvailableTime=timeAvailableService.notAvailable(date);
        if (timeAvailablesOrdered.size()==0){

            model.addAttribute("error_message", (Error) request.getSession().getAttribute("errorMessage"));


            return "index";
        }
        model.addAttribute("customer", customer);
        model.addAttribute("time_available", timeAvailablesOrdered);
        model.addAttribute("not_available", notAvailableTime);
        return "time";
    }
    @RequestMapping(path = "/booking/{id}")
    @PostMapping
    public void next(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response, @RequestParam(name = "date") @DateTimeFormat(pattern = "dd-MM-yyyy") Date date, @RequestParam(name = "time") TimeEnum timeEnum) throws IOException {
    try{
        List<TimeAvailable> timeAvailables=timeAvailableService.findTimeAvailable(date,timeEnum);
        timeAvailables.get(0).setAvailable(false);
        Customer customer = customerService.customerById(id);
        Appointment appointment=new Appointment();
        appointment.setDate(date);
        appointment.setTime(timeEnum);
        appointment.setCustomer(customer);
        request.getSession().setAttribute("customer", customer);

        appointmentService.addAppointment(appointment);

    }
    catch(Exception e) {
        response.sendRedirect("/time");
    }

        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect("/confirm");
    }

}
