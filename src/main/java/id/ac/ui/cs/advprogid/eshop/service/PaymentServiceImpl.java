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
        String status = "PENDING"; // Status default

        if ("Cash on Delivery".equals(method)) {
            // 1) Cek apakah ada voucher code
            String voucherCode = paymentData.get("voucherCode");
            if (voucherCode != null) {
                // Sub-feature voucher code
                if (isValidVoucherCode(voucherCode)) {
                    status = "SUCCESS";
                } else {
                    status = "REJECTED";
                }
            }
            else {
                boolean hasAddress = paymentData.containsKey("address");
                boolean hasFee = paymentData.containsKey("deliveryFee");

                if (hasAddress || hasFee) {
                    if (!hasAddress || !hasFee) {
                        // Salah satu kunci tidak ada
                        status = "REJECTED";
                    } else {
                        // Keduanya ada, cek nilainya
                        String address = paymentData.get("address");
                        String fee = paymentData.get("deliveryFee");
                        if (address == null || address.trim().isEmpty() ||
                                fee == null || fee.trim().isEmpty()) {
                            status = "REJECTED";
                        }
                    }
                }
            }
        }
        else if ("Bank Transfer".equals(method)) {
            // Sub-feature Bank Transfer:
            return null;
        }

        Payment payment = Payment.builder()
                .id(UUID.randomUUID().toString())
                .method(method)
                .paymentData(paymentData)
                .status(status)
                .order(order)
                .build();

        return paymentRepository.save(payment);
    }

    private boolean isValidVoucherCode(String voucherCode) {
        // Harus 16 karakter DAN diawali "ESHOP"
        if (voucherCode.length() != 16 || !voucherCode.startsWith("ESHOP")) {
            return false;
        }
        // Hitung semua digit di voucher code
        String digits = voucherCode.replaceAll("[^0-9]", "");
        return digits.length() == 8;
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

