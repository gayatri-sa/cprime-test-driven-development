package tdd.training;

import java.util.ArrayList;
import java.util.List;

public class Timeline {

    private static final int DEFAULT_FETCH_COUNT = 10;
    public static final int FETCH_COUNT_LOWER_BOUND = 1;
    public static final int FETCH_COUNT_UPPER_BOUND = 20;
    private int fetchCount;

    private final ItemProvider itemProvider;
    private List<Item>items;

    public Timeline(ItemProvider itemProvider) {
        this.fetchCount = DEFAULT_FETCH_COUNT;
        this.itemProvider = itemProvider;
        this.items = new ArrayList<Item>();
    }

    public void setFetchCount(int fetchCount) {
        if ( fetchCount >= FETCH_COUNT_LOWER_BOUND && fetchCount <= FETCH_COUNT_UPPER_BOUND ) {
            this.fetchCount = fetchCount;
        }
    }

    public int getFetchCount() {
        return this.fetchCount;
    }

    public ItemProvider getItemProvider() {
        return this.itemProvider;
    }

    public List<Item> getItems() {
        return items;
    }

    public void fetchItems() {
        items.addAll( this.getItemProvider().fetchItems(getFetchCount(), null));
    }
}
