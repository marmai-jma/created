package com.bnpparibas.crafters.tutorial;

import com.bnpparibas.crafters.tutorial.assertj.Customer;

public class Given {
    public static Customer theCustomerWithId(int id) {
        return new Customer(id, null, null, null);
    }

    public static Customer theCustomerWithName(String name) {
        return new Customer(name, null, null);
    }
}
