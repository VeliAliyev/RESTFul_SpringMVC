package com.velialiyev.restful_springmvc.services;

import com.velialiyev.restful_springmvc.api.v1.model.CustomerDto;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> getAllCustomers();
    CustomerDto findCustomerById(Long id);
    CustomerDto createNewCustomer(CustomerDto customer);
}
