package org.nop.fastparse.entity;

import org.nop.fastparse.util.GenStringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Type {
    jsonList("List", "import java.util.List;\nimport java.util.ArrayList;"),
    jsonMap("Map", "import java.util.Map;"),
    jsonInteger("int", ""),
    jsonBoolean("boolean", ""),
    jsonString("String", ""),
    jsonLong("long", ""),
    jsonDouble("double", ""),
    jsonFloat("float", ""),
    jsonByte("byte", ""),
    sqlString("String", "char"),
    sqlString1("String", "varchar"),
    sqlInteger("int", "int");
    String paramName;
    String packageName;

    Type(String paramName, String packageName) {
        this.paramName = paramName;
        this.packageName = packageName;
    }

    public static Map checkType(Object object, String key, int typeCode) {
        Map map = new HashMap();
        String param = null;
        String type = null;
        if (object instanceof String) {
            type = "String";
            param = "String " + key;
        }
        if (object instanceof List) {
            List<Object> list = (List<Object>) object;
            for (Object o : list) {
                type = (String) checkType(o, key, 0).get("type");
            }
            map.put("package", jsonList.packageName);
            if (typeCode == 0) {
                param = "List<" + type + "> " + key + "=new ArrayList<>()";
            } else {
                param = "List<" + type + "> " + key;
            }
        }
        if (object instanceof Map) {
            type = GenStringUtils.captureName(key);
            if (typeCode == 0) {
                param = GenStringUtils.captureName(key) + " " + key + "=new " + GenStringUtils.captureName(key) + "()";
            } else {
                param = GenStringUtils.captureName(key) + " " + key;
            }

            map.put("baseEntity", key);
        }
        if (object instanceof Integer) {
            type = "Integer";
            param = "int " + key;
        }
        if (object instanceof Boolean) {
            type = "Boolean";
            param = "boolean " + key;
        }

        if (object instanceof Byte) {
            type = "Byte";
            param = "byte " + key;

        }
        if (object instanceof Double) {
            type = "Double";
            param = "double " + key;
        }
        if (object instanceof Float) {
            type = "Float";
            param = "float " + key;
        }
        if (typeCode == 0) {
            map.put("param", param + ";");
        } else {
            map.put("param", param);
        }
        map.put("type", type);
        return map;
    }

    public static Map checkSQLType(Object typeName, Object name) {
        Map map = new HashMap();
        String param = null;
        String type = null;
        if (typeName.equals("char") || typeName.equals("varchar")) {
            type = "String";
            param = "String " + name;
        }
        if (typeName.equals("int")) {
            type = "Integer";
            param = "int " + name;
        }
        if (typeName.equals("boolean")) {
            type = "Boolean";
            param = "boolean " + name;
        }

        if (typeName.equals("byte")) {
            type = "Byte";
            param = "byte " + name;

        }
        if (typeName.equals("double")) {
            type = "Double";
            param = "double " + name;
        }
        if (typeName.equals("float")) {
            type = "Float";
            param = "float " + name;
        }
        map.put("param", param + ";");
        map.put("type", type);
        return map;
    }
}
