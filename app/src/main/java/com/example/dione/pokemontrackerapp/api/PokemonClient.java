package com.example.dione.pokemontrackerapp.api;

import com.example.dione.pokemontrackerapp.api.interfaces.IPokemon;
import com.example.dione.pokemontrackerapp.api.models.Pokemon;
import com.squareup.okhttp.OkHttpClient;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by dione on 11/08/2016.
 */
public class PokemonClient {
    private static final String BASE_URL = "http://pokesnipers.com/api/v1/pokemon.json?referrer=home";
//    public static final String API_URL = BASE_URL + API_KEY;

    private static PokemonClient mPokemonClient;
    private static RestAdapter mRestAdapter;

    public static PokemonClient getClient() {
        if (mPokemonClient == null)
            mPokemonClient = new PokemonClient();
        return mPokemonClient;
    }

    private PokemonClient() {
        mRestAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setClient(new OkClient(new OkHttpClient()))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build(); 
    }

    public void getWeather(Callback<Pokemon> callback) {
        IPokemon weather = mRestAdapter.create(IPokemon.class);
        weather.getPokemon(callback);
    }


}
