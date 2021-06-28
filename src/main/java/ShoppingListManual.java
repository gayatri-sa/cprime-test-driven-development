import org.schogini.ShoppingListItem;

import java.util.ArrayList;

public class ShoppingListManual {

    public static void main() {
        ShoppingListItem shoppingListItem = new ShoppingListItem();
        shoppingListItem.name = "Bread";
        shoppingListItem.qty = 2;

        ShoppingListManual test = new ShoppingListManual();
        ArrayList myNewShoppingList = new ArrayList();
        myNewShoppingList.add(shoppingListItem);
        test.setListOfItemForShopping(myNewShoppingList);

        if (myNewShoppingList == test.getListOfItemForShopping()) {
            System.out.println("All good");
        } else {
            System.out.println("Something is not right!");
        }
    }

    ArrayList listOfItemsForShopping = new ArrayList();
    public ArrayList getListOfItemForShopping() {
        return this.listOfItemsForShopping;
    }

    public void setListOfItemForShopping(ArrayList itemList) {
        this.listOfItemsForShopping = itemList;
    }
}
