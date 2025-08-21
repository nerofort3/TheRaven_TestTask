package com.neroforte.theraven_testtask.service;

import com.neroforte.theraven_testtask.DTO.CustomerRequest;
import com.neroforte.theraven_testtask.DTO.CustomerResponse;
import com.neroforte.theraven_testtask.entity.CustomerEntity;
import com.neroforte.theraven_testtask.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<CustomerResponse> getAllCustomers() {
        List<CustomerEntity> customers = customerRepository.findAll();             // not quite sure on whether i should
        return customers.stream()                                                  // show users with isActive == false
                .map(CustomerResponse::customerEntityToCustomerResponse).toList(); // left version that hides unactive customers commented

//        return customers.stream()
//                .filter(item -> item.getIsActive()==true)
//                .map(CustomerResponse::customerEntityToCustomerResponse).toList();
    }

    public CustomerResponse getCustomerById(long id) {
        CustomerEntity customerEntity = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer with id " + id + " not found"));
        return CustomerResponse.customerEntityToCustomerResponse(customerEntity);
    }


    public CustomerResponse updateCustomer(long id, CustomerRequest request) {          //update customer by id
        CustomerEntity existingCustomer = customerRepository.findById(id)               //updating also handles "-" in received fields
                .orElseThrow(() -> new IllegalArgumentException("Customer with id " + id + " not found"));

        String phone = request.getPhone().replaceAll("-","");

        existingCustomer.setFullName(request.getFullName());
        existingCustomer.setEmail(request.getEmail());
        existingCustomer.setPhone(phone);

        customerRepository.save(existingCustomer);
        return  CustomerResponse.customerEntityToCustomerResponse(customerRepository.save(existingCustomer));
    }

    @Transactional
    public CustomerResponse saveCustomer(CustomerRequest request) {         //saving a customer into a DB
        String phone = request.getPhone().replaceAll("-","");

        CustomerEntity customerEntity = CustomerEntity.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phone(phone)
                .isActive(true)
                .build();

        customerRepository.save(customerEntity);
        return CustomerResponse.customerEntityToCustomerResponse(customerEntity);
    }


    @Transactional
    public void deleteCustomerById(long id) {
        CustomerEntity customerEntity =  customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer with id " + id + " not found"));
        customerEntity.setIsActive(false);
        customerRepository.save(customerEntity);
    }
}
