package com.bnpparibas.crafters.tutorial.methods;

import com.bnpparibas.crafters.tutorial.mockito.DataSource;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;

import static org.junit.Assert.*;

//@ExtendWith(MockitoExtension.class) // en Junit 5
@RunWith(MockitoJUnitRunner.class)  // en Junit 4
public class LayeredServiceTest {
    DataLayer dataSource;
    LayeredService service;

    @Before  //appelée avant chaque jeu de test
    public void configureMock() throws Exception {

        // Factorisation de la création du service
        dataSource = Mockito.spy(DataLayer.class); // dataSource ne doit pas être en variable locale pour être utilisé en tests
        service = new LayeredService(dataSource);
    }

    @Test
    public void mock_should_return_provided_instance(){
        UUID uuid = UUID.randomUUID();
        DataRef dataFromDependency = new DataRef(UUID.randomUUID(),"donnees");

        // Les lignes 33 et 34 sont équivalentes à 37 et 38
        Mockito.doReturn(dataFromDependency)
                .when(dataSource).fetchDataBy(Mockito.any(UUID.class)); // when sur un stubber


        Mockito.when(dataSource.fetchDataBy(Mockito.any(UUID.class)))  //when sur une méthode du mock de dataSource
                .thenReturn(dataFromDependency);

        DataRef dataRef = service.dataProducerFiltered(UUID.randomUUID());
        System.out.println("result = "+dataRef);  //Par défaut, le mock crée du null, tant qu'on ne manipule rien, ça va
                                                    // RQ un test sans assert est passant mais ne teste rien
        Assertions.assertThat(dataRef).isSameAs(dataFromDependency);
    }

    ArgumentCaptor<DataRef> dataCaptor = ArgumentCaptor.forClass(DataRef.class); // pourrait être dans le test si utilisé
    //une seule fois, sinon le mettre dans le setup tout en haut
    @Test
    public void mock_should_capture_published_data(){
        DataRef dataToDependency = new DataRef(UUID.randomUUID(),"data to publish");

        // mettre un spy en haut et configurer via doNothing un retour void quand on appelle save, ça permet de
        //mocker une methode et pas une classe toute entière
        Mockito.doNothing()
                .when(dataSource).save(Mockito.any(DataRef.class));

        service.dataPublisher(dataToDependency);

        //verifier que sur dataSource on a appelé la methode save avec les données que je veux capturer via dataCaptor
        Mockito.verify(dataSource).save(dataCaptor.capture());   //verifie que la méthode save a été appelée
                                                                // capture l'argument utilisé
        //Mockito.verify(dataSource, new Times(1)).save(dataCaptor.capture());  //verif 1 seul appel
        System.out.println("captured = "+dataCaptor.getValue());
        Assertions.assertThat(dataCaptor.getValue()).isSameAs((dataToDependency)); //verifie que l'arg capturé est celui
                                                                                    // qu'on a passé.
    }
}