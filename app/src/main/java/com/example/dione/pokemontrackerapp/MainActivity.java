package com.example.dione.pokemontrackerapp;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.dione.pokemontrackerapp.api.models.Results;
import com.example.dione.pokemontrackerapp.api.models.Pokemon;
import com.example.dione.pokemontrackerapp.application.PokemonApplication;
import com.example.dione.pokemontrackerapp.event.GetPokemonEvent;
import com.example.dione.pokemontrackerapp.event.SendPokemonEvent;
import com.example.dione.pokemontrackerapp.event.SendWeatherEventError;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private RecyclerView pokemonList;
    private TextView errorMessage;
    private Spinner spinnerRaritySelection;
    private PokemonApplication pokemonApplication;
    private List<Results> results;
    private List<Results> filteredResults;
    private PokemonAdapter pokemonAdapter;
    private SwipeRefreshLayout pokemonSwipeRefresh;
    private boolean isFirstLoad = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initSpinner();
        initSwipeRefreshLayout();
        SendPokemonListRequest();
    }

    private void init(){
        pokemonList = (RecyclerView) findViewById(R.id.pokemonList);
        errorMessage = (TextView) findViewById(R.id.errorMessage);
        spinnerRaritySelection = (Spinner) findViewById(R.id.spinnerRaritySelection);
        pokemonSwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.pokemonSwipeRefresh);
        filteredResults= new ArrayList<>();
    }

    private void initSwipeRefreshLayout(){
        pokemonSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                SendPokemonListRequest();
            }
        });
    }

    private void initSpinner(){
        spinnerRaritySelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(!isFirstLoad){
                    filteredResults.clear();
                    refreshPokemonList(position);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void refreshPokemonList(int position){
        for (Results r : results){
            if (position == 0 && r.getmRare().equals("uncommon")){
                filteredResults.add(r);
            }else if (position == 1 && r.getmRare().equals("rare")){
                filteredResults.add(r);
            }else if (position == 2 && r.getmRare().equals("very_rare")){
                filteredResults.add(r);
            } else if (position == 3 && r.getmRare().equals("special")){
                filteredResults.add(r);
            }
        }
        pokemonAdapter.notifyDataSetChanged();
    }

    private void SendPokemonListRequest(){
        pokemonApplication = new PokemonApplication();
        pokemonApplication.mBus.post(new GetPokemonEvent());
    }

    @Subscribe
    public void onSendPokemonEventRequest(SendPokemonEvent sendPokemonEvent) {
        spinnerRaritySelection.setVisibility(View.VISIBLE);
        pokemonList.setVisibility(View.VISIBLE);
        errorMessage.setVisibility(View.GONE);
        setPokemonList(sendPokemonEvent);

    }
    private void setPokemonList(SendPokemonEvent sendPokemonEvent){
        filteredResults.clear();
        Pokemon pokemon = sendPokemonEvent.getmPokemon();
        results = pokemon.getmResults();
        pokemonAdapter = new PokemonAdapter(getApplicationContext(), filteredResults);
        refreshPokemonList(spinnerRaritySelection.getSelectedItemPosition());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        pokemonList.setLayoutManager(mLayoutManager);
        pokemonList.setItemAnimator(new DefaultItemAnimator());
        pokemonList.setAdapter(pokemonAdapter);
        pokemonAdapter.notifyDataSetChanged();
        pokemonSwipeRefresh.setRefreshing(false);
        isFirstLoad = false;
    }

    @Subscribe
    public void onSendWeatherEventError(SendWeatherEventError sendWeatherEventError) {
        pokemonList.setVisibility(View.GONE);
        spinnerRaritySelection.setVisibility(View.GONE);
        errorMessage.setVisibility(View.VISIBLE);
        errorMessage.setText(sendWeatherEventError.getmRetroFitError().toString());
    }


    @Override
    public void onResume() {
        super.onResume();
        pokemonApplication.mBus.register(this);
    }
    @Override
    public void onPause() {
        super.onPause();
        pokemonApplication.mBus.unregister(this);
    }

}
