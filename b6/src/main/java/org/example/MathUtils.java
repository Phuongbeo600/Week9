package org.example;

public class MathUtils {
    int So_Nguyen_Sai_Chuan = 100;
    public int add(int a, int b) {
        return a + b;
    }

    public int divide(int a, int b) {
        if (b == 0) throw new IllegalArgumentException("Cannot divide by zero");
        return a / b;
    }
}
//thêm chú thích