import java.util.ArrayList;
import java.util.List;

public class Auction {
    private int currentBid;
    private int currentLeaderId;
    private List<Bidder> bidders = new ArrayList<>();
    // incrementing amount for each new bed
    private final int incrementStep = 50;

    public Auction(int startingBid) {
        this.currentBid = startingBid;
    }

    public void addBidder(Bidder bidder) {
        bidders.add(bidder);
        updateInitialBid();
    }

    public void simulateAuction() {
        currentBid = 550;
        currentLeaderId = 2;
        System.out.println("Auction house bids for "+ currentLeaderId +" with bid amount: " + currentBid + " Kr");

        placeLiveBid(3, 600);
        placeLiveBid(2, 650);
        placeLiveBid(3, 700);
        placeLiveBid(2, 700);
        placeLiveBid(3, 750);
    }

    // Updating initial bid based on commission bids
    private void updateInitialBid() {
        for (Bidder bidder : bidders) {
            if (bidder.getMaxBid() >= currentBid + incrementStep) {
                currentBid += incrementStep;
                currentLeaderId = bidder.getId();
            }
        }
    }

    // Placing bid during live auction
   /*public void placeLiveBid(int bidderId, int bidAmount) {
        if (bidAmount > currentBid) {
            if (bidAmount >= getMaxCommissionBid() && bidAmount > currentBid) {
                currentBid = bidAmount;
                currentLeaderId = bidderId;
            } else {
                // Adjusting bid to the maximum commission bid
                currentBid = Math.max(currentBid + incrementStep, getMaxCommissionBid());
                currentLeaderId = getHighestCommissionBidder().getId();
            }
        }
    }*/
    /*public void placeLiveBid(int bidderId, int bidAmount) {
        if (bidAmount > currentBid) {
            currentBid = bidAmount;
            currentLeaderId = bidderId;
            System.out.println((bidderId == 3 ? "C" : "Auction house") + " bids " + bidAmount + " Kr");
        }
    }*/

    public void placeLiveBid(int bidderId, int bidAmount) {
        if (bidAmount > currentBid) {
            currentBid = bidAmount;
            currentLeaderId = bidderId;
            System.out.println("Bidder #" + bidderId + " bids " + bidAmount + " Kr");
        }
    }

    private int getMaxCommissionBid() {
        int maxBid = 0;
        for (Bidder bidder : bidders) {
            if (bidder.getMaxBid() > maxBid) {
                maxBid = bidder.getMaxBid();
            }
        }
        return maxBid;
    }

    private Bidder getHighestCommissionBidder() {
        Bidder maxBidder = null;
        for (Bidder bidder : bidders) {
            if (maxBidder == null || bidder.getMaxBid() > maxBidder.getMaxBid()) {
                maxBidder = bidder;
            }
        }
        return maxBidder;
    }

    public void announceWinner() {
        System.out.println("Going once... Going twice... Sold to bidder #" + currentLeaderId + " for " + currentBid + " Kr!");
    }
}
