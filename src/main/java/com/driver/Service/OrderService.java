package com.driver.Service;

import com.driver.DeliveryPartner;
import com.driver.Order;
import com.driver.Repository.OrderRepository;
import com.driver.Repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PartnerRepository partnerRepository;

    public void addOrder(Order order) throws Exception{
        // validation check
        if(order == null) throw new Exception("order is null ");
        if(orderRepository.isAvailable(order.getId())) throw new Exception("order is already booked ");
        orderRepository.save(order);
    }

    public Order getOrder(String orderId) throws Exception{
        //validation;
        if(!orderRepository.isAvailable(orderId)) throw new Exception("Order id is invalid ");
        return orderRepository.getOrder(orderId);
    }
    public void makePair(String orderId, String partnerId) throws Exception{
        if(!orderRepository.isAvailable(orderId)) throw new Exception("Order id is invalid ");
        if(!partnerRepository.isAvailable(partnerId)) throw new Exception("partner id is invalid ");
        //increase no of order in partner model
        DeliveryPartner partner =partnerRepository.getPartner(partnerId);
        partner.setNumberOfOrders(partner.getNumberOfOrders()+1);

        //save partner in repository
        partnerRepository.save(partner);
        //here all validation check now make pair
        partnerRepository.makePair(orderId,partnerId);
    }

    public List<String> getAllOrder(){
        return orderRepository.getAll();
    }

    public int getCountOfUnassignedOrders(){
        int totalOrders=orderRepository.getAll().size();
        int signedOrders= partnerRepository.countOfSignedOrders();
        return totalOrders-signedOrders;
    }
}
