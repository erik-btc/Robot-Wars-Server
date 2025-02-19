package com.btcag.bootcamp.DatabaseEntities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class PostService {


    public static void postRobot(Robot robot)  {

       SessionFactory sessionFactory = Connection.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(robot);
        session.getTransaction().commit();
        session.close();
        Connection.shutdown();

    }


}
