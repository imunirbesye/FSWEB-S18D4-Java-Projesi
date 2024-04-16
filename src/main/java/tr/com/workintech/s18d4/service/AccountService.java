package tr.com.workintech.s18d4.service;

import tr.com.workintech.s18d4.entity.Account;

import java.util.List;

public interface AccountService {
    List<Account> get();
    Account getById(long id);
    Account save(Account a);
    Account update(Account a);
    Account delete(long id);

}
