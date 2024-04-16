package tr.com.workintech.s18d4.service;

import org.springframework.stereotype.Service;
import tr.com.workintech.s18d4.entity.Address;

import java.util.List;


public interface AddressService {
    List<Address> get();
    Address getById(long id);
    Address save(Address a);
    Address update(long id, Address a);
    Address delete(long id);
}
