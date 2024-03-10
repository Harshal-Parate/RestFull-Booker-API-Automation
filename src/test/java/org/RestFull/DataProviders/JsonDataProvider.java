package org.RestFull.DataProviders;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonDataProvider {



    //This Method reads the data form JSON file which is stored in array and return the data in 2D array format used by data provider
    public static <T> Object[][] provideData(String jsonFilePath, Class<T> className) {


        // Create a Gson instance for JSON parsing
        Gson gson = new Gson();

        // Define the type to deserialize directly into objects of the specified class --> this is from GSON Lia.
        Type type = TypeToken.getParameterized(List.class, className).getType();   //---> Initializing the TYPE which is parametrised and setting it according to the class structure provided

        // Initialize an empty list to hold the data
        List<T> data = new ArrayList<>(); //---> creation of the empty list to store list of TYPE which is created above

        // Read and deserialize the JSON data
        try (FileReader reader = new FileReader(jsonFilePath)) {   //---> reading the data from file path in arguments.
            data = gson.fromJson(reader, type);                    //---> parsing the data i.e form JSON to objects and mapping it according to the class provided
        } catch (IOException e) {
            throw new RuntimeException("Error reading JSON file: " + jsonFilePath, e);  //---> Exception handled
        }

        // Convert the list of objects into a two-dimensional array
        Object[][] dataArray = new Object[data.size()][1]; //---> Object array size initialization by keeping one index constant and given the list size by "data.size()"
        for (int i = 0; i < data.size(); i++) {
            dataArray[i][0] = data.get(i); //---> [row][col] --> filing out list TYPE data in 0th index col.
        }
        return dataArray; //--> returning the 2D array
    }
}
