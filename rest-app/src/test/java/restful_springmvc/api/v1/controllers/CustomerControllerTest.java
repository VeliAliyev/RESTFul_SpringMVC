package restful_springmvc.api.v1.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.restful_springmvc.CustomerDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import restful_springmvc.services.CustomerService;

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

        CustomerDto c1 = new CustomerDto();
        c1.setId(1L);
        c1.setFirstname("Veli");
        c1.setLastname("Aliyev");
        c1.setCustomerUrl("/api/v1/customers/1");

        CustomerDto c2 = new CustomerDto();
        c1.setId(2L);
        c1.setFirstname("Michael");
        c1.setLastname("Page");
        c1.setCustomerUrl("/api/v1/customers/2");

        CustomerDto c3 = new CustomerDto();
        c1.setId(3L);
        c1.setFirstname("Jenna");
        c1.setLastname("Ortega");
        c1.setCustomerUrl("/api/v1/customers/3");



                //given
        List<CustomerDto> customers = new ArrayList<>(Arrays.asList(c1, c2, c3));

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
        CustomerDto customer = new CustomerDto();
        customer.setId(1L);
        customer.setFirstname("Veli");
        customer.setLastname("Aliyev");
        customer.setCustomerUrl("/api/v1/customers/1");

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
        CustomerDto customer = new CustomerDto();
        customer.setFirstname("Veli");
        customer.setLastname("Aliyev");

        CustomerDto returnedCustomer = new CustomerDto();
        returnedCustomer.setId(1L);
        returnedCustomer.setFirstname("Veli");
        returnedCustomer.setLastname("Aliyev");
        returnedCustomer.setCustomerUrl("/api/v1/customers/1");
        when(customerService.createNewCustomer(any())).thenReturn(returnedCustomer);

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