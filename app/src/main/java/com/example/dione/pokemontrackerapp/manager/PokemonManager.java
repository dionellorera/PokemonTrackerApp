package com.example.dione.pokemontrackerapp.manager;

import android.content.Context;

import com.example.dione.pokemontrackerapp.api.PokemonClient;
import com.example.dione.pokemontrackerapp.api.models.Pokemon;
import com.example.dione.pokemontrackerapp.event.GetPokemonEvent;
import com.example.dione.pokemontrackerapp.event.SendPokemonEvent;
import com.example.dione.pokemontrackerapp.event.SendWeatherEventError;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import retrofit.Callback;
import retrofit.RetrofitError;

/**
 * Created by dione on 11/08/2016.
 */
public class PokemonManager {
    private Context mContext;
    private Bus mBus;
    private PokemonClient sPokemonClient;
    public PokemonManager(Context context, Bus bus) {
        this.mContext = context;
        this.mBus = bus;
        sPokemonClient = PokemonClient.getClient();
    }

    @Subscribe
    public void onGetWeatherEvent(GetPokemonEvent getPokemonEvent) {
        Callback<Pokemon> callback = new Callback<Pokemon>() {
            @Override
            public void success(Pokemon weather, retrofit.client.Response response) {
                mBus.post(new SendPokemonEvent(weather));
            }

            @Override
            public void failure(RetrofitError error) {
                mBus.post(new SendWeatherEventError(error));
            }
        };
        sPokemonClient.getWeather(callback);
    }
}
