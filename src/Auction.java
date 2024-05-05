import java.util.ArrayList;
import java.util.List;

public class Auction { //{Auction:Auction,Bidder}
    private int currentBid; //{Auction:Auction,Bidder}
    private int currentLeaderId; //{Auction:Auction,Bidder}
    private List<Bidder> bidders = new ArrayList<>();//{Auction:Auction}
    private final int incrementStep = 50;// {Auction:Auction}

    public Auction(int startingBid) { //Input:{Auction:Auction,Bidder} Output:{Auction:Auction,Bidder}
        this.currentBid = startingBid;
        System.out.println("Auction house starts at: " + this.currentBid + " Kr");
    }

    public void addBidder(Bidder bidder) {//{Auction:Auction}
        bidders.add(bidder);
        updateInitialBid();
    }

    public int getCurrentBid() {//{Auction:Auction,Bidder}
        return currentBid;
    }

    public int getCurrentLeaderId() { // {Auction:Auction,Bidder}
        return currentLeaderId;
    }

    public void simulateAuction() { //{Auction:Auction,Bidder}
        placeLiveBid(3, 600); // true
        placeLiveBid(1, 800); // true
        placeLiveBid(2, 650); // false
        placeLiveBid(3, 700); // false
        placeLiveBid(2, 700); // false
        placeLiveBid(4, 820); // truee
        placeLiveBid(3, 750); // false
        placeLiveBid(2, 800); // false
        placeLiveBid(3, 1100); // false because of limit
        placeLiveBid(1, 1200); // truee
        placeLiveBid(4, 20000); // truee and winner!
    }

    // Updating initial bid based on commission bids
    private void updateInitialBid() { //{Auction:Auction}
        int initialCommissionBid = 0; //{Auction:Auction}
        for (Bidder bidder : bidders) { //{Auction:Auction,Bidder}
            if ((bidder.isVerifiedByReference() || bidder.getReputation() >= 2) && bidder.getMaxBid() > initialCommissionBid) {
                initialCommissionBid = Math.min(bidder.getMaxBid(), initialCommissionBid + incrementStep); //{Auction:Auction,Bidder}
            }
        }
        if (initialCommissionBid > currentBid) {
            currentBid = initialCommissionBid; //{Auction:Auction,Bidder}
            currentLeaderId = getHighestCommissionBidder().getId(); //{Auction:Auction}
        }
    }

    public void placeLiveBid(int bidderId, int bidAmount) { //{Auction:Auction,Bidder}
        Bidder bidder = bidders.stream().filter(b -> b.getId() == bidderId).findFirst().orElse(null); //{Auction:Auction,Bidder}
        if (bidder != null && bidAmount > (currentBid + incrementStep - 1) && bidAmount <= bidder.getMaxBid()) {
            // Check if the bid is a valid increment over the current bid
            if (bidAmount > currentBid + incrementStep - 1) {
                currentBid = bidAmount; //{Auction:Auction,Bidder}
                currentLeaderId = bidderId; //{Auction:Auction,Bidder}
                System.out.println("Bidder #" + bidderId + " bids " + bidAmount + " Kr"); //{Auction:Auction,Bidder}
            }
        } else if (bidder != null && bidder.getReputation() >= 3) {
            // High reputation bidders can ignore the max bid constraint
            if (bidAmount > currentBid + incrementStep - 1) {
                currentBid = bidAmount; //{Auction:Auction,Bidder}
                currentLeaderId = bidderId; //{Auction:Auction,Bidder}
                System.out.println("Bidder #" + bidderId + " (trusted) bids " + bidAmount + " Kr"); //{Auction:Auction,Bidder}
            }
        }
    }


    private Bidder getHighestCommissionBidder() { //Output:{Auction:Auction}
        Bidder maxBidder = null; //{Auction:Auction}
        for (Bidder bidder : bidders) {
            if (bidder.isVerifiedByReference() || bidder.getReputation() >= 2) {
                if (maxBidder == null || bidder.getMaxBid() > maxBidder.getMaxBid()) {
                    maxBidder = bidder; //{Auction:Auction}
                }
            }
        }
        return maxBidder;//{Auction:Auction}
    }

    public void announceWinner() {//{Auction:Auction,Bidder}
        System.out.println("Going once... Going twice... Sold to bidder #" + this.currentLeaderId + " for " + this.currentBid + " Kr!"); //{Auction:Auction,Bidder}
    }
}
