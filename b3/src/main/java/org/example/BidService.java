package org.example;

public class BidService {
    public boolean isValidBid(double currentPrice, double newBid) {
        return newBid > currentPrice;
    }
}
