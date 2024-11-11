package com.yk.tg_bot_get_crypto_rate.provider;

import com.yk.tg_bot_get_crypto_rate.service.RateService;

import java.util.HashMap;
import java.util.Map;

public class ServiceCache {

    private final Map<String, RateService> rateServices = new HashMap<>();

    public  RateService getService(String serviceName) {
        return rateServices.get(serviceName);
    }

    public void addService(RateService newService) {
        rateServices.put(newService.getServiceName(), newService);
    }
}
