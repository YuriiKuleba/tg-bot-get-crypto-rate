package com.yk.tg_bot_get_crypto_rate.service;

import com.yk.tg_bot_get_crypto_rate.model.CurrencyModel;
import com.yk.tg_bot_get_crypto_rate.model.StableCoin;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.Scanner;

public class RateServiceImpl implements RateService {

    private static final String BINANCE_API_URL = "https://api.binance.com/api/v3/ticker/price?symbol=";

    @Override
    public String getCurrencyRate(String coin) throws IOException {

        URL url = new URL(BINANCE_API_URL + coin.toUpperCase() + StableCoin.USDT);

        Scanner scanner = new Scanner((InputStream) url.getContent());

        StringBuilder result = new StringBuilder();

        while (scanner.hasNext()) {
            result.append(scanner.nextLine());
        }

        JSONObject jsonObject = new JSONObject(result.toString());

//        model.setSymbol(jsonObject.getString("symbol"));
//        model.setPrice(jsonObject.getDouble("price"));

        return "The price of %s at the moment is: $%s".formatted(coin.toUpperCase(), 0.26);
    }

    @Override
    public String getServiceName() {
        return "RateService";
    }

    private static BigDecimal getScale(CurrencyModel model) {
        return BigDecimal.valueOf(model.getPrice()).setScale(3, RoundingMode.HALF_UP);
    }

}
