package com.yk.tg_bot_get_crypto_rate.command;

import com.yk.tg_bot_get_crypto_rate.model.CurrencyModel;
import com.yk.tg_bot_get_crypto_rate.provider.ServiceLocator;
import com.yk.tg_bot_get_crypto_rate.service.RateService;
import com.yk.tg_bot_get_crypto_rate.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;

public class GetCurrencyRateCommand implements Command {

    CurrencyModel currencyModel;

    RateService rateService;

    private final SendBotMessageService sendBotMessageService;

    private final static String GET_RATE_FAILED_MESSAGE = """
        We have not found such a cryptocurrency.
        Enter the cryptocurrency whose exchange rate
        you want to know in relation to USDT
        For example: DOT""";

    public GetCurrencyRateCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
        this.rateService = ServiceLocator.getService("RateService");
    }

    @Override
    public void perform(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String chatId = update.getMessage().getChatId().toString();

            String coin = retrieveDataFromMessage(
                update.hasMessage() ? update.getMessage().getText() : update.getCallbackQuery().getData()
            );

            if (coin.isBlank() && coin.isEmpty()) {
                return;
            }
            try {
                String priceMessage = rateService.getCurrencyRate(coin);
                sendBotMessageService.sendMessage(chatId, priceMessage);
            } catch (IOException e) {
                sendBotMessageService.sendMessage(chatId, GET_RATE_FAILED_MESSAGE);
            }
        }
    }

    private String retrieveDataFromMessage(String message) {
        String messageText = message.trim();
        String[] arr = messageText.split(" ");
        if (arr.length == 1) return "";
        return arr[1].toUpperCase();
    }

}
