package com.yk.tg_bot_get_crypto_rate.provider;

import com.yk.tg_bot_get_crypto_rate.service.RateService;

import java.util.HashMap;
import java.util.Map;

public class ServiceCache {

    private Map<String, RateService> rateServices = new HashMap<>();

    public  RateService getService(String serviceName) {
        // retrieve from the list
        return rateServices.get(serviceName);
    }

    public void addService(RateService newService) {
        // add to the list
        rateServices.put(newService.getServiceName(), newService);
    }
}
