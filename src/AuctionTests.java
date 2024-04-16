import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AuctionTests {
    private Auction auction;

    @BeforeEach
    void setUp() {
        auction = new Auction(500);
        auction.addBidder(new Bidder(1, 500));
        auction.addBidder(new Bidder(2, 700));
    }

    @Test
    void testInitialBid() {
        assertEquals(550, auction.getCurrentBid(), "Initial bid should increase based on the highest commission bid");
    }

    @Test
    void testLiveBid() {
        auction.placeLiveBid(3, 600);
        assertEquals(600, auction.getCurrentBid(), "Bid should update to 600 after live bid");
    }

    @Test
    void testAuctionWinner() {
        auction.placeLiveBid(3, 600);
        auction.placeLiveBid(2, 650);
        auction.placeLiveBid(3, 700);
        auction.placeLiveBid(2, 700);
        auction.placeLiveBid(3, 750);
        assertEquals(750, auction.getCurrentBid(), "Final bid should be 750");
        assertEquals(3, auction.getCurrentLeaderId(), "Bidder #3 should be the winner");
    }
}
