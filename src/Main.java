
public class Main {
    public static void main(String[] args) {
        Auction auction = new Auction(500);
        auction.addBidder(new Bidder(1, 500));  // commission
        auction.addBidder(new Bidder(2, 750));  // commission
        auction.addBidder(new Bidder(3, 0));    // live

        // Simulate live bidding
        //auction.placeLiveBid(3, 600);
        //auction.placeLiveBid(3, 700);
        //auction.placeLiveBid(3, 750);

        auction.simulateAuction();

        auction.announceWinner();
    }
}