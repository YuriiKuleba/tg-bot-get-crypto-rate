package com.yk.tg_bot_get_crypto_rate.command;

import com.yk.tg_bot_get_crypto_rate.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StopCommand implements Command {

    private static final String STOP_MESSAGE = "Deactivated all your subscriptions \uD83D\uDE1F.";

    private final SendBotMessageService sendBotMessageService;

    public StopCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void perform(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), STOP_MESSAGE);
    }
}
