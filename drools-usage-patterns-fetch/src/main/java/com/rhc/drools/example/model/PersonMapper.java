package com.rhc.drools.example.model;

import com.rhc.drools.example.persistence.Person;

/**
 * Created by srang on 8/2/16.
 */
public class PersonMapper {
    public static MyPerson unMap(Person person) {
        return new MyPerson(NameMapper.unMap(person.getName()), person.getAge());
    }

    public static Person peopleMap(MyPerson person) {
        return new Person(NameMapper.nameMap(person.getName()), person.getAge());
    }
}
