package com.synechron.group1.onlineretail.service;

import com.synechron.group1.onlineretail.domain.Order;

public interface PaymentService {
    String createRazorpayOrder(Order order);
    boolean verifyPayment(String razorpayOrderId, String razorpayPaymentId, String razorpaySignature);
}