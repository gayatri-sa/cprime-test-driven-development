package tdd.training;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.client.model.Filters.gt;

public class TimelineDataProvider implements ItemProvider {
    public MongoDatabase database;
    public MongoCollection collection;

    TimelineDataProvider() {
        this.database = new MongoClient().getDatabase("timelineDatabase");
        this.collection = this.database.getCollection("tddClass");
    }

    @Override
    public List<Item> fetchItems(int fetchCount, Item ancestor) {
        int counter = 0;
        List<Item> itemListToReturn = new ArrayList<>();

        MongoCursor<Item> iterator;
        iterator = this.getCollection().find(afterItem(ancestor)).limit(fetchCount).iterator();

        while (iterator.hasNext()) {
            Item document = iterator.next();
            // double check only items after the ancestor are collected
            if (null == ancestor || document.getTimestamp() < ancestor.getTimestamp()) {
                itemListToReturn.add(document);
                counter++;

            }

            if (counter == fetchCount) {
                break;
            }
        }
        // sort is always ascending hence we reverse the compare to check o2 against o1
        Collections.sort(itemListToReturn, (Item o1, Item o2) -> Long.compare(o2.getTimestamp(), o1.getTimestamp()));
        return itemListToReturn;
    }

    public MongoDatabase getDatabase() {
        return this.database;
    }

    public MongoCollection getCollection() {
        return this.collection;
    }

    public void saveUpdate(Item item) {
        this.getCollection().insertOne(item);
    }

    private Bson afterItem( Item ancestor ){
        Bson filter = gt("timestamp", 0);
        if (null != ancestor){
            filter = lt("timestamp", ancestor.getTimestamp());
        }

        return filter;
    }

    public int getTotalItems() {
        return (int)this.getCollection().countDocuments();
    }
}
