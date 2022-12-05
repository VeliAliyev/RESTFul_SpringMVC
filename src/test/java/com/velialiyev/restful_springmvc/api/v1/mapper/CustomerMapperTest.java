package com.velialiyev.restful_springmvc.api.v1.mapper;

import com.velialiyev.restful_springmvc.api.v1.model.CustomerDto;
import com.velialiyev.restful_springmvc.domain.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {

    @Test
    void customerToCustomerDto() {
        //given
        Customer customer = Customer.builder()
                .id(1L)
                .firstname("Veli")
                .lastname("Aliyev")
                .customer_url("/api/v1/customers/1")
                .build();
        //when
        CustomerDto customerDto = CustomerMapper.INSTANCE.customerToCustomerDto(customer);

        //then
        assertEquals(1L, customerDto.getId());
        assertEquals("Veli", customerDto.getFirstname());
        assertEquals("Aliyev", customerDto.getLastname());
        assertEquals("/api/v1/customers/1", customerDto.getCustomer_url());
    }
}