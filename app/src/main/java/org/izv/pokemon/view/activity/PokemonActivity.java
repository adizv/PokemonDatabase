package org.izv.pokemon.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import org.izv.pokemon.R;
import org.izv.pokemon.model.entity.Type;
import org.izv.pokemon.view.adapter.viewholder.PokemonViewHolder;
import org.izv.pokemon.viewmodel.PokemonViewModel;
import org.izv.pokemon.viewmodel.TypeViewModel;

public class PokemonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        PokemonViewModel pvm = new ViewModelProvider(this).get(PokemonViewModel.class);
        TypeViewModel tvm = new ViewModelProvider(this).get(TypeViewModel.class);

        Type type = new Type();
        type.name = "Fuego";

        tvm.insertType(type);

    }
}