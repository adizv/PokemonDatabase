package org.izv.pokemon.view.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
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
import android.widget.Toast;

import org.izv.pokemon.R;
import org.izv.pokemon.model.entity.Pokemon;
import org.izv.pokemon.model.entity.Type;
import org.izv.pokemon.viewmodel.PokemonViewModel;
import org.izv.pokemon.viewmodel.TypeViewModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;

public class CreatePokemonActivity extends AppCompatActivity {

    //https://www.pokemon.com/es/api/pokedex/kalos

    private EditText etName, etHeight, etWeight, etUrl;
    private Spinner spType;
    private ImageView ivImage;
    private Pokemon pokemon;
    private PokemonViewModel pvm;
    private boolean firstTime = true;

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
            Pokemon pokemon = getPokemon();
            if(pokemon.isValid()) {
                addPokemon(pokemon);
            } else {
                Toast.makeText(this, "12 no valid", Toast.LENGTH_LONG).show();
            }
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
        return pokemon;
    }

    private void addPokemon(Pokemon pokemon) {
        pvm.insertPokemon(pokemon);
    }

    private void getViewModel() {
        pvm = new ViewModelProvider(this).get(PokemonViewModel.class);

        pvm.getKalos();
        pvm.getKalosResult().observe(this, s -> {
            //aqui me llega la lista de pokemons
            try {
                JSONArray array = new JSONArray(s);
                for (int i = 0, size = array.length(); i < size; i++) {
                    JSONObject objectInArray = array.getJSONObject(i);
                    //String[] elementNames = JSONObject.getNames(objectInArray);
                    /*System.out.printf("%d ELEMENTS IN CURRENT OBJECT:\n", elementNames.length);
                    for (String elementName : elementNames)
                    {
                        String value = objectInArray.getString(elementName);
                        System.out.printf("name=%s, value=%s\n", elementName, value);
                    }
                    System.out.println();*/
                }
                Log.v("xyzyx", "Pokemons: " + array.length());
            } catch (JSONException e) {
                Log.v("xyzyx", "Excepcion: " + e.getMessage());
            }
        });

        /*pvm.getInsertResult().observe(this, aLong -> {
            Log.v("xyzyx", "res: " + aLong);
        });*/
        pvm.getInsertResults().observe(this, list -> {
            Log.v("xyzyx", "res: " + list);
            if(list.get(0) > 0) {
                if(firstTime) {
                    firstTime = false;
                    alert();
                } else {
                    cleanFields();
                }
            } else {
                Toast.makeText(this, "10 no inserted", Toast.LENGTH_LONG).show();
            }
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

    private void alert() {
        AlertDialog.Builder builder  = new AlertDialog.Builder(this);
        builder.setTitle("Insertar mas?")
                .setMessage("El pokemon se ha insertado correctamente, desea seguir agregando pokemons?")
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setPositiveButton( android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cleanFields();
                    }
                });
        builder.create().show();
    }

    private void cleanFields() {
        etUrl.setText("");
        etWeight.setText("");
        etHeight.setText("");
        etName.setText("");
        spType.setSelection(0);
        Toast.makeText(this, "8 inserted", Toast.LENGTH_LONG).show();
    }
}