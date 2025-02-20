package com.btcag.bootcamp.DatabaseEntities;

import org.hibernate.Session;

public class PostService {


    public static void postRobot(Robots robots)  {


        Session session = Connection.getSession();
        session.beginTransaction();

        session.save(robots);
        session.getTransaction().commit();
        session.close();
    }


}