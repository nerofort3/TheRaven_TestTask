package com.neroforte.theraven_testtask.controller;


import com.neroforte.theraven_testtask.DTO.CustomerRequest;
import com.neroforte.theraven_testtask.DTO.CustomerResponse;
import com.neroforte.theraven_testtask.entity.CustomerEntity;
import com.neroforte.theraven_testtask.repository.CustomerRepository;
import com.neroforte.theraven_testtask.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping()
    public List<CustomerResponse> findAllUsers()  {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public CustomerResponse findCustomerById(@PathVariable Long id)  {
        return customerService.getCustomerById(id);
    }

    @PostMapping()
    public CustomerResponse createCustomer(@RequestBody @Valid CustomerRequest customerRequest) {
        return customerService.saveCustomer(customerRequest);
    }

    @PutMapping("/{id}")
    public CustomerResponse updateCustomerById(@PathVariable Long id, @Valid @RequestBody CustomerRequest customerRequest) {
        return customerService.updateCustomer(id, customerRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomerById(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
    }


}
