package org.izv.pokemon.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bumptech.glide.Glide;

import org.izv.pokemon.R;
import org.izv.pokemon.model.entity.Pokemon;
import org.izv.pokemon.model.entity.Type;
import org.izv.pokemon.viewmodel.PokemonViewModel;
import org.izv.pokemon.viewmodel.TypeViewModel;

public class EditPokemon extends AppCompatActivity {
    private EditText etName, etHeight, etWeight, etUrl;
    private Spinner spType;
    private ImageView ivImage;
    private Pokemon pokemon;
    private PokemonViewModel pvm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pokemon);
        initialize();
    }

    private void initialize() {
        pokemon = getIntent().getExtras().getParcelable("pokemon");
        Log.v("xyzyx", pokemon.name);

        spType = findViewById(R.id.spType);
        etName = findViewById(R.id.etName);
        etWeight = findViewById(R.id.etWeight);
        etHeight = findViewById(R.id.etHeight);
        etUrl = findViewById(R.id.etUrl);
        ivImage = findViewById(R.id.ivImage);

        etName.setText(pokemon.name);
        etWeight.setText(pokemon.weight + "");
        etHeight.setText(pokemon.height + "");
        etUrl.setText(pokemon.url);
        Glide.with(this).load(pokemon.url).into(ivImage);

        PokemonViewModel pvm = new ViewModelProvider(this).get(PokemonViewModel.class);
        TypeViewModel tvm = new ViewModelProvider(this).get(TypeViewModel.class);
        tvm.getTypes().observe(this, types -> {
            Type type = new Type();
            type.id = 0;
            type.name = getString(R.string.default_type);
            types.add(0, type);
            ArrayAdapter<Type> adapter =
                    new ArrayAdapter<Type>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, types);
            spType.setAdapter(adapter);
            spType.setSelection((int) pokemon.idtype);
        });

        Button btEdit = findViewById(R.id.btEdit);
        btEdit.setOnClickListener(v -> {
            Pokemon p = getPokemon();
            if (p.isValid()){
                pvm.updatePokemon(p);
                finish();
            }
        });

    }

    private Pokemon getPokemon() {
        String name = etName.getText().toString().trim();
        String height = etHeight.getText().toString().trim();
        String weight = etWeight.getText().toString().trim();
        String url = etUrl.getText().toString().trim();
        Type type = (Type) spType.getSelectedItem();
        Pokemon pokemon = new Pokemon();
        pokemon.name = name;
        pokemon.height = Double.parseDouble(height);
        pokemon.weight = Integer.parseInt(weight);
        pokemon.url = url;
        pokemon.idtype = type.id;
        pokemon.id = this.pokemon.id;
        return pokemon;
    }
}