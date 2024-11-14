package com.yk.tg_bot_get_crypto_rate.command;

import lombok.Getter;

@Getter
public enum CommandName {

    START("/start"),
    STOP("/stop"),
    HELP("/help"),
    GET_RATE("/"),
    NO("/No");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

}
