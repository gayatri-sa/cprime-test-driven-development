package tdd.training;

import java.util.List;

public class ItemProviderDummy implements ItemProvider {
    @Override
    public List<Item> fetchItems(int fetchCount, Item ancestor) {
        return null;
    }
}
