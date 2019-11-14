package com.bnpparibas.crafters.tutorial.assertj;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

public class SoftAssertionsTest {
    @Rule
    public JUnitSoftAssertions should = new JUnitSoftAssertions();

    @Test
    public void should_assert_ComplexForms() {
        PhoneValueObject actualPhone = new PhoneValueObject(33, "0605040302");
        PhoneValueObject expectedPhone = new PhoneValueObject(33, "0605040302");
        Customer actualCustomer = new Customer(123, "FirstName", "LastName", actualPhone);
        Customer expectedCustomer = new Customer(123, null, null, null);

        should.assertThat(actualCustomer)
                .hasNoNullFieldsOrProperties()
                .hasFieldOrPropertyWithValue("id", 123)
                .hasFieldOrPropertyWithValue("firstName", "FirstName")
                .hasFieldOrPropertyWithValue("lastName", "LastName")
                .isEqualTo(expectedCustomer)
                .isNotSameAs(expectedCustomer);
        should.assertThat(actualCustomer.getPhoneValueObject())
                .isEqualTo(expectedPhone)
                .isEqualToComparingFieldByField(expectedPhone);
    }

    @Ignore
    @Test
    public void should_assert_ComplexForms_with_nice_eror_messages() {
        PhoneValueObject actualPhone = new PhoneValueObject(33, "0605040302");
        PhoneValueObject expectedPhone = new PhoneValueObject(33, "7605040302");
        Customer actualCustomer = new Customer(123, "FirstName", "LastName", actualPhone);
        Customer expectedCustomer = new Customer(123, null, null, null);

        should.assertThat(actualCustomer)
                .hasNoNullFieldsOrProperties()
                .hasFieldOrPropertyWithValue("id", 123)
                .hasFieldOrPropertyWithValue("firstName", "First")
                .hasFieldOrPropertyWithValue("lastName", "Name")
                .isEqualTo(expectedCustomer)
                .isNotSameAs(expectedCustomer);
        should.assertThat(actualCustomer.getPhoneValueObject())
                .isEqualTo(expectedPhone)
                .isEqualToComparingFieldByField(expectedPhone);
    }

}
