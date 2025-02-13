package id.ac.ui.cs.advprogid.eshop.repository;

import id.ac.ui.cs.advprogid.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreate() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }


    // === Edit (Update) Product ===

    @Test
    void testUpdateProduct_Positive() {
        // Arrange: Buat dan simpan sebuah produk
        Product product = new Product();
        product.setProductId("id1");
        product.setProductName("Original Name");
        product.setProductQuantity(100);
        productRepository.create(product);

        // Act: Update produk yang sudah ada dengan data baru
        Product updatedProduct = new Product();
        updatedProduct.setProductId("id1");
        updatedProduct.setProductName("Updated Name");
        updatedProduct.setProductQuantity(150);
        Product result = productRepository.update(updatedProduct);

        // Assert: Pastikan update berhasil dan data produk telah berubah
        assertNotNull(result, "Hasil update tidak boleh null");
        assertEquals("id1", result.getProductId(), "Product ID harus sama");
        assertEquals("Updated Name", result.getProductName(), "Nama produk harus terupdate");
        assertEquals(150, result.getProductQuantity(), "Kuantitas produk harus terupdate");
    }

    @Test
    void testUpdateProduct_Negative() {
        // Act: Coba update produk yang tidak ada di repository
        Product nonExistingProduct = new Product();
        nonExistingProduct.setProductId("non-existent");
        nonExistingProduct.setProductName("Does Not Exist");
        nonExistingProduct.setProductQuantity(0);
        Product result = productRepository.update(nonExistingProduct);

        // Assert: Karena produk tidak ada, seharusnya update mengembalikan null
        assertNull(result, "Update produk non-existing harus mengembalikan null");
    }

    // === Delete Product ===

    @Test
    void testDeleteProduct_Positive() {
        // Arrange: Buat dan simpan sebuah produk
        Product product = new Product();
        product.setProductId("id2");
        product.setProductName("Product To Delete");
        product.setProductQuantity(200);
        productRepository.create(product);

        // Act: Hapus produk yang sudah ada
        boolean deleted = productRepository.delete("id2");

        // Assert: Penghapusan harus berhasil, dan produk tidak ditemukan lagi
        assertTrue(deleted, "Penghapusan produk harus berhasil");
        assertNull(productRepository.findById("id2"), "Produk yang dihapus tidak boleh ditemukan");
    }

    @Test
    void testDeleteProduct_Negative() {
        // Act: Coba hapus produk dengan ID yang tidak ada
        boolean deleted = productRepository.delete("non-existent");

        // Assert: Karena produk tidak ada, penghapusan harus gagal (mengembalikan false)
        assertFalse(deleted, "Penghapusan produk non-existing harus gagal");
    }
}
