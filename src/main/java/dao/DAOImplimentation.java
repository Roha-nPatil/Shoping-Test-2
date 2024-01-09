package dao;

import dto.OrderDTO;
import dto.ProductDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOImplimentation implements ShoppingDAO{

    private static Connection conn;
    static {
        String url = "jdbc:mysql://localhost:3306/shoppingdb";
        String user = "root";
        String password = "sql123";
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.err.println("Connection Failed");
        }
    }


    @Override
    public List<ProductDTO> displayProducts() {
        String displayQuery = "SELECT * FROM product_info";
        List<ProductDTO> productList = new ArrayList<>();
        try {
            Statement stmt =conn.createStatement();
            ResultSet rs = stmt.executeQuery(displayQuery);

            while (rs.next()){
                int productId =rs.getInt(1);
                String productName = rs.getString(2);
                int productQty = rs.getInt(3);
                double productPrice = rs.getDouble(4);

                ProductDTO s =new ProductDTO(productId,productName,productQty,productPrice);
                productList.add(s);
            }
        } catch (SQLException e) {
            System.err.println("Product Not Displayed !!");
        }


        return productList;
    }

    @Override
    public int removeProducts(int productId) {
        PreparedStatement pstmt = null;
        String removeQuery = "delete from product_info where product_id = ?";
        int count = 0;

        try {
            pstmt = conn.prepareStatement(removeQuery);
            pstmt.setInt(1,productId);
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }

        return count;
    }

    @Override
    public int updateProduct(ProductDTO p) {
        PreparedStatement pstmt = null;
        int count =0;
        String updateQuery = "UPDATE product_info SET product_name=?, product_qty=?, product_price=? WHERE product_id=?";
        try {
            pstmt = conn.prepareStatement(updateQuery);
            pstmt.setString(1,p.getProductName());
            pstmt.setInt(3,p.getProductQty());
            pstmt.setDouble(2,p.getProductPrice());
            pstmt.setInt(4,p.getProductId());
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return count;
    }

    @Override
    public boolean placeOrder(OrderDTO o , ProductDTO p) {
        boolean count =false;
        try {
            CallableStatement cstmt = conn.prepareCall("{CALL placeOrder(?,?,?,?)}");
            cstmt.setString(1,o.getCustomerName());
            cstmt.setInt(2, p.getProductId());
            cstmt.setInt(3, o.getProductQty());
            cstmt.execute();
            count=cstmt.getBoolean(4);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return count;
    }

    @Override
    public List<OrderDTO> displayAllOrders() {
        String query="SELECT o.order_id, o.customer_name, p.product_name, o.product_qty, o.product_qty * p.product_price AS Total_Price\n" +
                "FROM order_info o INNER JOIN product_info p ON o.product_id = p.product_id ORDER BY o.order_id asc;";
        List<OrderDTO> orderList = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                int orderId = rs.getInt(1);
                String customerName = rs.getString(2);
                String productName = rs.getString(3);
                int productQty = rs.getInt(4);
                double totalBill = rs.getDouble(5);
                OrderDTO order = new OrderDTO(orderId,customerName,productName,productQty,totalBill);
                orderList.add(order);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return orderList;
    }
}
