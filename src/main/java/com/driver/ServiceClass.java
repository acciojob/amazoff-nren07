package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

    public int undeliveredOrdersByDeliveryPartner(String time,String partnerId){
        List<Order>orders=repositoryClassObj.getOrdersByPartnerId(partnerId);
        int cnt=0;
        String charArr[]=time.split(":");
        int hh=Integer.parseInt(charArr[0]);
        int mm=Integer.parseInt(charArr[1]);
        int intTime=hh*60+mm;
        for(Order order : orders){
            if(order.getDeliveryTime()<intTime) cnt++;
        }
        return cnt;
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId){
        List<Order>orders=repositoryClassObj.getOrdersByPartnerId(partnerId);
        Collections.sort(orders,(a,b)->b.getDeliveryTime()-a.getDeliveryTime());
        int lastTime=orders.get(0).getDeliveryTime();
        int mm=lastTime%60;
        int hh=lastTime/60;
        String time=hh+":"+mm;
        return time;
    }
}
