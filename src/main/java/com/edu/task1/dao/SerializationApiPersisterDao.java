package com.edu.task1.dao;

import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SerializationApiPersisterDao implements PersisterDao {
    private String subCatalogName = "Serialization";

    @Override
    public void save(List list, String fileName) {
        try (ObjectOutputStream objOutStream =
                     new ObjectOutputStream(
                             new BufferedOutputStream(Files.newOutputStream(Paths.get(catalogName, subCatalogName, fileName))))) {
            for (Object object: list) {
                objOutStream.writeObject(object);
            }
        } catch (InvalidPathException e) {
            System.err.println("Oшибкa указания пути " + fileName + e);
        } catch (IOException e) {
            System.err.println("Oшибкa ввода-вывода : " + fileName + e);
        }
    }

    @Override
    public List load(String fileName) {
        List list = new ArrayList();

        try (ObjectInputStream objInpStream = new ObjectInputStream(
                new BufferedInputStream(Files.newInputStream(Paths.get(catalogName, subCatalogName, fileName))))) {
            while (true) {
                Object object = objInpStream.readObject();
                list.add(object);
            }
        } catch (EOFException e) {

        } catch (NoSuchFileException e) {
            System.err.println("Файл не существует " + fileName + e);
        } catch (InvalidPathException e) {
            System.err.println("Oшибкa указания пути " + fileName + e);
        } catch (IOException e) {
            System.err.println("Oшибкa ввода- вывода : " + fileName + e);
        } finally {
            return list;
        }
    }
}
