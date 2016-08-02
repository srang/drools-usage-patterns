package com.rhc.drools.example.model;

import com.rhc.drools.example.persistence.Person;

/**
 * Created by srang on 8/2/16.
 */
public class PersonMapper {
    public static MyPerson peopleMap(Person person) {
        return new MyPerson(NameMapper.nameMap(person.getName()), person.getAge());
    }

    public static Person unMap(MyPerson person) {
        return new Person(NameMapper.unMap(person.getName()), person.getAge());
    }
}
