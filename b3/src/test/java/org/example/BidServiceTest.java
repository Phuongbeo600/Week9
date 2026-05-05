package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BidServiceTest {
    @Test
    public void testValidBid_WhenNewBidIsHigher() {
        // 1. Arrange (Chuẩn bị): Khởi tạo class bên src/main để chuẩn bị test
        BidService bidService = new BidService();
        double currentPrice = 1000.0;
        double newBid = 1500.0; // Giá mới cao hơn giá cũ

        // 2. Act (Hành động): Gọi hàm thật bên src/main ra để chạy thử
        boolean result = bidService.isValidBid(currentPrice, newBid);

        // 3. Assert (Xác nhận): Kiểm tra xem kết quả có đúng là TRUE như kỳ vọng không
        assertTrue(result, "Giá mới cao hơn giá cũ thì phải trả về true");
    }

    @Test
    public void testValidBid_WhenNewBidIsLower() {
        // 1. Arrange
        BidService bidService = new BidService();
        double currentPrice = 1000.0;
        double newBid = 500.0; // Giá mới thấp hơn giá cũ

        // 2. Act
        boolean result = bidService.isValidBid(currentPrice, newBid);

        // 3. Assert: Kiểm tra xem kết quả có đúng là FALSE như kỳ vọng không
        assertFalse(result, "Giá mới thấp hơn giá cũ thì phải trả về false");
    }

}