package com.bankingApplication.controller;

import com.bankingApplication.payLoad.AccountDto;
import com.bankingApplication.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/banking")
public class AccountController {

    @Autowired
    private AccountService accountService;
    //http://localhost:8080/api/banking/addAccount
    @PostMapping("/addAccount")
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        AccountDto account = accountService.createAccount(accountDto);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @GetMapping("/id")
    public ResponseEntity<AccountDto> getById(@RequestParam long id){
        AccountDto accountById = accountService.getAccountById(id);
        return new ResponseEntity<>(accountById, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AccountDto>> getAll(){
        List<AccountDto> allAccounts = accountService.getAllAccounts();
        return new ResponseEntity<>(allAccounts, HttpStatus.OK);
    }
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable long id, @RequestBody Map<String, Double> request){
        Double amount = request.get("amount");
        AccountDto amountDto = accountService.deposit(id,amount);
        return new ResponseEntity<>(amountDto,HttpStatus.OK);
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable long id, @RequestBody Map<String, Double> request){
        Double amount = request.get("amount");
        AccountDto amountDto = accountService.withdraw(id,amount);
        return new ResponseEntity<>(amountDto,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable long id){

        accountService.deleteById(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }


}
