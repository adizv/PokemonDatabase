package org.izv.pokemon.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import org.izv.pokemon.R;
import org.izv.pokemon.model.entity.Pokemon;
import org.izv.pokemon.model.entity.PokemonType;
import org.izv.pokemon.model.entity.Type;
import org.izv.pokemon.view.adapter.viewholder.PokemonViewHolder;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonViewHolder> {

    private List<PokemonType> pokemonList;
    private Context context;

    public PokemonAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        PokemonType pokemonType = pokemonList.get(position);
        Pokemon pokemon = pokemonType.pokemon;
        Type type = pokemonType.type;
        holder.tvUrl.setText(pokemon.url);
        holder.tvWeight.setText(pokemon.weight + " " + context.getString(R.string.weight_unit));
        holder.tvHeight.setText(pokemon.height + " " + context.getString(R.string.height_unit));
        holder.tvType.setText(type.name + " (" + type.id + ")");
        holder.tvName.setText(pokemon.name);
        //Picasso.get().load(pokemon.url).into(holder.ivPokemon);
        Glide.with(context).load(pokemon.url).into(holder.ivPokemon);
    }

    @Override
    public int getItemCount() {
        if(pokemonList == null) {
            return 0;
        }
        return pokemonList.size();
    }

    public void setPokemonList(List<PokemonType> pokemonList) {
        /*if(this.pokemonList == null) {
            this.pokemonList = pokemonList;
        }*/
        this.pokemonList = pokemonList;
        notifyDataSetChanged();
    }
}
