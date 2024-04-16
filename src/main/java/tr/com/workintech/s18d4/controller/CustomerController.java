package tr.com.workintech.s18d4.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tr.com.workintech.s18d4.dao.CustomerResponse;
import tr.com.workintech.s18d4.entity.Address;
import tr.com.workintech.s18d4.entity.Customer;
import tr.com.workintech.s18d4.exceptions.ApiException;
import tr.com.workintech.s18d4.service.AddressService;
import tr.com.workintech.s18d4.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;
    private AddressService addressService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> get() {
        return customerService.get();
    }

    @GetMapping("/{id}")
    public CustomerResponse getById(@PathVariable long id) {
        Customer c = this.customerService.getById(id);
        if(c != null) {
            CustomerResponse customerResponse = new CustomerResponse(c.getId(), c.getEmail(), c.getSalary(), c.getAddress());
            return customerResponse;
        }

        throw new ApiException("Customer is not found with given id: " + id, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public CustomerResponse save(@RequestBody Customer c) {
        Address address = addressService.getById(c.getAddress().getId());
        if(address != null){
            Customer saved = customerService.save(c);
            return new CustomerResponse(saved.getId(), saved.getEmail(), saved.getSalary(), saved.getAddress());
        } else {
            throw new ApiException("No customer found with addressId: " + c.getAddress().getId(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public CustomerResponse update(@PathVariable long id,@RequestBody Customer c) {
        Address address = addressService.getById(c.getAddress().getId());

        if(customerService.getById(id) != null) {
            if(address != null){
                Customer saved = customerService.update(id, c);
                return new CustomerResponse(saved.getId(), saved.getEmail(), saved.getSalary(), saved.getAddress());
            } else {
                throw new ApiException("No address found with addressId: " + c.getAddress().getId(), HttpStatus.NOT_FOUND);
            }
        } else {
            throw new ApiException("No customer found with addressId: " + c.getAddress().getId(), HttpStatus.NOT_FOUND);
        }


    }

    @DeleteMapping("/{id}")
    public Customer delete(@PathVariable long id) {
        return customerService.delete(id);
    }

}
