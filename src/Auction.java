import java.util.ArrayList;
import java.util.List;

public class Auction { //{AuctionHouse:AuctionHouse, Bidder}
    private int currentBid; //{AuctionHouse:AuctionHouse,Bidder}
    private int currentLeaderId; //{AuctionHouse:AuctionHouse}
    private List<Bidder> bidders = new ArrayList<>();//{AuctionHouse:AuctionHouse}
    private final int incrementStep = 50;// {AuctionHouse:AuctionHouse, Bidder} 

    public Auction(int startingBid) { 
        this.currentBid = startingBid;
        System.out.println("AuctionHouse house starts at: " + this.currentBid + " Kr");
    }

    public void addBidder(Bidder bidder) {//{AuctionHouse:AuctionHouse}
        bidders.add(bidder);
        //updateInitialBid();
    }

    public int getCurrentBid() {
        return currentBid;
    }

    public int getCurrentLeaderId() { 
        return currentLeaderId;
    }

    public void simulateAuctionHouse() { 
        placeBid(3, 600); // should be placed
        placeBid(1, 800); // should be placed
        placeBid(2, 650); // should not be placed
        placeBid(3, 700); // should not be placed
        placeBid(2, 700); // should not be placed
        placeBid(4, 820); // should be placed
        placeBid(3, 750); // should not be placed
        placeBid(2, 800); // should not be placed
        placeBid(3, 1100); // should not be placed because the limit was reached
        placeBid(1, 1200); // should be placed
        placeBid(4, 20000); // should be placed and WINNER!
    }

    // Updating initial bid based on commission bids
    /*private void updateInitialBid() { 
        int initialCommissionBid = 0; 
        for (Bidder bidder : bidders) { 
            if ((bidder.isVerifiedByReference() || bidder.getReputation() >= 2) && bidder.getMaxBid() > initialCommissionBid) {
                initialCommissionBid = Math.min(bidder.getMaxBid(), initialCommissionBid + incrementStep); 
            }
        }
        if (initialCommissionBid > currentBid) {
            currentBid = initialCommissionBid; 
            currentLeaderId = getHighestCommissionBidder().getId(); 
    }*/

    public void placeBid(
        int bidderId , //{Bidder:AuctionHouse,Bidder}
        int bidAmount //{Bidder:AuctionHouse,Bidder}
        ){ 
        
        Bidder bidder /* {AuctionHouse:AuctionHouse} */
            = bidders.stream() 
                .filter(b -> b.getId() == bidderId) //{AuctionHouse:AuctionHouse}
                .findFirst().orElse(null);
         if (bidder != null && bidAmount > (currentBid + incrementStep - 1) && bidAmount <= bidder.getMaxBid()) {
            // Check if the bid is a valid increment over the current bid
            if (bidAmount > currentBid + incrementStep - 1) {
                currentBid = bidAmount; //{AuctionHouse:AuctionHouse}
                currentLeaderId = bidderId; //{AuctionHouse:AuctionHouse}
                System.out.println("New Current Highest Bid is: " + bidAmount + " Kr"); //{AuctionHouse:AuctionHouse,Bidder}
            }
        } else if (bidder != null && bidder.getReputation() >= 3) {
            // High reputation bidders can ignore the max bid constraint
            if (bidAmount > currentBid + incrementStep - 1) {
                currentBid = bidAmount; //{AuctionHouse:AuctionHouse}
                currentLeaderId = bidderId; //{AuctionHouse:AuctionHouse}
                System.out.println("New Current Highest Bid is: " + bidAmount + " Kr"); //{AuctionHouse:AuctionHouse,Bidder}
            }
        }
    }


    /*private Bidder getHighestCommissionBidder() { 
        Bidder maxBidder = null;
        for (Bidder bidder : bidders) {
            if (bidder.isVerifiedByReference() || bidder.getReputation() >= 2) {
                if (maxBidder == null || bidder.getMaxBid() > maxBidder.getMaxBid()) {
                    maxBidder = bidder; 
                }
            }
        }
        return maxBidder;
    }*/

    public void announceWinner() {//{AuctionHouse:AuctionHouse,Bidder}
        System.out.println("Going once... Going twice... Sold to bidder #" 
        +this.currentLeaderId //{AuctionHouse:AuctionHouse,Bidder}
        + " for "
        +this.currentBid //{AuctionHouse:AuctionHouse,Bidder}
        + " Kr!"); 
    }
}
