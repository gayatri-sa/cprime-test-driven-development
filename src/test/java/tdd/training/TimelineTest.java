package tdd.training;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class TimelineTest {

    private Timeline timeline;
    private ItemProviderStub itemProvider;

    @Before
    public void setUp() throws Exception {
        //ItemProviderDummy itemProvider = new ItemProviderDummy();
        itemProvider = new ItemProviderStub();

        timeline = new Timeline(itemProvider);

        // populate the database (ItemProvider) with dummy data
        List<Item> itemList = new ArrayList<Item>();
        for (int i=1; i<=20; i++){
            itemList.add(new FakeItem((i*10)));
        }
        itemProvider.addItems(itemList);
    }

    @Test
    public void setFetchCount() {
        int expected = 5;

        timeline.setFetchCount( expected );
        int actual = timeline.getFetchCount();

        assertEquals( expected, actual );
    }

    @Test
    public void initialFetchCountValue(){
        assertTrue( timeline.getFetchCount() > 0);
    }

    @Test
    public void setFetchCountExceedsUpperBound() {
        int originalFetchCount = timeline.getFetchCount();

        timeline.setFetchCount(Timeline.FETCH_COUNT_UPPER_BOUND + 1);

        assertEquals(originalFetchCount, timeline.getFetchCount());

    }

    @Test
    public void setFetchCountExceedsLowerBound() {
        int originalFetchCount = timeline.getFetchCount();

        timeline.setFetchCount(Timeline.FETCH_COUNT_LOWER_BOUND - 1);

        assertEquals(originalFetchCount, timeline.getFetchCount());

    }

    @Test
    public void initializeTimelineWithItemProvider() {
        ItemProviderDummy itemProvider = new ItemProviderDummy();

        Timeline newtimeline = new Timeline(itemProvider);
        assertEquals(itemProvider, newtimeline.getItemProvider());
    }

    @Test
    public void getTimelineItemProvider() {
        assertNotNull(timeline.getItemProvider());
    }

    @Test
    public void getItems() {
        assertNotNull(timeline.getItems());
    }

    @Test
    public void fetchItemsDefaultFetchCount() {
        timeline.fetchItems();
        assertEquals(10, timeline.getItems().size());
    }

    @Test
    public void fetchedItemsInDescOrder() {
        timeline.fetchItems();
        List<Item> actual = timeline.getItems(); // desc order
        List<Item> sorted = new ArrayList<Item>(actual);
        Collections.reverse(sorted); // asc order

        assertEquals(sorted.get(actual.size()-1), actual.get(0));
    }

    @Test
    public void secondFetchLoads20Items() {
        timeline.fetchItems(); // 10
        timeline.fetchItems(); // 10 more

        assertEquals(20, timeline.getItems().size());
    }

}
