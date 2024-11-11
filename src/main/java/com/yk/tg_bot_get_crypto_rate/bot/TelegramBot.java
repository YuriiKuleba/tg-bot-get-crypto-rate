package com.yk.tg_bot_get_crypto_rate.bot;

import com.yk.tg_bot_get_crypto_rate.command.CommandContainer;
import com.yk.tg_bot_get_crypto_rate.service.SendBotMessageServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.yk.tg_bot_get_crypto_rate.command.CommandName.NO;

@Component
@AllArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {

    @Value("${bot.name}")
    private String botName;

    @Value("${bot.token}")
    private String botToken;

    public static String COMMAND_PREFIX = "/";

    private final CommandContainer commandContainer;

    public TelegramBot() {
        this.commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this));
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            if (message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();
                commandContainer.retrieveCommand(commandIdentifier).perform(update);
            } else {
                commandContainer.retrieveCommand(NO.getCommandName()).perform(update);
            }
        }

    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

}
