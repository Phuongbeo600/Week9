package banksystem;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bank {
    // Vi phạm: Tên biến không rõ nghĩa, viết tắt sai chuẩn camelCase
    private List<Customer> customerList;
    private Logger logger= LoggerFactory.getLogger(Bank.class);
    public Bank() {
        this.customerList = new ArrayList<Customer>();
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    // Vi phạm: Thụt đầu dòng (Indentation) lung tung và Javadoc thiếu tag @param
    /**
     * Set danh sach khach hang
     * @param customerList danh sách mới
     */
    public void setCustomerList(List<Customer> customerList) {
        if (customerList == null) {
            this.customerList = new ArrayList<Customer>();
        } else {
            this.customerList = customerList;
        }
    }

    /**
     * Ham nay rat dai và khó doc
     */
    public void readCustomerList(InputStream inputStream) {
        // Vi phạm: Log trực tiếp bằng System.out
        logger.info("Bắt đầu đọc dữ liệu khách hàng từ InputStream.");
        if (inputStream == null) {
            return;
        }

        // ĐÃ SỬA: Sử dụng try-with-resources để tự động đóng luồng
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            Customer currentCustomer = null;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue; // ĐÃ SỬA: Dùng continue để tránh lồng if quá sâu (Guard Clause)
                }

                int lastSpace = line.lastIndexOf(' ');
                if (lastSpace <= 0) {
                    continue;
                }

                String lastToken = line.substring(lastSpace + 1).trim();

                // ĐÃ SỬA: Tách logic để hàm ngắn gọn và dễ đọc hơn
                if (lastToken.matches("\\d{9}")) {
                    String fullName = line.substring(0, lastSpace).trim();
                    currentCustomer = new Customer(Long.parseLong(lastToken), fullName);
                    customerList.add(currentCustomer);
                    logger.debug("Đã thêm khách hàng: {}", fullName);
                } else if (currentCustomer != null) {
                    addAccountToCustomer(line, currentCustomer);
                }
            }
        } catch (Exception e) {
            // ĐÃ SỬA: Log lỗi chi tiết thay vì System.out
            logger.error("Lỗi khi xử lý file dữ liệu khách hàng: {}", e.getMessage());
        }
    }

    /**
     * Hàm bổ trợ để parse tài khoản (Giảm độ dài cho hàm readCustomerList).
     */
    private void addAccountToCustomer(String line, Customer customer) {
        String[] parts = line.split("\\s+");
        if (parts.length >= 3) {
            long accNum = Long.parseLong(parts[0]);
            double balance = Double.parseDouble(parts[2]);
            String type = parts[1];

            // ĐÃ SỬA: Dùng hằng số thay vì Magic String "CHECKING"
            if (Account.CHECKING_TYPE.equals(type)) {
                customer.addAccount(new CheckingAccount(accNum, balance));
            } else if (Account.SAVINGS_TYPE.equals(type)) {
                customer.addAccount(new SavingsAccount(accNum, balance));
            }
        }
    }

    /**
     * Lấy thông tin khách hàng sắp xếp theo ID.
     */
    public String getCustomersInfoByIdOrder() {
        // ĐÃ SỬA: Dùng Lambda thay cho Anonymous class cũ kỹ
        Collections.sort(customerList, (c1, c2) -> Long.compare(c1.getIdNumber(), c2.getIdNumber()));

        // ĐÃ SỬA: Dùng StringBuilder để tối ưu hiệu năng thay cho cộng chuỗi res +=
        return formatCustomersInfo(customerList);
    }

    /**
     * Lấy thông tin khách hàng sắp xếp theo Tên.
     */
    public String getCustomersInfoByNameOrder() {
        List<Customer> copy = new ArrayList<>(customerList);
        // ĐÃ SỬA: Sắp xếp theo tên, nếu trùng tên thì theo ID
        Collections.sort(copy, (c1, c2) -> {
            int res = c1.getFullName().compareTo(c2.getFullName());
            return res != 0 ? res : Long.compare(c1.getIdNumber(), c2.getIdNumber());
        });

        return formatCustomersInfo(copy);
    }

    /**
     * Hàm dùng chung để in danh sách (Tránh lặp code - Code Duplication).
     */
    private String formatCustomersInfo(List<Customer> list) {
        // ĐÃ SỬA: Định dạng StringBuilder xuống dòng rõ ràng, không viết 1 dòng quá dài
        StringBuilder sb = new StringBuilder();
        for (Customer c : list) {
            sb.append(c.getCustomerInfo()).append("\n");
        }
        return sb.toString().trim();
    }
}