package com.velialiyev.restful_springmvc.repository;

import com.velialiyev.restful_springmvc.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
