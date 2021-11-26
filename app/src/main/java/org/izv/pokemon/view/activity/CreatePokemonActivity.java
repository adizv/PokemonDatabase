package org.izv.pokemon.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import org.izv.pokemon.R;
import org.izv.pokemon.model.entity.Pokemon;
import org.izv.pokemon.model.entity.Type;
import org.izv.pokemon.viewmodel.PokemonViewModel;
import org.izv.pokemon.viewmodel.TypeViewModel;

import java.util.List;

public class CreatePokemonActivity extends AppCompatActivity {

    //after first insert
    //preguntar si se quiere insertar más
    //si no, se cierra y se vuelve atrás
    //si sí, ya no se vuelve a preguntar
    //alertdialog

    private EditText etName, etHeight, etWeight, etUrl;
    private Spinner spType;
    private ImageView ivImage;
    private Pokemon pokemon;
    private PokemonViewModel pvm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pokemon);
        initialize();
    }

    private void initialize() {
        spType = findViewById(R.id.spType);
        etName = findViewById(R.id.etName);
        etWeight = findViewById(R.id.etWeight);
        etHeight = findViewById(R.id.etHeight);
        etUrl = findViewById(R.id.etUrl);
        ivImage = findViewById(R.id.ivImage);
        getViewModel();
        defineAddListener();
    }

    private void defineAddListener() {
        Button btAdd = findViewById(R.id.btAdd);
        btAdd.setOnClickListener(v -> {
            addPokemon();
            //1º validar los datos
            //2º si está bien
            //   3º insertar
            //   4º si he insertado bien
            //      5º si es la primera vez
            //         6º mostrar el alert
            //      7º sino
            //         8º limpiar + toast
            //   9º sino
            //      10º toast
            //11º sino
            //   12º toast
        });
    }

    private void addPokemon() {
        if(isInputDataValid()) {
            Pokemon p = new Pokemon();
            p.name = "otro";
            p.weight = 12;
            p.height = 1.2;
            p.url = "url";
            p.idtype = 1;
            pvm.insertPokemon(pokemon, p);
        }
    }

    private boolean isInputDataValid(){
        String name = etName.getText().toString().trim();
        String height = etHeight.getText().toString().trim();
        String weight = etWeight.getText().toString().trim();
        String url = etUrl.getText().toString().trim();
        Type type = (Type) spType.getSelectedItem();
        pokemon = new Pokemon();
        pokemon.idtype = type.id;
        pokemon.name = name;
        pokemon.height = Double.parseDouble(height);
        pokemon.weight = Integer.parseInt(weight);
        pokemon.url = url;
        return !name.isEmpty() && !height.isEmpty() && !weight.isEmpty() && !url.isEmpty() && !(type.id==0);
    }

    private void getViewModel() {
        pvm = new ViewModelProvider(this).get(PokemonViewModel.class);

        pvm.getInsertResult().observe(this, aLong -> {
            Log.v("xyzyx", "res: " + aLong);
        });

        pvm.getInsertResults().observe(this, list -> {
            Log.v("xyzyx", "res: " + list);
        });

        TypeViewModel tvm = new ViewModelProvider(this).get(TypeViewModel.class);
        tvm.getTypes().observe(this, types -> {
            Type type = new Type();
            type.id = 0;
            type.name = getString(R.string.default_type);
            types.add(0, type);
            ArrayAdapter<Type> adapter =
                    new ArrayAdapter<Type>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, types);
            spType.setAdapter(adapter);
        });
    }
}