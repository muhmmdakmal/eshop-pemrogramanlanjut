package id.ac.ui.cs.advprogid.eshop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PaymentTest {
    @Test
    void testPaymentBuilder() {
        // Given
        String id = "123e4567-e89b-12d3-a456-426614174000";
        String method = "CreditCard";
        String status = "PENDING";
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("transactionId", "TXN001");

        // Misalnya, buat Order sederhana dengan list produk kosong (sesuaikan dengan implementasi Order Anda)
        Order order = new Order("order-001", new ArrayList<>(), 1708560000L, "Test Author");

        // When
        Payment payment = Payment.builder()
                .id(id)
                .method(method)
                .status(status)
                .paymentData(paymentData)
                .order(order)
                .build();

        // Then
        assertEquals(id, payment.getId());
        assertEquals(method, payment.getMethod());
        assertEquals(status, payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals(order, payment.getOrder());
    }

    @Test
    void testPaymentSetters() {
        // Given
        Payment payment = Payment.builder().build();
        String id = "321e4567-e89b-12d3-a456-426614174000";
        String method = "PayPal";
        String status = "SUCCESS";
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("info", "Paid successfully");

        Order order = new Order("order-002", new ArrayList<>(), 1708560000L, "Another Author");

        // When
        payment.setId(id);
        payment.setMethod(method);
        payment.setStatus(status);
        payment.setPaymentData(paymentData);
        payment.setOrder(order);

        // Then
        assertEquals(id, payment.getId());
        assertEquals(method, payment.getMethod());
        assertEquals(status, payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals(order, payment.getOrder());
    }
}
