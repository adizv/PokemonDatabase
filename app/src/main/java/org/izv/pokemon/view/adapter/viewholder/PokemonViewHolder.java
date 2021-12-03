package org.izv.pokemon.view.adapter.viewholder;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.izv.pokemon.R;
import org.izv.pokemon.model.entity.Pokemon;
import org.izv.pokemon.view.activity.CreatePokemonActivity;
import org.izv.pokemon.view.activity.EditPokemon;

public class PokemonViewHolder extends RecyclerView.ViewHolder {

    public TextView tvName, tvType, tvHeight, tvWeight, tvUrl;
    public ImageView ivPokemon;
    public Pokemon pokemon;

    public PokemonViewHolder(@NonNull View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tvName);
        tvType = itemView.findViewById(R.id.tvType);
        tvHeight = itemView.findViewById(R.id.tvHeight);
        tvWeight = itemView.findViewById(R.id.tvWeight);
        tvUrl = itemView.findViewById(R.id.tvUrl);
        ivPokemon = itemView.findViewById(R.id.ivPokemon);
        itemView.setOnClickListener(v -> {
            Log.v("xyzyx", "onclick" + pokemon.name);
            Intent intent = new Intent(itemView.getContext(), EditPokemon.class);
            intent.putExtra("pokemon", pokemon);
            itemView.getContext().startActivity(intent);
        });
    }
}
