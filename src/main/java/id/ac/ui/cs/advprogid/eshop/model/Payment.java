package id.ac.ui.cs.advprogid.eshop.model;

import id.ac.ui.cs.advprogid.eshop.enums.PaymentStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
public class Payment {
    private String id;                   // UUID
    private String method;               // Nama sub-fiturnya
    private String status;               // Misalnya: PENDING, SUCCESS, REJECTED, dll.
    private Map<String, String> paymentData; // Data sub-fiturnya
    private Order order;                 // Order terkait

    // Custom setter untuk status dengan validasi menggunakan enum PaymentStatus
    public void setStatus(String status) {
        if (PaymentStatus.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Invalid Payment Status: " + status);
        }
    }
}
