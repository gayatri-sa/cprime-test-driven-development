package tdd.training;

public class FakeItem implements Item{
    private long timestamp;

    FakeItem() {
        this.timestamp = 0;
    }

    FakeItem(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public long getTimestamp() {
        return this.timestamp;
    }

    public String toString() {
        return "FakeItem [timestamp=" + this.timestamp + "]";
    }
}
