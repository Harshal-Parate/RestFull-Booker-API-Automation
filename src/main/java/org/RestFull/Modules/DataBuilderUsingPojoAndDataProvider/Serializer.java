package org.RestFull.Modules.DataBuilderUsingPojoAndDataProvider;

import com.google.gson.Gson;


public class Serializer {

    public static <T> String objectToString(T Object) {
        Gson gson = new Gson();
        return gson.toJson(Object);
    }
}