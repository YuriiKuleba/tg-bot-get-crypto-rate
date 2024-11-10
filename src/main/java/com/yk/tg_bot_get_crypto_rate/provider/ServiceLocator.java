package com.yk.tg_bot_get_crypto_rate.provider;

import com.yk.tg_bot_get_crypto_rate.service.RateService;

public class ServiceLocator {

    private static ServiceCache cache = new ServiceCache();

    public static RateService getService(String serviceName) {

        RateService service = cache.getService(serviceName);

        if (service != null) {
            return service;
        }

        InitialContext context = new InitialContext();
        RateService service1 = (RateService) context
            .lookup(serviceName);
        cache.addService(service1);
        return service1;
    }



}
