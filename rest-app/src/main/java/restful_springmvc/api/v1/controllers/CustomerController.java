package restful_springmvc.api.v1.controllers;


import com.restful_springmvc.CustomerDto;
import com.restful_springmvc.CustomerListDto;
import restful_springmvc.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/customers")
@AllArgsConstructor
public class CustomerController {

    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<CustomerListDto> getAllCustomers(){
        CustomerListDto customers = new CustomerListDto();
        customers.getCustomers().addAll(customerService.getAllCustomers());
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(customerService.findCustomerById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createNewCustomer(@RequestBody CustomerDto customerDTO){
        return new ResponseEntity<CustomerDto>(customerService.createNewCustomer(customerDTO),
                HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable(name = "id") Long id, @RequestBody CustomerDto customerDTO){
        return new ResponseEntity<CustomerDto>(customerService.updateCustomer(id, customerDTO),
                HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerDto> patchCustomer(@PathVariable(name = "id") Long id, @RequestBody CustomerDto customerDTO){
        return new ResponseEntity<CustomerDto>(customerService.patchCustomer(id, customerDTO),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable(name = "id")Long id){
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
