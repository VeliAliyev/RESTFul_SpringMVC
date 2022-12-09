package restful_springmvc.services.impl;

import restful_springmvc.api.v1.mapper.CustomerMapper;
import restful_springmvc.api.v1.model.CustomerDto;
import restful_springmvc.domain.Customer;
import restful_springmvc.repository.CustomerRepository;
import restful_springmvc.services.CustomerService;
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
                .stream().map(customerMapper::customerToCustomerDto)
                .map(customerDto -> {
                    customerDto.setCustomer_url("/api/v1/customers/" + customerDto.getId());
                    return customerDto;
                }).collect(Collectors.toList());
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

    @Override
    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
        Customer customer = customerMapper.customerDtoToCustomer(customerDto);
        customer.setId(id);
        Customer saved = customerRepository.save(customer);
        saved.setCustomer_url("/api/v1/customers/" + saved.getId());
        return customerMapper.customerToCustomerDto(saved);
    }

    @Override
    public CustomerDto patchCustomer(Long id, CustomerDto customerDto) {

        return customerRepository.findById(id).map(customer -> {
            if(customerDto.getFirstname() != null){
                customer.setFirstname(customerDto.getFirstname());
            }

            if(customerDto.getLastname() != null){
                customer.setLastname(customerDto.getLastname());
            }
            Customer saved = customerRepository.save(customer);
            return customerMapper.customerToCustomerDto(saved);
        }).orElseThrow();
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
