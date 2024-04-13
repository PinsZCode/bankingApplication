package com.bankingApplication.service;

import com.bankingApplication.payLoad.AccountDto;

import java.util.List;

public interface AccountService {
    AccountDto createAccount (AccountDto accountDto);

   AccountDto getAccountById(long id);

    List<AccountDto> getAllAccounts();

    AccountDto deposit(long id , double amount);

    AccountDto withdraw(long id , double amount);

    void deleteById(long id);
}
