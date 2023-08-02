package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceClass {
    @Autowired
    private RepositoryClass repositoryClassObj;

    public int getOrderCountByPartnerId(String partnerId){
        List<Order> orderList=repositoryClassObj.getOrdersByPartnerId(partnerId);
        return orderList.size();
    }

    public List<String> getOrdersByPartnerId(String partnerId){
        List<Order>orders=repositoryClassObj.getOrdersByPartnerId(partnerId);
        List<String>ans=new ArrayList<>();
        for(Order order : orders){
            ans.add(order.getId());
        }
        return ans;
    }
}
