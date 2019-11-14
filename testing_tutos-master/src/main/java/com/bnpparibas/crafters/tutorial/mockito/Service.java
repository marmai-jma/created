package com.bnpparibas.crafters.tutorial.mockito;

import com.bnpparibas.crafters.tutorial.assertj.Customer;

public class Service {

    private final DataPersistence dataPersistence;
    private DataSource pkGenerator;

    public Service(DataPersistence dataPersistence, DataSource pkGenerator) {
        this.dataPersistence = dataPersistence;
        this.pkGenerator = pkGenerator;
    }

    public int saveCustomers(Customer... customers) {
        int nbRecords = 0;

        for (Customer newCustomer : customers) {
            Customer recordableCustomer;
            if (newCustomer.getId() == null) { // create
                recordableCustomer = new Customer(pkGenerator.getNextCustomerPk(), newCustomer);
            } else { // update
                recordableCustomer = newCustomer;
            }
            dataPersistence.publish(recordableCustomer);
            nbRecords++;
        }
        // catch(T) { log(nbRecords have been saved);}
        return nbRecords++;
    }
}
