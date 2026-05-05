package banksystem;

/**
 * Tai khoan tiet kiem - Lop nay thuc thi cac quy dinh ve rut tien và nap tien.
 */
public class SavingsAccount extends Account {
    // Sử dụng hằng số để tránh Magic Numbers
    private static final double MAX_WITHDRAW_LIMIT = 1000.0;
    private static final double MIN_BALANCE_REQUIRED = 5000.0;

    // Giả định các loại giao dịch được định nghĩa tập trung
    private static final int TYPE_DEPOSIT_SAVINGS = 3;
    private static final int TYPE_WITHDRAW_SAVINGS = 4;

    public SavingsAccount(long n, double b) {
        super(n, b);
    }

    @Override
    public void deposit(double amount) {
        double initialBalance = getBalance();
        try {
            doDepositing(amount);
            double finalBalance = getBalance();

            // Tạo giao dịch với hằng số thay vì số 3
            Transaction transaction = new Transaction(
                    TYPE_DEPOSIT_SAVINGS,
                    amount,
                    initialBalance,
                    finalBalance
            );

            addTransaction(transaction);
            System.out.printf("Nap tien vao tai khoan %d thanh cong: +%.2f%n",
                    getAccountNumber(), amount);

        } catch (Exception e) {
            // Trong thực tế nên catch các Exception cụ thể hơn
            System.err.println("Loi nap tien: " + e.getMessage());
        }
    }

    @Override
    public void withdraw(double amount) {
        double initialBalance = getBalance();

        try {
            // Kiểm tra các điều kiện trước khi thực hiện giao dịch
            validateWithdrawal(amount, initialBalance);

            doWithdrawing(amount);
            double finalBalance = getBalance();

            Transaction transaction = new Transaction(
                    TYPE_WITHDRAW_SAVINGS,
                    amount,
                    initialBalance,
                    finalBalance
            );
            addTransaction(transaction);

            System.out.printf("[SAVINGS] Rut %.2f thanh cong. So du con: %.2f%n",
                    amount, finalBalance);

        } catch (InvalidFundingAmountException | InsufficientFundsException e) {
            // Catch cụ thể các lỗi nghiệp vụ thay vì Exception chung chung
            System.err.println("Giao dich that bai: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Loi he thong khong xac dinh!");
            e.printStackTrace();
        }
    }

    /**
     * Tách riêng logic kiểm tra điều kiện rút tiền.
     */
    private void validateWithdrawal(double amount, double currentBalance)
            throws InvalidFundingAmountException, InsufficientFundsException {

        if (amount > MAX_WITHDRAW_LIMIT) {
            throw new InvalidFundingAmountException(amount);
        }

        if (currentBalance - amount < MIN_BALANCE_REQUIRED) {
            throw new InsufficientFundsException(amount);
        }
    }
}