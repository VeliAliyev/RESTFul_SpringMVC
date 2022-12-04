package com.velialiyev.restful_springmvc.services.impl;

import com.velialiyev.restful_springmvc.api.v1.mapper.CustomerMapper;
import com.velialiyev.restful_springmvc.api.v1.model.CustomerDto;
import com.velialiyev.restful_springmvc.domain.Customer;
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
        return customerRepository.findById(id).map(customerMapper::customerToCustomerDto)
                .map(customerDto -> {
                    customerDto.setCustomer_url("/api/v1/customers/" + customerDto.getId());
                    return customerDto;
                }).orElseThrow();
    }

    @Override
    public CustomerDto createNewCustomer(CustomerDto customerDto) {

        Customer customer = customerMapper.customerDtoToCustomer(customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        savedCustomer.setCustomer_url("/api/v1/customers/" + savedCustomer.getId());
        return customerMapper.customerToCustomerDto(savedCustomer);
    }
}
