package id.ac.ui.cs.advprogid.eshop.controller;

import id.ac.ui.cs.advprogid.eshop.model.Product;
import id.ac.ui.cs.advprogid.eshop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Karena di controller kita menggunakan service, maka kita perlu me-mock service tersebut
    @MockBean
    private ProductService productService;

    @Test
    void testCreateProductPage() throws Exception {
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("createProduct"));
    }

    @Test
    void testCreateProductPost() throws Exception {
        // Mock perilaku service ketika create dipanggil
        Product dummyProduct = new Product();
        dummyProduct.setProductId("123");
        dummyProduct.setProductName("Dummy Product");
        dummyProduct.setProductQuantity(10);

        when(productService.create(any(Product.class))).thenReturn(dummyProduct);

        // Lakukan POST ke /product/create
        mockMvc.perform(post("/product/create")
                        .param("productName", "Dummy Product")
                        .param("productQuantity", "10"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));

        // Verifikasi bahwa service.create() dipanggil sekali
        verify(productService, times(1)).create(any(Product.class));
    }

    @Test
    void testProductListPage() throws Exception {
        // Mock data
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());
        when(productService.findAll()).thenReturn(productList);

        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("productList"))
                .andExpect(model().attributeExists("products"));

        verify(productService, times(1)).findAll();
    }

    @Test
    void testEditProductPage() throws Exception {
        Product dummy = new Product();
        dummy.setProductId("abc");
        dummy.setProductName("Product ABC");
        dummy.setProductQuantity(5);

        when(productService.findById("abc")).thenReturn(dummy);

        mockMvc.perform(get("/product/edit/abc"))
                .andExpect(status().isOk())
                .andExpect(view().name("editProduct"))
                .andExpect(model().attribute("product", dummy));

        verify(productService, times(1)).findById("abc");
    }

    @Test
    void testEditProductPost() throws Exception {
        Product updated = new Product();
        updated.setProductId("abc");
        updated.setProductName("Updated Product");
        updated.setProductQuantity(7);

        when(productService.update(any(Product.class))).thenReturn(updated);

        mockMvc.perform(post("/product/edit")
                        .param("productId", "abc")
                        .param("productName", "Updated Product")
                        .param("productQuantity", "7"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));

        verify(productService, times(1)).update(any(Product.class));
    }

    @Test
    void testDeleteProduct() throws Exception {
        when(productService.delete("abc")).thenReturn(true);

        mockMvc.perform(get("/product/delete/abc"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(productService, times(1)).delete("abc");
    }

    @Test
    void testCreateProductPostWithExistingId() throws Exception {
        // Siapkan product dummy yang sudah memiliki ID
        Product dummyProduct = new Product();
        dummyProduct.setProductId("EXISTING_ID");
        dummyProduct.setProductName("Existing ID Product");
        dummyProduct.setProductQuantity(5);

        when(productService.create(any(Product.class))).thenReturn(dummyProduct);

        mockMvc.perform(post("/product/create")
                        // Kirim form param dengan productId yang sudah terisi
                        .param("productId", "EXISTING_ID")
                        .param("productName", "Existing ID Product")
                        .param("productQuantity", "5"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));

        // Verifikasi service dipanggil
        verify(productService, times(1)).create(any(Product.class));
    }

}
