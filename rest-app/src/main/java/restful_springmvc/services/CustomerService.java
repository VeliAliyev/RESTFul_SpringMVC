package restful_springmvc.services;

import restful_springmvc.api.v1.model.CustomerDto;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> getAllCustomers();
    CustomerDto findCustomerById(Long id);
    CustomerDto createNewCustomer(CustomerDto customerDto);
    CustomerDto updateCustomer(Long id, CustomerDto customerDto);
    CustomerDto patchCustomer(Long id, CustomerDto customerDto);
    void deleteCustomer(Long id);
}
