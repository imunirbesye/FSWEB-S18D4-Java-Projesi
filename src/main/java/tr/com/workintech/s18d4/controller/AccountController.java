package tr.com.workintech.s18d4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tr.com.workintech.s18d4.dao.AccountResponse;
import tr.com.workintech.s18d4.dao.CustomerResponse;
import tr.com.workintech.s18d4.entity.Account;
import tr.com.workintech.s18d4.entity.Customer;
import tr.com.workintech.s18d4.exceptions.ApiException;
import tr.com.workintech.s18d4.service.AccountService;
import tr.com.workintech.s18d4.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    private AccountService accountService;
    private CustomerService customerService;

    @Autowired
    public AccountController(AccountService accountService, CustomerService customerService) {
        this.accountService = accountService;
        this.customerService = customerService;
    }

    @GetMapping
    public List<Account> get() {
        return accountService.get();
    }

    @GetMapping("/{id}")
    public Account getById(@PathVariable long id) {
        Account a = accountService.getById(id);
        if(a != null) {
            return a;
        }

        throw new ApiException("Account not found with given id: " + id, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{custopmerId}")
    public AccountResponse save(@RequestBody Account a, @PathVariable long customerId) {
        Customer customer = customerService.getById(customerId);
        if(customer != null) {
            customer.getAccount().add(a);
            a.setCustomer(customer);
            accountService.save(a);
        } else {
            throw new ApiException("No customer found with given customerId: " + customerId, HttpStatus.NOT_FOUND);
        }

        return new AccountResponse(a.getId(), a.getAccountName(), a.getMoneyAmount(),
                new CustomerResponse(customerId, customer.getEmail(), customer.getSalary(), customer.getAddress()));
    }

    @PutMapping("/{id}")
    public AccountResponse update(@PathVariable long customerId,@RequestBody Account a) {
        Customer c = customerService.getById(customerId);
        Account foundAccount = null;
        for(Account cAccount: c.getAccount()) {
            if(a.getId() == cAccount.getId())
                foundAccount = cAccount;
        }
        if(foundAccount == null){
            throw new ApiException("Account("+ a.getId() + ") not found for this customer: " + customerId, HttpStatus.NOT_FOUND);
        }

        int indexOfFound = c.getAccount().indexOf(foundAccount);
        c.getAccount().set(indexOfFound, a);
        a.setCustomer(c);
        accountService.save(a);

        return new AccountResponse(a.getId(), a.getAccountName(), a.getMoneyAmount(),
                new CustomerResponse(customerId, c.getEmail(), c.getSalary(), c.getAddress()));
    }

    @DeleteMapping("/{id}")
    public AccountResponse delete(@PathVariable long id) {
        Account account = accountService.getById(id);
        if(account != null) {
            accountService.delete(id);
            return new AccountResponse(account.getId(), account.getAccountName(), account.getMoneyAmount(),
                    new CustomerResponse(account.getCustomer().getId(), account.getCustomer().getEmail(), account.getCustomer().getSalary(), account.getCustomer().getAddress()));
        } else {
            throw new ApiException("Account not found with given id: " + id, HttpStatus.NOT_FOUND);
        }
    }
}
