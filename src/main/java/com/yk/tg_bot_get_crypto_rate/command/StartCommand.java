package com.yk.tg_bot_get_crypto_rate.command;

import com.yk.tg_bot_get_crypto_rate.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartCommand implements Command{

    private final static String START_MESSAGE = """
        Hi, %s, nice to meet you!
        
        I am a bot created by Yuri Kuleba -
        a young programming star 😀
        
        Type /help to see what I can or
        type /+Cryptocurrency you want 
        to know in relation to USDT, 
        for example: <em>/ DOT</em>.
        """;

    private final SendBotMessageService sendBotMessageService;

    public StartCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void perform(Update update) {
        sendBotMessageService.sendMessage(
            update.getMessage().getChatId().toString(),
            START_MESSAGE.formatted(update.getMessage().getChat().getFirstName())
        );
    }

}
