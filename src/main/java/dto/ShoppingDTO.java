package dto;

public class ShoppingDTO {
    private int productId;
    private String productName;
    private int productQty;
    private double productPrice;

    public ShoppingDTO() {
    }

    public ShoppingDTO(String productName, int productQty, double productPrice) {
        this.productName = productName;
        this.productQty = productQty;
        this.productPrice = productPrice;
    }

    public int getProductId() {
        return productId;
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

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
}
