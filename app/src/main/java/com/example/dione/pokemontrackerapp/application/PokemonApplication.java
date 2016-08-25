package com.example.dione.pokemontrackerapp.application;

import android.app.Application;

import com.example.dione.pokemontrackerapp.bus.BusProvider;
import com.example.dione.pokemontrackerapp.manager.PokemonManager;
import com.squareup.otto.Bus;

/**
 * Created by dione on 11/08/2016.
 */
public class PokemonApplication extends Application {
    private PokemonManager mPokemonManager;
    public Bus mBus = BusProvider.getInstance();
    @Override
    public void onCreate() {
        super.onCreate();
        mPokemonManager = new PokemonManager(this, mBus);
        mBus.register(mPokemonManager);
        mBus.register(this);
    }
}
