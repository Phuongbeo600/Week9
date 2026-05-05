package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MathUtilsTest {

    @Test
    void testAdd() {
        MathUtils mathUtils = new MathUtils();

        // Kiểm tra hàm cộng
        assertEquals(5, mathUtils.add(2, 3), "2 + 3 phải bằng 5");
        assertEquals(-1, mathUtils.add(-4, 3), "-4 + 3 phải bằng -1");
    }

    @Test
    void testDivideNormal() {
        MathUtils mathUtils = new MathUtils();

        // Kiểm tra nhánh chia hợp lệ
        assertEquals(2, mathUtils.divide(6, 3), "6 chia 3 phải bằng 2");
        assertEquals(-3, mathUtils.divide(9, -3), "9 chia -3 phải bằng -3");
    }

    @Test
    void testDivideByZero() {
        MathUtils mathUtils = new MathUtils();

        // Kiểm tra nhánh ném ngoại lệ khi chia cho 0 (để ăn trọn coverage chỗ lệnh if)
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            mathUtils.divide(5, 0);
        });

        // (Tùy chọn) Kiểm tra luôn xem câu thông báo lỗi có đúng chuẩn không
        assertEquals("Cannot divide by zero", exception.getMessage());
    }
}