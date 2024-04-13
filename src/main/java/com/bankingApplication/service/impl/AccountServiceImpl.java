package com.bankingApplication.service.impl;

import com.bankingApplication.Exception.ResourceNotFoundException;
import com.bankingApplication.entity.Account;
import com.bankingApplication.payLoad.AccountDto;
import com.bankingApplication.payLoad.AccountMapper;
import com.bankingApplication.repository.AccountRepository;
import com.bankingApplication.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;



    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account saved = accountRepository.save(account);

        AccountDto dto = AccountMapper.mapToAccountDto(saved);

        return dto;
    }

    @Override
    public AccountDto getAccountById(long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id with number" + id + "not found"));
        AccountDto accountDto = AccountMapper.mapToAccountDto(account);
        return accountDto;
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> all = accountRepository.findAll();
//        List<AccountDto> dto = new ArrayList<>();
//        for (Account allData: all){
//            AccountDto accountDto = AccountMapper.mapToAccountDto(allData);
//            dto.add(accountDto);
//
//        }
        List<AccountDto> collect = all.stream().map((account) -> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public AccountDto deposit(long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id with number" + id + "not found"));
        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account saved = accountRepository.save(account);
        AccountDto accountDto = AccountMapper.mapToAccountDto(saved);
        return accountDto;
    }

    @Override
    public AccountDto withdraw(long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id with number" + id + "not found"));
            if(account.getBalance()<amount){
                throw new ResourceNotFoundException("insufficant balance");
            }else {
                double total = account.getBalance() - amount;
                account.setBalance(total);
                Account saved = accountRepository.save(account);
                AccountDto accountDto = AccountMapper.mapToAccountDto(saved);
                return accountDto;
            }
    }

    @Override
    public void deleteById(long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id with number" + id + "not found"));
        if (account!=null){
            accountRepository.deleteById(id);
        }
    }
}
