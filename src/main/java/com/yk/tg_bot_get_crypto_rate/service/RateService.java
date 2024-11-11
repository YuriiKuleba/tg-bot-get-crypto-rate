package com.yk.tg_bot_get_crypto_rate.service;

import java.io.IOException;

public interface RateService {

    String getCurrencyRate(String coin) throws IOException;

    String getServiceName();
}
