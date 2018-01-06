package com.edu.task1.dao;

import com.edu.task1.entity.Machine;
import com.google.gson.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by damager on 03.01.18.
 */
public class GsonPersisterDao implements PersisterDao {
    private String subCatalogName = "json";
    private String postfixFile = ".json";
    private Gson gson;

    public GsonPersisterDao() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Machine.class, new MachineAdapter());
        gson = gsonBuilder.create();
    }

    @Override
    public void save(List list, String fileName) {
        fileName = getFileNameWithPostFix(fileName);

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(catalogName, subCatalogName, fileName))) {
            Class listClass = list.get(0).getClass();
            writer.write(listClass.getName().concat("\n"));

            for (Object object : list) {
                String str = gson.toJson(object);
                writer.write(str.concat("\n"));
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

        try {
            fileName = getFileNameWithPostFix(fileName);
            List<String> listStrings = Files.readAllLines(Paths.get(catalogName, subCatalogName, fileName), StandardCharsets.UTF_8);

            String className = listStrings.get(0).replace("\n", "");
            Class entityClass = Class.forName(className);
            listStrings.remove(0);

            for (String str : listStrings) {
                Object object = gson.fromJson(str, entityClass);
                list.add(object);
            }
        } catch (IOException e) {
            System.err.println("Oшибкa ввода-вывода : " + fileName + e);
        } catch (ClassNotFoundException e) {
            System.err.println("Класс не найден  : " + fileName + e);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Пустой массив : " + fileName + e);
        } catch (NullPointerException e) {
            System.err.println("Пустой массив строк файла : " + fileName + e);
        }

        return list;
    }

    private String getFileNameWithPostFix(String fileName) {
        return fileName.concat(postfixFile);
    }

    class MachineAdapter implements JsonSerializer<Machine>, JsonDeserializer<Machine> {
        private final String CLASSNAME = "CLASSNAME";
        private final String INSTANCE = "INSTANCE";

        @Override
        public Machine deserialize(JsonElement jElement, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jObject = jElement.getAsJsonObject();
            JsonPrimitive jPrimitive = (JsonPrimitive) jObject.get(CLASSNAME);
            String className = jPrimitive.getAsString();

            Class<?> klass = null;
            try {
                klass = Class.forName(className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new JsonParseException(e.getMessage());
            }

            return context.deserialize(jObject.get(INSTANCE), klass);
        }

        @Override
        public JsonElement serialize(Machine machine, Type type, JsonSerializationContext context) {
            JsonObject jObject = new JsonObject();
            String className = machine.getClass().getName();
            jObject.addProperty(CLASSNAME, className);
            JsonElement jElement = context.serialize(jObject);
            jObject.add(INSTANCE, jElement);

            return jObject;
        }
    }
}
