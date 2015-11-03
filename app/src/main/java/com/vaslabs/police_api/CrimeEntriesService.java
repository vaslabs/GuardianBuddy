package com.vaslabs.police_api;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vaslabs.police_api.adapters.CategoryTypeAdapter;

import java.io.Reader;

/**
 * Created by vnicolaou on 02/11/15.
 */
public class CrimeEntriesService {
    private static CrimeEntriesService instance = null;
    private final RequestQueue queue;

    private String uri = "https://data.police.uk/api/crimes-street/all-crime?lat=%s&lng=%s&date=%s";

    public CrimeEntriesService(Context context) {
        queue = Volley.newRequestQueue(context);
    }

    public static void init(Context context) {
        instance = new CrimeEntriesService(context);
    }

    public static CrimeEntriesService getInstance() {
        return instance;
    }

    public void getCrimeEntriesAround(LatLng latLng, String month, Context context, Response.Listener<String> responseListener, Response.ErrorListener errorListener) {
        String fullUrl = String.format(uri, latLng.latitude, latLng.longitude, month);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, fullUrl, responseListener, errorListener);
        queue.add(stringRequest);
    }


    public static CrimeEntry[] getCrimeEntriesFromJson(String response) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(CategoryType.class, new CategoryTypeAdapter() );
        Gson gson = gsonBuilder.create();
        return gson.fromJson(response, CrimeEntry[].class);
    }

    public static CrimeEntry[] getCrimeEntriesFromJson(Reader reader) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(CategoryType.class, new CategoryTypeAdapter() );
        Gson gson = gsonBuilder.create();
        return gson.fromJson(reader, CrimeEntry[].class);
    }
}
