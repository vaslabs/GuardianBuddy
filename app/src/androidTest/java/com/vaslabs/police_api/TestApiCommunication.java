package com.vaslabs.police_api;

import android.test.AndroidTestCase;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import java.util.concurrent.CountDownLatch;


/**
 * Created by vnicolaou on 02/11/15.
 */
public class TestApiCommunication extends AndroidTestCase{

    public void testApiCommunicationReturnsData() throws InterruptedException {
        CrimeEntriesService.init(this.getContext());
        CrimeEntriesService crimeEntriesService = CrimeEntriesService.getInstance();
        LatLng latLng = new LatLng(53.472225, -2.2936317);
        final CountDownLatch signal = new CountDownLatch(1);
        TestResponseListener responseListener = new TestResponseListener(signal, this);
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                fail(error.toString());
                signal.countDown();
            }
        };
        crimeEntriesService.getCrimeEntriesAround(latLng, "2015-07", this.getContext(), responseListener, errorListener);
        signal.await();
        assertTrue(responseListener.resultFlag);
    }


}

class TestResponseListener<T> implements Response.Listener<String> {
    private CountDownLatch signal;
    public boolean resultFlag = false;
    private TestApiCommunication testClass;
    public TestResponseListener(CountDownLatch signal, TestApiCommunication testApiCommunication) {
        this.signal = signal;
        this.testClass = testApiCommunication;
    }

    @Override
    public void onResponse(String response) {
        CrimeEntry[] crimeEntries = CrimeEntriesService.getCrimeEntriesFromJson(response);
        testClass.assertTrue(crimeEntries.length > 0);
        resultFlag = true;
        signal.countDown();
    }

}
