package org.izv.pokemon.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.izv.pokemon.R;
import org.izv.pokemon.model.entity.Pokemon;
import org.izv.pokemon.model.entity.PokemonType;
import org.izv.pokemon.model.entity.Type;
import org.izv.pokemon.view.adapter.PokemonAdapter;
import org.izv.pokemon.view.adapter.viewholder.PokemonViewHolder;
import org.izv.pokemon.viewmodel.CommonViewModel;
import org.izv.pokemon.viewmodel.PokemonViewModel;
import org.izv.pokemon.viewmodel.TypeViewModel;

import java.util.List;

public class PokemonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        //lista de pokemon
        RecyclerView rvPokemon = findViewById(R.id.rvPokemon);
        rvPokemon.setLayoutManager(new LinearLayoutManager(this));

        PokemonViewModel pvm = new ViewModelProvider(this).get(PokemonViewModel.class);
        PokemonAdapter pokemonAdapter = new PokemonAdapter(this);

        rvPokemon.setAdapter(pokemonAdapter);

        //LiveData<List<Pokemon>> listaPokemon = pvm.getPokemons();
        LiveData<List<PokemonType>> listaPokemonType = pvm.getAllPokemon();
        listaPokemonType.observe(this, pokemons -> {
            pokemonAdapter.setPokemonList(pokemons);
        });

        FloatingActionButton fab = findViewById(R.id.fabAddPokemon);
        fab.setOnClickListener(v -> {
            /*Pokemon pokemon = new Pokemon();
            pokemon.height = 0.32;
            pokemon.weight = 2;
            pokemon.name = "Ditto";
            pokemon.url = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/132.png";
            pokemon.idtype = 1;
            pvm.insertPokemon(pokemon);*/

            Type type = new Type();
            type.name = "Aguas";
            Pokemon pokemon = new Pokemon();
            pokemon.height = 2.2;
            pokemon.weight = 2;
            pokemon.name = "Squirtle";
            pokemon.url = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/007.png";
            pvm.insertPokemon(pokemon, type);
        });
        /*listaPokemon.observe(this, pokemons -> {
            pokemonAdapter.setPokemonList(pokemons);
        });*/
        //RecyclerView rv = findViewById(R.id.tvUrl);
        /*
        TypeViewModel tvm = new ViewModelProvider(this).get(TypeViewModel.class);
        CommonViewModel cvm = new ViewModelProvider(this).get(CommonViewModel.class);
        Type type = new Type();
        type.name = "Aguas";
        Pokemon pokemon = new Pokemon();
        pokemon.height = 2.2;
        pokemon.weight = 2;
        pokemon.name = "Squirtle";
        pokemon.url = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/007.png";
        pvm.insertPokemon(pokemon, type); //hebra*/
    }
}