package id.ac.ui.cs.advprogid.eshop.service;

import id.ac.ui.cs.advprogid.eshop.model.Product;
import id.ac.ui.cs.advprogid.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Test Product");
        product.setProductQuantity(10);

        // Kita mock repository untuk mengembalikan produk yang sama
        when(productRepository.create(product)).thenReturn(product);

        Product result = productService.create(product);

        verify(productRepository, times(1)).create(product);
        assertEquals(product, result);
    }

    @Test
    void testFindAll() {
        Product product1 = new Product();
        product1.setProductId("1");
        product1.setProductName("Product 1");
        product1.setProductQuantity(5);

        Product product2 = new Product();
        product2.setProductId("2");
        product2.setProductName("Product 2");
        product2.setProductQuantity(15);

        List<Product> products = Arrays.asList(product1, product2);
        Iterator<Product> iterator = products.iterator();

        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> result = productService.findAll();

        verify(productRepository, times(1)).findAll();
        assertEquals(2, result.size());
        assertEquals(product1, result.get(0));
        assertEquals(product2, result.get(1));
    }

    @Test
    void testFindById() {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Test Product");
        product.setProductQuantity(10);

        when(productRepository.findById("1")).thenReturn(product);

        Product result = productService.findById("1");

        verify(productRepository, times(1)).findById("1");
        assertEquals(product, result);
    }

    @Test
    void testUpdate() {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Updated Product");
        product.setProductQuantity(20);

        when(productRepository.update(product)).thenReturn(product);

        Product result = productService.update(product);

        verify(productRepository, times(1)).update(product);
        assertEquals(product, result);
    }

    @Test
    void testDelete() {
        when(productRepository.delete("1")).thenReturn(true);

        boolean result = productService.delete("1");

        verify(productRepository, times(1)).delete("1");
        assertTrue(result);
    }
}
