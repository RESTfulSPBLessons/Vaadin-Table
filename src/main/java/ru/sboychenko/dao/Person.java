package ru.sboychenko.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Random;

/**
 * Created by Anton on 16.02.2018.
 */
//@Entity
public class Person {

    Random rand = new Random();

  //  @Id
  //  @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private  String name;



    public Person() {
        this.id = randInt(1,100);
        this.name = String.valueOf(this.id);
    }


    public Person(String name) {
        this.name = name;
        this.id = id;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer randInt(int min, int max) {

        Integer randomNum = rand.nextInt((max - min) + 1) + min;


        return randomNum;
    }

}
