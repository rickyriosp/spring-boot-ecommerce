package com.riosr.ecommerce.service;

import com.riosr.ecommerce.dao.CustomerRepository;
import com.riosr.ecommerce.dto.PurchaseDto;
import com.riosr.ecommerce.dto.PurchaseResponseDto;
import com.riosr.ecommerce.entity.Address;
import com.riosr.ecommerce.entity.Customer;
import com.riosr.ecommerce.entity.Order;
import com.riosr.ecommerce.entity.OrderItem;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CustomerRepository customerRepository;

    @Autowired
    public CheckoutServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
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
        customer.add(order);

        // save to the database
        customerRepository.save(customer);

        // return a response
        return new PurchaseResponseDto(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {

        // generate a random UUID number (UUID version-4)
        // for details see: https://en.wikipedia.org/wiki/Universally_unique_identifier
        //
        return UUID.randomUUID().toString();
    }
}
