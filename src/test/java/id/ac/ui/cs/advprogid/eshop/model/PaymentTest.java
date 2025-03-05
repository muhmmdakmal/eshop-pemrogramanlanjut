package id.ac.ui.cs.advprogid.eshop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentTest {

    @Test
    void testPaymentBuilder() {
        String id = "123e4567-e89b-12d3-a456-426614174000";
        String method = "CreditCard";
        String status = "PENDING";
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("transactionId", "TXN001");

        List<Product> products = new ArrayList<>();
        Product dummyProduct = new Product();
        dummyProduct.setProductId("prod-001");
        dummyProduct.setProductName("Dummy Product");
        dummyProduct.setProductQuantity(1);
        products.add(dummyProduct);
        Order order = new Order("order-001", products, 1708560000L, "Test Author");

        Payment payment = Payment.builder()
                .id(id)
                .method(method)
                .status(status)
                .paymentData(paymentData)
                .order(order)
                .build();

        assertEquals(id, payment.getId());
        assertEquals(method, payment.getMethod());
        assertEquals(status, payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals(order, payment.getOrder());
    }

    @Test
    void testPaymentSetters() {
        List<Product> products = new ArrayList<>();
        Product dummyProduct = new Product();
        dummyProduct.setProductId("prod-002");
        dummyProduct.setProductName("Dummy Product 2");
        dummyProduct.setProductQuantity(2);
        products.add(dummyProduct);
        Order order = new Order("order-002", products, 1708560000L, "Another Author");

        Payment payment = Payment.builder().build();
        String id = "321e4567-e89b-12d3-a456-426614174000";
        String method = "PayPal";
        String status = "SUCCESS";
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("info", "Paid successfully");

        payment.setId(id);
        payment.setMethod(method);
        payment.setStatus(status);
        payment.setPaymentData(paymentData);
        payment.setOrder(order);

        assertEquals(id, payment.getId());
        assertEquals(method, payment.getMethod());
        assertEquals(status, payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals(order, payment.getOrder());
    }
}
