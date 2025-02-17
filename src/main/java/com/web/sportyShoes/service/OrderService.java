package com.web.sportyShoes.service;

import com.web.sportyShoes.model.CustomerOrder;
import com.web.sportyShoes.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<CustomerOrder> getAllOrders() {
        return orderRepository.findAll();
    }

    public CustomerOrder getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    }

    public CustomerOrder saveOrder(CustomerOrder customerOrder) {
        return orderRepository.save(customerOrder);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}