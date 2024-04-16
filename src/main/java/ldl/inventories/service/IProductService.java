package ldl.inventories.service;

import ldl.inventories.model.Product;

import java.util.List;

/**
 * Interface for ProductService.
 * Defines operations to be performed on products.
 */
public interface IProductService {

    /**
     * Retrieves a list of all products.
     *
     * @return List of all products
     */
    List<Product> listProducts();

    /**
     * Finds a product by its ID.
     *
     * @param productId The ID of the product to find
     * @return The found product, or null if not found
     */
    Product findProductById(Integer productId);

    /**
     * Saves a product.
     *
     * @param product The product to save
     */
    Product saveProduct(Product product);

    /**
     * Deletes a product by its ID.
     *
     * @param productId The ID of the product to delete
     */
    void deleteProductById(Integer productId);
}
