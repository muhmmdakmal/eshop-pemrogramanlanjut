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
    private String id;
    private String method;
    private String status;
    private Map<String, String> paymentData;
    private Order order;

    public void setStatus(String status) {
        if (PaymentStatus.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Invalid Payment Status: " + status);
        }
    }
}
