package solutions.segorus.ofbiz.quickstart

class CommandUtils {
    private static boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");

    static boolean isWindows()
    {
        return isWindows
    }

    static CmdResult runCmd(String[] args)
    {
        ProcessBuilder builder = new ProcessBuilder();

        builder.directory(new File("install"))

        String[] shell = isWindows ? ["cmd.exe"] : ["sh"]

        builder.command(shell + args);

        println(shell + args)

        Process process = builder.start();

        BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        String outLines = "";
        String errLines = "";
        while ((line = stdout.readLine()) != null) {
            System.out.println(line);
            outLines += line + "<br>"
        }

        BufferedReader stderr = new BufferedReader(new InputStreamReader(process.getErrorStream()));

        while ((line = stderr.readLine()) != null) {
            System.out.println(line);
            errLines += line + "<br>"
        }

        return new CmdResult(
                outLines,
                errLines,
                process.waitFor()
        );
    }
}
