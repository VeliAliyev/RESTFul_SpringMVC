package com.velialiyev.restful_springmvc.services.impl;

import com.velialiyev.restful_springmvc.api.v1.mapper.CustomerMapper;
import com.velialiyev.restful_springmvc.api.v1.model.CustomerDto;
import com.velialiyev.restful_springmvc.repository.CustomerRepository;
import com.velialiyev.restful_springmvc.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll()
                .stream().map(customerMapper::customerToCustomerDto).collect(Collectors.toList());
    }

    @Override
    public CustomerDto findCustomerById(Long id) {
        return customerMapper.customerToCustomerDto(customerRepository.findById(id).orElseThrow());
    }

    @Override
    public CustomerDto createNewCustomer(CustomerDto customer) {
        return customerMapper.customerToCustomerDto(
                customerRepository.save(customerMapper.customerDtoToCustomer(customer))
        );
    }
}
