package com.vaslabs.police_api;

import android.test.AndroidTestCase;

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
        Gson gson = new Gson();
        Reader reader = new BufferedReader(new InputStreamReader(this.getContext().getResources().openRawResource(R.raw.sample_data)));
        CrimeEntry[] crimeEntries = CrimeEntriesService.getCrimeEntriesFromJson(reader);
        CrimeStatistics crimeStatistics = CrimeStatistics.generate(crimeEntries);
        assertEquals(42, crimeStatistics.getByCategory(CategoryType.VIOLENT_CRIME));
        assertEquals(107, crimeStatistics.getByCategory(CategoryType.ANTISOCIAL));
        assertEquals(5, crimeStatistics.getByCategory(CategoryType.DRUGS));
        assertEquals(0, crimeStatistics.getByCategory(CategoryType.UNIDENTIFIED));
    }

}
