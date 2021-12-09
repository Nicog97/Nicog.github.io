package com.project.zzbarber.service;


import com.project.zzbarber.model.Customer;
import com.project.zzbarber.repository.CustomerRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Component
public class CustomerService {
    private final CustomerRepository customerRepository;
    public CustomerService (CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }
    public Customer checkCustomer(String name, String surname, String phoneNumber) {
        List<Customer> customers = customerRepository.findCustomerByNameSAndSurnameAndPhoneNumber(name, surname, phoneNumber);

        if (!customers.isEmpty()) {
            return customers.get(0);
        }
        else return null;
    }
    public Customer customerById(Long id) throws EntityNotFoundException {
        return customerRepository.getById(id);
    }

    public void addCustomer(Customer customer) {

        customerRepository.save(customer);
    }
}
