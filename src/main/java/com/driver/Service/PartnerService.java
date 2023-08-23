package com.driver.Service;

import com.driver.DeliveryPartner;
import com.driver.Repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartnerService {
    @Autowired
    private PartnerRepository partnerRepository;

    public void addPartner(String partnerId) throws Exception{
        if(partnerRepository.isAvailable(partnerId)) throw new Exception("partner is already registered");
        DeliveryPartner partner=new DeliveryPartner(partnerId);
        partnerRepository.save(partner);
    }

    public DeliveryPartner getPartner(String partnerId) throws Exception{
        if(!partnerRepository.isAvailable(partnerId)) throw new Exception("partner id is invalid ");
        return partnerRepository.getPartner(partnerId);
    }

    public List<String> getOrdersByPartnerId(String partnerId) throws Exception{
        if(!partnerRepository.isAvailable(partnerId)) throw new Exception("partner is not registered");
        return partnerRepository.getOrdersByPartnerId(partnerId);
    }
}
