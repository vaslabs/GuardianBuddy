package com.vaslabs.police_api.adapters;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.vaslabs.police_api.CategoryType;

import java.lang.reflect.Type;

public class CategoryTypeAdapter implements
        JsonDeserializer<CategoryType>
{

    @Override
    public CategoryType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String category = json.getAsString();
        return CategoryType.findByAbbr(category);
    }
}
