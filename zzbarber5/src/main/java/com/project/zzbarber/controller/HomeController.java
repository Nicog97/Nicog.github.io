package com.project.zzbarber.controller;


import com.project.zzbarber.model.Appointment;
import com.project.zzbarber.model.Customer;
import com.project.zzbarber.model.TimeAvailable;
import com.project.zzbarber.service.AppointmentService;
import com.project.zzbarber.service.CustomerService;
import com.project.zzbarber.service.TimeAvailableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/auth")
public class HomeController {

    private final CustomerService customerService;
    private final AppointmentService appointmentService;
    public final TimeAvailableService timeAvailableService;


    @Autowired
    public HomeController(CustomerService customerService, AppointmentService appointmentService, TimeAvailableService timeAvailableService) {
        this.customerService = customerService;
        this.appointmentService = appointmentService;
        this.timeAvailableService = timeAvailableService;
    }


    @RequestMapping(path = "/next")
    @PostMapping
    public void next(HttpServletRequest request, HttpServletResponse response, @RequestParam(name = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) throws IOException {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if (date.before(new Date())) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.getSession().setAttribute("errorMessage", new Error("La data selezionata non è disponibile"));
            response.sendRedirect("/");
            return;
        }
        else if(c.get(Calendar.DAY_OF_WEEK)==2){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.getSession().setAttribute("errorMessage", new Error("Seleziona un altra data, lunedì il negozio resterà chiuso "));
            response.sendRedirect("/");
            return;

        }
        else if((c.get(Calendar.DAY_OF_MONTH)==24 || c.get(Calendar.DAY_OF_MONTH)==25 || c.get(Calendar.DAY_OF_MONTH)==26) && (c.get(Calendar.MONTH)==11)){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.getSession().setAttribute("errorMessage", new Error("Seleziona un altra data, Natale, Vigilia e Santo Stefano il negozio resterà chiuso "));
            response.sendRedirect("/");
            return;
        }
        else if((c.get(Calendar.DAY_OF_MONTH)==31 && (c.get(Calendar.MONTH)==11))|| ((c.get(Calendar.DAY_OF_MONTH)==1 && (c.get(Calendar.MONTH)==0 )))){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.getSession().setAttribute("errorMessage", new Error("Seleziona un altra data, 31 Dicembre e Capodanno il negozio resterà chiuso "));
            response.sendRedirect("/");
            return;
        }

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String telNumber = request.getParameter("telNumber");

        request.getSession().setAttribute("data", date);

        if (name == null || telNumber == null || name.isEmpty() || telNumber.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.getSession().setAttribute("error_message", new Error("Empty values"));
            response.sendRedirect("/");
            return;
        } else {
            try {

                Customer customer = customerService.checkCustomer(name, surname, telNumber);
                if (customer == null) {
                    Customer newCustomer = new Customer();
                    newCustomer.setName(name);
                    newCustomer.setSurname(surname);
                    newCustomer.setPhoneNumber(telNumber);
                    request.getSession().setAttribute("customer", newCustomer);
                    customerService.addCustomer(newCustomer);
                } else {
                    request.getSession().setAttribute("customer", customer);
                }

                List<Appointment> appointments = appointmentService.checkAppointment(date);
                List<TimeAvailable> timeAvailables = timeAvailableService.checkTimeAvailable(date);
                if (appointments.size() == 0) {
                    if (timeAvailables.size() == 0) {
                        try {
                            timeAvailableService.addTimeAvailable(date);
                        } catch (Exception e) {
                            System.out.println("TimeVailable yet create for selected data");
                        }
                    }

                }
                List<TimeAvailable> timeAvailablesOrdered = timeAvailableService.orderTimeAvailable(date);
                if (timeAvailablesOrdered.size() == 0) {

                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    request.getSession().setAttribute("errorMessage", new Error("Non ci sono orari disponibili per la data selezionata"));

                }
            }
            catch (Exception e){
                System.out.println("aa");

            }
        }


        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect("/time");
        return;
    }
}