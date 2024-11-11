package com.yk.tg_bot_get_crypto_rate.provider;

import com.yk.tg_bot_get_crypto_rate.service.RateService;

public class ServiceLocator {

    private static final ServiceCache cache = new ServiceCache();

    public static RateService getService(String serviceName) {

        RateService rateService = cache.getService(serviceName);

        if (rateService != null) return rateService;

        InitialContext context = new InitialContext();
        RateService service = (RateService) context.lookup(serviceName);

        cache.addService(service);
        return service;
    }



}
