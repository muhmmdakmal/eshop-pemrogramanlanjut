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
class PaymentServiceImplTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    private Order dummyOrder;
    private Map<String, String> paymentData;
    private List<Product> dummyProducts;

    @BeforeEach
    void setUp() {
        dummyProducts = new ArrayList<>();
        Product product = new Product();
        product.setProductId("prod-001");
        product.setProductName("Dummy Product");
        product.setProductQuantity(1);
        dummyProducts.add(product);

        dummyOrder = new Order("order-001", dummyProducts, 1708560000L, "Test Author");

        paymentData = new HashMap<>();
        paymentData.put("transactionId", "txn-001");
    }

    @Test
    void testAddPayment() {
        when(paymentRepository.save(any(Payment.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Payment result = paymentService.addPayment(dummyOrder, "CreditCard", paymentData);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("CreditCard", result.getMethod());
        assertEquals("PENDING", result.getStatus());
        assertEquals(paymentData, result.getPaymentData());
        assertEquals(dummyOrder, result.getOrder());

        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testSetStatusSuccess() {
        Payment payment = Payment.builder()
                .id("payment-001")
                .method("CreditCard")
                .status("PENDING")
                .paymentData(paymentData)
                .order(dummyOrder)
                .build();
        dummyOrder.setStatus("WAITING_PAYMENT");

        when(paymentRepository.save(any(Payment.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Payment result = paymentService.setStatus(payment, "SUCCESS");

        assertEquals("SUCCESS", result.getStatus());
        assertEquals("SUCCESS", result.getOrder().getStatus());

        verify(paymentRepository, times(1)).save(payment);
    }

    @Test
    void testSetStatusRejected() {
        Payment payment = Payment.builder()
                .id("payment-002")
                .method("PayPal")
                .status("PENDING")
                .paymentData(paymentData)
                .order(dummyOrder)
                .build();
        dummyOrder.setStatus("WAITING_PAYMENT");

        when(paymentRepository.save(any(Payment.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Payment result = paymentService.setStatus(payment, "REJECTED");

        assertEquals("REJECTED", result.getStatus());
        assertEquals("FAILED", result.getOrder().getStatus());

        verify(paymentRepository, times(1)).save(payment);
    }

    @Test
    void testGetPayment() {
        Payment payment = Payment.builder()
                .id("payment-003")
                .method("DebitCard")
                .status("PENDING")
                .paymentData(paymentData)
                .order(dummyOrder)
                .build();

        when(paymentRepository.findById("payment-003")).thenReturn(payment);

        Payment result = paymentService.getPayment("payment-003");

        assertNotNull(result);
        assertEquals("payment-003", result.getId());
        verify(paymentRepository, times(1)).findById("payment-003");
    }

    @Test
    void testGetAllPayments() {
        Payment payment1 = Payment.builder()
                .id("payment-004")
                .method("CreditCard")
                .status("PENDING")
                .paymentData(paymentData)
                .order(dummyOrder)
                .build();
        Payment payment2 = Payment.builder()
                .id("payment-005")
                .method("PayPal")
                .status("PENDING")
                .paymentData(paymentData)
                .order(dummyOrder)
                .build();

        List<Payment> paymentList = Arrays.asList(payment1, payment2);
        when(paymentRepository.findAll()).thenReturn(paymentList);

        List<Payment> results = paymentService.getAllPayments();

        assertNotNull(results);
        assertEquals(2, results.size());
        verify(paymentRepository, times(1)).findAll();
    }
}
