package com.example.library.domain.enums;

public enum LoanStatus {
    LOAN_ACTIVE("Active"),
    LOAN_RETURNED("Returned");

    private String name;
    private String getName(){
        return  name;
    }


     LoanStatus(String name){
        this.name = name;
    }

}


