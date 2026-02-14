package com.airtribe.meditrack.entity;

public abstract class Person extends MedicalEntity {

    public String name;
    protected int age;

    public Person(String id, String name, int age) {
        super(id); // constructor chaining
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public void displayInfo() {
        System.out.println("ID: " + id + " Name: " + name + " Age: " + age);
    }
}