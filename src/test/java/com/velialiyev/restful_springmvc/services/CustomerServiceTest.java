package com.velialiyev.restful_springmvc.services;

import com.velialiyev.restful_springmvc.api.v1.mapper.CustomerMapper;
import com.velialiyev.restful_springmvc.api.v1.model.CustomerDto;
import com.velialiyev.restful_springmvc.domain.Customer;
import com.velialiyev.restful_springmvc.repository.CustomerRepository;
import com.velialiyev.restful_springmvc.services.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    void getAllCustomers() {
        //given
        List<Customer> customers = Arrays.asList(
                Customer.builder().id(1L).firstname("Veli").lastname("Aliyev").customer_url("/api/v1/customers/1").build(),
                Customer.builder().id(2L).firstname("Emma").lastname("Watson").customer_url("/api/v1/customers/2").build(),
                Customer.builder().id(3L).firstname("Jenna").lastname("Ortega").customer_url("/api/v1/customers/3").build()
        );
        when(customerRepository.findAll()).thenReturn(customers);

        //when
        List<CustomerDto> customersList = customerService.getAllCustomers();

        //then
        assertEquals(3, customersList.size());
    }

    @Test
    void findCustomerById() {
        //given
        Customer veli = Customer.builder().id(1L).firstname("Veli").lastname("Aliyev").customer_url("/api/v1/customers/1").build();
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(veli));

        //when
        CustomerDto veliDto = customerService.findCustomerById(anyLong());

        //then

        assertEquals(1L, veliDto.getId());
        assertEquals("Veli", veliDto.getFirstname());
        assertEquals("Aliyev", veliDto.getLastname());
        assertEquals("/api/v1/customers/1", veliDto.getCustomer_url());
    }
}