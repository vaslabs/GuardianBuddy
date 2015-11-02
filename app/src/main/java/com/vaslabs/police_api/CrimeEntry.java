package com.vaslabs.police_api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vnicolaou on 02/11/15.
 */
public final class CrimeEntry {
    private String category;

    @SerializedName("location_type")
    private String locationType;

    private Location location;
    private String context;

    @SerializedName("outcome_status")
    private OutcomeStatus outcomeStatus;

    @SerializedName("persistent_id")
    private String persistentId;
    private long id;

    @SerializedName("location_subtype")
    private String locationSubtype;
    private String month;

    public String getCategory() {
        return category;
    }

    public String getLocationType() {
        return locationType;
    }

    public Location getLocation() {
        return location;
    }

    public String getContext() {
        return context;
    }

    public OutcomeStatus getOutcomeStatus() {
        return outcomeStatus;
    }

    public String getPersistentId() {
        return persistentId;
    }

    public long getId() {
        return id;
    }

    public String getLocationSubtype() {
        return locationSubtype;
    }

    public String getMonth() {
        return month;
    }
}
