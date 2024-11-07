package com.yk.tg_bot_get_crypto_rate.service;

import com.yk.tg_bot_get_crypto_rate.config.BotConfig;
import com.yk.tg_bot_get_crypto_rate.model.CurrencyModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.text.ParseException;

@Component
@AllArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig botConfig;

    @Override
    public void onUpdateReceived(Update update) {
        CurrencyModel currencyModel = new CurrencyModel();
        String currency = "";

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText) {
                case "/start" -> {
                    startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                }
                default -> {
                    try {
                        currency = CurrencyService.getCurrencyRate(messageText, currencyModel);
                    } catch (IOException e) {
                        sendMessage(chatId, "We have not found such a cryptocurrency." + "\n" +
                            "Enter the cryptocurrency whose exchange rate" + "\n" +
                            "you want to know in relation to USDT" + "\n" +
                            "For example: DOT");
                    }catch (ParseException e) {
                        throw new RuntimeException("Unable to parse date");
                    }
                    sendMessage(chatId, currency);
                }
            }
        }

    }

    public String getBotToken() {
        return botConfig.getBotToken();
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    private void startCommandReceived(Long chatId, String name) {
        String answer = "Hi, " + name + ", nice to meet you!" + "\n" +
            "Enter the cryptocurrency whose exchange rate" + "\n" +
            "you want to know in relation to USDT." + "\n" +
            "For example: DOT";
        sendMessage(chatId, answer);
    }

    private void sendMessage(Long chatId, String textToSend) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

}
