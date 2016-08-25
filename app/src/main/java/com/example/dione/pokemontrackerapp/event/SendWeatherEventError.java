package com.example.dione.pokemontrackerapp.event;


import retrofit.RetrofitError;

/**
 * Created by dione on 11/08/2016.
 */
public class SendWeatherEventError {
    private RetrofitError mRetroFitError;
    public SendWeatherEventError(RetrofitError error){
        this.mRetroFitError = error; 
    }

    public RetrofitError getmRetroFitError() {
        return mRetroFitError;
    }
}
