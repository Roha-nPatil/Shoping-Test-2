package dao;

import dto.ShoppingDTO;

import java.util.List;

public interface ShoppingDAO {
    List<ShoppingDTO> displayProducts();

    int removeProducts(int productId);

}
