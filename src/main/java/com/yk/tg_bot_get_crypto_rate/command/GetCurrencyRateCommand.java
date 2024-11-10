package com.yk.tg_bot_get_crypto_rate.command;

import com.yk.tg_bot_get_crypto_rate.model.CurrencyModel;
import com.yk.tg_bot_get_crypto_rate.service.CurrencyService;
import com.yk.tg_bot_get_crypto_rate.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;
import java.text.ParseException;

public class GetCurrencyRateCommand implements Command {

    CurrencyModel currencyModel;

    private final static String GET_RATE_MESSAGE = """
        Hi, %s, nice to meet you!
        Enter the cryptocurrency whose exchange rate
        you want to know in relation to USDT
        For example: DOT.
        """;
    private final static String GET_RATE_FAILED_MESSAGE = """
        We have not found such a cryptocurrency.
        Enter the cryptocurrency whose exchange rate
        you want to know in relation to USDT
        For example: DOT""";

    private final SendBotMessageService sendBotMessageService;

    public GetCurrencyRateCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void perform(Update update) {
        String currency = "";
//        sendBotMessageService.sendMessage(chatId, GET_RATE_MESSAGE.formatted(update.getMessage().getChat().getFirstName()));

        if (update.hasMessage() && update.getMessage().hasText()) {
            String chatId = update.getMessage().getChatId().toString();
            try {
                currency = CurrencyService.getCurrencyRate(update.getMessage().getText(), currencyModel);
                sendBotMessageService.sendMessage(chatId, currency);
            } catch (IOException e) {
                sendBotMessageService.sendMessage(chatId, GET_RATE_FAILED_MESSAGE);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
