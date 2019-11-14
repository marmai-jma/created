package com.bnpparibas.crafters.tutorial.assertj;

import java.util.Objects;

public class Customer {
    private final Integer id;
    private final String firstName;
    private final String lastName;

    private final PhoneValueObject phoneValueObject;

    public Customer(Integer id, String firstName, String lastName, PhoneValueObject phoneValueObject) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneValueObject = phoneValueObject;
    }

    public Customer(String firstName, String lastName, PhoneValueObject phoneValueObject) {
        this(null, firstName, lastName, phoneValueObject);
    }

    public Customer(Integer id, Customer source) {
        this(id, source.firstName, source.lastName, source.phoneValueObject);
    }

    public PhoneValueObject getPhoneValueObject() {
        return phoneValueObject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneValueObject=" + phoneValueObject +
                '}';
    }

    public Integer getId() {
        return id;
    }
}
