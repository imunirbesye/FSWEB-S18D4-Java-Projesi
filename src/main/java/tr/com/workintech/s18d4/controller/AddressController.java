package tr.com.workintech.s18d4.controller;

import org.springframework.web.bind.annotation.*;
import tr.com.workintech.s18d4.entity.Address;
import tr.com.workintech.s18d4.service.AddressService;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    private AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public List<Address> get() {
        return addressService.get();
    }

    @GetMapping("/{id}")
    public Address getById(@PathVariable long id) {
        return addressService.getById(id);
    }

    @PostMapping
    public Address save(@RequestBody Address a) {
        return addressService.save(a);
    }

    @PutMapping("/{id}")
    public Address update(@PathVariable long id,@RequestBody Address a) {
        return addressService.update(id, a);
    }

    @DeleteMapping("/{id}")
    public Address delete(@PathVariable long id) {
        return addressService.delete(id);
    }

}
