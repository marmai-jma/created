package com.bnpparibas.crafters.tutorial.mockito;

import java.util.concurrent.atomic.AtomicInteger;

public class DataSource {
    private static AtomicInteger nextCustomerPk = new AtomicInteger(256);

    public Integer getNextCustomerPk() {
        return nextCustomerPk.getAndIncrement();
    }
}
