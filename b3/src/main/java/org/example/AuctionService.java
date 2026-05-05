package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.slf4j.LoggerFactory.getLogger;


public class AuctionService {
    private static final Logger logger= getLogger(AuctionService.class);
    public void placeBid(String user, double amount) {
        logger.info("Người dùng {} đang thử đặt mức giá: {}", user, amount);
        if (amount <= 0) {
            logger.error("Lỗi! Mức giá đặt không hợp lệ."); // Báo lỗi nghiêm trọng
            return;
        }
        logger.debug("Logic cập nhật cơ sở dữ liệu sẽ chạy ở đây..."); // Log dùng khi debug code
    }
}
