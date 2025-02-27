package id.ac.ui.cs.advprogid.eshop.service;

import id.ac.ui.cs.advprogid.eshop.model.Product;
import id.ac.ui.cs.advprogid.eshop.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final IProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(Product product) {
        productRepository.create(product);
        return product;
    }

    @Override
    public List<Product> findAll() {
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> allProducts = new ArrayList<>();
        productIterator.forEachRemaining(allProducts::add);
        return allProducts;
    }

    @Override
    public Product findById(String productId) {
        return productRepository.findById(productId);
    }

    @Override
    public Product update(Product product) {
        productRepository.update(product);
        return product;
    }

    @Override
    public boolean delete(String productId) {
        return productRepository.delete(productId);
    }
}
