package org.izv.pokemon.model.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import org.izv.pokemon.model.entity.Pokemon;
import org.izv.pokemon.model.entity.Type;
import org.izv.pokemon.model.room.PokemonDao;
import org.izv.pokemon.model.room.PokemonDatabase;

import java.util.List;

public class PokemonRepository {

    private PokemonDao dao;
    LiveData<List<Pokemon>> livePokemons;
    LiveData<List<Type>> liveTypes;
    LiveData<Pokemon> livePokemon;
    LiveData<Type> liveType;

    public PokemonRepository(Context context) {
        PokemonDatabase db = PokemonDatabase.getDatabase(context);
        dao = db.getDao();
    }

    public void insertPokemon(Pokemon... pokemons) {
        Runnable r = () -> {
            dao.insertPokemon(pokemons);
        };
        new Thread(r).start();
    }

    public void insertType(Type... types) {
        Runnable r = () -> {
            dao.insertType(types);
        };
        new Thread(r).start();
    }

    public void updatePokemon(Pokemon... pokemons) {
        Runnable r = () -> {
            dao.updatePokemon(pokemons);
        };
        new Thread(r).start();
    }

    public void updateType(Type... types) {
        Runnable r = () -> {
            dao.updateType(types);
        };
        new Thread(r).start();
    }

    public void deletePokemons(Pokemon... pokemons) {
        Runnable r = () -> {
            dao.deletePokemons(pokemons);
        };
        new Thread(r).start();
    }

    public void deleteType(Type... types) {
        Runnable r = () -> {
            dao.deleteType(types);
        };
        new Thread(r).start();
    }

    public LiveData<List<Pokemon>> getPokemons() {
        if(livePokemons == null) {
            livePokemons = dao.getPokemons();
        }
        return livePokemons;
    }

    public LiveData<List<Type>> getTypes() {
        if(liveTypes == null) {
            liveTypes = dao.getTypes();
        }
        return liveTypes;
    }

    public LiveData<Pokemon> getPokemon(long id) {
        if(livePokemon == null) {
            livePokemon = dao.getPokemon(id);
        }
        return livePokemon;
    }

    public LiveData<Type> getType(long id) {
        if(liveType == null) {
            liveType = dao.getType(id);
        }
        return liveType;
    }
}