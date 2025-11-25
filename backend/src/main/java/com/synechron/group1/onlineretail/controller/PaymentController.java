package com.synechron.group1.onlineretail.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synechron.group1.onlineretail.domain.Order;
import com.synechron.group1.onlineretail.dto.RazorpayVerificationRequest;
import com.synechron.group1.onlineretail.enums.OrderStatus;
import com.synechron.group1.onlineretail.enums.PaymentStatus;
import com.synechron.group1.onlineretail.repository.OrderRepository;
import com.synechron.group1.onlineretail.service.PaymentService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/create")
    public ResponseEntity<String> createPayment(@RequestParam Long orderId) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new RuntimeException("Order not found"));

        String razorpayOrder = paymentService.createRazorpayOrder(order);
        return ResponseEntity.ok(razorpayOrder);
    }
    @PostMapping("/verify")
    public ResponseEntity<String> verifyPayment(@RequestBody RazorpayVerificationRequest request) {
        try {
            boolean isValid = paymentService.verifyPayment(
                request.getRazorpayOrderId(),
                request.getRazorpayPaymentId(),
                request.getRazorpaySignature()
            );

            if (isValid) {
                Order order = orderRepository.findByRazorpayOrderId(request.getRazorpayOrderId())
                    .orElseThrow(() -> new RuntimeException("Order not found"));

                order.setPaymentStatus(PaymentStatus.PAID);
                order.setOrderStatus(OrderStatus.CONFIRMED);
                order.setRazorpayPaymentId(request.getRazorpayPaymentId());

                orderRepository.save(order);

                return ResponseEntity.ok("Payment verified and order confirmed");
            } else {
                // Log the failed verification details
                System.err.println("Payment verification failed for:");
                System.err.println("Order ID: " + request.getRazorpayOrderId());
                System.err.println("Payment ID: " + request.getRazorpayPaymentId());
                System.err.println("Signature: " + request.getRazorpaySignature());

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Payment verification failed. Please check the signature or payment details.");
            }
        } catch (Exception e) {
            // Log unexpected errors
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred during payment verification: " + e.getMessage());
        }
    }
    @PostMapping("/test-verify")
    public ResponseEntity<String> testVerify(@RequestBody RazorpayVerificationRequest request) {
        System.out.println("Order ID: " + request.getRazorpayOrderId());
        System.out.println("Payment ID: " + request.getRazorpayPaymentId());
        System.out.println("Signature: " + request.getRazorpaySignature());

        return ResponseEntity.ok("Received");
    }
}
