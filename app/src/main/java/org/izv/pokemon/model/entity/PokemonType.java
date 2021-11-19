package org.izv.pokemon.model.entity;

import androidx.room.Embedded;

public class PokemonType {

    @Embedded
    private Pokemon pokemon;

    @Embedded(prefix="type_")
    private Type type;
}
