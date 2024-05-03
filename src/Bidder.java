public class Bidder {
    private int id;
    private String name;
    private int maxBid=0;
    // We implemented: 1 for new, 2 for verified, 3 for trusted
    private int reputation;
    private boolean isVerifiedByReference;

    public Bidder(int id, String name, int maxBid, int reputation, boolean isVerifiedByReference) {
        this.id = id;
        this.name = name;
        this.maxBid = maxBid;
        this.reputation = reputation;
        this.isVerifiedByReference = isVerifiedByReference;
    }

    // Reputation: Max Bid Amount
    // 1: New : 1000
    // 2: Verified : Specified Amount
    // 3: Trusted: Unlimited Amount
    public int getMaxBid() {
        if (reputation >= 3) {
            return Integer.MAX_VALUE; // Effectively no limit to their bid.
        } else if (isVerifiedByReference || reputation >= 2) {
            return maxBid;
        } else {
            // Limit for unverified new users
            return Math.min(maxBid, 1000);
        }
    }

    public int getReputation(){
        return this.reputation;
    }

    public boolean isVerifiedByReference(){
        return this.isVerifiedByReference;
    }

    public int getId() {
        return id;
    }
}
