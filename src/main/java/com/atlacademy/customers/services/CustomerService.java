package com.atlacademy.customers.services;

import com.atlacademy.customers.entities.Customer;
import com.atlacademy.customers.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public Customer getCustomer(Integer id) {
        Optional<Customer> customer = repository.findById(id);

        if(customer.isPresent()) {
            return customer.get();
        }

        return null;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> list = new ArrayList<>();

        Iterable<Customer> customers = repository.findAll();
        for(Customer customer : customers) {
            list.add(customer);
        }

        return list;
    }

    public void removeCustomer(Integer id) {
        repository.deleteById(id);
    }

    public void addCustomer(Customer customer) {
        repository.save(customer);
    }

    public void updateCustomer(Integer id, Customer updatedCustomer) {
        updatedCustomer.setId(id);
        repository.save(updatedCustomer);
    }

    public List<Customer> searchCustomer(String email, String address) {
        return repository.findByEmailOrAddress(email, address);
    }
}
