package com.bankingApplication.payLoad;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

        private long id;

        private String accountHolderName;

        private double balance;
}

