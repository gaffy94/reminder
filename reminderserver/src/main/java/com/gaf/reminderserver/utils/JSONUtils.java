package com.gaf.reminderserver.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;

@Service
public class JSONUtils {

    public String toJson(Object o) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create(); // pretty print
        return gson.toJson(o);
    }

    public Object toObject(String json, Class clazz) {
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }
}
