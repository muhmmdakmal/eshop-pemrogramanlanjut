package id.ac.ui.cs.advprogid.eshop.controller;

import id.ac.ui.cs.advprogid.eshop.model.Product;
import id.ac.ui.cs.advprogid.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/product")
public class ProductController extends AbstractCrudController<Product> {

    @Autowired
    private ProductService productService;

    @Override
    protected List<Product> getAll() {
        return productService.findAll();
    }

    @Override
    protected Product getById(String id) {
        return productService.findById(id);
    }

    @Override
    protected Product createEntity(Product entity) {
        if (entity.getProductId() == null) {
            entity.setProductId(UUID.randomUUID().toString());
        }
        return productService.create(entity);
    }

    @Override
    protected Product updateEntity(Product entity) {
        return productService.update(entity);
    }

    @Override
    protected void deleteEntity(String id) {
        productService.delete(id);
    }

    @Override
    protected String getListView() {
        return "ProductList";  // Pastikan nama view sesuai (case-sensitive)
    }

    @Override
    protected String getCreateView() {
        return "CreateProduct";
    }

    @Override
    protected String getEditView() {
        return "EditProduct";
    }

    @Override
    protected Product createNewInstance() {
        return new Product();
    }
}
