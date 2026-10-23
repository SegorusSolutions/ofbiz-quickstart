package solutions.segorus.ofbiz.quickstart

class CmdResult {
    private String stdout;
    private String stderr;
    private int code;

    CmdResult(String stdout, String stderr, int code)
    {
        this.stdout = stdout
        this.stderr = stderr
        this.code = code
    }

    String getStdout() {
        return stdout
    }

    String getStderr() {
        return stderr
    }

    int getCode() {
        return code
    }
}
