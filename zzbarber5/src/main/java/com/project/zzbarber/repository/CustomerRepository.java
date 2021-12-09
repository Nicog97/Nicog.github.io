package com.project.zzbarber.repository;


import com.project.zzbarber.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    @Query("SELECT c FROM Customer c WHERE c.name = ?1 AND c.surname = ?2 AND c.phoneNumber=?3")
    List<Customer> findCustomerByNameSAndSurnameAndPhoneNumber(String name, String surname, String phoneNumber);
    @Query ("select c from Customer c where c.id=?1")
    Optional<Customer> findById(Long id);
}
