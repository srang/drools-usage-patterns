package com.rhc.drools.example;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Comparator;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;


/**
 * Created by srang on 8/9/16.
 */
public class Mapper<T,Y> {
    private static final String PACKAGE = "com.rhc.drools.example";
    private Class sourceClass;
    private Class targetClass;
    private Map<String, String> fieldMappings;
    public Mapper(Class sourceClass, Class targetClass) {
        this.sourceClass = sourceClass;
        this.targetClass = targetClass;
    }

    public void setFieldMappings(Map<String,String> fieldMappings) {
        this.fieldMappings = fieldMappings;
    }

    public Y doMap(T base) throws MappingException{
        return (Y)mappy(base, targetClass);
    }

    public T unMap(Y base) throws MappingException{
        return (T)mappy(base, sourceClass);
    }

    private Object mappy(Object toMap, Class targetClass) throws MappingException{
        Class sourceClass = toMap.getClass();
        Object mapped = construct(targetClass);
        for(Field targetField : targetClass.getDeclaredFields()) {
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
        for(Field targetField : targetClass.getDeclaredFields()) {
            if(compareFieldNames(targetField, sourceField)) {
                return targetField;
            }
        }
        throw new MappingException("Matching field could not be found for field " + sourceField.getName() + " on class " + targetClass.getName());
    }

    private void setField(Object sourceObj, Field sourceField, Object targetObj, Field targetField) throws MappingException{
        Object value = null;
        sourceField.setAccessible(true);
        targetField.setAccessible(true);
        try {
            value = sourceField.get(sourceObj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new MappingException("Could not get field " + sourceField.getName() + " for object of class " + sourceObj.getClass().getName());
        }
        try {
            if (List.class.isAssignableFrom(sourceField.getType()) || List.class.isAssignableFrom(targetField.getType())) {
                if (((Class)((ParameterizedType) sourceField.getGenericType()).getActualTypeArguments()[0]).getPackage().getName().startsWith(PACKAGE)
                        && ((Class)((ParameterizedType) sourceField.getGenericType()).getActualTypeArguments()[0]).getPackage().getName().startsWith(PACKAGE)) {
                    Mapper subMap = new Mapper((Class)((ParameterizedType) sourceField.getGenericType()).getActualTypeArguments()[0], (Class)((ParameterizedType) targetField.getGenericType()).getActualTypeArguments()[0]);
                    subMap.setFieldMappings(this.fieldMappings);
                    List s = (List)sourceField.get(sourceObj);
                    List t = (List)targetField.get(targetObj);
                    for (Object item : s) {
                        t.add(subMap.doMap(item));
                    }
                } else if (((ParameterizedType) sourceField.getGenericType()).getActualTypeArguments()[0].equals(
                          ((ParameterizedType) sourceField.getGenericType()).getActualTypeArguments()[0])) {
                    List s = (List)sourceField.get(sourceObj);
                    List t = (List)targetField.get(targetObj);
                    t.addAll(s);
                }
            } else if (sourceField.getType().equals(targetField.getType())) {
                targetField.set(targetObj, value);
            } else if (sourceField.getType().getPackage().getName().startsWith(PACKAGE)) {
                Mapper subMap = new Mapper(sourceField.getType(), targetField.getType());
                subMap.setFieldMappings(this.fieldMappings);
                targetField.set(targetObj, subMap.doMap(sourceField.get(sourceObj)));
            } else {
                throw new MappingException("Source field " + sourceField.getName() + " and target field " + targetField.getName() + " types mismatch and are not mappable");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new MappingException("Source field " + sourceField.getName() + " and target field " + targetField.getName() + " were not mappable");
        }
    }

    private boolean compareFieldNames(Field f1, Field f2) {
        boolean returnVal = false;
        String field1 = normalizeField(f1);
        String field2 = normalizeField(f2);
        if (field1.equals(field2)) {
            returnVal = true;
        } else if (fieldMappings != null) {
            field1 = f1.getName();
            field2 = f2.getName();
            String val = fieldMappings.get(field1);
            String key = field2;
            if (val == null) {
                val = fieldMappings.get(field2);
                key = field1;
            }
            if (val != null && val.equals(key)) {
                returnVal = true;
            }
        }
        return returnVal;
    }

    private String normalizeField(java.lang.reflect.Field field) {
        String f = field.getName();
        f = f.toLowerCase();
        return f;
    }

}
