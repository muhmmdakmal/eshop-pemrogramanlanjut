package id.ac.ui.cs.advprogid.eshop.model;

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
}
