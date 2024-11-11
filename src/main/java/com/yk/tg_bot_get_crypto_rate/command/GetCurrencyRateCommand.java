package com.yk.tg_bot_get_crypto_rate.command;

import com.yk.tg_bot_get_crypto_rate.bot.TelegramBot;
import com.yk.tg_bot_get_crypto_rate.model.CurrencyModel;
import com.yk.tg_bot_get_crypto_rate.provider.ServiceLocator;
import com.yk.tg_bot_get_crypto_rate.service.RateService;
import com.yk.tg_bot_get_crypto_rate.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.generics.BotOptions;
import org.telegram.telegrambots.meta.generics.LongPollingBot;

import java.io.IOException;

public class GetCurrencyRateCommand implements Command{

    CurrencyModel currencyModel;

    RateService rateService;

    private final SendBotMessageService sendBotMessageService;

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

    public GetCurrencyRateCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
        this.rateService = ServiceLocator.getService("RateService");
    }

    @Override
    public void perform(Update update) {
        SendMessage message = new SendMessage();
        String coin = "";
        String chatId = update.getMessage().getChatId().toString();

        sendBotMessageService.sendMessage(chatId, GET_RATE_MESSAGE.formatted(update.getMessage().getChat().getFirstName()));

        if (update.hasMessage() && update.getMessage().hasText()) {
            message.setText(coin);
            message.setChatId(chatId);
            try {
                coin = rateService.getCurrencyRate(message.getText());
                sendBotMessageService.sendMessage(chatId, coin);
            } catch (IOException e) {
                sendBotMessageService.sendMessage(chatId, GET_RATE_FAILED_MESSAGE);
            }
        }
    }

}
