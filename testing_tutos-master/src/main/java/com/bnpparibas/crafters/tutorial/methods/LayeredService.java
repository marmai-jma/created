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

    public void dataPublisher(DataRef data) {
        dataService.save(data);
    }

    public DataRef dataProducerFiltered(UUID id) {
        return dataService.fetchDataBy(id);
    }

    public DataRef businessDataIntegration(UUID id) {
        Object otherInfo = dataService.fetchEnrichmentDataFromOtherSource(id);
        DataRef complexComputationResult = new DataRef(id, otherInfo);
        dataService.save(complexComputationResult);
        return complexComputationResult;

    }
}
