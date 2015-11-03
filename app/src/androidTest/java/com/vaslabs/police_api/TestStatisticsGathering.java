package com.vaslabs.police_api;

import android.test.AndroidTestCase;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.vaslabs.guardianbuddy.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Created by vnicolaou on 03/11/15.
 */
public class TestStatisticsGathering extends AndroidTestCase{

    public void testStatsFromCrimeEntries() {
        Reader reader = new BufferedReader(new InputStreamReader(this.getContext().getResources().openRawResource(R.raw.sample_data)));
        CrimeEntry[] crimeEntries = CrimeEntriesService.getCrimeEntriesFromJson(reader);
        CrimeStatistics crimeStatistics = CrimeStatistics.generate(crimeEntries);
        assertEquals(42, crimeStatistics.getByCategory(CategoryType.VIOLENT_CRIME));
        assertEquals(107, crimeStatistics.getByCategory(CategoryType.ANTISOCIAL));
        assertEquals(5, crimeStatistics.getByCategory(CategoryType.DRUGS));
        assertEquals(0, crimeStatistics.getByCategory(CategoryType.UNIDENTIFIED));
    }

    public void testGetCrimesSortedByNearestToMe() {
        LatLng myPosition = new LatLng(53.472225, -2.2936317);
        Reader reader = new BufferedReader(new InputStreamReader(this.getContext().getResources().openRawResource(R.raw.sample_data)));
        CrimeEntry[] crimeEntries = CrimeEntriesService.getCrimeEntriesFromJson(reader);
        CrimeStatistics crimeStatistics = CrimeStatistics.generate(crimeEntries);
        crimeEntries = crimeStatistics.orderByNearest(myPosition);
        float prevDistance = CrimeStatistics.distanceBetween(crimeEntries[0], myPosition);
        for (int i = 1; i < crimeEntries.length; i++) {
            final float currentDistance = CrimeStatistics.distanceBetween(crimeEntries[1], myPosition);
            assertTrue(currentDistance >= prevDistance);
            prevDistance = currentDistance;
        }

    }

}
