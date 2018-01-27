package com.edu.task1.dao;


import com.edu.task1.helpers.ReflectionHelper;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by damager on 15.12.17.
 */
public class CsvPersisterDAO implements PersisterDao {
    private String subCatalogName = "CSV";
    private String postFixFile = ".csv";

    @Override
    public void save(List list, String fileName) {
        fileName = getFileNameWithPostFix(fileName);

        Class listClass = list.get(0).getClass();
        List<Field> fields = new ArrayList<>();
        fields = ReflectionHelper.getAllFields(listClass, fields);

        int fieldCount = fields.size();
        String[] arrField = new String[fieldCount];
        for (int i = 0; i <= fieldCount - 1; i++) {
            arrField[i] = fields.get(i).getName();
        }

        String str = String.join(";", arrField).concat("\n");

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(catalogName, subCatalogName, fileName))) {
            writer.write(listClass.getName().concat("\n"));
            writer.write(str);

            for (Object object : list) {

                String[] arrStr = new String[fieldCount];
                for (int i = 0; i <= fieldCount - 1; i++) {
                    fields.get(i).setAccessible(true);
                    arrStr[i] = fields.get(i).get(object).toString();
                }
                writer.write(String.join(";", arrStr).concat("\n"));
            }
        } catch (InvalidPathException e) {
            System.err.println("Oшибкa указания пути " + fileName + e);
        } catch (IOException e) {
            System.err.println("Oшибкa ввода-вывода : " + fileName + e);
        } catch (IllegalAccessException e) {
            System.err.println("Oшибкa рефлексии : " + fileName + e);
        }
    }

    @Override
    public List load(String fileName) {
        List list = new ArrayList();

        try {
            fileName = getFileNameWithPostFix(fileName);
            List<String> arrStr = Files.readAllLines(Paths.get(catalogName, subCatalogName, fileName), StandardCharsets.UTF_8);

            String className = arrStr.get(0).replace("\n", "");
            Class entityClass = Class.forName(className);
            List<Field> fields = new ArrayList<>();
            fields = ReflectionHelper.getAllFields(entityClass, fields);

            String[] arrField = arrStr.get(1).split(";");
            arrStr.remove(0);
            arrStr.remove(0);

            for (String line : arrStr) {
                Object entityObject = entityClass.newInstance();

                String[] subStr = line.split(";");
                for (int i = 0; i <= arrField.length - 1; i++) {
                    Field field = fields.get(i);
                    ReflectionHelper.setValue(entityObject, subStr[i], field);
                }

                list.add(entityObject);
            }
        } catch (IOException e) {
            System.err.println("Oшибкa ввода-вывода : " + fileName + e);
        } catch (ClassNotFoundException e) {
            System.err.println("Класс не найден  : " + fileName + e);
        } catch (InstantiationException e) {
            System.err.println("Oшибкa Создания класса : " + fileName + e);
        } catch (IllegalAccessException e) {
            System.err.println("Oшибкa рефлексии : " + fileName + e);
//        } catch (NoSuchFieldException e) {
//            System.err.println("Oшибкa рефлексии, нет такого поля : " + fileName + e);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Пустой массив : " + fileName + e);
        }

        return list;
    }

    private String getFileNameWithPostFix(String fileName) {
        return fileName.concat(postFixFile);
    }

}
