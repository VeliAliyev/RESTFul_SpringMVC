package com.velialiyev.restful_springmvc.services;

import com.velialiyev.restful_springmvc.api.v1.mapper.CustomerMapper;
import com.velialiyev.restful_springmvc.api.v1.model.CustomerDto;
import com.velialiyev.restful_springmvc.bootstrap.Bootstrap;
import com.velialiyev.restful_springmvc.domain.Customer;
import com.velialiyev.restful_springmvc.repository.CategoryRepository;
import com.velialiyev.restful_springmvc.repository.CustomerRepository;
import com.velialiyev.restful_springmvc.services.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CustomerServiceImplIT {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    CustomerService customerService;

    @BeforeEach
    void setUp() throws Exception {
        Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository);
        bootstrap.run();
        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    void patchCustomerFirstName(){
        String updatedName = "UpdatedName";
        Long id = getCustomerId();

        Customer originalCustomer = customerRepository.findById(id).orElseThrow();
        String originalFirstName = originalCustomer.getFirstname();
        String originalLastName = originalCustomer.getLastname();
        assertNotNull(originalCustomer);

        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstname(updatedName);

        customerService.patchCustomer(id, customerDto);

        Customer patched = customerRepository.findById(id).orElseThrow();
        assertNotNull(patched);
        assertEquals(updatedName, patched.getFirstname());
        assertEquals(originalLastName, patched.getLastname());
        assertNotNull(updatedName, originalFirstName);
    }

    @Test
    void patchCustomerLastName(){
        String updatedLastName = "UpdatedLastName";
        Long id = getCustomerId();

        Customer customer = customerRepository.findById(id).orElseThrow();
        assertNotNull(customer);
        String originalName = customer.getFirstname();
        String originalLastName = customer.getLastname();

        CustomerDto customerDto = new CustomerDto();
        customerDto.setLastname(updatedLastName);
        customerService.patchCustomer(id, customerDto);

        Customer patched = customerRepository.findById(id).orElseThrow();
        assertNotNull(patched);
        assertEquals(updatedLastName, patched.getLastname());
        assertNotEquals(originalLastName, patched.getLastname());
        assertEquals(originalName, patched.getFirstname());
    }

    Long getCustomerId(){
        return customerRepository.findAll().get(0).getId();
    }

    @Test
    void deleteCustomer(){

        Long id = getCustomerId();
        assertNotNull(customerRepository.findById(id).orElseThrow());
        customerService.deleteCustomer(id);
        Optional<Customer> customer = customerRepository.findById(id);
        assertEquals(true, customer.isEmpty());
    }

}
