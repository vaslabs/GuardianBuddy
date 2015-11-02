package com.vaslabs.police_api;

import android.test.AndroidTestCase;

import com.google.gson.Gson;
import com.vaslabs.guardianbuddy.R;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

/**
 * Created by vnicolaou on 02/11/15.
 */
public class TestWrapperClasses extends AndroidTestCase{

    public void testCrimeEntryDeserialisation() {
        Gson gson = new Gson();
        Reader reader = new BufferedReader(new InputStreamReader(this.getContext().getResources().openRawResource(R.raw.sample_data)));
        CrimeEntry[] crimeEntries = gson.fromJson(reader, CrimeEntry[].class);
        CrimeEntry crimeEntry = crimeEntries[0];
        assertEquals("anti-social-behaviour", crimeEntry.getCategory());
        assertEquals("Force", crimeEntry.getLocationType());
        Location location = crimeEntry.getLocation();
        assertEquals(53.474670, location.getLatitude());
        assertEquals(-2.282090, location.getLongitude());
        Street street = location.getStreet();
        assertEquals(723141, street.getId());
        assertEquals("On or near Brigantine Close", street.getName());
        assertEquals("", crimeEntry.getContext());
        assertEquals(null, crimeEntry.getOutcomeStatus());
        assertEquals("9958614a375be926f96302c6ece7a2079a90cb444e710acb9e8c9c8301b25e00", crimeEntry.getPersistentId());
        assertEquals(42281166, crimeEntry.getId());
        assertEquals("", crimeEntry.getLocationSubtype());
        assertEquals("2015-07", crimeEntry.getMonth());

        crimeEntry = crimeEntries[crimeEntries.length - 1];
        OutcomeStatus outcomeStatus = crimeEntry.getOutcomeStatus();
        assertEquals("Investigation complete; no suspect identified", outcomeStatus.getCategory());
        assertEquals("2015-07", outcomeStatus.getDate());
    }

}
