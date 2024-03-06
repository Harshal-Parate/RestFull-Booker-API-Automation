package org.RestFull.Modules.DataBuilderUsingPojoAndDataProvider;

import com.google.gson.Gson;

public class Deserializer {

    public static <T> T stringToObjects(String payload, Class<T> className) {
        Gson gson = new Gson();
        return gson.fromJson(payload, className);
    }
}
