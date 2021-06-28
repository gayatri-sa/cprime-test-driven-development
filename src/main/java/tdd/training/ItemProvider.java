package tdd.training;

import java.util.Collection;
import java.util.List;

public interface ItemProvider {
    List<Item> fetchItems(int fetchCount, Item ancestor);
}
