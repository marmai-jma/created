package com.bnpparibas.crafters.tutorial.assertj;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertJTests {

    @Test
    public void should_assert_booleans() {
        boolean actualValue = true;
        assertThat(actualValue).isTrue().isEqualTo(true).isNotEqualTo(false);
        Boolean actualObject = Boolean.FALSE;
        assertThat(actualObject).isFalse().isEqualTo(false).isNotEqualTo(true);
    }

    @Test
    public void should_assert_strings() {
        String actual = "My String";
        assertThat(actual)
                .isEqualToIgnoringCase(actual.toLowerCase())
                .isEqualTo("My String")
                .startsWith("My")
                .endsWith("ing")
                .hasLineCount(1)
                .hasSize(actual.length());
    }

    @Test
    public void should_assert_objects() {
        Form actual = new Form(123, "First Name", "Last Name", "1 baker street", "London", "England", "+440102030405");
        Form expectedId = new Form(123, null, null, null, null, null, null);
        Form expectedFields = new Form(123, "First Name", "Last Name", null, null, null, null);

        assertThat(actual).isEqualToComparingFieldByField(actual);
        assertThat(actual).isEqualToIgnoringNullFields(expectedId);
        assertThat(actual).isEqualToComparingOnlyGivenFields(expectedFields, "id", "lastName", "firstName");
    }

    private class Form {
        private final Integer id;
        private final String firstName;
        private final String lastName;
        private final String adress;
        private final String city;
        private final String country;
        private final String phoneNumber;

        public Form(int id, String firstName, String lastName, String adress, String city, String country, String phoneNumber) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.adress = adress;
            this.city = city;
            this.country = country;
            this.phoneNumber = phoneNumber;
        }
    }
}
