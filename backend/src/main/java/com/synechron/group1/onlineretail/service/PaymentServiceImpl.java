package com.synechron.group1.onlineretail.service;
import java.math.BigDecimal;
import java.util.HashMap;
import com.razorpay.Utils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import com.synechron.group1.onlineretail.repository.OrderRepository;
import com.razorpay.Order; 

@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${razorpay.key_id}")
    private String keyId;

    @Value("${razorpay.key_secret}")
    private String keySecret;
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Override
    public String createRazorpayOrder(com.synechron.group1.onlineretail.domain.Order order) {
        try {
            RazorpayClient client = new RazorpayClient(keyId, keySecret);
            JSONObject options = new JSONObject();
            options.put("amount", order.getTotalAmount().multiply(BigDecimal.valueOf(100)));
            options.put("currency", "INR");
            options.put("receipt", "order_rcptid_" + order.getId());
            options.put("payment_capture", 1);

            Order razorpayOrder = client.orders.create(options);

            // Save Razorpay order ID to your domain Order
            order.setRazorpayOrderId(razorpayOrder.get("id"));
            orderRepository.save(order);  // persist the Razorpay order ID

            return razorpayOrder.toString();
        } catch (RazorpayException e) {
            throw new RuntimeException("Failed to create Razorpay order", e);
        }
    }
    
    
    public boolean verifyPayment(String orderId, String paymentId, String signature) {
        JSONObject options = new JSONObject();
        options.put("razorpay_order_id", orderId);
        options.put("razorpay_payment_id", paymentId);
        options.put("razorpay_signature", signature);

        String secret = "BD24cYsqPd89F7u7rtqocke6"; // Your Razorpay secret

        try {
            return Utils.verifyPaymentSignature(options, secret);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}