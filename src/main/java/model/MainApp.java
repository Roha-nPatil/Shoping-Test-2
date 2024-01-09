package model;

import dao.DAOImplimentation;
import dto.ShoppingDTO;

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
        List<ShoppingDTO> productList = dao.displayProducts();
        for(ShoppingDTO s: productList)
        {
            System.out.println(s.getProductName()+"     "+s.getProductPrice());
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
    }

    private static void placeOrder() {
    }

    private static void displayAllOrders() {
    }
}
