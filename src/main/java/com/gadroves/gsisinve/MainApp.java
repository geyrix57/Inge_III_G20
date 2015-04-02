package com.gadroves.gsisinve;
/**
 * Created by geykel on 04/03/2015.
 */

import com.gadroves.gsisinve.UI.controller.ScreensController;
import com.gadroves.gsisinve.UI.window.CustomWindow;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    private ScreensController mainContainer = new ScreensController();

    public static void main(String[] args) {
        launch(args);
        /*EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("DB_SISGADROVES");
        EntityManager em = entityManagerFactory.createEntityManager();

        JinqJPAStreamProvider streams = new JinqJPAStreamProvider(entityManagerFactory);
        JinqStream<TbBodega> Bodegas = streams.streamAll(em, TbBodega.class);

        Bodegas.toList().forEach(System.out::println);

        TbBodega b = new TbBodega();
        b.setCode("7");
        b.setDesc("Limon");

        em.getTransaction().begin();
        em.persist(b);
        em.getTransaction().commit();


        em.close();
        entityManagerFactory.close();*/

        //System.out.println(Tb_Test.where(t->t.getVal() > 25).getDebugQueryString());
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        if(mainContainer.setScreen("main"))
            new CustomWindow(primaryStage,mainContainer)
                    .show();
    }

}
