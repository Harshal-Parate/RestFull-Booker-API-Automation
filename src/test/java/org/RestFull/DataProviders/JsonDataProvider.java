package org.RestFull.DataProviders;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonDataProvider {


    public static <T> Object[][] provideData(String jsonFilePath, Class<T> className) {


        // Create a Gson instance for JSON parsing
        Gson gson = new Gson();

        // Define the type to deserialize directly into objects of the specified class
        Type type = TypeToken.getParameterized(List.class, className).getType();

        // Initialize an empty list to hold the data
        List<T> data = new ArrayList<>();

        // Read and deserialize the JSON data
        try (FileReader reader = new FileReader(jsonFilePath)) {
            data = gson.fromJson(reader, type);
        } catch (IOException e) {
            throw new RuntimeException("Error reading JSON file: " + jsonFilePath, e);
        }

        // Convert the list of objects into a two-dimensional array
        Object[][] dataArray = new Object[data.size()][1];
        for (int i = 0; i < data.size(); i++) {
            dataArray[i][0] = data.get(i);
        }
        return dataArray;
    }
}
