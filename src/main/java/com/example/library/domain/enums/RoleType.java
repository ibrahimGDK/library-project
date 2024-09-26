package com.example.library.domain.enums;

public enum RoleType {
    ROLE_CUSTOMER("Customer"),

     ROLE_ADMIN("Administrator");


    private  String name;

    private String getName() {
        return name;
    }

     RoleType(String name) {
        this.name = name;
    }
}

