package com.gadroves.gsisinve.model;

import org.jinq.jpa.JinqJPAStreamProvider;
import org.jinq.orm.stream.JinqStream;

import javax.persistence.*;

/**
 * Created by geykel on 8/04/2015.
 */
public final class DBAccess {

    private static DBAccess db = null;
    private EntityManagerFactory entityManagerFactory;
    private EntityManager em;
    private JinqJPAStreamProvider streams;

    private DBAccess() {
        this.Open();
    }

    public static DBAccess getInstance() {
        if (db == null) db = new DBAccess();
        return db;
    }

    public <U> JinqStream<U> Stream(Class<U> entity) {
        return streams.streamAll(em, entity);
    }

    public void Insert(Object var) throws Exception {
        em.persist(var);
    }

    public <T> T Update(T t) throws Exception {
        return em.merge(t);
    }

    public void Delete(Object var) throws Exception {
        em.remove(var);
    }

    public EntityTransaction getTransaction() {
        return this.em.getTransaction();
    }

    public boolean isOpen() {
        return em.isOpen();
    }

    public void Open() {
        entityManagerFactory = Persistence.createEntityManagerFactory("DB_SISGADROVES");
        em = entityManagerFactory.createEntityManager();
        streams = new JinqJPAStreamProvider(entityManagerFactory);
    }

    public void Close() {
        em.close();
        entityManagerFactory.close();
    }
    public <U> TypedQuery<U> TypedsqlQuery(String sql, Class<U> uClass){
        return  em.createQuery(sql, uClass);
    }
    public  Query nativeSqlQuery(String sql){
        return em.createNativeQuery(sql);
    }
}
