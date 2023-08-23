package com.driver.Repository;

import com.driver.DeliveryPartner;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PartnerRepository {

    Map<String, DeliveryPartner> deliveryPartnerMap=new HashMap<>();

    Map<String,List<String>>pairMap=new HashMap<>(); //here oneToMany relation is setup where 1 deliveryPartner have multiple order
    //one order is assigned to one deliverypartner
    //so key is delivery partner and value is orders;


    public void save(DeliveryPartner partner){
        String id=partner.getId();
        deliveryPartnerMap.put(id,partner);
    }

    public boolean isAvailable(String id){
        if(deliveryPartnerMap.containsKey(id)) return true;
        return false;
    }
    public DeliveryPartner getPartner(String id){
        if(isAvailable(id)) return deliveryPartnerMap.get(id);
        return null;
    }
    public void makePair(String orderId, String partnerId){
        List<String> oldList=pairMap.getOrDefault(partnerId,new ArrayList<>());
        oldList.add(orderId);
        pairMap.put(partnerId,oldList);
    }

    public List<String> getOrdersByPartnerId(String partnerId){
        return pairMap.getOrDefault(partnerId,new ArrayList<>());
    }

    public List<String> getAll(){
        List<String>partnerList=new ArrayList<>();
        partnerList.addAll(deliveryPartnerMap.keySet());
        return partnerList;
    }

    public int countOfSignedOrders(){
        int cnt=0;
        for(List<String> orderList: pairMap.values()){
            cnt+=orderList.size();
        }
        return cnt;
    }
}
