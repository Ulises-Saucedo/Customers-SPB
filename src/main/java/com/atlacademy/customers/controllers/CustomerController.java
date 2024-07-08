package com.atlacademy.customers.controllers;

import com.atlacademy.customers.entities.Customer;
import com.atlacademy.customers.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    CustomerService service = new CustomerService();

    @GetMapping("/customer")
    public List<Customer> getAllCustomers() {
        return service.getAllCustomers();
    }

    @GetMapping("/customer/{id}")
    public Customer getCustomerById(@PathVariable Integer id) {
        return service.getCustomer(id);
    }

    @PostMapping("/customer")
    public void addCustomer(@RequestBody Customer customer) {
        service.addCustomer(customer);
    }

    @PutMapping("/customer/{id}")
    public void updateCustomer(@PathVariable Integer id, @RequestBody Customer updatedCustomer) {
        service.updateCustomer(id, updatedCustomer);
    }

    @DeleteMapping("/customer/{id}")
    public void removeCustomer(@PathVariable Integer id) {
        service.removeCustomer(id);
    }

    @GetMapping("/customer/find")
    public List<Customer> searchCustomers(@RequestParam(name="email", required=false) String email,
                                          @RequestParam(name="address", required=false) String address) {
        return service.searchCustomer(email, address);
    }
}
