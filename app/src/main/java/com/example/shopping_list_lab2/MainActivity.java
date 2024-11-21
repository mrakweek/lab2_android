package com.example.shopping_list_lab2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ShoppingItem> shoppingList;
    private ShoppingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        shoppingList = new ArrayList<>();
        adapter = new ShoppingAdapter(shoppingList, new ShoppingAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(int position) {
                showEditDialog(position);
            }
        });
        recyclerView.setAdapter(adapter);

        // Кнопка для добавления
        Button btnAddItem = findViewById(R.id.btnAddItem);
        btnAddItem.setOnClickListener(view -> showAddDialog());
    }

    private void addItem(String itemName) {
        ShoppingItem item = new ShoppingItem(itemName);
        shoppingList.add(item);
        adapter.notifyDataSetChanged();
    }


    private void showEditDialog(final int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        View promptView = layoutInflater.inflate(R.layout.edit_dialog, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setView(promptView);

        final EditText editText = promptView.findViewById(R.id.editText);

        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", (dialog, id) -> {
                    String newItemName = editText.getText().toString();
                    if (!newItemName.isEmpty()) {
                        shoppingList.get(position).setItemName(newItemName);
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Cancel",
                        (dialog, id) -> dialog.cancel());

        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    private void showAddDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        View promptView = layoutInflater.inflate(R.layout.edit_dialog, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setView(promptView);

        final EditText editText = promptView.findViewById(R.id.editText);

        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", (dialog, id) -> {
                    String newItemName = editText.getText().toString();
                    if (!newItemName.isEmpty()) {
                        addItem(newItemName);
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Cancel",
                        (dialog, id) -> dialog.cancel());

        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

}
