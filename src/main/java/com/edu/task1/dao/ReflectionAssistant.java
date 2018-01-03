package com.edu.task1.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by damager on 24.12.17.
 */
public class ReflectionAssistant {
    public static List getAllFields(Class clazz, List list) {
        Field[] fields = clazz.getDeclaredFields();
        list.addAll(Arrays.asList(fields));

        Class sClazz = clazz.getSuperclass();
        if (sClazz != null) {
            getAllFields(sClazz, list);
        }

        return list;
    }

    public static void setValue(Object object, String value, Field field) throws IllegalAccessException {
        field.setAccessible(true);
        Type type = field.getType();

        String typeName = type.getTypeName();
        switch (typeName) {
            case "java.lang.String":
                field.set(object, value);
                break;
            case "int":
                field.setInt(object, Integer.parseInt(value));
                break;
            case "byte":
                field.setByte(object, Byte.parseByte(value));
                break;
            case "short":
                field.setShort(object, Short.parseShort(value));
                break;
            case "char":
                field.setChar(object, Character.valueOf(value.charAt(0)));
                break;
            case "long":
                field.setLong(object, Long.parseLong(value));
                break;
            case "float":
                field.setFloat(object, Float.parseFloat(value));
                break;
            case "double":
                field.setDouble(object, Double.parseDouble(value));
                break;
            case "boolean":
                field.setBoolean(object, Boolean.parseBoolean(value));
                break;
            case "java.util.Date":
                field.set(object, parseValueToDate(value));
                break;
            case "java.lang.Integer":
                field.set(object, Integer.parseInt(value));
                break;
            case "java.lang.Byte":
                field.set(object, Byte.parseByte(value));
                break;
            case "java.lang.Short":
                field.set(object, Short.parseShort(value));
                break;
            case "java.lang.Character":
                field.set(object, Character.valueOf(value.charAt(0)));
                break;
            case "java.math.BigDecimal":
                field.set(object, BigDecimal.valueOf(Double.parseDouble(value)));
                break;
            case "java.math.BigInteger":
                field.set(object, BigInteger.valueOf(Long.parseLong(value)));
                break;
            case "java.lang.Long":
                field.set(object, Long.parseLong(value));
                break;
            case "java.lang.Float":
                field.set(object, Float.parseFloat(value));
                break;
            case "java.lang.Double":
                field.set(object, Double.parseDouble(value));
                break;
            case "java.lang.Boolean":
                field.set(object, Boolean.valueOf(value));
        }
    }

    public static Date parseValueToDate(String value){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
            Date date = dateFormat.parse(value);

            return date;
        }catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> getPrimitiveTypeAsString(boolean asArray) {
        List<String> list = new ArrayList<>();
        list.add("byte");
        list.add("short");
        list.add("char");
        list.add("int");
        list.add("long");
        list.add("float");
        list.add("double");
        list.add("boolean");

        if (asArray) {
            for (int i = 0; i < list.size(); i++) {
                list.set(i, list.get(i).concat("[]"));
            }
        }

        return list;
    }

    public static List<String> getConteinerPrimitiveTypeAsString() {
        List<String> list = new ArrayList<>();
        list.add("java.lang.Byte");
        list.add("java.lang.Short");
        list.add("java.lang.Character");
        list.add("java.lang.Integer");
        list.add("java.math.BigDecimal");
        list.add("java.math.BigInteger");
        list.add("java.lang.Long");
        list.add("java.lang.Float");
        list.add("java.lang.Double");
        list.add("java.lang.Boolean");

        return list;
    }
}
