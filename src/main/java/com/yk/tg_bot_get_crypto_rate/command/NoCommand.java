package com.yk.tg_bot_get_crypto_rate.command;

import com.yk.tg_bot_get_crypto_rate.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class NoCommand implements Command {

    private final static String NO_MESSAGE = """
        I support commands starting with a slash(/).
        To see a list of commands, type /help
        """;

    private final SendBotMessageService sendBotMessageService;

    public NoCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void perform(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), NO_MESSAGE);
    }
}
