package org.schogini;

public class ShoppingListItem {
    public String name;
    public int qty;

    public ShoppingListItem() {
        this.name = "";
        this.qty = 0;
    }

    public ShoppingListItem(String name, int qty){
        this.name = name;
        this.qty = qty;
    }
}
