package org.fungover.haze;

import java.util.HashMap;
import java.util.Map;

public class Initialize {

    // Stores CLI options as key/value pairs (e.g. "-p", "6379") for later lookup.
    private static final Map<String, String> cliOptions = new HashMap<>();


    public void importCliOptions(String[] args) {
        // Parses arguments as alternating key/value tokens.
        for (int i = 0; i < args.length; i++) {
            if (i + 1 == args.length)
                break;
            cliOptions.put(args[i], args[i + 1]);
        }
    }
    public static void initializeServer(String[] args, Initialize initialize, Auth auth) {
        initialize.importCliOptions(args);
        auth.setPassword(initialize.getPassword());
    }
    public int getPort() {
        // Precedence: CLI short flag -> CLI long flag -> env var -> default port.
        if (cliOptions.containsKey("-p")) {
            return Integer.parseInt(cliOptions.get("-p"));
        } else if (cliOptions.containsKey("--port")) {
            return Integer.parseInt(cliOptions.get("--port"));
        } else if ((System.getenv("HAZE_PORT") != null)) {
            return Integer.parseInt(System.getenv("HAZE_PORT"));
        } else return 6379;
    }

    public String getPassword() {
        // Password follows the same precedence rules as port selection.
        if (cliOptions.containsKey("-pw")) {
            return cliOptions.get("-pw");
        } else if (cliOptions.containsKey("--password")) {
            return cliOptions.get("--password");
        } else if (System.getenv("HAZE_PASSWORD") != null) {
            return System.getenv("HAZE_PASSWORD");
        } else return null;
    }

    public void clearCliOptions() {
        cliOptions.clear();
    }

}
