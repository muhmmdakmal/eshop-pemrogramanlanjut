package id.ac.ui.cs.advprogid.eshop.service;

import id.ac.ui.cs.advprogid.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprogid.eshop.model.Order;
import id.ac.ui.cs.advprogid.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprogid.eshop.model.Product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {
    @InjectMocks
    OrderServiceImpl orderService;

    @Mock
    OrderRepository orderRepository;

    List<Order> orders;

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        // Siapkan beberapa order dummy
        orders = new ArrayList<>();
        Order order1 = new Order(
                "13652556-012a-4c07-b546-5eb139d679b",
                products,
                1708560000L,
                "Safira Sudrajat"
        );
        orders.add(order1);
        Order order2 = new Order(
                "79fe15bb-4f21-aebc-83cf-ae7ef1b2",
                products,
                1708560000L,
                "Safira Sudrajat"
        );
        orders.add(order2);
    }

    @Test
    void testCreateOrder() {
        Order order = orders.get(1);
        doReturn(order).when(orderRepository).save(order);

        Order result = orderService.createOrder(order);
        verify(orderRepository, times(1)).save(order);

        assertEquals(order.getId(), result.getId());
    }

    @Test
    void testCreateOrderIfAlreadyExists() {
        Order order = orders.get(1);
        doReturn(order).when(orderRepository).save(order);

        Order result = orderService.createOrder(order);
        verify(orderRepository, times(1)).save(order);
        assertEquals(order.getId(), result.getId());
    }

    @Test
    void testUpdateStatus(){
        Order order = orders.get(1);
        Order newOrder = new Order(order.getId(), order.getProducts(), order.getOrderTime(),
                order.getAuthor(), OrderStatus.SUCCESS.getValue());
        doReturn(order).when(orderRepository).findById(order.getId());
        doReturn(newOrder).when(orderRepository).save(any(Order.class));

        Order result = orderService.updateStatus(order.getId(), OrderStatus.SUCCESS.getValue());

        assertEquals(order.getId(), result.getId());
        assertEquals(OrderStatus.SUCCESS.getValue(), result.getStatus());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void testUpdateStatusInvalidStatus() {
        Order order = orders.get(1);
        doReturn(order).when(orderRepository).findById(order.getId());

        assertThrows(IllegalArgumentException.class, () -> {
            orderService.updateStatus(order.getId(), "MEOW");
        });

        verify(orderRepository, times(0)).save(any(Order.class));
    }

    @Test
    void testUpdateStatusInvalidId() {
        doReturn(null).when(orderRepository).findById("zczc");

        assertThrows(NoSuchElementException.class, () -> {
            orderService.updateStatus("zzcc", OrderStatus.SUCCESS.getValue());
        });

        verify(orderRepository, times(0)).save(any(Order.class));
    }

    @Test
    void testFindByIdIfIdFound() {
        Order order = orders.get(1);
        doReturn(order).when(orderRepository).findById(order.getId());

        Order result = orderService.findById(order.getId());
        assertEquals(order.getId(), result.getId());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        doReturn(null).when(orderRepository).findById("zczc");
        assertNull(orderService.findById("zczc"));
    }

    @Test
    void testFindAllByAuthorIfAuthorCorrect() {
        Order order = orders.get(1);
        doReturn(orders).when(orderRepository).findAllByAuthor(order.getAuthor());

        List<Order> results = orderService.findAllByAuthor(order.getAuthor());
        for (Order result : results) {
            assertEquals(order.getAuthor(), result.getAuthor());
        }
        assertEquals(2, results.size());
    }

    @Test
    void testFindAllByAuthorIfAllLowercase() {
        Order order = orders.get(1);
        doReturn(new ArrayList<Order>()).when(orderRepository)
                .findAllByAuthor(order.getAuthor().toLowerCase());

        List<Order> results = orderService.findAllByAuthor(order.getAuthor().toLowerCase());
        assertTrue(results.isEmpty());
    }
}
