package com.yk.tg_bot_get_crypto_rate.provider;

import com.yk.tg_bot_get_crypto_rate.service.RateServiceImpl;

public class InitialContext {

    public Object lookup(String serviceName) {
        if (serviceName.equalsIgnoreCase("RateService")) {
            return new RateServiceImpl();
        }
        return null;
    }

}
