package com.example.shopping_list_lab2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter.ViewHolder> {
    private List<ShoppingItem> shoppingList;
    private OnItemLongClickListener onItemLongClickListener; // Добавим интерфейс

    public interface OnItemLongClickListener {
        void onItemLongClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView itemNameTextView;
        public CheckBox itemCheckBox;

        public ViewHolder(View itemView) {
            super(itemView);
            itemNameTextView = itemView.findViewById(R.id.itemNameTextView);
            itemCheckBox = itemView.findViewById(R.id.itemCheckBox);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (onItemLongClickListener != null) {
                        onItemLongClickListener.onItemLongClick(getAdapterPosition());
                        return true;
                    }
                    return false;
                }
            });
        }
    }

    public ShoppingAdapter(List<ShoppingItem> shoppingList, OnItemLongClickListener onItemLongClickListener) {
        this.shoppingList = shoppingList;
        this.onItemLongClickListener = onItemLongClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ShoppingItem item = shoppingList.get(position);
        holder.itemNameTextView.setText(item.getItemName());
        holder.itemCheckBox.setChecked(item.isChecked());

        holder.itemCheckBox.setOnClickListener(view -> {
            item.setChecked(holder.itemCheckBox.isChecked());
        });
    }

    @Override
    public int getItemCount() {
        return shoppingList.size();
    }
}

