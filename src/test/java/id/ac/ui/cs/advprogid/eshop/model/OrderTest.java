package id.ac.ui.cs.advprogid.eshop.model;

import id.ac.ui.cs.advprogid.eshop.model.Order;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private List<Product> products;

    @BeforeEach
    void setUp() {
        this.products = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);

        Product product2 = new Product();
        product2.setProductId("a26c2528-63f3-4644-83c7-73bfda862105");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(1);

        this.products.add(product1);
        this.products.add(product2);
    }

    @Test
    void testCreateOrderEmptyProduct() {
        this.products.clear();

        assertThrows(IllegalArgumentException.class, () -> {
            Order order = new Order(
                    "13652556-012a-4c07-b546-5eb139d679b",
                    this.products,
                    1708560000L,
                    "Safira Sudrajat"
            );
        });
    }

    @Test
    void testCreateOrderDefaultStatus() {
        Order order = new Order(
                "13652556-012a-4c07-b546-5eb139d679b",
                this.products,
                1708560000L,
                "Safira Sudrajat"
        );

        assertSame(this.products, order.getProducts());
        assertEquals(2, order.getProducts().size());
        assertEquals("Sampo Cap Bambang", order.getProducts().get(0).getProductName());
        assertEquals("Sampo Cap Usep", order.getProducts().get(1).getProductName());

        assertEquals("13652556-012a-4c07-b546-5eb139d679b", order.getId());
        assertEquals(1708560000L, order.getOrderTime());
        assertEquals("Safira Sudrajat", order.getAuthor());
        assertEquals("WAITING_PAYMENT", order.getStatus());
    }

    @Test
    void testCreateOrderSuccessStatus() {
        Order order = new Order(
                "13652556-012a-4c07-b546-5eb139d679b",
                this.products,
                1708560000L,
                "Safira Sudrajat",
                "SUCCESS"
        );

        assertEquals("SUCCESS", order.getStatus());
    }

    @Test
    void testCreateOrderInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            Order order = new Order(
                    "13652556-012a-4c07-b546-5eb139d679b",
                    this.products,
                    1708560000L,
                    "Safira Sudrajat",
                    "NEW"
            );
        });
    }

    @Test
    void testSetStatusToCancelled() {
        Order order = new Order(
                "13652556-012a-4c07-b546-5eb139d679b",
                this.products,
                1708560000L,
                "Safira Sudrajat"
        );

        order.setStatus("CANCELLED");
        assertEquals("CANCELLED", order.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus() {
        Order order = new Order(
                "13652556-012a-4c07-b546-5eb139d679b",
                this.products,
                1708560000L,
                "Safira Sudrajat"
        );

        assertThrows(IllegalArgumentException.class, () -> {
            order.setStatus("NEW");
        });
    }

}
