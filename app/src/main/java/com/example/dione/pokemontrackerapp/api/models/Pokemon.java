package com.example.dione.pokemontrackerapp.api.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dione on 11/08/2016.
 */
public class Pokemon {
    @SerializedName("results")
    private List<Results> mResults;

    public List<Results> getmResults() {
        return mResults;
    }
}
