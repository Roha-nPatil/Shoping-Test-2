package model;

import dao.DAOImplimentation;
import dto.OrderDTO;
import dto.ProductDTO;

import java.util.List;
import java.util.Scanner;
public class MainApp {
    static DAOImplimentation dao = new DAOImplimentation();
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        boolean status = true;
        while(status){
            System.out.println("*** WELCOME TO SHOPPING APPLICATION ***");
            System.out.println("---------------------------------------");
            System.out.println("1. Display All Products");
            System.out.println("2. Remove Products");
            System.out.println("3. Update Products");
            System.out.println("4. Place Order");
            System.out.println("5. Display All Orders");
            System.out.println("6. Exit");
            System.out.println("---------------------------------------");

            System.out.print("Select : ");
            int ch = sc.nextInt();

            switch (ch){
                case 1:
                    displayAllProducts();
                    break;
                case 2:
                    removeProducts();
                    break;
                case 3:
                    updateProducts();
                    break;
                case 4:
                    placeOrder();
                    break;
                case 5:
                    displayAllOrders();
                    break;
                case 6:
                    status = false;
                default:
                    System.err.println("Invalid Input");
            }
        }

    }

    private static void displayAllProducts() {
        List<ProductDTO> productList = dao.displayProducts();
        for(ProductDTO s: productList)
        {
            System.out.println(s.getProductId()+"\t\t"+s.getProductName()+"\t\t"+s.getProductPrice());
        }
    }

    private static void removeProducts() {
        System.out.println("Enter Product Id");
        int productId = sc.nextInt();

        int count = dao.removeProducts(productId);
        if(count>0){
            System.out.println("Product Removed Successfully !!");
        }else {
            System.err.println("Product Is Not Removed !!");
        }

    }

    private static void updateProducts() {
        System.out.println("Enter Product Id");
        int productId = sc.nextInt();
        System.out.println("Enter Updated Product Name");
        String productName = sc.next();
        System.out.println("Enter Updated Product Quantity");
        int productQty = sc.nextInt();
        System.out.println("Enter Updated Product Price");
        double productPrice = sc.nextDouble();

        ProductDTO p = new ProductDTO(productId,productName, productQty, productPrice);
        int count = dao.updateProduct(p);
        if(count>0){
            System.out.println(count + " Product Updated");
        }else {
            System.out.println(count + " Product Updated");
        }
    }

    private static void placeOrder() {
        System.out.println("Enter Customer Name");
        String customerName = sc.next();
        System.out.println("Enter Product Id");
        int productId = sc.nextInt();
        System.out.println("Enter Quantity");
        int productQty = sc.nextInt();

        OrderDTO o = new OrderDTO(customerName,productQty);
        ProductDTO p = new ProductDTO(productId);
        boolean count = dao.placeOrder(o,p);
        if(count) {
            System.out.println(count+" Order Placed");
        }
        else {
            System.out.println(count+" Out Of Stock");
        }
    }

    private static void displayAllOrders() {
        List<OrderDTO> orderList = dao.displayAllOrders();
        System.out.println("OrderID \t\t Customer Name \t\t ProductName \t\t Order Quantity \t\t TotalBill");
        for (OrderDTO o: orderList){
            System.out.println(o.getOrderId() + "\t\t" + o.getCustomerName() + "\t\t" + o.getProductName()+ "\t\t" + o.getProductQty() +"\t\t" + o.getTotalBill());
        }
    }
}
