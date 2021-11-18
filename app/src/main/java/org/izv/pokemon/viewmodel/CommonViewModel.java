package org.izv.pokemon.viewmodel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import org.izv.pokemon.model.repository.PokemonRepository;

public class CommonViewModel extends ViewModel {

    private Context context;
    private PokemonRepository repository;

    public CommonViewModel() {
    }

    public void setContext(Context context) {
        this.context = context;
    }
}