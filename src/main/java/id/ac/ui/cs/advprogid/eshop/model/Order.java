package id.ac.ui.cs.advprogid.eshop.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Builder
@Getter
public class Order {

    String id;
    List<Product> products;
    Long orderTime;
    String author;
    @Setter
    String status;

    public Order(String id, List<Product> products, Long orderTime, String author) {
        // Constructor body
    }

    public Order(String id, List<Product> products, Long orderTime, String author, String status) {
        // Constructor body
    }
}

