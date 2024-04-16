package tr.com.workintech.s18d4.dao;

import tr.com.workintech.s18d4.entity.Address;

public record CustomerResponse(long id, String email, double salary, Address address) {
}
