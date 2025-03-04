package id.ac.ui.cs.advprogid.eshop.service;

import id.ac.ui.cs.advprogid.eshop.model.Payment;
import id.ac.ui.cs.advprogid.eshop.model.Order;
import id.ac.ui.cs.advprogid.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        Payment payment = Payment.builder()
                .id(UUID.randomUUID().toString())
                .method(method)
                .paymentData(paymentData)
                .status("PENDING")
                .order(order)
                .build();
        return paymentRepository.save(payment);
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        payment.setStatus(status);
        if ("SUCCESS".equals(status)) {
            payment.getOrder().setStatus("SUCCESS");
        } else if ("REJECTED".equals(status)) {
            payment.getOrder().setStatus("FAILED");
        }
        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}