package org.izv.pokemon.model.room;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import org.izv.pokemon.model.entity.Pokemon;
import org.izv.pokemon.model.entity.Type;

@Database(entities = {Pokemon.class, Type.class}, version = 1, exportSchema = false)
public abstract class PokemonDatabase extends RoomDatabase {

    public abstract PokemonDao getDao();

    private static volatile PokemonDatabase INSTANCE;

    /* versión simplificada */
    public static PokemonDatabase getDatabase(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), PokemonDatabase.class, "pokemon").build();
        }
        return INSTANCE;
    }

}