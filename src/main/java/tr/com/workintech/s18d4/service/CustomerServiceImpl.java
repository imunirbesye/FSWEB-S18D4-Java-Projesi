package tr.com.workintech.s18d4.service;

import org.springframework.stereotype.Service;
import tr.com.workintech.s18d4.entity.Address;
import tr.com.workintech.s18d4.entity.Customer;
import tr.com.workintech.s18d4.repository.AddressRepository;
import tr.com.workintech.s18d4.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl  implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> get() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getById(long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElse(null);

    }

    @Override
    public Customer save(Customer c) {
        return customerRepository.save(c);
    }

    @Override
    public Customer update(long id, Customer c) {
        Optional<Customer> o = customerRepository.findById(id);
        if(o.isPresent()){
            o.get().setEmail(c.getEmail());
            o.get().setSalary(c.getSalary());
            o.get().setFirstName(c.getFirstName());
            o.get().setLastName(c.getLastName());
        }

        return o.get();
    }

    @Override
    public Customer delete(long id) {
        Optional<Customer> o = customerRepository.findById(id);
        customerRepository.delete(o.get());
        return o.get();
    }

}