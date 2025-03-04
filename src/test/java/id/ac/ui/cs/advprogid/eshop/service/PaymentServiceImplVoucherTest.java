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
class PaymentServiceImplVoucherTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    private Order dummyOrder;

    @BeforeEach
    void setUp() {
        // Siapkan Order dummy dengan produk tidak kosong
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setProductId("prod-001");
        product.setProductName("Dummy Product");
        product.setProductQuantity(1);
        products.add(product);
        dummyOrder = new Order("order-001", products, 1708560000L, "Test Author");
    }

    @Test
    void testAddPaymentWithValidVoucherCode() {
        // Given: voucher code valid (16 karakter, mulai dengan "ESHOP", dan memiliki tepat 8 digit)
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        // Stub repository.save agar mengembalikan argumen yang disimpan
        when(paymentRepository.save(any(Payment.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When: Menambahkan payment dengan method "Cash on Delivery"
        Payment payment = paymentService.addPayment(dummyOrder, "Cash on Delivery", paymentData);

        // Then: Status harus SUCCESS, karena voucher code valid
        assertEquals("SUCCESS", payment.getStatus());
        assertEquals("Cash on Delivery", payment.getMethod());
    }

    @Test
    void testAddPaymentWithInvalidVoucherCode() {
        // Given: voucher code invalid (misal, kurang panjang, tidak diawali dengan "ESHOP", atau tidak memiliki 8 digit)
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "INVALIDCODE123"); // misalnya tidak valid

        when(paymentRepository.save(any(Payment.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When: Menambahkan payment dengan method "Cash on Delivery"
        Payment payment = paymentService.addPayment(dummyOrder, "Cash on Delivery", paymentData);

        // Then: Status harus REJECTED karena voucher code invalid
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testAddPaymentWithoutVoucherCode() {
        // Given: Tidak ada voucher code dalam paymentData
        Map<String, String> paymentData = new HashMap<>();

        when(paymentRepository.save(any(Payment.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When: Menambahkan payment dengan method "Cash on Delivery"
        Payment payment = paymentService.addPayment(dummyOrder, "Cash on Delivery", paymentData);

        // Then: Status default tetap PENDING jika voucher code tidak disediakan
        assertEquals("PENDING", payment.getStatus());
    }
}
