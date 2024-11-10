package com.yk.tg_bot_get_crypto_rate.command;

import com.google.common.collect.ImmutableMap;
import com.yk.tg_bot_get_crypto_rate.service.SendBotMessageService;

import static com.yk.tg_bot_get_crypto_rate.command.CommandName.*;

public class CommandContainer {

    private final ImmutableMap<String, Command> commandMap;

    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService) {

        commandMap = new ImmutableMap.Builder<String, Command>()
            .put(START.getCommandName(), new StartCommand(sendBotMessageService))
            .put(STOP.getCommandName(), new StopCommand(sendBotMessageService))
            .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
            .put(NO.getCommandName(), new NoCommand(sendBotMessageService))
            .put(GET_RATE.getCommandName(), new GetCurrencyRateCommand(sendBotMessageService))
            .build();

        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}
