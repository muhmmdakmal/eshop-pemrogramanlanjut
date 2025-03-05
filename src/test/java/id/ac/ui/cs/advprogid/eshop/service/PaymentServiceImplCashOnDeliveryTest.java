package id.ac.ui.cs.advprogid.eshop.service;

import id.ac.ui.cs.advprogid.eshop.model.Order;
import id.ac.ui.cs.advprogid.eshop.model.Payment;
import id.ac.ui.cs.advprogid.eshop.model.Product;
import id.ac.ui.cs.advprogid.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplCashOnDeliveryTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    private Order dummyOrder;
    private List<Product> dummyProducts;

    @BeforeEach
    void setUp() {
        dummyProducts = new ArrayList<>();
        Product product = new Product();
        product.setProductId("prod-001");
        product.setProductName("Dummy Product");
        product.setProductQuantity(1);
        dummyProducts.add(product);

        // Buat Order dummy dengan produk tidak kosong
        dummyOrder = new Order("order-001", dummyProducts, 1708560000L, "Test Author");
    }

    @Test
    void testAddPaymentCashOnDeliveryWithValidData() {
        // Given: paymentData dengan address dan deliveryFee valid
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "123 Main St");
        paymentData.put("deliveryFee", "5000");

        when(paymentRepository.save(any(Payment.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // When
        Payment payment = paymentService.addPayment(dummyOrder, "Cash on Delivery", paymentData);

        // Then: Karena data lengkap, status tetap default "PENDING"
        assertEquals("PENDING", payment.getStatus());
        assertEquals("Cash on Delivery", payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testAddPaymentCashOnDeliveryWithEmptyAddress() {
        // Given: paymentData dengan address kosong dan deliveryFee valid
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", ""); // address kosong
        paymentData.put("deliveryFee", "5000");

        when(paymentRepository.save(any(Payment.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // When
        Payment payment = paymentService.addPayment(dummyOrder, "Cash on Delivery", paymentData);

        // Then: Karena address kosong, status harus "REJECTED"
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testAddPaymentCashOnDeliveryWithNullDeliveryFee() {
        // Given: paymentData dengan address valid, namun deliveryFee null
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "456 Elm St");
        paymentData.put("deliveryFee", null); // deliveryFee null

        when(paymentRepository.save(any(Payment.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // When
        Payment payment = paymentService.addPayment(dummyOrder, "Cash on Delivery", paymentData);

        // Then: Karena deliveryFee null, status harus "REJECTED"
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testAddPaymentCashOnDeliveryWithMissingDeliveryFee() {
        // Given: paymentData dengan address valid, namun tidak ada key deliveryFee
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "789 Oak St");
        // deliveryFee tidak ditambahkan

        when(paymentRepository.save(any(Payment.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // When
        Payment payment = paymentService.addPayment(dummyOrder, "Cash on Delivery", paymentData);

        // Then: Karena tidak ada deliveryFee, status harus "REJECTED"
        assertEquals("REJECTED", payment.getStatus());
    }
}
