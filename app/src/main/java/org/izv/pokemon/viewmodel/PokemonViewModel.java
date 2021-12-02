package org.izv.pokemon.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.izv.pokemon.model.entity.Pokemon;
import org.izv.pokemon.model.entity.PokemonType;
import org.izv.pokemon.model.entity.Type;
import org.izv.pokemon.model.repository.PokemonRepository;

import java.util.List;

public class PokemonViewModel extends AndroidViewModel {

    private PokemonRepository repository;

    public PokemonViewModel(@NonNull Application application) {
        super(application);
        repository = new PokemonRepository(application);
    }

    public void insertPokemon(Pokemon... pokemons) {
        repository.insertPokemon(pokemons);
    }

    public void updatePokemon(Pokemon... pokemons) {
        repository.updatePokemon(pokemons);
    }

    public void deletePokemons(Pokemon... pokemons) {
        repository.deletePokemons(pokemons);
    }

    public LiveData<List<Pokemon>> getPokemons() {
        return repository.getPokemons();
    }

    public LiveData<Pokemon> getPokemon(long id) {
        return repository.getPokemon(id);
    }

    public void insertPokemon(Pokemon pokemon, Type type) {
        repository.insertPokemon(pokemon, type);
    }

    public LiveData<List<PokemonType>> getAllPokemon() {
        return repository.getAllPokemon();
    }

    public MutableLiveData<Long> getInsertResult() {
        return repository.getInsertResult();
    }

    public MutableLiveData<List<Long>> getInsertResults() {
        return repository.getInsertResults();
    }

    public void getKalos() {
        repository.getKalos();
    }

    public MutableLiveData<String> getKalosResult() {
        return repository.getKalosResult();
    }
}
