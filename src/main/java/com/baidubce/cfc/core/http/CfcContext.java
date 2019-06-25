package com.baidubce.cfc.core.http;

import java.util.Map;

public interface CfcContext {
    String getRequestId();

    String getClientIP();

    String getApiID();

    String getFunctionBrn();

    String getFunctionName();

    String getFunctionVersion();

    String getClientContext();

    long getMemoryLimitMB();

    StsCredential getCredential();

    Map<String, String> getPathParams();
}
