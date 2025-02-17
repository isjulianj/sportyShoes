package com.web.sportyShoes.controller;

import com.web.sportyShoes.model.CustomerOrder;
import com.web.sportyShoes.model.Product;
import com.web.sportyShoes.service.OrderService;
import com.web.sportyShoes.service.UserService;
import com.web.sportyShoes.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("/admin/orders")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final ProductService productService;

    public OrderController(OrderService orderService, UserService userService, ProductService productService) {
        this.orderService = orderService;
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping
    public String orders(Model model) {
        model.addAttribute("customerOrders", orderService.getAllOrders());
        return "admin/orders";
    }

    @GetMapping("/edit/{id}")
    public String editOrder(@PathVariable Long id, Model model) {
        CustomerOrder customerOrder = orderService.getOrderById(id);
        Set<Product> orderProducts = customerOrder.getProducts();
        model.addAttribute("customerOrder", customerOrder);
        model.addAttribute("users", userService.getALl());
        model.addAttribute("products", orderProducts);
        model.addAttribute("totalAmount", customerOrder.getTotalAmount());
        return "admin/edit-order";
    }

    @PostMapping("/edit/{id}")
    public String updateOrder(@PathVariable Long id, @ModelAttribute CustomerOrder customerOrder) {
        customerOrder.setId(id);
        orderService.saveOrder(customerOrder);
        return "redirect:/admin/orders";
    }

    @PostMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return "redirect:/admin/orders";
    }
}