package io.github.network.cli;

import com.beust.jcommander.JCommander;

/**
 * --add-opens java.base/java.lang=ALL-UNNAMED
 */
public class Starter {

    public static void main(String[] args) {
        JCommander jCommander = new JCommander();
        jCommander.setProgramName("network-cli-java");
        jCommander.addCommand("resolve", new CmdResolve());
        jCommander.parse(args);
        if (args.length == 0) {
            jCommander.usage();
        } else {
            String command = jCommander.getParsedCommand();
            if (command == null) {
                jCommander.usage();
            } else {
                switch (command) {
                    case "resolve":
                        CmdResolve cmdResolve = (CmdResolve) jCommander.getCommands().get(command).getObjects().get(0);
                        Resolve.resolve(cmdResolve);
                        break;
                    default:
                        jCommander.usage();
                }
            }
        }
    }

}
