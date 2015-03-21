package com.gadroves.gsisinve.model.daos.DAOInterfaces;

import java.util.Collection;
import java.util.function.Predicate;

/**
 * Created by Casa on 18/03/2015.
 */
public interface IntermediateDelete<T> {
    /**
     * Borra una instancia de la DB
     * @param ref el objeto a eliminar
     */
    public boolean single(T ref);

    /**
     * Borra una collecion de onjectos de la DB
     * @param refs la collecion de objetos a borrar
     */
    public void aCollection(Collection<T> refs);

    /**
     * Borra los objetos que cumplan las condiciones en refs
     * @param refs los objetos a testear y borrar
     * @param conds las condiciones para borrar
     */
    public void aCollectionWhere(Collection<T> refs,Predicate<T>... conds);
}
