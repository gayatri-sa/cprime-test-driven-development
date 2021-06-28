package tdd.training;

import java.util.*;
import java.util.stream.Collectors;

public class ItemProviderStub implements ItemProvider {
    private List<Item> items;

    public ItemProviderStub() {
        items = new ArrayList<Item>();
    }
    public void addItems(List<Item> itemsToAdd) {
        items.addAll( itemsToAdd );
    }

    @Override
    public List<Item> fetchItems(int fetchCount, Item ancestor) {
        return items
                .stream()
                .sorted( descending() )
                .limit( fetchCount )
                .collect( Collectors.toList() );
    }

    private Comparator<? super Item> descending() {
        return ( first, second )
            -> Long.compare( second.getTimestamp(), first.getTimestamp());
    }
}
