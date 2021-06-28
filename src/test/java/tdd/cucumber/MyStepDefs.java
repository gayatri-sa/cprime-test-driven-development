package tdd.cucumber;
import io.cucumber.java.en.*;
import static org.junit.Assert.*;

import tdd.training.Item;
import tdd.training.Timeline;

import java.util.*;

public class MyStepDefs {
    private Timeline timeline;
    private ItemProviderStub timelineDataProvider;
    private List<Item> itemList;

    @Given("Timeline Database has at least 10 items")
    public void timeline_database_has_at_least_10_items() {
        itemList = new ArrayList<>();
        for (int i=1; i <= 12; i++) {
            itemList.add(new FakeItem( (i * 10)));
        }
        timelineDataProvider = new ItemProviderStub();
        timelineDataProvider.addItems(itemList);
    }

    @When("Timeline is initialized by consumer")
    public void timeline_is_initialized_by_consumer() {
        timeline = new Timeline(timelineDataProvider);
    }

    @Then("timeline should be loaded with {int} items from database")
    public void timeline_should_be_loaded_with(int expectedAnswer) {
        timeline.fetchItems();
        assertEquals(expectedAnswer, timeline.getItems().size());
    }

    @And("items should be sorted in descending order of date-time when items were created")
    public void itemsShouldBeSortedInDescendingOrderOfDateTimeWhenItemsWereCreated() {
        Collections.reverse(itemList);
        assertArrayEquals(itemList.stream().limit(10).toArray(), timeline.getItems().toArray());
    }

    @Given("Database has more than 20 items")
    public void database_has_more_than_20_items() {
        itemList = new ArrayList<>();
        for (int i=1; i <= 25; i++) {
            itemList.add(new FakeItem( (i * 10)));
        }
        timelineDataProvider = new ItemProviderStub();
        timelineDataProvider.addItems(itemList);
    }

    @When("Consumer requests more items")
    public void consumer_requests_more_items() {
        // Since its more items we will call fetch once here for the first page
        // and once in the THEN function below
        timeline.fetchItems();
    }

    @Then("Timeline should be loaded with {int} more items")
    public void timeline_should_be_loaded_with_more_items(int expected) {
        int currentsize = timeline.getItems().size();

        // calling fetchItems the second time because its more items
        timeline.fetchItems();
        assertEquals(currentsize + expected, timeline.getItems().size());
    }

    @Then("total item count should be {int}")
    public void total_item_count_should_be(int expected) {
        assertEquals(expected, timeline.getItems().size());
    }

}
