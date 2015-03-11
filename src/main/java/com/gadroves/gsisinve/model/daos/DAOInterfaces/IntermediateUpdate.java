package com.gadroves.gsisinve.model.daos.DAOInterfaces;

import java.util.Collection;
import java.util.function.Predicate;

/**
 * Created by Casa on 10/03/2015.
 */
public interface IntermediateUpdate<Type,PK> {
    /**
     * Updates A single Object
     * @param reference the object to Update on DataSource
     * @return True if update is successful
     */
    public boolean updateSingle(Type reference);

    /**
     * Updates a collection of objects
     * @param collection the collection of objects to be updated
     * @return
     */
    public boolean updateCollection(Collection<Type> collection);

    /**
     * Updates a object if bypasses the condition
     * @param ref the object to be updated
     * @param what the conditions to be checked
     * @return true if update was sucess
     */
    public boolean updateIf(Type ref, Predicate<Type>... what);

    /**
     *
     * @param refs Collection to be updated for each object that bypasses condition
     * @param what Predicates to be checked
     * @return true if at least 1 update was success
     */
    @SuppressWarnings("unchecked")
    public boolean updateCollectionWhen(Collection<Type> refs, Predicate<Type>... what);

}
