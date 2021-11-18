package org.izv.pokemon.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.izv.pokemon.model.entity.Type;
import org.izv.pokemon.model.repository.PokemonRepository;

import java.util.List;

public class TypeViewModel extends AndroidViewModel {

    private PokemonRepository repository;

    public TypeViewModel(@NonNull Application application) {
        super(application);
        repository = new PokemonRepository(application);
    }

    public void insertType(Type... types) {
        repository.insertType(types);
    }

    public void updateType(Type... types) {
        repository.updateType(types);
    }

    public void deleteType(Type... types) {
        repository.deleteType(types);
    }

    public LiveData<List<Type>> getTypes() {
        return repository.getTypes();
    }

    public LiveData<Type> getType(long id) {
        return repository.getType(id);
    }
}
