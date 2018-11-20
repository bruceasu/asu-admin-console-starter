package me.asu.telnet.command;

import me.asu.telnet.*;

/**
 * AuthCommand.
 * <p>2017 Suk All rights reserved.</p>
 *
 * @author Suk
 * @version 1.0.0
 * @since 2017-12-20 16:26
 */
public class HelpCommand implements TelnetCommandHandler {

    public static final String NAME = "help";


    @Override
    public String name() {
        return NAME;
    }

    @Override
    public String help() {
        StringBuilder builder = new StringBuilder();
        builder.append(NAME).append("用法： \n").append(NAME).append(" [cmd]\n").append("\tcmd\t")
               .append("命令\n");
        return builder.toString();
    }

    @Override
    public String shortDescription() {
        return "打印帮助。";
    }

    @Override
    public String handle(TelnetWorker worker, String... args) {
        CommandDispatcher dispatcher = CommandDispatcher.getInstance();
        if (args == null || args.length == 0) {
            return dispatcher.commandList();
        }

        TelnetCommandHandler cmd = dispatcher.getCommand(args[0]);
        if (cmd == null) {
            return "无此命令";
        }

        return cmd.help() + "\n";
    }
}
