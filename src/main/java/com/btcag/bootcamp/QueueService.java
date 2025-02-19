
package com.btcag.bootcamp;
import com.btcag.bootcamp.DatabaseEntities.Robot;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/queue")
public class QueueService {
    public QueueService(){

        String name = "r1", name2 = "r2";
        BigDecimal helth1 = BigDecimal.valueOf(1), dmg1 = BigDecimal.valueOf(1), range1 = BigDecimal.valueOf(1), move1 = BigDecimal.valueOf(1);
        BigDecimal helth2 = BigDecimal.valueOf(2), dmg2 = BigDecimal.valueOf(2), range2 = BigDecimal.valueOf(2), move2 = BigDecimal.valueOf(2);

        Robot robot1 = new Robot(name, helth1, dmg1, range1, move1);
        Robot robot2 = new Robot(name2, helth2, dmg2, range2, move2);

        robots.pushFront(robot1);
        robots.pushLast(robot2);

    }
    private PQueueListGeneric<Robot> robots = new PQueueListGeneric<>();

    @GetMapping("/seek")
    public Robot seeFirstItem(){
        return robots.get(0);
    }
    @GetMapping("/pop")
    public Robot removeFirstItem(@RequestParam(name = "front", defaultValue = "true") boolean fromFront ){
        if (fromFront){
            System.out.println("FROMFRONT");
            return robots.popFront();
        }
        System.out.println("FROMLAST");
        Robot r =  robots.popLast();
        return r;
    }

    @GetMapping("/{id}/")
    public Robot seeSpecificItem(@PathVariable(value = "id") int id){
        System.out.println("GET ITEM with id " + id);
        return robots.get(id);
    }

    @GetMapping
    public String sayHello(){
        return "Henlo Frens";
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void addToList(@RequestBody Robot r){
        this.robots.pushFront(r);
        System.out.println("Elements: "+ robots.getElemCnt());

    }
}
 
 