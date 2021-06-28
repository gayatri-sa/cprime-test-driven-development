package tdd.training;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.conversions.Bson;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TimelineDataProviderTest {

    TimelineDataProvider timelineDataProvider;
    private MongoCollection mockCollection;

    private FindIterable iterable;
    private MongoCursor cursor;

    @Before
    public void setUp() throws Exception {
        timelineDataProvider = Mockito.spy(new TimelineDataProvider());
        this.mockCollection = Mockito.mock(com.mongodb.client.MongoCollection.class);
        when(timelineDataProvider.getCollection()).thenReturn(mockCollection);

        iterable = mock(FindIterable.class);
        cursor = mock(MongoCursor.class);
        when(mockCollection.find()).thenReturn(iterable);
        when(iterable.iterator()).thenReturn(cursor);

        loadData();
    }

    @Test
    public void getDatabase() {
        assertNotNull(timelineDataProvider.getDatabase());
    }

    @Test
    public void getDatabaseName() {
        assertEquals("timelineDatabase", timelineDataProvider.getDatabase().getName());
    }

    @Test
    public void getCollection() {
        assertNotNull(timelineDataProvider.getCollection());
    }

    @Test
    public void whenSavingDataItemThenInvokeMongoCollection() {
        FakeItem item = new FakeItem(System.currentTimeMillis());
        timelineDataProvider.saveUpdate(item);

        verify(mockCollection, times(1)).insertOne(any(Item.class));
    }

    @Test
    public void fetchDefaultCountOfItems() {
        assertEquals(10, timelineDataProvider.fetchItems(10, null).size());
    }

    @Test
    public void fetchedItemsIsInDescOrder() {
        List<Item> original = timelineDataProvider.fetchItems(10, null);
        List<Item> sorted = new ArrayList<Item>(original);
        Collections.reverse(sorted);

        assertEquals(original.get(0), sorted.get(sorted.size()-1));
    }

    @Test
    public void getOlderItemsOnSecondFetch() {
        List<Item> items = timelineDataProvider.fetchItems(10, null);
        Item ancestor = items.get(items.size() - 1);
        List<Item> nextitems = timelineDataProvider.fetchItems(10, ancestor);
        assertNotNull(nextitems);
        assertTrue(ancestor.getTimestamp() > nextitems.get(0).getTimestamp());
    }

    @Test
    public void getTotalPostCount() {
        assertEquals(20, timelineDataProvider.getTotalItems());

    }

    private void loadData() {
        List<Item> itemList = new ArrayList<Item>();
        for (int i=20; i>=1; i--){
            itemList.add(new FakeItem((i*10)));
        }

        when(mockCollection.find().limit(anyInt())).thenReturn(iterable);
        when(mockCollection.find(any(Bson.class))).thenReturn(iterable);

        when(cursor.next()).thenReturn(itemList.get(0), itemList.get(1), itemList.get(2), itemList.get(3), itemList.get(4), itemList.get(5), itemList.get(6), itemList.get(7), itemList.get(8), itemList.get(9), itemList.get(10), itemList.get(11), itemList.get(12), itemList.get(13), itemList.get(14), itemList.get(15), itemList.get(16), itemList.get(17), itemList.get(18), itemList.get(19));
        when(cursor.hasNext()).thenReturn(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false);

        when(mockCollection.countDocuments()).thenReturn((long)itemList.size());
    }
}
