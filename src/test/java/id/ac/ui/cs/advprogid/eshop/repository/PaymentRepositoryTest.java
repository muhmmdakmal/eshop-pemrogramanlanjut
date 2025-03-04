package id.ac.ui.cs.advprogid.eshop.repository;

import id.ac.ui.cs.advprogid.eshop.model.Order;
import id.ac.ui.cs.advprogid.eshop.model.Payment;
import id.ac.ui.cs.advprogid.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentRepositoryTest {

    private PaymentRepository paymentRepository;
    private Payment payment1;
    private Payment payment2;
    private Order dummyOrder;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();

        // Siapkan list produk dummy untuk Order
        List<Product> products = new ArrayList<>();
        Product dummyProduct = new Product();
        dummyProduct.setProductId("dummy-001");
        dummyProduct.setProductName("Dummy Product");
        dummyProduct.setProductQuantity(1);
        products.add(dummyProduct);

        // Buat Order dummy dengan produk tidak kosong
        dummyOrder = new Order("order-001", products, 1708560000L, "Test Author");

        // Buat dua Payment dummy
        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("transactionId", "txn-001");

        payment1 = Payment.builder()
                .id("payment-001")
                .method("CreditCard")
                .status("PENDING")
                .paymentData(paymentData1)
                .order(dummyOrder)
                .build();

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("transactionId", "txn-002");

        payment2 = Payment.builder()
                .id("payment-002")
                .method("PayPal")
                .status("PENDING")
                .paymentData(paymentData2)
                .order(dummyOrder)
                .build();
    }

    @Test
    void testSaveNewPayment() {
        Payment saved = paymentRepository.save(payment1);
        assertNotNull(saved);
        assertEquals("payment-001", saved.getId());

        Payment found = paymentRepository.findById("payment-001");
        assertNotNull(found);
        assertEquals("CreditCard", found.getMethod());
    }

    @Test
    void testSaveUpdatePayment() {
        // Simpan payment1
        paymentRepository.save(payment1);
        // Buat Payment baru dengan ID yang sama, namun method dan paymentData berbeda
        Map<String, String> updatedData = new HashMap<>();
        updatedData.put("transactionId", "txn-001-updated");

        Payment updatedPayment = Payment.builder()
                .id("payment-001")
                .method("DebitCard")
                .status("PENDING")
                .paymentData(updatedData)
                .order(dummyOrder)
                .build();

        Payment result = paymentRepository.save(updatedPayment);
        assertEquals("payment-001", result.getId());
        assertEquals("DebitCard", result.getMethod());

        Payment found = paymentRepository.findById("payment-001");
        assertEquals("DebitCard", found.getMethod());
        assertEquals(updatedData, found.getPaymentData());
    }

    @Test
    void testFindByIdNotFound() {
        Payment found = paymentRepository.findById("non-existent");
        assertNull(found);
    }

    @Test
    void testFindAllPayments() {
        paymentRepository.save(payment1);
        paymentRepository.save(payment2);
        List<Payment> allPayments = paymentRepository.findAll();
        assertEquals(2, allPayments.size());
    }
}
