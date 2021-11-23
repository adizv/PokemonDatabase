package org.izv.pokemon.model.entity;

import androidx.room.Embedded;

public class PokemonType {

    @Embedded
    public Pokemon pokemon;

    @Embedded(prefix="type_")
    public Type type;
}
