package ldl.inventories.service;

import ldl.inventories.model.Product;
import ldl.inventories.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing products.
 */
@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Retrieves a list of all products.
     *
     * @return List of all products
     */
    @Override
    public List<Product> listProducts() {
        // Retrieve and return all products from the repository
        return this.productRepository.findAll();
    }

    /**
     * Finds a product by its ID.
     *
     * @param productId The ID of the product to find
     * @return The found product, or null if not found
     */
    @Override
    public Product findProductById(Integer productId) {
        // Find a product by its ID from the repository
        return this.productRepository.findById(productId).orElse(null);
    }

    /**
     * Saves a product.
     *
     * @param product The product to save
     * @return The saved product
     */
    @Override
    public Product saveProduct(Product product) {
        // Save the product to the repository and return the saved product
        return this.productRepository.save(product);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param productId The ID of the product to delete
     */
    @Override
    public void deleteProductById(Integer productId) {
        // Delete the product by its ID from the repository
        this.productRepository.deleteById(productId);
    }
}
