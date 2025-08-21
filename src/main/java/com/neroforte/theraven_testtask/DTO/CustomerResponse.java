package com.neroforte.theraven_testtask.DTO;

import com.neroforte.theraven_testtask.entity.CustomerEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponse {

    private Long id;

    private String fullName;

    private String email;

    private String phone;

    public static CustomerResponse customerEntityToCustomerResponse(CustomerEntity customerEntity) {
        return CustomerResponse.builder()
                .id(customerEntity.getId())
                .fullName(customerEntity.getFullName())
                .email(customerEntity.getEmail())
                .phone(customerEntity.getPhone())
                .build();
    }
}
