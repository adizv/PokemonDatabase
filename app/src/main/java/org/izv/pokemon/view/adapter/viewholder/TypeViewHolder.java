package org.izv.pokemon.view.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.izv.pokemon.R;

public class TypeViewHolder extends RecyclerView.ViewHolder {

    private TextView tvType;

    public TypeViewHolder(@NonNull View itemView) {
        super(itemView);
        tvType = itemView.findViewById(R.id.tvType);
    }

    public TextView getTypeItemView() {
        return tvType;
    }
}
