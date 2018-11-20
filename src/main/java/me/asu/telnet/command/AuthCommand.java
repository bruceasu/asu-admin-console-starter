package me.asu.telnet.command;

import me.asu.telnet.TelnetCommandHandler;
import me.asu.telnet.TelnetWorker;
import me.asu.util.Md5Utils;

/**
 * AuthCommand.
 * <p>2017 Suk All rights reserved.</p>
 *
 * @author Suk
 * @version 1.0.0
 * @since 2017-12-20 16:26
 */
public class AuthCommand implements TelnetCommandHandler {

    public static final  String NAME             = "auth";
    private static final String DEFAULT_PASSWORD = Md5Utils.getMD5("monitor");

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public String help() {
        return NAME + " 用法：" + NAME + "<password>";
    }

    @Override
    public String shortDescription() {
        return "登录身份认证。";
    }

    @Override
    public String handle(TelnetWorker worker, String... args) {
        if (args.length < 1) {
            return "需要密码。";
        }

        String password = System.getProperty("monitor.server.password");
        if (password == null) {
            password = DEFAULT_PASSWORD;
        } else {
            password = Md5Utils.getMD5(password);
        }

        if (password.equals(Md5Utils.getMD5(args[0]))) {
            worker.setLogin(true);
            return "OK";
        } else {
            return "密码错误";
        }
    }
}
