package tr.com.workintech.s18d4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import tr.com.workintech.s18d4.entity.Account;
import tr.com.workintech.s18d4.repository.AccountRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> get() {
        return accountRepository.findAll();
    }

    @Override
    public Account getById(long id) {
        Optional<Account> account = accountRepository.findById(id);
        if(account.isEmpty())
            return null;

        return account.get();
    }

    @Override
    public Account save(Account a) {
        return accountRepository.save(a);
    }

    @Override
    public Account update(Account a) {
        Optional<Account> o = accountRepository.findById(a.getId());
       /* if(o.isPresent()){
            o.get().setAccountName(a.getAccountName());
            o.get().setCustomer(a.getCustomer());
            o.get().setMoneyAmount(a.getMoneyAmount());
        }
*/
        return o.get();
    }

    @Override
    public Account delete(long id) {
        Optional<Account> o = accountRepository.findById(id);
        o.ifPresent(account -> accountRepository.delete(account));

        return o.get();
    }
}
