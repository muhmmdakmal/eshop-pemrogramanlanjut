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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplBankTransferTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    private Order dummyOrder;

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setProductId("prod-001");
        product.setProductName("Dummy Product");
        product.setProductQuantity(1);
        products.add(product);

        dummyOrder = new Order("order-001", products, 1708560000L, "Test Author");
    }

    @Test
    void testAddPaymentBankTransferWithValidData() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "Bank ABC");
        paymentData.put("referenceCode", "REF123456");

        when(paymentRepository.save(any(Payment.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Payment payment = paymentService.addPayment(dummyOrder, "Bank Transfer", paymentData);

        assertEquals("PENDING", payment.getStatus());
        assertEquals("Bank Transfer", payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testAddPaymentBankTransferWithEmptyBankName() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "");
        paymentData.put("referenceCode", "REF123456");

        when(paymentRepository.save(any(Payment.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Payment payment = paymentService.addPayment(dummyOrder, "Bank Transfer", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testAddPaymentBankTransferWithNullReferenceCode() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "Bank XYZ");
        paymentData.put("referenceCode", null);

        when(paymentRepository.save(any(Payment.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Payment payment = paymentService.addPayment(dummyOrder, "Bank Transfer", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testAddPaymentBankTransferWithMissingReferenceCode() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "Bank 123");

        when(paymentRepository.save(any(Payment.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Payment payment = paymentService.addPayment(dummyOrder, "Bank Transfer", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }
}
