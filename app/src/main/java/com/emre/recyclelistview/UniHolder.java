package com.emre.recyclelistview;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.emre.recyclelistview.databinding.UniInfoBinding;

public class UniHolder extends RecyclerView.ViewHolder {
    UniInfoBinding binding;
    public UniHolder(UniInfoBinding binding) {
        super(binding.getRoot());
        this.binding=binding;
    }
}
