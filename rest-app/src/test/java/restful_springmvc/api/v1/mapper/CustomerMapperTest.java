package restful_springmvc.api.v1.mapper;

import com.restful_springmvc.CustomerDto;
import org.junit.jupiter.api.Test;

import restful_springmvc.domain.Customer;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerMapperTest {

    @Test
    void customerToCustomerDto() {
        //given
        Customer customer = Customer.builder()
                .id(1L)
                .firstname("Veli")
                .lastname("Aliyev")
                .customerUrl("/api/v1/customers/1")
                .build();
        //when
        CustomerDto customerDto = CustomerMapper.INSTANCE.customerToCustomerDto(customer);

        //then
        assertEquals(1L, customerDto.getId());
        assertEquals("Veli", customerDto.getFirstname());
        assertEquals("Aliyev", customerDto.getLastname());
        assertEquals("/api/v1/customers/1", customerDto.getCustomerUrl());
    }
}