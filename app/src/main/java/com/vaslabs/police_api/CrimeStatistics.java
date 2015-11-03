package com.vaslabs.police_api;

import android.location.*;

import com.google.android.gms.maps.model.LatLng;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by vnicolaou on 03/11/15.
 */
public class CrimeStatistics {

    private int[] statsByCategory = new int[CategoryType.values().length];
    private CrimeEntry[] crimeEntries;
    public static CrimeStatistics generate(CrimeEntry[] crimeEntries) {
        CrimeStatistics crimeStatistics = new CrimeStatistics();
        for (CrimeEntry crimeEntry : crimeEntries) {
            crimeStatistics.statsByCategory[crimeEntry.getCategory().ordinal()]++;
        }
        crimeStatistics.crimeEntries = Arrays.copyOf(crimeEntries, crimeEntries.length);
        return crimeStatistics;
    }

    public int getByCategory(CategoryType categoryType) {
        return statsByCategory[categoryType.ordinal()];
    }

    public CrimeEntry[] orderByNearest(final LatLng position) {
        Arrays.sort(crimeEntries, new Comparator<CrimeEntry>() {
            @Override
            public int compare(CrimeEntry lhs, CrimeEntry rhs) {
                float resultLhs = distanceBetween(lhs, position);
                float resultRhs = distanceBetween(rhs, position);

                float diff = resultLhs - resultRhs;
                if (diff < 0) {
                    return -1;
                } else if (diff > 0)
                    return 1;
                else
                    return 0;
            }
        });
        return crimeEntries;
    }

    public static float distanceBetween(CrimeEntry crimeEntry, LatLng position) {
        double ceLatitude = crimeEntry.getLocation().getLatitude();
        double ceLongitude = crimeEntry.getLocation().getLongitude();
        double centerLatitude = position.latitude;
        double centerLongitude = position.longitude;
        float[] result = new float[1];
        android.location.Location.distanceBetween(centerLatitude, centerLongitude, ceLatitude, ceLongitude, result);
        return result[0];
    }
}
