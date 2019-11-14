package com.bnpparibas.crafters.tutorial.assertj;

import com.bnpparibas.crafters.tutorial.Given;
import org.assertj.core.groups.Tuple;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertJCollectionTest {

    private List<Customer> givenACollection = new ArrayList<Customer>() {{
        add(new Customer(1, "Tom", "Cat", null));
        add(new Customer(2, "Tiger", "Password", null));
        add(new Customer(3, "Admin", "Admin", null));
        add(new Customer(4, "Prénom", "Nom", null));
        add(new Customer(5, "first", "last", null));
    }};

    @Test
    public void should_assert_collection() {
        assertThat(givenACollection)
                .hasSize(givenACollection.size())
                .contains(Given.theCustomerWithId(3))
                .doesNotContain(Given.theCustomerWithId(6))
                .containsExactlyInAnyOrder(
                        Given.theCustomerWithId(5),
                        Given.theCustomerWithId(4),
                        Given.theCustomerWithId(3),
                        Given.theCustomerWithId(2),
                        Given.theCustomerWithId(1)
                )
                .extracting("firstName", "lastName")
                .containsExactly(
                        new Tuple("Tom", "Cat"),
                        new Tuple("Tiger", "Password"),
                        new Tuple("Admin", "Admin"),
                        new Tuple("Prénom", "Nom"),
                        new Tuple("first", "last"));
        assertThat(givenACollection)
                .extracting("id")
                .containsExactly(1, 2, 3, 4, 5);
    }
}
