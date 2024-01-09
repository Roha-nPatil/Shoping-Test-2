package dao;

import dto.OrderDTO;
import dto.ProductDTO;

import java.util.List;

public interface ShoppingDAO {
    List<ProductDTO> displayProducts();

    int removeProducts(int productId);

    int updateProduct(ProductDTO p);

    boolean placeOrder(OrderDTO o, ProductDTO p);

    List<OrderDTO> displayAllOrders();
}
