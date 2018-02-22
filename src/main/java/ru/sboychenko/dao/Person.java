package ru.sboychenko.dao;

public class Person {


    private Integer id;
    private String name;

    public Person() {
        this.id = 0;
        this.name = "";
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

}
