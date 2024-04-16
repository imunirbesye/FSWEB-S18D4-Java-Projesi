package tr.com.workintech.s18d4.service;

import tr.com.workintech.s18d4.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> get();
    Customer getById(long id);
    Customer save(Customer c);
    Customer update(long id, Customer c);
    Customer delete(long id);
}
