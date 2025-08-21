package com.neroforte.theraven_testtask.repository;

import com.neroforte.theraven_testtask.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

}
