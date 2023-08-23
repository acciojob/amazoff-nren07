package com.driver.Repository;

import com.driver.Order;
import jdk.jfr.Registered;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.*;

@Repository
public class OrderRepository {
    Map<String, Order> orderDb=new HashMap<>(); //order database



    public void save(Order order){
        String id=order.getId();
        orderDb.put(id,order);
    }

    public Order getOrder(String id){
        return orderDb.getOrDefault(id,null);
    }
    public boolean isAvailable(String id){
        if(orderDb.containsKey(id)) return true;
        return false;
    }
    public List<String> getAll(){
        List<String>orderList=new ArrayList<>();
        orderList.addAll(orderDb.keySet());
        return orderList;
    }




}
