import java.util.ArrayList;
import java.util.List;

public class Auction {
    private int currentBid;
    private int currentLeaderId;
    private List<Bidder> bidders = new ArrayList<>();
    private final int incrementStep = 50;

    public Auction(int startingBid) {
        this.currentBid = startingBid;
        System.out.println("Auction house starts at: " + this.currentBid + " Kr");
    }

    public void addBidder(Bidder bidder) {
        bidders.add(bidder);
        updateInitialBid();
    }

    public int getCurrentBid() {
        return currentBid;
    }

    public int getCurrentLeaderId() {
        return currentLeaderId;
    }

    public void simulateAuction() {
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
    private void updateInitialBid() {
        int initialCommissionBid = 0;
        for (Bidder bidder : bidders) {
            if ((bidder.isVerifiedByReference() || bidder.getReputation() >= 2) && bidder.getMaxBid() > initialCommissionBid) {
                initialCommissionBid = Math.min(bidder.getMaxBid(), initialCommissionBid + incrementStep);
            }
        }
        if (initialCommissionBid > currentBid) {
            currentBid = initialCommissionBid;
            currentLeaderId = getHighestCommissionBidder().getId();
        }
    }

    public void placeLiveBid(int bidderId, int bidAmount) {
        Bidder bidder = bidders.stream().filter(b -> b.getId() == bidderId).findFirst().orElse(null);
        if (bidder != null && bidAmount > (currentBid + incrementStep - 1) && bidAmount <= bidder.getMaxBid()) {
            // Check if the bid is a valid increment over the current bid
            if (bidAmount > currentBid + incrementStep - 1) {
                currentBid = bidAmount;
                currentLeaderId = bidderId;
                System.out.println("Bidder #" + bidderId + " bids " + bidAmount + " Kr");
            }
        } else if (bidder != null && bidder.getReputation() >= 3) {
            // High reputation bidders can ignore the max bid constraint
            if (bidAmount > currentBid + incrementStep - 1) {
                currentBid = bidAmount;
                currentLeaderId = bidderId;
                System.out.println("Bidder #" + bidderId + " (trusted) bids " + bidAmount + " Kr");
            }
        }
    }


    private Bidder getHighestCommissionBidder() {
        Bidder maxBidder = null;
        for (Bidder bidder : bidders) {
            if (bidder.isVerifiedByReference() || bidder.getReputation() >= 2) {
                if (maxBidder == null || bidder.getMaxBid() > maxBidder.getMaxBid()) {
                    maxBidder = bidder;
                }
            }
        }
        return maxBidder;
    }

    public void announceWinner() {
        System.out.println("Going once... Going twice... Sold to bidder #" + this.currentLeaderId + " for " + this.currentBid + " Kr!");
    }
}
