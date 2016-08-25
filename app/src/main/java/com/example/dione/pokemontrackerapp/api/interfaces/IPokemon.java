package com.example.dione.pokemontrackerapp.api.interfaces;

import com.example.dione.pokemontrackerapp.api.models.Pokemon;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by dione on 11/08/2016.
 */
public interface IPokemon {
    @GET("/")
    void getPokemon(Callback<Pokemon> callback);
}
