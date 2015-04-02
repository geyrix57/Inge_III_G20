package com.gadroves.gsisinve;
/**
 * Created by geykel on 04/03/2015.
 */

import com.gadroves.gsisinve.UI.controller.ScreensController;
import com.gadroves.gsisinve.UI.window.CustomWindow;
import com.gadroves.gsisinve.model.entities.TbFacturaVenta;
import javafx.application.Application;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;

public class MainApp extends Application {

    private ScreensController mainContainer = new ScreensController();

    public static void main(String[] args) {
        //launch(args);
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("DB_SISGADROVES");
        EntityManager em = entityManagerFactory.createEntityManager();

        /*JinqJPAStreamProvider streams = new JinqJPAStreamProvider(entityManagerFactory);
        JinqStream<TbFacturaVenta> Bodegas = streams.streamAll(em, TbFacturaVenta.class);

        Bodegas.toList().forEach(System.out::println);*/

        TbFacturaVenta fv = new TbFacturaVenta();
        fv.setFacDate(new Date(new java.util.Date().getTime()));
        fv.setSub(new BigDecimal(123));
        fv.setTotal(new BigDecimal(159));
        fv.setTax(new BigDecimal(25));
        fv.setAddress("Hola");

        em.getTransaction().begin();
        em.persist(fv);
        em.getTransaction().commit();


        em.close();
        entityManagerFactory.close();

        //System.out.println(Tb_Test.where(t->t.getVal() > 25).getDebugQueryString());
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        if(mainContainer.setScreen("main"))
            new CustomWindow(primaryStage,mainContainer)
                    .show();
    }

}
