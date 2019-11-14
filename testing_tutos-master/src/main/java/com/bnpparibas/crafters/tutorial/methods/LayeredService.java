package com.bnpparibas.crafters.tutorial.methods;

import java.util.UUID;

/**
 * Yes, this is the service you meet everywhere.
 */
public class LayeredService {

    private final DataLayer dataService;

    //@Autowired
    public LayeredService(DataLayer dataSource) {
        dataService = dataSource;
    }
    // Ici on a des méthodes qui modifient les données.
    //ecriture en base simulé qu'on mockera
    public void dataPublisher(DataRef data) {
        dataService.save(data);
    }

    //methode qui simule un accès à des données cad une methode de lecture de données. DataService est bidon.
    //En test, on va mocker le fetch pour retourner un dataRef.
    //rq: ici cette methode ne fait rien de plus que l'appel, donc quand on aura mocké l'appel, on n'aura plus rien à tester.
    public DataRef dataProducerFiltered(UUID id) {
        return dataService.fetchDataBy(id);
    }

    //chercher donner puis publier - version super simple
    public DataRef businessDataIntegration(UUID id) {
        Object otherInfo = dataService.fetchEnrichmentDataFromOtherSource(id);
        DataRef complexComputationResult = new DataRef(id, otherInfo);
        dataService.save(complexComputationResult);
        return complexComputationResult;

    }
}
