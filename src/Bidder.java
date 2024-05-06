public class Bidder { //{Bidder: AuctionHouse, Bidder}
    private int id; //{Bidder: AuctionHouse, Bidder}
    private String name; //{Bidder: AuctionHouse, Bidder}
    private int maxBid=0; //{Bidder: AuctionHouse, Bidder}
    // We implemented: 1 for new, 2 for verified, 3 for trusted
    private int reputation; //{AuctionHouse: AuctionHouse, Bidder}
    private boolean isVerifiedByReference; //{AuctionHouse: AuctionHouse,Bidder}

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
    public int getMaxBid() { //Output: {Bidder: AuctionHouse, Bidder}
        if (reputation >= 3) {
            return Integer.MAX_VALUE; // Effectively no limit to their bid. 
        } else if (isVerifiedByReference || reputation >= 2) {
            return maxBid;
        } else {
            // Limit for unverified or new users
            return Math.min(maxBid, 1000);
        }
    }

    public int getReputation(){ //Output: {AuctionHouse: AuctionHouse, Bidder}
        return this.reputation;
    }

    public boolean isVerifiedByReference(){ //Output: {AuctionHouse: AuctionHouse, Bidder}
        return this.isVerifiedByReference;
    }

    public int getId() { //Output: {Bidder: AuctionHouse, Bidder}
        return id;
    }
}
