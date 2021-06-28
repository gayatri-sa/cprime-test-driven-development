import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.schogini.*;
public class ShoppingListAutomated {

    ArrayList listOfItemsForShopping = new ArrayList();
    public ArrayList getListOfItemForShopping() {
        return this.listOfItemsForShopping;
    }

    public void setListOfItemForShopping(ArrayList itemList) {
        this.listOfItemsForShopping = itemList;
    }

    @Test
    public void getListTest() {
        //assertTrue(null != this.getListOfItemForShopping());
        assertNotNull(this.getListOfItemForShopping());
    }

    @Test
    public void setItemTest() {
        ShoppingListItem shoppingListItem = new ShoppingListItem();
        shoppingListItem.name = "Bread";
        shoppingListItem.qty = 2;

        ArrayList myNewShoppingList = new ArrayList();
        myNewShoppingList.add(shoppingListItem);
        this.setListOfItemForShopping(myNewShoppingList);
        assertEquals(myNewShoppingList, this.getListOfItemForShopping());
    }
}
