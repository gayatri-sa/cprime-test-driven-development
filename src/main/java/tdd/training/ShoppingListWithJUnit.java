package tdd.training;

import org.schogini.ShoppingListItem;

import java.util.ArrayList;

public class ShoppingListWithJUnit {
    ArrayList<ShoppingListItem> listOfItemsForShopping = new ArrayList<ShoppingListItem>();

    public ArrayList getListOfItemForShopping() {
        return this.listOfItemsForShopping;
    }

    public void setListOfItemForShopping(ArrayList<ShoppingListItem> itemList){
        this.listOfItemsForShopping = itemList;
    }
}
