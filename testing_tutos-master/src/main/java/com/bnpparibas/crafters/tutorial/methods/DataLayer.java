package com.bnpparibas.crafters.tutorial.methods;

import java.util.UUID;

public class DataLayer {
//    public DataRef fetchNewData() {
//        throw new DependencyCalledException("You have called a costly production system");
//    }
//
    public DataRef fetchDataBy(UUID id) {
        throw new DependencyCalledException("You have called a costly production system");
    }
//    public void increment() {
//        throw new DependencyCalledException("You have called a costly production system");
//    }
//
    public void save(DataRef data) {
        throw new DependencyCalledException("You have called a costly production system");
    }

    public Object fetchEnrichmentDataFromOtherSource(UUID id) {
        throw new DependencyCalledException("You have called a costly production system");
    }
}
