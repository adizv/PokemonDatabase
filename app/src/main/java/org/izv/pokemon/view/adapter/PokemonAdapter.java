package org.izv.pokemon.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.izv.pokemon.R;
import org.izv.pokemon.model.entity.Pokemon;
import org.izv.pokemon.view.adapter.viewholder.PokemonViewHolder;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonViewHolder> {

    private List<Pokemon> pokemonList;

    public PokemonAdapter(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        Pokemon pokemon = pokemonList.get(position);
        holder.tvUrl.setText(pokemon.url);
        holder.tvWeight.setText(pokemon.weight + "");
        holder.tvHeight.setText(pokemon.height + "");
        holder.tvType.setText(pokemon.idtype + "");
        holder.tvName.setText(pokemon.name);
    }

    @Override
    public int getItemCount() {
        if(pokemonList == null) {
            return 0;
        }
        return pokemonList.size();
    }

    public void setPokemonList(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
        notifyDataSetChanged();
    }
}
