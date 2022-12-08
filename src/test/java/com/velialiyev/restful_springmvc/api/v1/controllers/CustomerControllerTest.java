package com.velialiyev.restful_springmvc.api.v1.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.velialiyev.restful_springmvc.api.v1.model.CustomerDto;
import com.velialiyev.restful_springmvc.api.v1.model.CustomerListDto;
import com.velialiyev.restful_springmvc.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.swing.text.html.HTML;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomerControllerTest extends AbstractRestControllerTest{

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getAllCustomers() throws Exception {

        //given
        List<CustomerDto> customers = new ArrayList<>(Arrays.asList(
                CustomerDto.builder().id(1L).firstname("Veli").lastname("Aliyev").customer_url("/api/v1/customers/1").build(),
                CustomerDto.builder().id(2L).firstname("Michael").lastname("Page").customer_url("/api/v1/customers/2").build(),
                CustomerDto.builder().id(3L).firstname("Jenna").lastname("Ortega").customer_url("/api/v1/customers/3").build()
        ));

        when(customerService.getAllCustomers()).thenReturn(customers);

        //when
        mockMvc.perform(get("/api/v1/customers/")
                        .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(3)));

    }

    @Test
    void getCustomerById() throws Exception {
        //given
        CustomerDto customer = CustomerDto.builder().id(1L).firstname("Veli").lastname("Aliyev").build();

        when(customerService.findCustomerById(anyLong())).thenReturn(customer);

        //when
        mockMvc.perform(get("/api/v1/customers/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo("Veli")))
                .andExpect(jsonPath("$.lastname", equalTo("Aliyev")));
    }

    @Test
    void createNewCustomer() throws Exception {
        //given
        CustomerDto customer = CustomerDto.builder()
                .firstname("Veli").lastname("Aliyev").build();

        CustomerDto returnedCustomer = CustomerDto.builder()
                .id(1L).firstname("Veli").lastname("Aliyev").customer_url("/api/v1/customers/1").build();
        when(customerService.createNewCustomer(any(CustomerDto.class))).thenReturn(returnedCustomer);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(customer);

        //when
        mockMvc.perform(post("/api/v1/customers/")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstname", equalTo("Veli")))
                .andExpect(jsonPath("$.lastname", equalTo("Aliyev")));
    }

    @Test
    void deleteCustomer() throws Exception {
        mockMvc.perform(delete("/api/v1/customers/1"))
                .andExpect(status().isOk());

        verify(customerService, times(1)).deleteCustomer(anyLong());
    }
}