package com.riosr.ecommerce.service;

import com.riosr.ecommerce.dao.CustomerRepository;
import com.riosr.ecommerce.dto.PaymentInfoDto;
import com.riosr.ecommerce.dto.PurchaseDto;
import com.riosr.ecommerce.dto.PurchaseResponseDto;
import com.riosr.ecommerce.entity.Address;
import com.riosr.ecommerce.entity.Customer;
import com.riosr.ecommerce.entity.Order;
import com.riosr.ecommerce.entity.OrderItem;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CustomerRepository customerRepository;

    @Autowired
    public CheckoutServiceImpl(CustomerRepository customerRepository, @Value("${stripe.key.secret}") String secretKey) {
        this.customerRepository = customerRepository;

        // initialize Stripe API with secret key
        Stripe.apiKey = secretKey;
    }

    @Override
    @Transactional
    public PurchaseResponseDto placeOrder(PurchaseDto purchaseDto) {

        // retrieve the order info from dto
        Order order = purchaseDto.getOrder();

        //generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        // populate order with orderItems
        Set<OrderItem> orderItems = purchaseDto.getOrderItems();
        order.setOrderItems(orderItems);

        //populate order with shippingAddress and billingAddress
        Address shippingAddress = purchaseDto.getShippingAddress();
        Address billingAddress = purchaseDto.getBillingAddress();

        order.setShippingAddress(shippingAddress);
        order.setBillingAddress(billingAddress);

        // populate customer with order
        Customer customer = purchaseDto.getCustomer();

        // check if this is an existing customer
        String email = customer.getEmail();
        Customer customerFromDB = this.customerRepository.findByEmail(email);

        if (customerFromDB != null) {
            customer = customerFromDB;
        }

        customer.add(order);

        // save to the database
        customerRepository.save(customer);

        // return a response
        return new PurchaseResponseDto(orderTrackingNumber);
    }

    @Override
    public PaymentIntent createPaymentIntent(PaymentInfoDto paymentInfo) throws StripeException {
        List<String> paymentMethodsTypes = new ArrayList<>();
        paymentMethodsTypes.add("card");

        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setCurrency(paymentInfo.getCurrency())
                        .setAmount((long) paymentInfo.getAmount())
                        .setReceiptEmail(paymentInfo.getReceiptEmail())
                        .setDescription("SimTrackUSA LLC.")
                        .addAllPaymentMethodType(paymentMethodsTypes)
                        // accepts payment methods that you enable in the Dashboard and that
                        // are compatible with this PaymentIntent’s other parameters.
//                        .setAutomaticPaymentMethods(
//                                PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
//                                        .setEnabled(true)
//                                        .build()
//                        )
                        .build();

        return PaymentIntent.create(params);
    }

    private String generateOrderTrackingNumber() {

        // generate a random UUID number (UUID version-4)
        // for details see: https://en.wikipedia.org/wiki/Universally_unique_identifier
        //
        return UUID.randomUUID().toString();
    }
}
