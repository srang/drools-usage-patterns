package com.rhc.drools.example.model;

import com.rhc.drools.example.persistence.Name;

/**
 * Created by srang on 8/2/16.
 */
public class NameMapper {
    public static MyName nameMap(Name name)  {
        return new MyName(name.getFirstName(), name.getLastName());
    }

    public static Name unMap(MyName name) {
        return new Name(name.getFirstName(),name.getLastName());
    }
}
