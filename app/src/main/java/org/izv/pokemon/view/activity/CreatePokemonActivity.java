package org.izv.pokemon.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import org.izv.pokemon.R;
import org.izv.pokemon.model.entity.Type;
import org.izv.pokemon.viewmodel.PokemonViewModel;
import org.izv.pokemon.viewmodel.TypeViewModel;

import java.util.List;

public class CreatePokemonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pokemon);
        initialize();
    }

    private void initialize() {
        Spinner spType = findViewById(R.id.spType);
        TypeViewModel tvm = new ViewModelProvider(this).get(TypeViewModel.class);
        tvm.getTypes().observe(this, types -> {
            ArrayAdapter<Type> adapter =
                    new ArrayAdapter<Type>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, types);
            spType.setAdapter(adapter);
        });
    }
}