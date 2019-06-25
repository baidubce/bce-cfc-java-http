package com.baidubce.cfc.core.http;

import java.util.Map;

class ContextImpl implements CfcContext {
    private String requestId;
    private String clientIP;
    private String apiID;
    private String functionBrn;
    private String functionName;
    private String functionVersion;
    private String clientContext;
    private long memoryLimit;
    private StsCredential credential;
    private Map<String, String> pathParams;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getClientIP() {
        return clientIP;
    }

    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }

    public String getApiID() {
        return apiID;
    }

    public void setApiID(String apiID) {
        this.apiID = apiID;
    }

    public String getFunctionBrn() {
        return functionBrn;
    }

    public void setFunctionBrn(String functionBrn) {
        this.functionBrn = functionBrn;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getFunctionVersion() {
        return functionVersion;
    }

    public void setFunctionVersion(String functionVersion) {
        this.functionVersion = functionVersion;
    }

    public String getClientContext() {
        return clientContext;
    }

    public void setClientContext(String clientContext) {
        this.clientContext = clientContext;
    }

    public long getMemoryLimitMB() {
        return memoryLimit;
    }

    public void setMemoryLimit(long memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public StsCredential getCredential() {
        return credential;
    }

    public void setCredential(StsCredential credential) {
        this.credential = credential;
    }

    public Map<String, String> getPathParams() {
        return pathParams;
    }

    public void setPathParams(Map<String, String> pathParams) {
        this.pathParams = pathParams;
    }
}
