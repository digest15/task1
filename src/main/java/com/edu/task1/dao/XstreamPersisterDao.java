package com.edu.task1.dao;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.reflection.ObjectAccessException;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by damager on 22.01.18.
 */
public class XstreamPersisterDao implements PersisterDao {
    private String subCatalogName = "xStream";
    private String postfixFile = ".xml";
    private XStream xStream;

    public XstreamPersisterDao() {
        xStream = new XStream();
    }

    @Override
    public void save(List list, String fileName) {
        fileName = getFileNameWithPostFix(fileName);

        try (ObjectOutputStream writer = xStream.createObjectOutputStream(Files.newBufferedWriter(Paths.get(catalogName, subCatalogName, fileName)))) {
            for (Object object : list) {
                writer.writeObject(object);
            }
        } catch (InvalidPathException e) {
            System.err.println("Oшибкa указания пути " + fileName + e);
        } catch (IOException e) {
            System.err.println("Oшибкa ввода-вывода : " + fileName + e);
        }
    }

    @Override
    public List load(String fileName) {
        fileName = getFileNameWithPostFix(fileName);
        List list = new ArrayList();

        try (ObjectInputStream inputStream = xStream.createObjectInputStream(
                Files.newInputStream(Paths.get(catalogName, subCatalogName, fileName)))) {
            while (true) {
                Object object = inputStream.readObject();
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

    private String getFileNameWithPostFix(String fileName) {
        return fileName.concat(postfixFile);
    }
}
