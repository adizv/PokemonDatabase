package org.izv.pokemon.model.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Query;

import org.izv.pokemon.model.api.PokemonList;
import org.izv.pokemon.model.entity.Pokemon;
import org.izv.pokemon.model.entity.PokemonType;
import org.izv.pokemon.model.entity.Type;
import org.izv.pokemon.model.room.PokemonDao;
import org.izv.pokemon.model.room.PokemonDatabase;

import java.util.ArrayList;
import java.util.List;

public class PokemonRepository {

    private static final String INIT = "init";

    private PokemonDao dao;
    private LiveData<List<PokemonType>> allPokemon;
    private LiveData<List<Pokemon>> livePokemons;
    private LiveData<List<Type>> liveTypes;
    private LiveData<Pokemon> livePokemon;
    private LiveData<Type> liveType;
    private MutableLiveData<Long> liveInsertResult;
    private MutableLiveData<List<Long>> liveInsertResults;
    private MutableLiveData<String> liveGetKalosResult;
    private SharedPreferences preferences;
    private PokemonList pokemonList;

    public PokemonRepository(Context context) {
        PokemonDatabase db = PokemonDatabase.getDatabase(context);
        pokemonList = new PokemonList();
        dao = db.getDao();
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        liveInsertResult = new MutableLiveData<>();
        liveInsertResults = new MutableLiveData<>();
        liveGetKalosResult = new MutableLiveData<>();
        if(!getInit()) {
            typesPreload();
            setInit();
        }
    }

    public void insertPokemon(Pokemon pokemon, Type type) {
        Runnable r = () -> {
            pokemon.idtype = insertTypeGetId(type);
            if(pokemon.idtype > 0) {
                dao.insertPokemon(pokemon);
            }
        };
        new Thread(r).start();
    }

    public MutableLiveData<Long> getInsertResult() {
        return liveInsertResult;
    }

    public MutableLiveData<String> getKalosResult() {
        return liveGetKalosResult;
    }

    public MutableLiveData<List<Long>> getInsertResults() {
        return liveInsertResults;
    }

    private long insertTypeGetId(Type type) {
        List<Long> ids = dao.insertType(type);
        if(ids.get(0) < 1) {
            return dao.getIdType(type.name);
        } else {
            return ids.get(0);
        }
    }

    public void insertPokemon(Pokemon... pokemons) {
        Runnable r = () -> {
            List<Long> resultList = dao.insertPokemon(pokemons);
            liveInsertResult.postValue(resultList.get(0));
            liveInsertResults.postValue(resultList);
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

    public LiveData<List<PokemonType>> getAllPokemon() {
        if(allPokemon == null) {
            allPokemon = dao.getAllPokemon();
        }
        return allPokemon;
    }

    public void typesPreload() {
        String[] typeNames = new String[] {"normal", "fire", "grass", "ghost", "psychic", "electric", "water", "poison", "rock", "flying"};
        Type[] types = new Type[typeNames.length];
        Type type;
        int cont = 0;
        for (String s: typeNames) {
            type = new Type();
            type.name = s;
            types[cont] = type;
            cont++;
        }
        insertType(types);
    }

    public void setInit() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(INIT, true);
        editor.commit();
    }

    public boolean getInit() {
        return preferences.getBoolean(INIT, false);
    }

    public void getKalos() {
        Runnable r = () -> {
            String result = pokemonList.getKalos("https://www.pokemon.com/es/api/pokedex/kalos");
            liveGetKalosResult.postValue(result);
            Log.v("xyzyx", result);
        };
        new Thread(r).start();
    }
}