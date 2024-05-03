
public class Main {
    public static void main(String[] args) {
        Auction auction = new Auction(500);
        auction.addBidder(new Bidder(1, "Nick", 1500, 2,true)); // Commission
        auction.addBidder(new Bidder(2,"Mozim", 1200, 3,true)); // Commission
        auction.addBidder(new Bidder(3,"Aarbhi", 1000,1, false)); // Live, new
        auction.addBidder(new Bidder(4,"Some guy", 0,4, false)); // Trusted  // maxBid is zero, because it's not considerable

        auction.simulateAuction();

        auction.announceWinner();
    }
}