package com.gadroves.gsisinve.model.daos.DAOInterfaces;

import java.util.Collection;
import java.util.function.Predicate;

/**
 * Created by Casa on 18/03/2015.
 */
public interface IntermediateInsert<T> {
    /**
     * Inserta la referencia segun su estado actual en la DB
     * @param ref la referencia a Insertar
     * @return true si se logro insertar
     */
    public boolean single(T ref);

    /**
     * INserta la collecion de refencias
     * @param refs una colleccion con todos los objetos a insertar
     * @return true si almenos uno se inserta
     */
    public boolean aCollection(Collection<T> refs);

    /**
     * Inserta los objectos de la colleccion que cumplan con las condiciones
     * @param refs la colleccion con todos los objetos a insertar
     * @param conds las condiciones a cumplir
     * @return
     */
    public boolean aCollectionWhere(Collection<T> refs, Predicate<T>... conds);
}
