package com.vaslabs.police_api;

/**
 * Created by vnicolaou on 03/11/15.
 */
public class CrimeStatistics {

    private int[] statsByCategory = new int[CategoryType.values().length];

    public static CrimeStatistics generate(CrimeEntry[] crimeEntries) {
        CrimeStatistics crimeStatistics = new CrimeStatistics();
        for (CrimeEntry crimeEntry : crimeEntries) {
            crimeStatistics.statsByCategory[crimeEntry.getCategory().ordinal()]++;
        }
        return crimeStatistics;
    }

    public int getByCategory(CategoryType categoryType) {
        return statsByCategory[categoryType.ordinal()];
    }
}
