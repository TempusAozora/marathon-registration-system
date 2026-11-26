/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yrusprint.helpers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.io.File;

/**
 *
 * @author tempus
 */
public class DataService {
    private static final ObjectMapper mapper = new ObjectMapper();

    private static List<Map<String, Object>> getJsonData(String filePath) {
        try {
            return mapper.readValue(new File(filePath), new TypeReference<List<Map<String, Object>>>(){});
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void writeJsonData(String filePath, List<Map<String, Object>> content) {
        try {
            mapper.writeValue(new File(filePath), content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
	public static List<Map<String, Object>> read(String filePath, Predicate<Map<String, Object>> conditions) {
        List<Map<String, Object>> entries = getJsonData(filePath);
        if (conditions == null) return entries;
        
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> entry : entries) {
            Boolean match = conditions.test(entry);
            if (match) result.add(entry);
        }

        return result;
	}

    public static List<Map<String, Object>> read(String filePath) {
        return read(filePath, null);
	}
	
	public static void insert(String filePath, Map<String, Object> data) {
        List<Map<String, Object>> entries = read(filePath);
        entries.add(data);
        writeJsonData(filePath, entries);
	}

    // public static void update(String filePath, Map<Object, Object> data, Predicate<Map<String, Object>> conditions) {

    // }
}
