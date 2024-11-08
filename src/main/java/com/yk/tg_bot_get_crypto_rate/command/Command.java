package com.yk.tg_bot_get_crypto_rate.command;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface Command {

    void perform(Update update);
}
