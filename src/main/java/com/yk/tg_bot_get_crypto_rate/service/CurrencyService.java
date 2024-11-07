package com.yk.tg_bot_get_crypto_rate.service;

import com.yk.tg_bot_get_crypto_rate.model.CurrencyModel;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.ParseException;
import java.util.Scanner;

public class CurrencyService {

    public static String getCurrencyRate(String message, CurrencyModel model) throws IOException, ParseException {

        URL url = new URL("https://api.binance.com/api/v3/avgPrice?symbol=" + message.toUpperCase() + "USDT");

        Scanner scanner = new Scanner((InputStream) url.getContent());

        String result = "";

        while (scanner.hasNext()) {
            result += scanner.nextLine();
        }

        JSONObject jsonObject = new JSONObject(result);

        model.setMins(jsonObject.getInt("mins"));
        model.setPrice(jsonObject.getDouble("price"));
        model.setCloseTime(jsonObject.getLong("closeTime"));

        return "The price of %s at the moment is: $%s".formatted(message.toUpperCase(), getScale(model));
    }

    private static BigDecimal getScale(CurrencyModel model) {
        return BigDecimal.valueOf(model.getPrice()).setScale(2, RoundingMode.HALF_UP);
    }
}
