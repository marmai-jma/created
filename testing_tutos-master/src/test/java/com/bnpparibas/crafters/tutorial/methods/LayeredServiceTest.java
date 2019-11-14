package com.bnpparibas.crafters.tutorial.methods;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;

import static org.junit.Assert.*;

//@ExtendWith(MockitoExtension.class) // en Junit 5
@RunWith(MockitoJUnitRunner.class)  // en Junit 4
public class LayeredServiceTest {
    LayeredService service;

    @Before  //appelée avant chaque jeu de test
    public void configureMock() throws Exception {

        // Factorisation de la création du service
        //DataLayer dataSource = new DataLayer();   // la vraie classe => exception
        DataLayer dataSource = Mockito.mock(DataLayer.class); // la version mockée de la ligne du dessus
        service = new LayeredService(dataSource);
    }

    @Test
    public void rename_me(){
        DataRef dataRef = service.dataProducerFiltered(UUID.randomUUID());
        System.out.println("result = "+dataRef);  //Par défaut, le mock crée du null, tant qu'on ne manipule rien, ça va
                                                    // RQ un test sans assert est passant mais ne teste rien
    }

}