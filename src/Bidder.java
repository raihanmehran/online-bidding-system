public class Bidder {
    private int id;
    private int maxBid;
    SecurityLabel securityLabel = SecurityLabel.PRIVATE;

    public Bidder(int id, int maxBid) {
        this.id = id;
        this.maxBid = maxBid;
    }

    public int getId() {
        return id;
    }

    public int getMaxBid() {
        return maxBid;
    }
}
