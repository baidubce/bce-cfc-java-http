package com.baidubce.cfc.core.http;

public class ClientConfig {
    private String functionBrn;
    private String functionName;
    private int functionMemory;
    private String functionVersion;
    private String functionHandler;
    private String userCodeRoot;
    private String httpSocket;
    private String httpMode;
    private String runnerMode;

    public String getFunctionBrn() {
        return functionBrn;
    }

    public String getFunctionName() {
        return functionName;
    }

    public int getFunctionMemory() {
        return functionMemory;
    }

    public String getFunctionVersion() {
        return functionVersion;
    }

    public String getFunctionHandler() {
        return functionHandler;
    }

    public String getHttpSocket() {
        return httpSocket;
    }

    public String getHttpMode() {
        return httpMode;
    }

    public String getRunnerMode() {
        return runnerMode;
    }

    public String getUserCodeRoot() {
        return userCodeRoot;
    }

    private ClientConfig() {
        functionBrn = System.getenv("BCE_USER_FUNCTION_BRN");
        functionName = System.getenv("BCE_USER_FUNCTION_NAME");
        functionMemory = Integer.parseInt(System.getenv("BCE_USER_FUNCTION_MEMSIZE"));
        functionVersion = System.getenv("BCE_USER_FUNCTION_VERSION");
        functionHandler = System.getenv("BCE_USER_FUNCTION_HANDLER");
        userCodeRoot = System.getenv("BCE_USER_CODE_ROOT");
        httpSocket = System.getenv("BCE_CFC_HTTP_SOCKET");
        httpMode = System.getenv("BCE_CFC_HTTP_MODE");
        runnerMode = System.getenv("BCE_CFC_RUNTIME_MODE");
    }

    public static ClientConfig getInstance() {
        return instance;
    }

    private static ClientConfig instance;

    static {
        instance = new ClientConfig();
    }
}
