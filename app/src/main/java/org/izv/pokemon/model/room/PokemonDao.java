package org.izv.pokemon.model.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.izv.pokemon.model.entity.Pokemon;
import org.izv.pokemon.model.entity.Type;

import java.util.List;

@Dao
public interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPokemon(Pokemon... pokemons);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertType(Type... types);

    @Update
    public void updatePokemon(Pokemon... pokemons);

    @Update
    public void updateType(Type... types);

    @Delete
    public void deletePokemons(Pokemon... pokemons);

    @Delete
    public void deleteType(Type... types);

    @Query("select * from pokemon order by name asc")
    LiveData<List<Pokemon>> getPokemons();

    @Query("select * from pokemontype order by name asc")
    LiveData<List<Type>> getTypes();

    @Query("select * from pokemon where id = :id")
    LiveData<Pokemon> getPokemon(long id);

    @Query("select * from pokemontype where id = :id")
    LiveData<Type> getType(long id);
}