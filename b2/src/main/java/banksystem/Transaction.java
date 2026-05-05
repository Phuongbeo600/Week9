package banksystem;

import java.util.Locale;

/**
 * Đại diện cho một giao dịch tài chính trong hệ thống ngân hàng.
 * Lưu trữ thông tin về loại giao dịch, số tiền và biến động số dư.
 */
public class Transaction {
    // Các hằng số loại giao dịch
    public static final int TYPE_DEPOSIT_CHECKING = 1;
    public static final int TYPE_WITHDRAW_CHECKING = 2;
    public static final int TYPE_DEPOSIT_SAVINGS = 3;
    public static final int TYPE_WITHDRAW_SAVINGS = 4;

    private int type;
    private double amount;
    private double initialBalance;
    private double finalBalance;

    /**
     * Khởi tạo một đối tượng giao dịch mới.
     */
    public Transaction(int type, double amount, double initialBalance, double finalBalance) {
        this.type = type;
        this.amount = amount;
        this.initialBalance = initialBalance;
        this.finalBalance = finalBalance;
    }

    // --- Getters and Setters ---

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(double initialBalance) {
        this.initialBalance = initialBalance;
    }

    public double getFinalBalance() {
        return finalBalance;
    }

    public void setFinalBalance(double finalBalance) {
        this.finalBalance = finalBalance;
    }

    /**
     * Chuyển đổi mã loại giao dịch sang chuỗi mô tả tiếng Việt tương ứng.
     *
     * @param transactionType Mã loại giao dịch.
     * @return Chuỗi mô tả loại giao dịch.
     */
    public static String getTypeString(int transactionType) {
        switch (transactionType) {
            case TYPE_DEPOSIT_CHECKING:
                return "Nạp tiền vãng lai";
            case TYPE_WITHDRAW_CHECKING:
                return "Rút tiền vãng lai";
            case TYPE_DEPOSIT_SAVINGS:
                return "Nạp tiền tiết kiệm";
            case TYPE_WITHDRAW_SAVINGS:
                return "Rút tiền tiết kiệm";
            default:
                return "Không rõ";
        }
    }

    /**
     * Trả về bản tóm tắt chi tiết của giao dịch dưới dạng chuỗi định dạng.
     *
     * @return Chuỗi chứa thông tin tóm tắt giao dịch.
     */
    public String getTransactionSummary() {
        // Sử dụng phương thức phụ trợ để định dạng tiền tệ cho sạch code
        String strInitial = formatCurrency(initialBalance);
        String strAmount = formatCurrency(amount);
        String strFinal = formatCurrency(finalBalance);
        String typeDesc = getTypeString(this.type);

        // Chia nhỏ việc nối chuỗi để không vi phạm quy định về độ dài dòng (Line Length)
        return String.format(
                "- Kiểu giao dịch: %s. Số dư ban đầu: %s. Số tiền: %s. Số dư cuối: %s.",
                typeDesc,
                strInitial,
                strAmount,
                strFinal
        );
    }

    /**
     * Hỗ trợ định dạng số thực thành chuỗi tiền tệ theo chuẩn US.
     */
    private String formatCurrency(double amount) {
        return String.format(Locale.US, "$%.2f", amount);
    }
}