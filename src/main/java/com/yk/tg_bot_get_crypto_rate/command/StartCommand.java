package com.yk.tg_bot_get_crypto_rate.command;

import com.yk.tg_bot_get_crypto_rate.bot.TelegramBot;
import com.yk.tg_bot_get_crypto_rate.model.CurrencyModel;
import com.yk.tg_bot_get_crypto_rate.service.CurrencyService;
import com.yk.tg_bot_get_crypto_rate.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;
import java.text.ParseException;

public class StartCommand implements Command{

    private final static String START_MESSAGE = """
        Hi, %s, nice to meet you!
        Enter the cryptocurrency whose exchange rate
        you want to know in relation to USDT
        For example: DOT.
        """;

    private final SendBotMessageService sendBotMessageService;

    public StartCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void perform(Update update) {
        TelegramBot bot = new TelegramBot();
        CurrencyModel currencyModel = new CurrencyModel();
        String currency = "";
        long chatId = update.getMessage().getChatId();


        sendBotMessageService.sendMessage(
            update.getMessage().getChatId().toString(),
            START_MESSAGE.formatted(update.getMessage().getChat().getFirstName())
        );

        try {
            currency = CurrencyService.getCurrencyRate(update.getMessage().getText(), currencyModel);
        } catch (IOException e) {
            bot.sendMessage(chatId,"""
                        We have not found such a cryptocurrency.
                        Enter the cryptocurrency whose exchange rate
                        you want to know in relation to USDT
                        For example: DOT""");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        bot.sendMessage(chatId, currency);
    }

}
