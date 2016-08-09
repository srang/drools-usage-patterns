package com.rhc.drools.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;


/**
 * Created by srang on 8/9/16.
 */
public class Mapper<T,Y> {
    private static final String PACKAGE = "com.rhc.drools.example";
    private Class clatt;
    private Class clayy;
    public Mapper(Class clatt, Class clayy) {
        this.clatt = clatt;
        this.clayy = clayy;
        System.out.println("Create a new mapper");
    }

    public Y doMap(T base) throws MappingException{
        return (Y)mappy(base, clayy);
    }

    public T unMap(Y base) throws MappingException{
        return (T)mappy(base, clatt);
    }

    private Object mappy(Object toMap, Class targetClass) throws MappingException{
        Class sourceClass = toMap.getClass();
        Object mapped = construct(sourceClass);
        for(Field targetField : targetClass.getFields()) {
            Field sourceField = matchField(sourceClass, targetField);
            setField(toMap, sourceField, mapped, targetField);
        }

        return mapped;
    }

    private Object construct(Class clazz) throws MappingException{
        Object obj = null;
        try {
            obj = clazz.getConstructor().newInstance();
        } catch (InstantiationException|IllegalAccessException|InvocationTargetException|NoSuchMethodException e) {
            e.printStackTrace();
            throw new MappingException("Target class " + clazz.getName() + " could not be instantiated");
        }
        return obj;
    }

    private Field matchField(Class targetClass, Field sourceField) throws MappingException{
        FieldComp comp = new FieldComp();
        for(Field targetField : targetClass.getFields()) {
            if(comp.compare(targetField, sourceField) == 0) {
                return targetField;
            }
        }
        throw new MappingException("Matching field could not be found for field " + sourceField.getName() + " on class " + targetClass.getName());
    }

    private void setField(Object sourceObj, Field sourceField, Object targetObj, Field targetField) throws MappingException{
        Object value = null;
        try {
            value = sourceField.get(sourceObj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new MappingException("Could not get field " + sourceField.getName() + " for object of class " + sourceObj.getClass().getName());
        }
        if (sourceField.getType().equals(targetField.getType())) {
            try {
                targetField.set(targetObj, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else if (sourceField.getType().getPackage().getName().startsWith(PACKAGE)) {

        } else {
            throw new MappingException("Source field " + sourceField.getName() + " and target field " + targetField.getName() + " types mismatch and are not mappable");
        }
    }



    private class FieldComp<Field> implements Comparator<Field> {
        @Override
        public int compare(Field field, Field t1) {
            return 0;
        }
        private String normalizeField(java.lang.reflect.Field field) {
            String f = field.getName();
            f = f.toLowerCase();
            //strip out extra chars
            return f;
        }
    }
}
