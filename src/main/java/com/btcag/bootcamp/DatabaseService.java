package com.btcag.bootcamp;
import com.btcag.bootcamp.DatabaseEntities.Connection;
import com.btcag.bootcamp.DatabaseEntities.PostService;
import com.btcag.bootcamp.DatabaseEntities.Robots;
import org.hibernate.Session;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/database")
public class DatabaseService {
    private static final String DB_URL = "jdbc:sqlite:src\\main\\resources\\RobotWars.db";

    public DatabaseService(){
    }





    @GetMapping("/robots/all")
    public List<Robots> getAllRobots(){
        Session session = Connection.getSession();
        return session.createQuery( "from Robots", Robots.class).getResultList();
    }




    @DeleteMapping("/robots/remove/id/{id}")
    public void removeById(@PathVariable(value = "id") String uid){
        Session session = Connection.getSession();
        System.out.println(session.createQuery( "delete from Robots r where r.id = :id", Robots.class).setParameter("id", uid).executeUpdate());
    }

    @GetMapping("robots/get/id/{id}")
    public Robots seeSpecificItem(@PathVariable(value = "id") String id){
        Session session = Connection.getSession();
        return session.createQuery( "select r from Robots r where r.id = :id", Robots.class).setParameter("id",id).getSingleResult();


    }



    @GetMapping
    public String sayHello(){
        return "Henlo Frens";
    }

    @PostMapping(value = "robots/add",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void addToList(@RequestBody Robots r){
        PostService.postRobot(r);
        Session session = Connection.getSession();
        Long count = session.createQuery("Select COUNT(r) FROM Robots r", Long.class).getSingleResult();
        System.out.println("Elements: "+ count);
        session.close();
    }
}
