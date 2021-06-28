package tdd.training;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.schogini.ShoppingListItem;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ShoppingListWithJUnitTest {

    ShoppingListWithJUnit shoppingList;
    ShoppingListItem shoppingListItem;
    @Before
    public void setUp() throws Exception {
        shoppingList = new ShoppingListWithJUnit();
        shoppingListItem = new ShoppingListItem("Bread", 2);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getListOfItemForShopping() {
        assertNotNull(shoppingList.getListOfItemForShopping());
    }

    @Test
    public void setListOfItemForShopping() {
        ArrayList<ShoppingListItem> myNewShoppingList = new ArrayList<ShoppingListItem>();
        myNewShoppingList.add(shoppingListItem);

        shoppingList.setListOfItemForShopping(myNewShoppingList);
        assertEquals(myNewShoppingList, shoppingList.getListOfItemForShopping());

    }
}