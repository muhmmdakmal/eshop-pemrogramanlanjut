package id.ac.ui.cs.advprogid.eshop.repository;

import id.ac.ui.cs.advprogid.eshop.model.Product;
import java.util.Iterator;

public interface IProductRepository {
    Product create(Product product);
    Iterator<Product> findAll();
    Product findById(String productId);
    Product update(Product product);
    boolean delete(String productId);
}
