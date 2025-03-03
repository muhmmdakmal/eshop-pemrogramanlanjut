package id.ac.ui.cs.advprogid.eshop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter @Setter
public class Order {

    private String id;
    private List<Product> products;
    private long orderTime;
    private String author;
    private String status;

    // Daftar status yang diperbolehkan
    private static final List<String> ALLOWED_STATUSES = Arrays.asList(
            "WAITING_PAYMENT",
            "FAILED",
            "CANCELLED",
            "SUCCESS"
    );

    /**
     * Constructor utama dengan parameter status.
     * Jika status null atau invalid, akan diset ke "WAITING_PAYMENT".
     * Jika products kosong, lempar IllegalArgumentException.
     */
    public Order(String id, List<Product> products, long orderTime, String author, String status) {
        if (products == null || products.isEmpty()) {
            throw new IllegalArgumentException("Products must not be empty.");
        }
        if (author == null) {
            throw new IllegalArgumentException("Author must not be null.");
        }

        this.id = id;
        this.products = products;
        this.orderTime = orderTime;
        this.author = author;

        // Tetapkan status, default ke WAITING_PAYMENT jika null atau invalid
        if (status == null || !ALLOWED_STATUSES.contains(status)) {
            this.status = "WAITING_PAYMENT";
        } else {
            this.status = status;
        }
    }

    /**
     * Constructor overload tanpa parameter status.
     * Secara default status akan "WAITING_PAYMENT".
     */
    public Order(String id, List<Product> products, long orderTime, String author) {
        this(id, products, orderTime, author, null);
    }
}
