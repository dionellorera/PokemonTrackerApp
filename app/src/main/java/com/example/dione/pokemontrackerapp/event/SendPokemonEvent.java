package com.example.dione.pokemontrackerapp.event;

import com.example.dione.pokemontrackerapp.api.models.Pokemon;

/**
 * Created by dione on 11/08/2016.
 */
public class SendPokemonEvent {
    private Pokemon mPokemon;
    public SendPokemonEvent(Pokemon pokemon) {
        this.mPokemon = pokemon;
    }

    public Pokemon getmPokemon() {
        return mPokemon;
    }
}
