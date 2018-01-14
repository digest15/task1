package com.edu.task1.dao;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by damager on 21.12.17.
 */
public class MyTxtFormatSerializationPersisterDao implements PersisterDao {
    private String subCatalogName = "MyTxtFormat";
    private String postfixFile = ".txt";

    @Override
    public void save(List list, String fileName) {
        fileName = getFileNameWithPostFix(fileName);

        Class listClass = list.get(0).getClass();
        List<Field> fields = new ArrayList<>();
        fields = ReflectionAssistant.getAllFields(listClass, fields);

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(catalogName, subCatalogName, fileName))) {
            writer.write(listClass.getName().concat("\n"));
            int fieldCount = fields.size();

            for (Object object : list) {
                String str = "(";
                for (int i = 0; i <= fieldCount - 1; i++) {
                    str = getValueAsString(str, fields.get(i), object);
                }
                writer.write(str.concat(")\n"));
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
            List<String> listStrings = Files.readAllLines(Paths.get(catalogName, subCatalogName, fileName), StandardCharsets.UTF_8);

            String className = listStrings.get(0).replace("\n", "");
            listStrings.remove(0);

            for (String str : listStrings) {
                Object entityObject = getEntityFromString(str, className);
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
//      } catch (NoSuchFieldException e) {
//            System.err.println("Oшибкa рефлексии, нет такого поля : " + fileName + e);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Пустой массив : " + fileName + e);
        } catch (NullPointerException e) {
            System.err.println("Пустой массив строк файла : " + fileName + e);
        }

        return list;
    }

    private Object getEntityFromString(String str, String className) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        str = trimStrRL(str);

        int i = 0;
        int strLength = str.length();
        if (className.equals("")) {
            while (i < strLength) {
                char c = str.charAt(i);
                if (c != ':') {
                    className = className.concat(String.valueOf(c));
                } else {
                    i++;
                    break;
                }
                i++;
            }
            str = str.substring(i, strLength);
        }

        Class entityClass = Class.forName(className);
        List<Field> fields = new ArrayList<>();
        fields = ReflectionAssistant.getAllFields(entityClass, fields);
        Object object = entityClass.newInstance();

        Map<String, String> fieldValue = parseString(str);

        List<String> primitiveTypeList = ReflectionAssistant.getPrimitiveTypeAsString(false);
        List<String> primitiveArrayTypeList = ReflectionAssistant.getPrimitiveTypeAsString(true);
        List<String> conteinerPrimitiveTypeList = ReflectionAssistant.getConteinerPrimitiveTypeAsString();

        for (Field field : fields) {
            field.setAccessible(true);
            String typeName = field.getType().getTypeName();
            String value = fieldValue.get(field.getName());
            if (primitiveTypeList.contains(typeName) ||
                    conteinerPrimitiveTypeList.contains(typeName) ||
                    typeName.equals("java.lang.String") ||
                    typeName.equals("java.util.Date")) {
                ReflectionAssistant.setValue(object, value, field);
            }else if (typeName.equals("java.util.List")) {
                List listValue = getListFromString(value);
                field.set(object, listValue);
            } else {
                Object entityObject = getEntityFromString(value, "");
                field.set(object, entityObject);
            }
        }

        return object;
    }

    private String trimStrRL(String str) {
        int strLength = str.length();
        str = str.substring(1, strLength);
        strLength--;
        str = str.substring(0, strLength-1);
        strLength--;
        return str;
    }

    private List<?> getListFromString(String str) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        List list = new ArrayList<>();

        str = trimStrRL(str);
        int strLength = str.length();

        int i = 0;

        String typeName = "";
        while (i < strLength) {
            char c = str.charAt(i);
            if (c != ':') {
                typeName = typeName.concat(String.valueOf(c));
            } else {
                i++;
                break;
            }
            i++;
        }

        List<String> listStrings = new ArrayList();
        String strValue = "";
        int countSpecSimbol = 0;
        while (i < strLength) {
            char c = str.charAt(i);
            if (c == '[' || c == '{' || c == '(') {
                countSpecSimbol++;
                strValue = strValue.concat(String.valueOf(c));
            } else if (c == ']' || c == '}' || c == ')') {
                countSpecSimbol--;
                strValue = strValue.concat(String.valueOf(c));
            } else if (c == ',' & countSpecSimbol == 0) {
                listStrings.add(strValue);
                strValue = "";
            } else {
                strValue = strValue.concat(String.valueOf(c));
            }
            i++;
        }
        listStrings.add(strValue);

        switch (typeName) {
            case "java.lang.String":
                list = listStrings;
                break;
            case "java.util.Date":
                for (String value : listStrings) {
                    list.add(ReflectionAssistant.parseValueToDate(value));
                }
                break;
            case "java.util.List":
                for (String value : listStrings) {
                    list.add(getListFromString(value));
                }
                break;
            case "java.lang.Integer":
                for (String value : listStrings) {
                    list.add(Integer.parseInt(value));
                }
                break;
            case "java.math.BigDecimal":
                for (String value : listStrings) {
                    list.add(BigDecimal.valueOf(Double.parseDouble(value)));
                }
                break;
            case "java.math.BigInteger":
                for (String value : listStrings) {
                    list.add(BigInteger.valueOf(Integer.parseInt(value)));
                }
                break;
            case "java.lang.Byte":
                for (String value : listStrings) {
                    list.add(Byte.parseByte(value));
                }
                break;
            case "java.lang.Double":
                for (String value : listStrings) {
                    list.add(Double.parseDouble(value));
                }
                break;
            case "java.lang.Float":
                for (String value : listStrings) {
                    list.add(Float.parseFloat(value));
                }
                break;
            case "java.lang.Long":
                for (String value : listStrings) {
                    list.add(Long.parseLong(value));
                }
                break;
            case "java.lang.Short":
                for (String value : listStrings) {
                    list.add(Short.parseShort(value));
                }
                break;
            default:
                for (String value : listStrings) {
                    list.add(getEntityFromString(value, typeName));
                }
        }

        return list;
    }

    private Map<String, String> parseString(String str) {
        int i = 0;
        Map<String, String> map = new HashMap<>();
        map = recursionParseString(str, i, map);

        return map;
    }

    private Map<String, String> recursionParseString(String str, int i, Map<String, String> map) {
        String fieldName = "";
        String fieldValueStr = "";
        int strlength = str.length();

        while (i < strlength) {
            char c = str.charAt(i);
            if (c != '=') {
                fieldName = fieldName.concat(String.valueOf(c));
            } else {
                i++;
                break;
            }
            i++;
        }

        int countSpecSimbol = 0;
        while (i < strlength) {
            char c = str.charAt(i);
            if (c == '[' || c == '{' || c == '(') {
                countSpecSimbol++;
                fieldValueStr = fieldValueStr.concat(String.valueOf(c));
            } else if (c == ']' || c == '}' || c == ')') {
                countSpecSimbol--;
                fieldValueStr = fieldValueStr.concat(String.valueOf(c));
            } else if (c == ';' & countSpecSimbol == 0) {
                map.put(fieldName, fieldValueStr);
                i++;
                break;
            } else {
                fieldValueStr = fieldValueStr.concat(String.valueOf(c));
            }
            i++;
        }

        if (i < strlength) {
            map = recursionParseString(str, i, map);
        }

        return map;
    }

    private String getFileNameWithPostFix(String fileName) {
        return fileName.concat(postfixFile);
    }

    private String getValueAsString(String str, Field field, Object object) throws IllegalAccessException {
        str += field.getName().concat("=");

        field.setAccessible(true);
        String typeName = field.getType().getTypeName();

        List<String> primitiveTypeList = ReflectionAssistant.getPrimitiveTypeAsString(false);
        List<String> primitiveArrayTypeList = ReflectionAssistant.getPrimitiveTypeAsString(true);
        List<String> conteinerPrimityveTypeList = ReflectionAssistant.getConteinerPrimitiveTypeAsString();

        if (primitiveTypeList.contains(typeName) ||
                conteinerPrimityveTypeList.contains(typeName) ||
                typeName.equals("java.lang.String") ||
                typeName.equals("java.util.Date")) {
            str += field.get(object).toString();
        } else if (primitiveArrayTypeList.contains(typeName)) {
            str = getPrimitiveArrayAsString(str, field, object);
        } else if (typeName.equals("java.util.List")) {
            str = getListAsString(str, field, object);
        } else {
            str = getObjectAsString(str, field.get(object));
        }

        return str.concat(";");
    }

    private String getPrimitiveArrayAsString(String str, Field field, Object object) throws IllegalAccessException {

        return str;
    }

    private String getListAsString(String str, Field field, Object object) throws IllegalAccessException {
        str = str.concat("[");

        List<?> list = (List) field.get(object);
        int listSize = list.size() - 1;

        str = str.concat(list.get(0).getClass().getTypeName()).concat(":");

        for (int i = 0; i <= listSize; i++) {
            Object listItem = list.get(i);
            if (listItem instanceof String ||
                    listItem instanceof Number) {
                str += ((String) listItem).toString();
            } else {
                str = getObjectAsString(str, listItem);
            }

            if (i != listSize) {
                str = str.concat(",");
            }
        }

        return str.concat("]");
    }

    private String getObjectAsString(String str, Object object) throws IllegalAccessException {
        str = str.concat("(");

        String typeName = object.getClass().getTypeName();
        str = str.concat(typeName).concat(":");

        Class itemClass = object.getClass();
        List<Field> fields = new ArrayList<>();
        fields = ReflectionAssistant.getAllFields(itemClass, fields);

        for (Field subField : fields) {
            str = getValueAsString(str, subField, object);
        }

        str = str.concat(")");
        return str;
    }
}
