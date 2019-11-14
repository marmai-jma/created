package com.bnpparibas.crafters.tutorial.mockito;

import com.bnpparibas.crafters.tutorial.Given;
import com.bnpparibas.crafters.tutorial.assertj.Customer;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static com.bnpparibas.crafters.tutorial.Given.theCustomerWithId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class MockitoTest {

    @Rule
    public MockitoRule mockingRules = MockitoJUnit.rule();

    @Mock
    DataPersistence dep;

    @Spy
    DataSource pkGenerator;

    @Captor
    ArgumentCaptor<Customer> customers;

    @InjectMocks
    Service toBeTested;

    @Test
    public void should_see_external_side_effects() {
        //given:
        doReturn(256, 257).when(dep).publish(customers.capture());
        Customer persona = Given.theCustomerWithName("Tom");
        Customer buildByCriteria = theCustomerWithId(42);
        Customer anotherPersona = Given.theCustomerWithName("Matt");

        //when:
        int customerCount = toBeTested.saveCustomers(
                persona,
                buildByCriteria,
                anotherPersona
        );

        //then:
        // return value is:
        assertThat(customerCount).isEqualTo(3);

        // real data provider called ?
        verify(pkGenerator, times(2)).getNextCustomerPk();

        // side effect is published ?
        assertThat(customers.getAllValues())
                .contains(buildByCriteria)
                .extracting("firstName")
                .containsSubsequence("Tom", "Matt");
    }

}
