package com.yk.tg_bot_get_crypto_rate.command;

import com.yk.tg_bot_get_crypto_rate.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.yk.tg_bot_get_crypto_rate.command.CommandName.*;

public class HelpCommand implements Command {

    private final String HELP_MESSAGE = """
        ✨<b>Available commands</b>✨
        
        <b>Start\\end working with the bot</b>
        %s - start working with me
        %s - pause working with me
        %s - i can show you crypto prices
        
        %s - get help working with me
        """.formatted(
        START.getCommandName(),
        STOP.getCommandName(),
        GET_RATE.getCommandName(),
        HELP.getCommandName());

    private final SendBotMessageService sendBotMessageService;

    public HelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void perform(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), HELP_MESSAGE);
    }
}
