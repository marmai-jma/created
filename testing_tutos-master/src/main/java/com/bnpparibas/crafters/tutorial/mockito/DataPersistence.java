package com.bnpparibas.crafters.tutorial.mockito;

import com.bnpparibas.crafters.tutorial.assertj.Customer;

public class DataPersistence {
    public Integer publish(Customer customer) {
        throw new RuntimeException("You do not want to call I/O dependencies in unit tests");
    }
}
