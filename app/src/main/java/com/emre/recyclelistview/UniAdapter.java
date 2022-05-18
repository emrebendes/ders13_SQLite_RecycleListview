package com.emre.recyclelistview;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.emre.recyclelistview.databinding.UniInfoBinding;

import java.util.ArrayList;

public class UniAdapter extends RecyclerView.Adapter<UniHolder> {
    ArrayList<University> uni;

    public UniAdapter(ArrayList<University> uni) {
        this.uni = uni;
    }

    @NonNull
    @Override
    public UniHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UniInfoBinding binding = UniInfoBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new UniHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UniHolder holder, int position) {
        holder.binding.imageView3.setImageResource(uni.get(position).getLogo());
        holder.binding.textView3.setText(uni.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),detay.class);
                intent.putExtra("uni",uni.get(position));
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return uni.size();
    }
}
