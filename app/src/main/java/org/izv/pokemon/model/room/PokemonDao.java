package org.izv.pokemon.model.room;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.izv.pokemon.model.entity.Pokemon;
import org.izv.pokemon.model.entity.PokemonType;
import org.izv.pokemon.model.entity.Type;

import java.util.List;

@Dao
public interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    List<Long> insertPokemon(Pokemon... pokemons);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Long insert(Pokemon pokemon);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    List<Long> insertType(Type... types);

    @Update
    int updatePokemon(Pokemon... pokemons);

    @Update
    int updateType(Type... types);

    @Delete
    int deletePokemons(Pokemon... pokemons);

    @Delete
    int deleteType(Type... types);

    @Query("delete from pokemontype")
    int deleteAllTypes();

    @Query("delete from pokemon")
    int deleteAllPokemon();

    @Query("select * from pokemon order by name asc")
    LiveData<List<Pokemon>> getPokemons();

    @Query("select p.*, pt.id type_id, pt.name type_name from pokemon p join pokemontype pt on p.idtype = pt.id order by name asc")
    LiveData<List<PokemonType>> getAllPokemon();

    @Query("select * from pokemontype order by name asc")
    LiveData<List<Type>> getTypes();

    @Query("select * from pokemon where id = :id")
    LiveData<Pokemon> getPokemon(long id);

    @Query("select * from pokemontype where id = :id")
    LiveData<Type> getType(long id);

    @Query("select id from pokemontype where name = :name")
    long getIdType(String name);

    @Query("select * from pokemontype where name = :name")
    Type getType(String name);
}