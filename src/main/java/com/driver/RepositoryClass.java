package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RepositoryClass {
    Map<String,Order> orderDb=new HashMap<>();
    Map<String,DeliveryPartner>partnerDb=new HashMap<>();
    Map<String, List<Order>> deliveryPartnerOrderMap=new HashMap<>();
    Map<String,String>orderAssignPartnerDb=new HashMap<>();
    int countOfSignedOrder=0;

    public int getOrderDbSize() {
        return orderDb.size();
    }

    public String addOrderInDb(Order order){
        String id=order.getId();

        orderDb.put(id,order);
        return id;
    }

    public void addPartnerInDb(String partnerId){
        DeliveryPartner partner=new DeliveryPartner(partnerId);
        partnerDb.put(partnerId,partner);
    }
    public void addOrderPartnerDb(String orderId,String partnerId){
        Order order=orderDb.get(orderId);
        List<Order>ordersList=deliveryPartnerOrderMap.getOrDefault(partnerId,new ArrayList<>());
        ordersList.add(order);
        deliveryPartnerOrderMap.put(partnerId,ordersList);
        orderAssignPartnerDb.put(orderId,partnerId);
        countOfSignedOrder++;
    }

    public Order getOrderFromDb(String orderId){
        Order order=orderDb.getOrDefault(orderId,null);
        return order;
    }
    public DeliveryPartner getPartnerFromDb(String partnerId){
        DeliveryPartner partner=partnerDb.getOrDefault(partnerId,null);
        return partner;
    }

    public List<Order> getOrdersByPartnerId(String partnerId){
        return deliveryPartnerOrderMap.getOrDefault(partnerId,new ArrayList<>());
    }

    public List<String> getAllOrders(){
        List<String>ordersList=new ArrayList<>();

        for(Order order : orderDb.values()){
            ordersList.add(order.getId());
        }
        return ordersList;
    }

    public int unsignedOrder(){
        return orderDb.size()-countOfSignedOrder;
    }

    public void deletePartnerFromDb(String partnerId){
        try{
            partnerDb.remove(partnerId);
            int size=deliveryPartnerOrderMap.get(partnerId).size();
            countOfSignedOrder-=size;
            deliveryPartnerOrderMap.remove(partnerId);
            for(String orderId : orderAssignPartnerDb.keySet()){
                if(orderAssignPartnerDb.get(orderId).equals(partnerId)) orderAssignPartnerDb.remove(orderId);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteOrderFromDb(String orderId){
        try{
            orderDb.remove(orderId);
            String partnerId=orderAssignPartnerDb.get(orderId);
            orderAssignPartnerDb.remove(orderId);
            List<Order>orderList=deliveryPartnerOrderMap.get(partnerId);
            int i=0;
            for(Order order: orderList){
                if(order.getId().equals(orderId)){
                    orderList.remove(i);
                }
                i++;
            }
            deliveryPartnerOrderMap.put(partnerId,orderList);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
