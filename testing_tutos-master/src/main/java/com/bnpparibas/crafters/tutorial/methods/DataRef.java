package com.bnpparibas.crafters.tutorial.methods;

import java.util.UUID;

public class DataRef {
    private UUID id;

    private Object preciousInformation;

    public DataRef(UUID id, Object data) {
        this.id = id;
        preciousInformation = data;
    }

    @Override
    public String toString() {
        return "DataRef{" +
                "id=" + id +
                ", preciousInformation=" + preciousInformation +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public Object getPreciousInformation() {
        return preciousInformation;
    }

    public DataRef seriousBusinessComputation() {
        if (preciousInformation == null) {
            return new DataRef(id, id);
        }
        return this;
    }

    public DataRef seriousBusinessComputation(Object argument) {
        if (argument == null) {
            return new DataRef(id, id);
        }
        return new DataRef(id, argument);
    }

/* Do you feel like equals is missing ?
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataRef dataRef = (DataRef) o;
        return Objects.equals(id, dataRef.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
*/

}
