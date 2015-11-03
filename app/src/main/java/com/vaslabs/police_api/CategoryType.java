package com.vaslabs.police_api;

import android.util.Log;

/**
 * Created by vnicolaou on 03/11/15.
 */
public enum CategoryType {
    ANTISOCIAL("anti-social-behaviour"), BICYCLE_THEFT("bicycle-theft"), BURGLARY("burglary"), CRIMINAL_DAMAGE_ARSON("criminal-damage-arson"),
    DRUGS("drugs"), OTHER_THEFT("other-theft"), SHOPLIFTING("shoplifting"), THEFT_FROM_PERSON("theft-from-the-person"), VEHICLE_CRIME("vehicle-crime"),
    VIOLENT_CRIME("violent-crime"), ROBBERY("robbery"), OTHER_CRIME("other-crime"), POSSESSION_OF_WEAPONS("possession-of-weapons"),
    UNIDENTIFIED("other"), LOCAL_RESOLUTION("Local resolution"), PUBLIC_ORDER("public-order");

    private final String description;

    CategoryType(String description) {
        this.description = description;
    }

    public static CategoryType findByAbbr(String value)
    {
        for (CategoryType currEnum : CategoryType.values())
        {
            if (currEnum.description.equals(value))
            {
                return currEnum;
            }
        }

        Log.w("UNIDENTIFIED", value);

        return UNIDENTIFIED;
    }
}
