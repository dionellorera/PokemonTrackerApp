package com.example.dione.pokemontrackerapp.api.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dione on 11/08/2016.
 */
public class Results {
    @SerializedName("id")
    private String mId;

    public String getmId() {
        return mId;
    }

    @SerializedName("name")
    private String mName;

    public String getmName() {
        return mName;
    }

    @SerializedName("coords")
    private String mCoords;

    public String getmCoords() {
        return mCoords;
    }

    @SerializedName("until")
    private String mUntil;

    public String getmUntil() {
        return mUntil;
    }

    @SerializedName("iv")
    private String mIv;

    public String getmIv() {
        return mIv;
    }

    @SerializedName("icon")
    private String mIcon;

    public String getmIcon() {
        return mIcon;
    }

    @SerializedName("rarity")
    private String mRare;

    public String getmRare() {
        return mRare;
    }
}
