public class Bidder { //{Auction, Bidder: Auction, Bidder}
    private int id; //{Auction, Bidder: Auction, Bidder}
    private String name; //{Auction, Bidder: Auction, Bidder}
    private int maxBid=0; //{Bidder: Auction, Bidder}
    // We implemented: 1 for new, 2 for verified, 3 for trusted
    private int reputation; //{Auction: Auction, Bidder}
    private boolean isVerifiedByReference; //{Auction: Auction}

    public Bidder(int id, String name, int maxBid, int reputation, boolean isVerifiedByReference) { //{Auction: Auction, Bidder} not sure about this?
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
    public int getMaxBid() { //Output: {Auction: Auction, Bidder}
        if (reputation >= 3) {
            return Integer.MAX_VALUE; // Effectively no limit to their bid. //{Auction: Auction, Bidder}
        } else if (isVerifiedByReference || reputation >= 2) {
            return maxBid;
        } else {
            // Limit for unverified new users
            return Math.min(maxBid, 1000);
        }
    }

    public int getReputation(){ //Output: {Auction: Auction, Bidder}
        return this.reputation;
    }

    public boolean isVerifiedByReference(){ //Output: {Auction: Auction, Bidder}
        return this.isVerifiedByReference;
    }

    public int getId() { //Output: {Auction: Auction, Bidder}
        return id;
    }
}
