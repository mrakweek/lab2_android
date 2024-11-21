package com.example.shopping_list_lab2;

public class ShoppingItem {
    private String itemName;
    private boolean isChecked;

    public ShoppingItem(String itemName) {
        this.itemName = itemName;
        this.isChecked = false;
    }

    public String getItemName() {
        return itemName;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public void setItemName(String newItemName) {
        this.itemName = newItemName;
    }
}
