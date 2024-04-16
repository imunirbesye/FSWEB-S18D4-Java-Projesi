package tr.com.workintech.s18d4.service;

import org.springframework.stereotype.Service;
import tr.com.workintech.s18d4.entity.Account;
import tr.com.workintech.s18d4.entity.Address;
import tr.com.workintech.s18d4.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> get() {
        return addressRepository.findAll();
    }

    @Override
    public Address getById(long id) {
        Optional<Address> account = addressRepository.findById(id);
        if(account.isPresent())
            return account.get();

        return null;
    }

    @Override
    public Address save(Address a) {
        return addressRepository.save(a);
    }

    @Override
    public Address update(long id, Address a) {
        Optional<Address> o = addressRepository.findById(id);
        if(o.isPresent()){
            o.get().setStreet(a.getStreet());
            o.get().setNo(a.getNo());
            o.get().setCity(a.getCity());
            o.get().setCountry(a.getCountry());
            o.get().setDescription(a.getDescription());
        }

        return o.get();
    }

    @Override
    public Address delete(long id) {
        Optional<Address> o = addressRepository.findById(id);
        addressRepository.delete(o.get());
        return o.get();
    }

}
