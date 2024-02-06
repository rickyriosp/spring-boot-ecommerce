package com.riosr.ecommerce.dto;

import com.riosr.ecommerce.entity.Address;
import com.riosr.ecommerce.entity.Customer;
import com.riosr.ecommerce.entity.Order;
import com.riosr.ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class PurchaseDto {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
