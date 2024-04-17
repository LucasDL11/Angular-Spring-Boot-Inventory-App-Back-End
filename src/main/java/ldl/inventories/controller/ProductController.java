package ldl.inventories.controller;

import ldl.inventories.exception.ResourceNotFoundException;
import ldl.inventories.model.Product;
import ldl.inventories.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller class for managing product-related HTTP requests.
 */
@RestController
// Base URL for this controller
@RequestMapping("inventory-app")
// Allowing cross-origin requests from http://localhost:4200
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
            return ResponseEntity.ok(product);
        } else {
            throw new ResourceNotFoundException("Product not found");
        }
    }

    /**
     * Updates a product by its ID.
     *
     * @param id             The ID of the product to update
     * @param productReceived The updated product information
     * @return ResponseEntity containing the updated product if found, or a ResourceNotFoundException if not found
     */
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable int id,
            @RequestBody Product productReceived) {
        Product product = this.productService.findProductById(id);
        if (product == null) {
            throw new ResourceNotFoundException("ProductID not found: " + id);
        }
        // Update the product information
        product.setDescription(productReceived.getDescription());
        product.setPrice(productReceived.getPrice());
        product.setQuantity(productReceived.getQuantity());
        // Save the updated product
        this.productService.saveProduct(product);
        return ResponseEntity.ok(product);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id The ID of the product to delete
     * @return ResponseEntity containing a map indicating the success of the deletion operation
     */
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable int id) {
        Product product = productService.findProductById(id);
        if (product == null) {
            throw new ResourceNotFoundException("ProductID not found: " + id);
        }
        // Delete the product
        this.productService.deleteProductById((product.getProductId()));
        // Prepare response indicating successful deletion
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
