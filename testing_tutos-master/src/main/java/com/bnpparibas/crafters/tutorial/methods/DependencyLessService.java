package com.bnpparibas.crafters.tutorial.methods;

import java.util.UUID;

/**
 * Yes, this could be a business domain object.
 */
public class DependencyLessService {

    // internal state is an implementation detail !
    // you shall test side effects only, never test implementation details :-)
    private long internalCounter;
    private DataRef data;

    //@Autowired
    public DependencyLessService() {
    }

    public void signalConsumer() {
        internalCounter++;
    }

    public long signalProducer() {
        return internalCounter;
    }

    public void dataConsumer(DataRef data) {
        this.data = data;
    }

    public DataRef dataProducerBy(UUID id) {
        return new DataRef(id, null);
    }

}
