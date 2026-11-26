/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yrusprint;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 *
 * @author tempus
 */
public class DataService {
    private static final ObjectMapper mapper = new ObjectMapper();

    private static List<Map<String, Object>> getJsonData(String resourceFile) {
        try (InputStream inputStream = DataService.class.getClassLoader().getResourceAsStream(resourceFile)) {
            if (inputStream == null) return null;
            JsonNode root = mapper.readTree(inputStream);
            return mapper.convertValue(root, new TypeReference<List<Map<String, Object>>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    } 
        
	public static List<Map<String, Object>> read(String fileName, Predicate<Map<String, Object>> conditions) {
        List<Map<String, Object>> entries = getJsonData(fileName);
        if (conditions == null) return entries;
        
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> entry : entries) {
            Boolean match = conditions.test(entry);
            if (match) result.add(entry);
        }

        return result;
	}

    public static List<Map<String, Object>> read(String fileName) {
        return read(fileName, null);
	}
	
    // public static void update(String fileName, Map<Object, Object> data, Predicate<Map<String, Object>> conditions) {

    // }

	public static void insert(String fileName, Map<String, Object> data) {
        List<Map<String, Object>> entries = read(fileName);
        entries.add(data);

        mapper.writerWithDefaultPrettyPrinter().writeValue(, entries);
	}
}
