package dto;

public class OrderDTO {
    private int orderId;
    private String customerName;
    private String productName;
    private int productQty;
    private double totalBill;

    public OrderDTO() {
    }

    public OrderDTO(int orderId, String customerName, String productName, int productQty, double totalBill) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.productName = productName;
        this.productQty = productQty;
        this.totalBill = totalBill;
    }

    public OrderDTO(String customerName, int productQty) {
        this.customerName = customerName;
        this.productQty = productQty;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductQty() {
        return productQty;
    }

    public void setProductQty(int productQty) {
        this.productQty = productQty;
    }

    public double getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(double totalBill) {
        this.totalBill = totalBill;
    }

}
