package com.btcag.bootcamp.DatabaseEntities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;



import java.math.BigDecimal;


@Entity
@Table(name = "Robots")
public class Robot {


    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name = null;

    @Column(name = "health")
    private BigDecimal health = null;

    @Column(name = "attackDamage")
    private BigDecimal attackDamage = null;

    @Column(name = "attackRange")
    private BigDecimal attackRange = null;

    @Column(name = "movementRate")
    private BigDecimal movementRate = null;

    public Robot() {
    }


    public Robot(String name, BigDecimal health, BigDecimal attackDamage, BigDecimal attackRange, BigDecimal movementRate) {
        this.name = name;
        this.health = health;
        this.attackDamage = attackDamage;
        this.attackRange = attackRange;
        this.movementRate = movementRate;
    }


    public String getName() {
        return name;
    }

    public BigDecimal getHealth() {
        return health;
    }

    public BigDecimal getAttackDamage() {
        return attackDamage;
    }

    public BigDecimal getAttackRange() {
        return attackRange;
    }

    public BigDecimal getMovementRate() {
        return movementRate;
    }

    public int getId() {
        return id;
    }
}
