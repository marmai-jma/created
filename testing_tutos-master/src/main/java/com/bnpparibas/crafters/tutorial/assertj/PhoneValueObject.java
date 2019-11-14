package com.bnpparibas.crafters.tutorial.assertj;

import java.util.Objects;

public class PhoneValueObject {
    private final int countryCode;
    private final String localNumber;

    public PhoneValueObject(int countryCode, String localNumber) {

        this.countryCode = countryCode;
        this.localNumber = localNumber;
    }

    @Override
    public String toString() {
        return "PhoneValueObject{" +
                "countryCode=" + countryCode +
                ", localNumber='" + localNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneValueObject that = (PhoneValueObject) o;
        return countryCode == that.countryCode &&
                Objects.equals(localNumber, that.localNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(countryCode, localNumber);
    }
}
