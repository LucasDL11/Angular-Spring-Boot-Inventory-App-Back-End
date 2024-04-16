package ldl.inventories.controller;

import ldl.inventories.exception.ResourceNotFoundException;
import ldl.inventories.model.Product;
import ldl.inventories.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for managing product-related HTTP requests.
 */
@RestController
// Base URL for this controller
@RequestMapping("inventory-app")
@CrossOrigin(value = "http://localhost:4200")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    /**
     * Retrieves a list of all products.
     *
     * @return List of all products
     */
    @GetMapping("/products")
    public List<Product> getProducts() {
        // Retrieve list of products from the service
        List<Product> products = this.productService.listProducts();

        // Log information about the products retrieved
        logger.info("Products listed");
        products.forEach((product -> logger.info(product.toString())));

        // Return the list of products
        return products;
    }

    /**
     * Adds a new product.
     *
     * @param product The product to add
     * @return The added product
     */
    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product) {
        logger.info("Product to add: " + product);
        return this.productService.saveProduct(product);
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id The ID of the product to retrieve
     * @return ResponseEntity containing the product if found, or a ResourceNotFoundException if not found
     */
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = this.productService.findProductById(id);
        if (product != null) {
            throw new ResourceNotFoundException("Product not found");
        } else {
            return ResponseEntity.ok(product);
        }
    }
}